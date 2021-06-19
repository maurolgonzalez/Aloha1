package commands;

import controller.ComponentController;

public class CmdDepend extends Command {

    public static final String NAME = "DEPEND";

    protected CmdDepend(String[] cmd) {
        super(cmd);
    }

    @Override
    public boolean execute() {
        super.echo();

        ComponentController.getInstance().createDepend(super.getCmdLine());

        return false;
    }

    
}
