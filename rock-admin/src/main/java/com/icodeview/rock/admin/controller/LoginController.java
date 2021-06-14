package com.icodeview.rock.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.icodeview.rock.dto.admin.LoginDto;
import com.icodeview.rock.exception.BadHttpRequestException;
import com.icodeview.rock.security.JwtAuthService;
import com.icodeview.rock.vo.CommonResult;
import com.nimbusds.jose.JOSEException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "用户登录")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private JwtAuthService jwtAuthService;

    @ApiOperation(value = "用户名密码登录")
    @PostMapping("account")
    public CommonResult<String> account(@RequestBody @Validated LoginDto dto){
        String token=null;
        try {
            token = jwtAuthService.login(dto.getUsername(), dto.getPassword());

        } catch (JsonProcessingException | JOSEException e) {
            e.printStackTrace();
            throw new BadHttpRequestException("登录失败，请重试！");
        }
        return CommonResult.success("登录成功！",token);
    }
}
