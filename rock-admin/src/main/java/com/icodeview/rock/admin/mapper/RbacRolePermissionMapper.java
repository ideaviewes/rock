package com.icodeview.rock.admin.mapper;

import com.icodeview.rock.admin.pojo.RbacRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.icodeview.cactus.admin.pojo.RbacRolePermission
 */
public interface RbacRolePermissionMapper extends BaseMapper<RbacRolePermission> {
    List<String> getPermissionUrl(@Param("roleIds") List<Integer> roleIds);
}




