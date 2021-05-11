package com.icodeview.rock.admin.service;

import com.icodeview.rock.admin.dto.RbacPermissionDto;
import com.icodeview.rock.admin.pojo.RbacPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icodeview.rock.admin.vo.MenuDataItem;

import java.util.List;

/**
 *
 */
public interface RbacPermissionService extends IService<RbacPermission> {
    void createPermission(RbacPermissionDto dto);
    void updatePermission(RbacPermissionDto dto);
    void delete(Long id);
    List<RbacPermission> getIndex();
    List<MenuDataItem> getMenuByIds(List<Integer> ids);
}
