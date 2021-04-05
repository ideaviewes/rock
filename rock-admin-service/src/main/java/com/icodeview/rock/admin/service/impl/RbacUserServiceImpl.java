package com.icodeview.rock.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.icodeview.rock.admin.pojo.RbacUser;
import com.icodeview.rock.admin.service.RbacRolePermissionService;
import com.icodeview.rock.admin.service.RbacRoleService;
import com.icodeview.rock.admin.service.RbacUserRoleService;
import com.icodeview.rock.admin.service.RbacUserService;
import com.icodeview.rock.admin.mapper.RbacUserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class RbacUserServiceImpl extends ServiceImpl<RbacUserMapper, RbacUser>
        implements RbacUserService, UserDetailsService {
    @Resource
    private RbacUserRoleService rbacUserRoleService;
    @Resource
    private RbacRoleService rbacRoleService;
    @Resource
    private RbacRolePermissionService rbacRolePermissionService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RbacUser user = lambdaQuery().eq(RbacUser::getUsername, username).one();
        if(user==null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        List<Integer> roleIds = rbacUserRoleService.getRoleIdByUserId(user.getId());

        List<String> authorities = rbacRolePermissionService.getPermissionByRoleIds(roleIds);

        List<String> roles = rbacRoleService.getRoleByIds(roleIds);
        roles = roles.stream().map(rc -> "ROLE_" + rc).collect(Collectors.toList());

        authorities.addAll(roles);

        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", authorities));

        user.setAuthorities(authorityList);

        UserDetails userDetails=user;

        return userDetails;
    }
}




