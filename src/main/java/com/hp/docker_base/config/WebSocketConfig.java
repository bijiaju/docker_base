package com.hp.docker_base.config;


import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.URI;

/**
 * @Auther: bee
 * @Date: 2020/1/11 17:38
 * @Description: 配置websocket后台客户端
 */
@Slf4j
@Component
//@RestController
//@RequestMapping("/wscfg")
public class WebSocketConfig {

    private static Logger log = LoggerFactory.getLogger(WebSocketConfig.class);

    @Value("${websocketServer}")
    public String websocketServer;

    @Bean
    public WebSocketClient webSocketClient() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String hostname = addr.getHostName();

            WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://"+websocketServer+":8082/websocket/"+hostname),new Draft_6455()) {


                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    log.info("[websocket] 连接成功");
                }

                @Override
                public void onMessage(String message) {
                    log.info("[websocket] 收到消息={}",message);
                    ICommand command = new CommandFactory().execCmd(message);
                    try {
                        command.operate();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    log.info("[websocket] 退出连接");

                }

                @Override
                public void onError(Exception ex) {
                    log.info("[websocket] 连接错误={}",ex.getMessage());
                }
            };
            webSocketClient.connect();
            return webSocketClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}