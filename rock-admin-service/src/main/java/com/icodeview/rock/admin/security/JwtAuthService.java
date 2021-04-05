package com.icodeview.rock.admin.security;

import com.icodeview.rock.exception.BadHttpRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class JwtAuthService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    public String login(String username, String password){
        try{
            //使用用户名密码进行登录验证
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken( username, password );
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch(AuthenticationException e){
            throw new BadHttpRequestException("用户名密码错误！");
        }
        //生成JWT
        UserDetails userDetails = userDetailsService.loadUserByUsername( username );
        return jwtTokenUtil.generateToken(userDetails);
    }

    public String refreshToken(String oldToken) {
        if (!jwtTokenUtil.isTokenExpired(oldToken)) {
            return jwtTokenUtil.refreshToken(oldToken);
        }
        return null;
    }
}
