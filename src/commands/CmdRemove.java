package commands;

import controller.ComponentController;

public class CmdRemove extends Command{
    public static final String NAME = "REMOVE";

    public CmdRemove(String[] cmdLine) {
        super(cmdLine);
    }

    @Override
    public boolean execute() {
        super.echo();
        
        ComponentController.getInstance().removeComponent(getCmdLine());
        
        return false;
    }
}
