package com.hp.docker_base;

import com.hp.docker_base.config.WebSocketConfig;
import com.hp.docker_base.enum1.OSinfo;
import org.java_websocket.client.WebSocketClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@Configurable
@EnableScheduling//开启定时任务
public class TimeTask {

    @Autowired
    private WebSocketConfig config;

    @Scheduled(cron = "0 */1 * * * * ")//每一分钟执行一次
    public void updateDocker(){
        WebSocketClient webSocketClient = config.webSocketClient();
        boolean closed = webSocketClient.isClosed();
        if (closed){
            System.out.println("服务断开，正在重连...");
            webSocketClient.connect();
        }else{
            return;
        }
        //return message;
    }


    public static void main(String [] args){
        System.out.println( System.getProperty("os.name") );
        System.out.println( System.getProperty("os.version") );
        System.out.println( System.getProperty("os.arch") );

        if(OSinfo.isWindows()){
            System.out.println( "当前更新不支持"+ System.getProperty("os.name"));
        }else if(OSinfo.isLinux()){
            System.out.println( "当前更新支持"+ System.getProperty("os.name"));
        }
    }
}
