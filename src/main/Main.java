package main;

import java.util.Scanner;

import commands.CmdEnd;
import commands.InvokerCommand;

public class Main {
    public static void main(String[] args)  {

        Scanner scanner = new Scanner(System.in);

        String cmdLine = "";
        InvokerCommand invoker = new InvokerCommand();

        while(scanner.hasNext() && cmdLine.compareTo(CmdEnd.NAME) != 0) {
            cmdLine = scanner.nextLine();

            if(invoker.processCommand(cmdLine)) {
                invoker.execute();
            }
        }

        scanner.close();
    }
}
