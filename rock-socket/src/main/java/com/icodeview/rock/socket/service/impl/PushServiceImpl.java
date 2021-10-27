package com.icodeview.rock.socket.service.impl;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.icodeview.rock.socket.netty.NettyConfig;
import com.icodeview.rock.socket.service.PushService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class PushServiceImpl implements PushService {
    @Override
    public boolean isUidOnline(Long userId) {
        ConcurrentHashSet<Channel> channels = NettyConfig.getClients().get(userId);
        if(channels!=null && !channels.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public boolean sendToUid(Long userId, String result) {
        if(NettyConfig.getClients()==null){
            return false;
        }
        ConcurrentHashSet<Channel> channels = NettyConfig.getClients().get(userId);
        if(channels==null || channels.isEmpty()){
            return false;
        }
        for (Channel channel : channels) {
            TextWebSocketFrame webSocketFrame = new TextWebSocketFrame(result);
            channel.writeAndFlush(webSocketFrame);
        }
        return true;
    }

    @Override
    public List<Long> getOnlineUid() {
        ConcurrentHashMap<Long, ConcurrentHashSet<Channel>> clients = NettyConfig.getClients();
        Iterator<Long> iterator = clients.keySet().iterator();
        ArrayList<Long> longs = new ArrayList<>();
        while (iterator.hasNext()){
            longs.add(iterator.next());
        }
        return longs;
    }
}
