package com.icodeview.rock.admin.service;

import com.icodeview.rock.admin.dto.RbacUserDto;
import com.icodeview.rock.admin.dto.StatusDto;
import com.icodeview.rock.admin.pojo.RbacPermission;
import com.icodeview.rock.admin.pojo.RbacUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.icodeview.rock.admin.vo.MenuDataItem;
import com.icodeview.rock.admin.vo.RbacUserVo;
import com.icodeview.rock.vo.PageResult;

import java.util.List;

/**
 *
 */
public interface RbacUserService extends IService<RbacUser> {
    List<MenuDataItem> getMenuByUserId(Integer userId);
    void createUser(RbacUserDto userDto);
    void updateUser(RbacUserDto userDto);
    void deleteUser(Integer id);
    void setUserStatus(StatusDto dto);
    PageResult<RbacUserVo> getIndex(String username,String mobile,Integer status,Long pageNum,Long pageSize);
    String getHomeUrl(Integer userId);
}
