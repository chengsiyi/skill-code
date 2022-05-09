package com.chengsy.code.netty.echo.service.chat.handler;

import com.chengsy.code.chat.message.LoginRequestMessage;
import com.chengsy.code.chat.message.LoginResponseMessage;
import com.chengsy.code.chat.service.UserServiceFactory;
import com.chengsy.code.chat.session.SessionFactory;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author chengsiyi
 * @date 2022/4/10 17:06
 */
@ChannelHandler.Sharable
public class LoginRequestMessageHandler extends SimpleChannelInboundHandler<LoginRequestMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestMessage message) throws Exception {
        String username = message.getUsername();
        String password = message.getPassword();
        boolean login = UserServiceFactory.getUserService().login(username, password);
        LoginResponseMessage resp = null;
        if (login) {
            SessionFactory.getSession().bind(ctx.channel(), username);
            resp = new LoginResponseMessage(true, "登录成功");
        } else {
            resp = new LoginResponseMessage(false, "用户名或密码不正确");

        }
        ctx.writeAndFlush(resp);
    }
}
