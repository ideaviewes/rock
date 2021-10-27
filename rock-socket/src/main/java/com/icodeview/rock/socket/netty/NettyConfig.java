package com.icodeview.rock.socket.netty;

import cn.hutool.core.collection.ConcurrentHashSet;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.concurrent.ConcurrentHashMap;

public class NettyConfig {
    private static final ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final ConcurrentHashMap<Long, ConcurrentHashSet<Channel>> clients=new ConcurrentHashMap<>();
    private NettyConfig(){}
    public static ChannelGroup getChannelGroup(){
        return channelGroup;
    }
    public static ConcurrentHashMap<Long, ConcurrentHashSet<Channel>>  getClients(){
        return clients;
    }
}
