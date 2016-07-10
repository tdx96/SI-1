package SI1.Command;

import java.sql.Connection;

public class InsertEntryProduct implements Command {
    @Override
    public void execute(Connection con) {

    }

    @Override
    public String getCommand() {
        return "insertEntryProduct";
    }

    @Override
    public String description() {
        return "Insere um movimento de entrada de um produto e respectivo armazenamento numa zona (ou em zonas) de armazém";
    }
}
