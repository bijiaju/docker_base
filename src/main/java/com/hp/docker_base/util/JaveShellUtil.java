package com.hp.docker_base.util;


import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * java调用shell
 */
public class JaveShellUtil {
    public static int ExecCommand(String command) {
        int retCode = 0;
        try {
            Process process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", command }, null, null);
            retCode = process.waitFor();
            ExecOutput(process);
        } catch (Exception e) {
            retCode = -1;
        }
        return retCode;
    }

    public static boolean ExecOutput(Process process) throws Exception {
        if (process == null) {
            return false;
        } else {
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            String line;
            String output = "";
            while ((line = input.readLine()) != null) {
                output += line + "\n";
            }
            input.close();
            ir.close();
            if (output.length() > 0) {
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String command="/demo.sh";
        //需要传递的就是shell脚本的位置
        int retCode=JaveShellUtil.ExecCommand(command);
        System.out.println(retCode+"retCode");
        if(retCode==0){
            System.out.println("success.....");
        }else{
            System.out.println("error.....");
        }
    }


    /*public static void main(String[] args) {
        String command="cfg/demo.sh";
        //需要传递的就是shell脚本的位置，参数的话每一个之间需要使用空格隔开，务必
        int retCode=JaveShellUtil.ExecCommand(command+" "+"zhoudado"+" "+"barnamefile"+" "+"bxmnamefile");
        System.out.println(retCode+"retCode");
        if(retCode==0){
            System.out.println("success.....");
        }else{
            System.out.println("error.....");
        }
    }*/
}
