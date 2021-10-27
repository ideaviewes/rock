package com.icodeview.rock.socket.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.net.InetSocketAddress;

@Component
@Slf4j
public class NettyServer {
    public static final String WEBSOCKET_PROTOCOL="WebSocket";
    @Value("${websocket.port}")
    private int port;
    @Value("${websocket.path}")
    private String path;

    private EventLoopGroup master;
    private EventLoopGroup worker;
    @Resource
    private WebsocketHandler websocketHandler;

    @PostConstruct
    private void init(){
        new Thread(() -> {
            log.info("websocket 服务器启动中...");
            start();
        }).start();
    }

    private void start(){
        master=new NioEventLoopGroup();
        worker=new NioEventLoopGroup();
        ServerBootstrap server = new ServerBootstrap();
        server.group(master,worker);
        server.channel(NioServerSocketChannel.class);
        server.localAddress(new InetSocketAddress(port));
        server.childOption(ChannelOption.SO_KEEPALIVE,true);
        server.childOption(ChannelOption.TCP_NODELAY,true);
        server.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                ChannelPipeline pipeline = channel.pipeline();
                pipeline.addLast(new HttpServerCodec());
                pipeline.addLast(new ObjectEncoder());
                pipeline.addLast(new ChunkedWriteHandler());;
                pipeline.addLast(new HttpObjectAggregator(8*1024));
                pipeline.addLast(new WebSocketServerProtocolHandler(path,WEBSOCKET_PROTOCOL,true,65536));
                pipeline.addLast(websocketHandler);
            }
        });
        try {
            ChannelFuture future = server.bind().sync();
            future.addListener((ChannelFutureListener) channelFuture -> {
                if(channelFuture.isSuccess()){
                    log.info("websocket服务器启动成功");
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("websocket服务器启动失败！{}",e.getMessage());
        }
    }
    @PreDestroy
    private void destroy(){
        if(master!=null){
            master.shutdownGracefully();
        }
        if(worker!=null){
            worker.shutdownGracefully();
        }
    }
}
