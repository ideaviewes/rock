package com.icodeview.rock.admin.service.impl;

import com.icodeview.rock.admin.service.RbacRolePermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class RbacRolePermissionServiceImplTest {
    @Resource
    private RbacRolePermissionService rbacRolePermissionService;
    @Test
    void getPermissionByRoleIds() {
        List<String> urls = rbacRolePermissionService.getPermissionByRoleIds(Collections.singletonList(1));
        System.out.println(urls);
    }
}