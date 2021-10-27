package com.icodeview.rock.socket.netty;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.icodeview.rock.dto.SocketDto;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ChannelHandler.Sharable
@Slf4j
public class WebsocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext context, TextWebSocketFrame textWebSocketFrame) throws Exception {
        Channel channel = context.channel();
        String msg=textWebSocketFrame.text();
        log.error("收到消息：{}",msg);
        if("ping".equals(msg)){
            HashMap<String, String> map = new HashMap<>();
            map.put("event","pong");
            map.put("type",String.valueOf(4));
            TextWebSocketFrame frame = new TextWebSocketFrame(objectMapper.writeValueAsString(map));
            channel.writeAndFlush(frame);
            log.error("回应消息：pong");
            return;
        }
        try{
            SocketDto data = objectMapper.readValue(msg, SocketDto.class);
            if("bind".equals(data.getEvent())){
                bindUserId(data,channel);
                return;
            }
            if("ping".equals(data.getEvent())){
                HashMap<String, String> map = new HashMap<>();
                map.put("event","pong");
                map.put("type",String.valueOf(4));
                TextWebSocketFrame frame = new TextWebSocketFrame(objectMapper.writeValueAsString(map));
                channel.writeAndFlush(frame);
            }
        }catch (MismatchedInputException exception){
           String message=String.format("接收到的信息：%s 非json格式 异常：%s",msg,exception.getMessage());
           log.debug(message);
        }
    }

    private void bindUserId(SocketDto data, Channel channel) {
        HashMap<String, Object> msg = new HashMap<>();
        ConcurrentHashMap<Long, ConcurrentHashSet<Channel>> clients = NettyConfig.getClients();
        Long userId = data.getUserId();
        if(userId==null){
            return;
        }
        AttributeKey<Long> userIdKey=AttributeKey.valueOf("userId");
        channel.attr(userIdKey).setIfAbsent(userId);
        ConcurrentHashSet<Channel> channels = clients.get(userId);
        if(channels==null){
            ConcurrentHashSet<Channel> channels1 = new ConcurrentHashSet<>();
            channels1.add(channel);
            clients.put(userId,channels1);
        }else{
            channels.add(channel);
        }
        msg.put("type",0);
        msg.put("msg","绑定成功！");
        try {
            String message = objectMapper.writeValueAsString(msg);
            TextWebSocketFrame frame = new TextWebSocketFrame(message);
            channel.writeAndFlush(frame);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        TextWebSocketFrame webSocketFrame = new TextWebSocketFrame("hello");
        ctx.channel().writeAndFlush(webSocketFrame);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error("exceptionCaught：{}",cause.getMessage());
        Channel channel = ctx.channel();
        NettyConfig.getChannelGroup().remove(channel);
        removeClient(channel,"exceptionCaught");
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        NettyConfig.getChannelGroup().remove(channel);
        removeClient(channel,"handlerRemoved");
        ctx.close();
    }
    private void removeClient(Channel channel,String method){
        ConcurrentHashMap<Long, ConcurrentHashSet<Channel>> clients = NettyConfig.getClients();
        AttributeKey<Long> userIdKey=AttributeKey.valueOf("userId");
        Long userId = channel.attr(userIdKey).get();
        userId=userId!=null?userId:0L;
        log.error("方法名称：{} 用户id：{}",method,userId);
        if(userId != 0){
            ConcurrentHashSet<Channel> channels = clients.get(userId);
            if(!channels.isEmpty()){
                channels.remove(channel);
            }
        }
    }
}
