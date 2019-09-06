package com.web.admin.modules.sys.mapper;

import com.web.admin.modules.sys.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色权限关系表 Mapper 接口
 * </p>
 *
 * @author zzj
 * @since 2019-09-04
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    void saveRolePermission(@Param("SysRolePermissionList") List<SysRolePermission> SysRolePermissionList);
}
