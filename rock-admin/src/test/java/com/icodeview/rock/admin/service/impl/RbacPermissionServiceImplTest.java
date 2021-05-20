package com.icodeview.rock.admin.service.impl;

import com.icodeview.rock.admin.pojo.RbacPermission;
import com.icodeview.rock.admin.service.RbacPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class RbacPermissionServiceImplTest {
    @Resource
    private RbacPermissionService rbacPermissionService;
    @Test
    void getIndex() {
        List<RbacPermission> list = rbacPermissionService.getIndex();
        log.error(list.toString());
    }
    @Test
    void testDelete(){
        rbacPermissionService.delete(20L);
    }
}