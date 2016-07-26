package SI1;

import SI1.Command.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main{
    public static final Command[] commands ={
            new CreateProduct(),
            new ShowProducts(),
            new ActualizePrice(),
            new RemoveProduct(),
            new InsertEntryProduct(),
            new InsertWorker(),
            new RemoveWorker(),
            new Help(),
            new Exit()
    };


    public static void main(String[] args) {
        Connection con = null;
        String url = "jdbc:sqlserver://localhost;databaseName=SI1;user=sa;password=isel;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);
            Scanner in = new Scanner(System.in);
            while (true) {
                boolean validCmd = false;
                String line = in.nextLine();
                String[] cmdArgs = line.split(" ");
                for (Command c : commands) {
                    if (cmdArgs[0].equals(c.getCommand())) {
                        c.execute(con);
                        validCmd = true;
                    }
                }

                if (!validCmd) {
                    new Help().execute(con);
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
