package SI1.Command;

import SI1.Main;

import java.sql.Connection;

public class Help implements Command {
    @Override
    public void execute(Connection con) {
        for(Command cmd: Main.commands)
            System.out.println(cmd.getCommand()+ " - "+cmd.description());
    }

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String description() {
        return "Mostra todos os comandos";
    }
}
