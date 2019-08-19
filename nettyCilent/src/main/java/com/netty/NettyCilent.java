package com.netty;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyCilent {

	private static Bootstrap client=new Bootstrap();
	private static EventLoopGroup enventLoop=new NioEventLoopGroup();
	
	
	public static void start(){
		try {
			
			client.group(enventLoop).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress("127.0.0.1",9999)).handler(new ChannelInitializer<NioSocketChannel>() {
	            protected void initChannel(NioSocketChannel ch) throws Exception {
	                ch.pipeline().addLast(new ClientHandler());
	
	            };
	        });
			
			ChannelFuture f = client.connect().sync();
            f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}finally{
			enventLoop.shutdownGracefully();
		}
	}
}
