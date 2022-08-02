package com.kakao.mis.tire.webflux.netty.ex.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.RequiredArgsConstructor;

import java.net.InetSocketAddress;

@RequiredArgsConstructor
public class EchoServer {
    private final int port;

    public void start() throws InterruptedException {
        final EchoServerHandler handler = new EchoServerHandler();
        final EventLoopGroup group = new NioEventLoopGroup();

        try {
            final ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(handler);
                        }
                    });
            final ChannelFuture future = serverBootstrap.bind().sync();
            future.channel().closeFuture().sync();      // 채널 closeFuture 를 얻고 종료, 완료될 때까지 현제 스레드 blocking
        } finally {
            group.shutdownGracefully().sync();          // EventLoopGroup 종료, 리소스 해제
        }

    }
}
