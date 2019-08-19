package com.lxy.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServerBootstrap {

	static ServerBootstrap bootstrap = new ServerBootstrap();
	static EventLoopGroup boss=new NioEventLoopGroup(1);
	static EventLoopGroup worker=new NioEventLoopGroup();
	
	public static void start(Integer port){
		try {
			 bootstrap.group(boss, worker)
             .channel(NioServerSocketChannel.class)
             .childHandler(new ChannelInitializer<SocketChannel>(){
					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline p=ch.pipeline();
						p.addLast(new CusterChannel());
						
					}
             	
             });
			 
		    ChannelFuture channelFuture = bootstrap.bind(port).sync();
		    channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			//关闭事件流组
            boss.shutdownGracefully();
            worker.shutdownGracefully();
		}
       
       
	}
	
}
