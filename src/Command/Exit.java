package Command;

/**
 * Created by Tiago on 14/06/2016.
 */
public class Exit implements Command {
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String getCommand() {
        return null;
    }
}
