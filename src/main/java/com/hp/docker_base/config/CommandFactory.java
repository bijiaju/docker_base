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

            case "restartClient":
                return new RestartAppService();

            case "downloadUpgradeFile":
                return new DownloadUpgradeFileService();

            default:
                break;
        }
        return null;
    }

}
