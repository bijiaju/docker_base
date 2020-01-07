package com.hp.docker_base.config;

import com.hp.docker_base.util.JaveShellUtil;
import org.springframework.stereotype.Component;

@Component
public class UpgradeVersion implements ICommand {
    @Override
    public void operate() throws Exception {
        String command="/conf/demo.sh";
        //需要传递的就是shell脚本的位置
        int retCode= JaveShellUtil.ExecCommand(command);
        System.out.println(retCode+"retCode");
        if(retCode==0){
            System.out.println("success.....");
        }else{
            System.out.println("error.....");
        }

    }
}
