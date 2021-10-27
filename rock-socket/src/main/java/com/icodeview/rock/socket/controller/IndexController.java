package com.icodeview.rock.socket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.icodeview.rock.dto.SocketSendDto;
import com.icodeview.rock.socket.service.PushService;
import com.icodeview.rock.vo.CommonResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class IndexController {
    @Resource
    private PushService pushService;
    @PostMapping("send")
    public CommonResult<Void> sendToUid(@RequestBody @Validated SocketSendDto dto) throws JsonProcessingException {
        pushService.sendToUid(dto.getUserId(),dto.getMessage());
        return CommonResult.success();
    }

    @GetMapping("online")
    public CommonResult<Boolean> online(@RequestParam(value = "id") Long id){
        boolean result = pushService.isUidOnline(id);
        return CommonResult.success(result);
    }

    @GetMapping("online/uid")
    public CommonResult<List<Long>> uid(){
        List<Long> onlineUid = pushService.getOnlineUid();
        return CommonResult.success(onlineUid);
    }
}
