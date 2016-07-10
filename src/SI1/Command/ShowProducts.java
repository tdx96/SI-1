package SI1.Command;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowProducts implements Command {
    @Override
    public void execute(Connection con) {
        Statement statement=null;
        ResultSet rs = null;
        try{
            statement=con.createStatement();
            String SQLcommand = "select Produto.Ref, Produto.Nome, qut from Produto " +
                                "inner join (select Referencia, sum(Quantidade) as qut " +
                                            "from ProdutoArmazenadoEm " +
                                            "group by Referencia)as M on Produto.Ref = M.Referencia";
            rs =statement.executeQuery(SQLcommand);
            int numCols = rs.getMetaData().getColumnCount();
            while(rs.next()){
                for (int i = 1; i <= numCols; i++) {
                    System.out.println("COlUMN "+ i + " = " + rs.getObject(i));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro na listagem dos produtos");
            System.out.println(e.getMessage());
        } finally {
            try{
                if(statement!=null)
                    statement.close();
                if(rs!=null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getCommand() {
        return "showProducts";
    }

    @Override
    public String description() {
        return "Lista todos os produtos e a quantidade total armazenada";
    }
}
