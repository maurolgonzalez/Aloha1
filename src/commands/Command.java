package commands;


public abstract class Command {

    public static final String REMOVE = "REMOVE";

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
        String result = String.join(" ", cmdLine);
        return result;
    }

    protected String[] getCmdLine() {
        return cmdLine;
    }
}
