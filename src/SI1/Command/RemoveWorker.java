package SI1.Command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RemoveWorker implements Command {
    @Override
    public void execute(Connection con) {
        Scanner in = new Scanner(System.in);
        Statement statement = null;
        ResultSet rs = null;
        try{
            con.setAutoCommit(false);
            statement=con.createStatement();
            System.out.println("Numero do funcionario a remover:");
            int numfunc = in.nextInt();
            rs = statement.executeQuery("select NumFunc from Movimento where NumFunc = "+numfunc);
            if(rs.next()){
                System.out.println("Não é possivel remover o funcionario:"+ numfunc);
                return;
            }
            try {
                statement.executeQuery("delete from Funcionario where NumFunc = " + numfunc);
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
            con.commit();
        } catch (SQLException e) {
            System.out.println("Erro na remoção do funcionario");
            System.out.println(e.getMessage());
        }finally {
            try{
                if(statement!= null)
                    statement.close();
                if(rs!=null)
                    rs.close();
                if(con!=null)
                    con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getCommand() {
        return "removeWorker";
    }

    @Override
    public String description() {
        return "Remove um funcionario";
    }
}
