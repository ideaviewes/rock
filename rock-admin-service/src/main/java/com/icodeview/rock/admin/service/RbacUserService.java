package com.icodeview.rock.admin.service;

import com.icodeview.rock.admin.pojo.RbacPermission;
import com.icodeview.rock.admin.pojo.RbacUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icodeview.rock.admin.vo.MenuDataItem;

import java.util.List;

/**
 *
 */
public interface RbacUserService extends IService<RbacUser> {
    List<MenuDataItem> getMenuByUserId(Integer userId);
}
