package com.hp.docker_base.controller;

import com.hp.docker_base.config.WebSocketConfig;
import com.hp.docker_base.service.impl.ScoketClient;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liaoshiyao
 * @Date: 2019/1/11 16:47
 * @Description: 测试后台websocket客户端
 */
@RestController
@RequestMapping("/ws")
public class IndexController {

    @Autowired
    private ScoketClient webScoketClient;

    @Autowired
    private WebSocketConfig config;

    @GetMapping("/restart")
    public String test(String message){
        WebSocketClient webSocketClient = config.webSocketClient();
        boolean closed = webSocketClient.isClosed();
        while (closed){
            webSocketClient.connect();
        }
        return message;
    }


    @GetMapping("/sendMessage")
    public String sendMessage(String message){
        webScoketClient.groupSending(message);
        return message;
    }
}