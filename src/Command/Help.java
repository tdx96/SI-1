package Command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Tiago on 14/06/2016.
 */
public class Help implements Command {
    @Override
        public void execute(Connection con) {
       /* try{
        }catch (SQLException e){
            System.out.println("Erro a obter ajuda");
            System.out.println(e.getMessage());
            return;
        }*/

    }

    @Override
    public String getCommand() {
        return "help";
    }
}
