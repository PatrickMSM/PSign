package tk.patsite.PSign;

import tk.patsite.PSign.commands.Command;
import tk.patsite.PSign.commands.GenkeyCommand;
import tk.patsite.PSign.commands.SignCommand;
import tk.patsite.PSign.commands.VerifyCommand;


import java.util.List;
import java.util.Map;

public final class CommandParser {
    private final Map<String, Command> commandMap = Map.of(
            "genkey", new GenkeyCommand(),
            "sign", new SignCommand(),
            "verify", new VerifyCommand()
    );

    public CommandParser(List<String> args) {
        if (args.size() < 1) {
            System.out.println("Command not specified!");
            return;
        }

        // Get the command and remove it
        final String command = args.remove(0);

        // Execute the command
        if (commandMap.containsKey(command)) {
            commandMap.get(command).invoke(args);
        } else {
            System.out.println("Invalid command \"" + command + "\"!");
        }
    }
}
