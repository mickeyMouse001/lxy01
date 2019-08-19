package org.lxy.adm.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Server {

	public void start() throws InterruptedException{
		
		EventLoopGroup  boss=new NioEventLoopGroup();
		EventLoopGroup  work=new NioEventLoopGroup();
		ServerBootstrap bootStrap=new ServerBootstrap();
		bootStrap.group(boss, work).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
				 .handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>(){

					@Override
					protected void initChannel(SocketChannel ch) throws Exception {
						ChannelPipeline p = ch.pipeline();
						p.addLast("server",new ChannelHandler(){

							@Override
							public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
								
								System.out.println("handlerAdded");
								System.out.println(ctx.channel());
							}

							@Override
							public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
								
								System.out.println("handlerRemoved");
							}

							@Override
							public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
								System.out.println("exceptionCaught");
								
							}
							
						});
						
					}
					 
				 });
		ChannelFuture f = bootStrap.bind(8088).sync();
		
		f.channel().closeFuture().sync();
	}
	public static void main(String[] args) throws InterruptedException {
		Server server=new Server();
		server.start();
	}
}
