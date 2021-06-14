package com.icodeview.rock.admin.security;

import com.icodeview.rock.security.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class JwtTokenUtilTest {
    @Test
    public void testToken(){
        String url="rbac/role/permission/index";
        String s = lineToHump(url);
        System.out.println(s);
    }
    private static final Pattern linePattern = Pattern.compile("/(\\w)");
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}