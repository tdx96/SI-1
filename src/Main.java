import Command.*;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main{
    private static Command[] commands ={
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

        Scanner in = new Scanner(System.in);
        while(true){
            boolean validCmd = false;
            String line = in.nextLine();
            String[] cmdArgs = line.split(" ");
            for(Command c : commands ){
                if(cmdArgs[0].equals(c.getCommand())){
                    c.execute();
                    validCmd = true;
                }
            }

            if(!validCmd){
                new Help().execute();
            }

        }
    }
}
