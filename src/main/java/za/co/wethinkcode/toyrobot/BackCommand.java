package za.co.wethinkcode.toyrobot;

public class BackCommand extends Command{
    
    public BackCommand(String argument){
        super("back", argument);
    }

    @Override
    public boolean execute(Robot target) {
        int nrOfSteps = Integer.parseInt(getArgument());
        if(target.updatePositionBack(nrOfSteps)){
            target.setStatus("Moved back by "+nrOfSteps+" steps.");
        }else{
            if(Robot.statusBloked){
                target.setStatus("Sorry, I cannot go outside my safe zone.");
            }else {
                target.setStatus("Sorry, there is an obstacle in the way.");
            }
        }
        return true;
    }




}