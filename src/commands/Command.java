package commands;


public abstract class Command {

    private String[] cmdLine;

    protected Command(String[] cmdLine) {
        this.cmdLine = cmdLine;
    }
    
    public void echo() {
        System.out.println(toString());
    }

    public abstract boolean execute();

    @Override
    public String toString() {
        return String.join(" ", cmdLine);
    }

    protected String[] getCmdLine() {
        return cmdLine;
    }
}
