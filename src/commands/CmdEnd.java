package commands;

public class CmdEnd extends Command {

    public static final String NAME = "END";

    public CmdEnd(String[] cmdLine) {
        super(cmdLine);
    }

    @Override
    public boolean execute() {
        super.echo();
        
        return false;
    }
}
