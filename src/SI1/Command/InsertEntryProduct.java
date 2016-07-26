package SI1.Command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
//import java.util.Gambiarra;


public class InsertEntryProduct implements Command {
    @Override
    public void execute(Connection con) {

    }

    @Override
    public String getCommand() {
        return "insertEntryProduct";
    }

    @Override
    public String description() {
        return "Insere uma nova entrada";
    }
}