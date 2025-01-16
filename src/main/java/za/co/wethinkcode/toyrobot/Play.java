package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;
import java.util.Scanner;

public class Play {

    static Scanner scanner;
    public static void main(String[] args) {
        
        scanner = new Scanner(System.in);

        Robot robot;

        String name = getInput("What do you want to name your robot?");

        System.out.println("Hello Kiddo!");

        if(args.length >= 1 && args[0].equalsIgnoreCase("text")){

            if(args.length != 1){
                robot = new Robot(name, "text",args[1]);
                System.out.println("Loading "+args[1]);
                robot.getWorld().showObstacles();
            }else {
                robot = new Robot(name,"text");
                System.out.println("Loading RandomMaze.");
                robot.getWorld().showObstacles();
            }         
        }else {
            robot = new Robot(name,"text");
            System.out.println("Loading RandomMaze.");
            robot.getWorld().showObstacles();
        }

        System.out.println(robot.toString());

        Command command;
        boolean shouldContinue = true;
        do {
            String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {
                command = Command.create(instruction);

                if (instruction.contains("forward")|| instruction.contains("back")
                        || instruction.contains("left")|| instruction.contains("right")
                        || instruction.contains("sprint")) {
                    Robot.ListCommands.add(instruction);
                }

                shouldContinue = robot.handleCommand(command);
            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
            }
            if (instruction.contains("sprint")){
                System.out.println(robot.getStatus());////for sprint add this line to make it work
            }
            else {
                System.out.println(robot);
            }

        } while (shouldContinue);
        Robot.ListCommands.clear();
    }

    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }

    public static void display(ArrayList<String> sprintOutPut){
        for(String messages: sprintOutPut){
            System.out.println(messages);
        }
    }
}
