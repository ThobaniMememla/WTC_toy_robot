package za.co.wethinkcode.toyrobot;

public class RightCommand extends Command{

    public RightCommand(){
        super("right");
    }

    @Override
    public boolean execute(Robot target) {
        target.updateDirection(false);
        target.setStatus("Turned right.");
        return true;
    }
}
