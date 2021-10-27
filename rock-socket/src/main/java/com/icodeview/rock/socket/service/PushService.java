package com.icodeview.rock.socket.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface PushService {
    boolean isUidOnline(Long userId);
    boolean sendToUid(Long userId, String data) throws JsonProcessingException;
    List<Long> getOnlineUid();
}
