package SI1.Command;

import java.sql.Connection;

public interface Command {
    void execute (Connection con);
    String getCommand();
    String description();
}
