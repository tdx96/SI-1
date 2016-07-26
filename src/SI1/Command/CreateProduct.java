package SI1.Command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.StringJoiner;

public class CreateProduct implements Command {
    @Override
    public void execute(Connection con) {
        Scanner in = new Scanner(System.in);
        PreparedStatement preparedStatement = null;
        try{
            con.setAutoCommit(false);
            preparedStatement = con.prepareStatement("insert into dbo.Produto(Ref,Nome,Volume,Descricao) values(?,?,?,?)");
            System.out.println("Introduza a referencia do produto:");
            int ref = in.nextInt();
            in.nextLine();
            preparedStatement.setInt(1,ref);
            System.out.println("Introduza o nome do produto:");
            String name = in.nextLine();
            preparedStatement.setString(2,name);
            System.out.println("Introduza o volume do produto:");
            float volume = in.nextFloat();
            preparedStatement.setFloat(3,volume);
            in.nextLine();
            System.out.println("Introduza a descrição do produto:");
            String descricao = in.nextLine();
            preparedStatement.setString(4,descricao);
            preparedStatement.execute();
            con.commit();

        }catch (SQLException e){
            System.out.println("Erro na criação do produto!");
            System.out.println(e.getMessage());
        }finally {
            try{
                if(preparedStatement != null)
                    preparedStatement.close();
                if(con!=null){
                    con.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getCommand() {
        return "createProduct";
    }

    @Override
    public String description() {
        return "Cria um novo produto";
    }
}
