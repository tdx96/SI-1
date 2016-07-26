package SI1.Command;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*
create table Entrada(
Num int primary key, 
Quantidade int, 
Data datetime, 
IDdoc int,
IDmove int
foreign key (Iddoc) references Documento(IDdoc),
foreign key (IDmove) references Movimento(IDmove)
)
*/

public class InsertEntryProduct implements Command {
    @Override
    public void execute(Connection con) {
        Scanner in = new Scanner(System.in);
        PreparedStatement preparedStatement = null;
        try {
            con.setAutoCommit(false);
            preparedStatement = con.prepareStatement("insert into Funcionario(Num,Quantidade,Data,IDdoc,IDmove) values (?,?,?,?,?)");
            System.out.println("Introduza o número da entrada:");
            int num = in.nextInt();
            preparedStatement.setInt(1, num);
            System.out.println("Introduza a quantidade entrada:");
            int quantidade = in.nextInt();
            preparedStatement.setInt(2, quantidade);
            /*int [] data = new int [3];
            data[0]= in.nextInt(); // dia
            data[1]= in.nextInt(); // mês
            data[2]= in.nextInt(); // ano
            */
            String date = in.nextLine();
            preparedStatement.setString(3, date);
            System.out.println("Introduza o ID do Documento referente");
            int idDoc = in.nextInt();
            preparedStatement.setInt(4, idDoc);
            System.out.println("Introduza o ID do Moviemento referente");
            int idMove = in.nextInt();
            preparedStatement.setInt(5, idMove);

        } catch (SQLException e) {
            System.out.println("Erro na criaçao de novo funcionario");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (con != null)
                    con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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