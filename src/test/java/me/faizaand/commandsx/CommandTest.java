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

    @Command(identifier = "test {name} command", desc = "This is a test command.")
    public void testCommand(@Arg(name = "name", desc = "The name of this test", inline = true) String name, @Flag(name = 'f', desc = "A flag") boolean flag) {
        System.out.println("It is " + flag + " that your name is " + name);
    }

}