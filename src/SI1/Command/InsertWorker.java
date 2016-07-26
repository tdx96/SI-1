package SI1.Command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertWorker implements Command {
    @Override
    public void execute(Connection con) {
        Scanner in = new Scanner(System.in);
        PreparedStatement preparedStatement = null;
        try{
            con.setAutoCommit(false);
            preparedStatement = con.prepareStatement(
                    "insert into Funcionario(NumFunc,Nome,BI,Morada,CodArmazem) values (?,?,?,?,?)"
            );
            System.out.println("Introduza o numero do funcionario:");
            int numFunc = in.nextInt();
            preparedStatement.setInt(1,numFunc);
            System.out.println("Introduza o nome do funcionario:");
            String name = in.nextLine();
            preparedStatement.setString(2,name);
            System.out.println("Introduza o BI do funcionario:");
            int bi = in.nextInt();
            preparedStatement.setInt(3,bi);
            System.out.println("Introduza a morada do funcionario:");
            String morada = in.nextLine();
            preparedStatement.setString(4, morada);
            System.out.println("Introduza o codigo do armazem onde o funcionario trabalha:");
            int codArm = in.nextInt();
            preparedStatement.setInt(5, codArm);
            con.commit();
        } catch (SQLException e) {
            System.out.println("Erro na criaçao de novo funcionario");
            System.out.println(e.getMessage());
        }finally {
            try{
                if(preparedStatement!=null)
                    preparedStatement.close();
                if(con != null)
                    con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getCommand() {
        return "insertWorker";
    }

    @Override
    public String description() {
        return "Insere um novo funcionario";
    }
}
