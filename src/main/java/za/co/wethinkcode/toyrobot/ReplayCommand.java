package za.co.wethinkcode.toyrobot;
import java.util.Collections;
import java.util.List;


public class ReplayCommand extends Command {

    private int x;
    private int y;

    public ReplayCommand(String argument) {
        super("replay", argument);
    }

    public ReplayCommand() {
        super("replay");
    }

    @Override
    public boolean execute(Robot target) {
        boolean reverse = false;
        List<String> history = Robot.myHistory(target);
        String argsInput = getArgument();

        if (argsInput.contains("reversed")) {
            reverse = true;
            argsInput = argsInput.replace("reversed", "").trim();
        }

        String[] arg = argsInput.split("[-]");

        if (argsInput.isEmpty()) {
            replayCommands(target, history, reverse, 0, history.size());
        } else if (arg.length == 1) {
            int replayCount = Integer.parseInt(arg[0]);
            List<String> subList = history.subList(Math.max(0, history.size() - replayCount), history.size());
            replayCommands(target, subList, reverse, replayCount, subList.size());
        } else if (arg.length >= 2) {
            int n = Integer.parseInt(arg[0]);
            int m = Integer.parseInt(arg[1]);
            List<String> subList = history.subList(Math.max(0, history.size() - n), Math.max(0, history.size() - m));
            int count = n - m;
            replayCommands(target, subList, reverse, count, subList.size());
        } else {
            throw new IllegalArgumentException();
        }
        return true;
    }

    private void replayCommands(Robot target, List<String> commands, boolean reverse, int count, int totalCount) {
        if (reverse) Collections.reverse(commands);
        for (String command : commands) {
            target.handleCommand(create(command));
            this.y = target.getPosition().getY();
            this.x = target.getPosition().getX();
            target.getStatus("[" + x + "," + y + "] " + target.getName() + "> " + target.getStatus());
        }
        target.setStatus("replayed " + (count <= 0 ? totalCount : Math.abs(count)) + " commands.");
    }
}
