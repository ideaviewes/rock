package com.icodeview.rock.admin.security;

import com.icodeview.rock.security.JwtTokenUtil;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class JwtTokenUtilTest {
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    public void testToken(){
    }
}