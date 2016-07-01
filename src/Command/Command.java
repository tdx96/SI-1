package Command;

import java.sql.Connection;

/**
 * Created by Tiago on 14/06/2016.
 */
public interface Command {
    void execute (Connection con);
    String getCommand();
}
