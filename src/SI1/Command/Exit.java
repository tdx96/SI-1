package SI1.Command;

import java.sql.Connection;

public class Exit implements Command {
    @Override
    public void execute(Connection con) {
        System.exit(0);
    }

    @Override
    public String getCommand() {
        return "exit";
    }

    @Override
    public String description() {
        return "Fecha a aplicação";
    }
}
