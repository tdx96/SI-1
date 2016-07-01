package Command;

import java.sql.Connection;

/**
 * Created by Tiago on 14/06/2016.
 */
public class ActualizePrice implements Command {
    @Override
    public void execute(Connection con) {
        //con.prepareStatement()
    }

    @Override
    public String getCommand() {
        return "actualize price";
    }
}
