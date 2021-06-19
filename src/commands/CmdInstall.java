package commands;

import controller.ComponentController;

public class CmdInstall extends Command {

    public static final String NAME = "INSTALL";

    public CmdInstall(String[] cmdLine) {
        super(cmdLine);
    }

    @Override
    public boolean execute() {
        super.echo();
        
        ComponentController.getInstance().installComponent(super.getCmdLine());
        
        return false;
    }
    
}
