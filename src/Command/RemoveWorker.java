package Command;

import java.sql.Connection;

/**
 * Created by Tiago on 14/06/2016.
 */
public class RemoveWorker implements Command {
    @Override
    public void execute(Connection con) {

    }

    @Override
    public String getCommand() {
        return "remove worker";
    }
}
