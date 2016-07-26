package SI1.Command;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class ActualizePrice implements Command {
    @Override
    public void execute(Connection con) {
        Scanner in = new Scanner(System.in);
        System.out.println("Referencia do Produto:");
        int ref = in.nextInt();
        System.out.println("Novo preço:");
        float price = in.nextFloat();
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs= null;
        try{
            con.setAutoCommit(false);
            statement = con.createStatement();
            String SQLcommand = "select HistoricoPreço.DataInicio, HistoricoPreço.DataFim from HistoricoPreço where HistoricoPreço.Ref = "+ ref +" order by HistoricoPreço.DataInicio desc";
            rs = statement.executeQuery(SQLcommand);
            preparedStatement = con.prepareStatement("insert into HistoricoPreço(DataInicio,Preço,DataFim,Ref) values(?,?,?,?)");
            try{
                rs.getDate(2);
            }catch (SQLException e) {
                statement.execute("begin transaction T1 " +
                        "begin try " +
                        "UPDATE HistoricoPreço " +
                        "SET DataFim = " + Date.valueOf(LocalDate.now()) + " "+
                        "WHERE Ref = " + ref + " " +
                        "commit transaction T1 " +
                        "end try " +
                        "begin catch " +
                        "rollback transaction T1 " +
                        "end catch");
            }
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.setFloat(2, price);
            preparedStatement.setDate(3, null);
            preparedStatement.setInt(4, ref);
            preparedStatement.execute();
            con.commit();

        } catch (SQLException e) {
            System.out.println("Erro na atualização do preço");
            System.out.println(e.getMessage());
        }finally {
            try{
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(statement != null)
                    statement.close();
                if(rs!=null)
                    rs.close();
                if(con != null)
                    con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getCommand() {
        return "actualizePrice";
    }

    @Override
    public String description() {
        return "Atualiza o preço do produto";
    }
}
