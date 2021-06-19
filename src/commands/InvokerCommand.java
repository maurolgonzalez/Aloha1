package commands;

public class InvokerCommand {
    Command command;

    void setCommand(Command command) {
        this.command = command;
    }

    public boolean processCommand(String cmdLine) {
        String[] cmdList = cmdLine.split(" ");

        if(cmdList.length >= 2 && cmdList[0].compareTo(CmdDepend.NAME) == 0) {
            command = new CmdDepend(cmdList);
        } else if (cmdList.length == 2 && cmdList[0].compareTo(CmdInstall.NAME) == 0) {
            command = new CmdInstall(cmdList);
        } else if (cmdList.length == 1 && cmdList[0].compareTo(CmdEnd.NAME) == 0) {
            command = new CmdEnd(cmdList);
        } else {
            return false;
        }

        return true;
    }

    public boolean execute() {
        return command.execute();
    }
}
