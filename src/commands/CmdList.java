package commands;

import controller.ComponentController;

public class CmdList extends Command {
    public static final String NAME = "LIST";

    public CmdList(String[] cmdLine) {
        super(cmdLine);
    }

    @Override
    public boolean execute() {
        super.echo();
        
        ComponentController.getInstance().listInstalledComponents();
        
        return false;
    }   

}
