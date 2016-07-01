package Command;

import java.sql.Connection;

/**
 * Created by Tiago on 14/06/2016.
 */
public class Exit implements Command {
    @Override
    public void execute(Connection con) {
        System.exit(0);
    }

    @Override
    public String getCommand() {
        return "exit";
    }
}
