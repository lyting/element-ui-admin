package com.web.admin.modules.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.web.admin.modules.biz.entity.dto.MoveGroupDTO;
import com.web.admin.modules.biz.entity.dto.ResOssDTO;
import com.web.admin.modules.biz.entity.po.ResOss;
import com.web.admin.modules.biz.service.ResOssService;
import com.web.common.exception.WebException;
import com.web.common.utils.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author zzj
 * @date 2019-02-15
 */
@Slf4j
@RestController
@RequestMapping("/resOss")
public class ResOssController {

    @Autowired
    private ResOssService resOssService;

    private String path = "\\upload\\";


    @PostMapping("/upload")
    @RequiresPermissions("res:oss:upload")
    public ResponseData upload(@RequestBody List<ResOssDTO> resOssDTOList) {
        List<String> imageUrls = new ArrayList<>();
        resOssDTOList.forEach(resOssDTO -> {
            String[] arr = resOssDTO.getUrlData().split(",");
            String suffix = arr[0];
            String imagePattern = "data:image/(jpg|png|jpeg|gif);base64";
            if (!Pattern.matches(imagePattern, suffix)) {
                throw new WebException("素材格式错误");
            }
            saveFile(arr[1], resOssDTO.getName());
            ResOss resOss = new ResOss();
            BeanUtils.copyProperties(resOssDTO, resOss);
            resOss.setAliasName(resOssDTO.getName());
            String[] type = suffix.split("[:;]");
            resOss.setResType(type[0]);
            resOss.setObjSuffix(type[1]);
            resOss.setObjUrl(path+resOssDTO.getName());
            resOssService.saveResOss(resOss);
        });
        return ResponseData.success();
    }

    /**
     * 分页列表
     *
     * @param params
     * @return
     */
    @GetMapping("/listPage")
    public ResponseData listPage(@RequestParam Map<String, Object> params) {
        IPage iPage = resOssService.listPage(params);
        return ResponseData.success(iPage);
    }

    @PostMapping("/updateById")
    @RequiresPermissions("res:oss:update")
    public ResponseData updateById(@RequestBody ResOss resOss) {
        resOssService.updateById(resOss);
        return ResponseData.success();
    }

    @PostMapping("/deleteById")
    @RequiresPermissions("res:oss:delete")
    public ResponseData deleteById(@RequestBody List<Long> ids) {
        resOssService.deleteById(ids);
        return ResponseData.success();
    }

    @PostMapping("/moveGroup")
    @RequiresPermissions("res:oss:moveGroup")
    public ResponseData moveGroup(@RequestBody MoveGroupDTO moveGroupDTO) {
        resOssService.moveGroup(moveGroupDTO);
        return ResponseData.success();
    }

    private void saveFile(String base64, String fileName) {
        File file = null;
        //创建文件目录
//        String filePath = "D:\\upload";
        String filePath = this.getClass().getClassLoader().getResource("").getPath()+path;
        File dir = new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.getDecoder().decode(base64);
            file = new File(filePath + fileName);
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
