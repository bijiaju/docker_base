package com.hp.docker_base.service;

import com.alibaba.fastjson.JSONObject;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DockerClientBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DockerClientService {
    /**
     * 连接docker服务器
     * @return
     */
   // @PostConstruct
    public static DockerClient connectDocker(){
        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://192.168.176.251:2375").build();
        Info info = dockerClient.infoCmd().exec();
        String infoStr = JSONObject.toJSONString(info);
        System.out.println("docker的环境信息如下：=================");
        System.out.println(info);
        return dockerClient;
    }

    /**
     * 启动容器
     * @param client
     * @param containerId
     */
    public static void startContainer(DockerClient client,String containerId){
        client.startContainerCmd(containerId).exec();
    }

    /**
     * 停止容器
     * @param client
     * @param containerId
     */
    public static void stopContainer(DockerClient client,String containerId){
        client.stopContainerCmd(containerId).exec();
    }

    public static void main(String [] args){
        DockerClient dockerClient = connectDocker();
        //dockerClient.stopContainer(dockerClient,"ed3a5f5d8d44");
        dockerClient.stopContainerCmd("ed3a5f5d8d44");
        dockerClient.startContainerCmd("192.168.176.251:5000/docker_base:1.0.1docke");
       // dockerClient.
        //startContainer(dockerClient,"hello-world");

    }
}
