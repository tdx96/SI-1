package SI1.Command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RemoveProduct implements Command {
    @Override
    public void execute(Connection con) {
        Scanner in = new Scanner(System.in);
        System.out.println("Referencia do produto a remover:");
        int ref= in.nextInt();
        Statement statement = null;
        ResultSet rs = null;
        try {
            con.setAutoCommit(false);
            statement = con.createStatement();
            rs = statement.executeQuery("select Referencia from ProdutoArmazenadoEm where Referencia = " + ref);
            if(rs.next()){
                System.out.println("Não pode ser removido");
                return;
            }
            try{
                statement.executeQuery("delete from EntradaDe where Ref = "+ref);
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
            try{
                statement.executeQuery("delete from MovimentoDe where Ref = "+ref);
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
            try{
                statement.executeQuery("delete from HistoricoPreço where Ref = "+ref);
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
            try{
                statement.executeQuery("delete from Produto where Ref = "+ref);
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
            con.commit();
        } catch (SQLException e) {
            System.out.println("Erro na remoção");
            System.out.println(e.getMessage());
        }finally {
            try{
                if(statement!=null)
                    statement.close();
                if(rs!=null)
                    rs.close();
                if(con!= null)
                    con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getCommand() {
        return "removeProduct";
    }

    @Override
    public String description() {
        return "Remove um produto";
    }
}
