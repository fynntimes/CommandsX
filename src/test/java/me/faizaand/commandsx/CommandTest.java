package me.faizaand.commandsx;

import me.faizaand.commandsx.parsing.CommandParser;
import org.junit.Test;

import java.util.List;

public class CommandTest {

    @Test public void testCommandParse() {
        CommandParser parser = new CommandParser();
        List<ParsedCommand> parse = parser.parse(this);
        for(ParsedCommand parsedCommand : parse) {
            System.out.println(parsedCommand.getName());
            System.out.println(parsedCommand.getDescription());
            System.out.println(parsedCommand.getLongDescription());
            System.out.println(parsedCommand.getArguments());
        }
    }

    @Test public void testCommandRun() {
        CommandManager manager = new CommandManager();
        manager.register(this);
        manager.runCommand(null, "test real command Faizaan");
    }

    @Command(identifier = "test {type} command", desc = "This is a test command.")
    public void testCommand(@InlineArg(name="type") String type, @Arg(name="name") String name) {
        System.out.println("Hi, your " + type + " name is " + name);
    }

}