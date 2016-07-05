package com.vic.mina.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * 服务器端业务逻辑
 * @author Administrator
 *
 */
public class TimeServerHandler extends IoHandlerAdapter {
	/**
	 * 连接创建事件
	 */
	public void sessionCreated(IoSession session){
		//显示客户端的ip和端口
		System.out.println(session.getRemoteAddress().toString());
	}
	
	public void execptionCaught(IoSession session, Throwable cause) throws Exception{
		cause.printStackTrace();
	}
	
	/**
	 * 消息接收事件
	 */
	public void messageReceived(IoSession session, Object message) throws Exception{
		String strMsg = message.toString();
		if(strMsg.trim().equalsIgnoreCase("quit")){
			session.close(true);
			return;
		}
		//返回消息字符串
		session.write("Hi Client!");
		//打印客户端传来的消息内容
		System.out.println("Message written : " + strMsg);
	}
	
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception{
		System.out.println("IDLE" + session.getIdleCount(status));
	}
}
