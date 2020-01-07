package com.hp.docker_base.config;

public class CommandFactory {
    public ICommand execCmd(String cmd) {
        switch (cmd) {

            case "execSQL":
                System.out.println("执行sql");
                return  new ExecSQL();

            case "rollback":
                return new RollbackVersion();

            case "execUpgrade":
                return new UpgradeVersion();

            case "restart":
                return new RestartApp();

            default:
                break;
        }
        return null;
    }

}
