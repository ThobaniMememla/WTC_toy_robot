package za.co.wethinkcode.toyrobot;

import java.util.List;
import za.co.wethinkcode.toyrobot.maze.*;
import za.co.wethinkcode.toyrobot.world.AbstractWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;
import za.co.wethinkcode.toyrobot.world.TurtleWorld;
import java.util.ArrayList;


public class Robot {

    private final Position TOP_LEFT = new Position(-100, 200);

    private final Position BOTTOM_RIGHT = new Position(100, -200);

    public static final Position CENTRE = new Position(0, 0);

    private String status;

    private final String name;

    public static List<String> ListCommands = new ArrayList<>();

    private AbstractWorld abstractWorld;

    private final ArrayList<String> message = new ArrayList<>();

    public static boolean statusBloked = true;

    private Position position;

    private Direction currentDirection;


    public Robot(String name) {
        
        this.name = name;
        this.currentDirection = Direction.NORTH;
        this.position = Robot.CENTRE;
        this.abstractWorld = new TextWorld(new RandomMaze());
        this.status = "Ready";
    }

    public static List<String> myHistory(Robot robot) {
        return ListCommands;
    }

    public Robot(String name, String world) {

        this.name = name;
        this.currentDirection = Direction.NORTH;
        this.position = Robot.CENTRE;
        this.status = "Ready";
        this.abstractWorld = world.equalsIgnoreCase("text") ? new TextWorld(new RandomMaze()) : new TurtleWorld(new RandomMaze());

    }

    public String getStatus() {
        return this.status;
    }


    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    public boolean handleCommand(Command command) {
        return command.execute(this);
    }

    public Robot(String name, String world, String maze) {
        this.name = name;
        this.currentDirection = Direction.NORTH;
        this.position = Robot.CENTRE;
        this.status = "Ready";

        AbstractMaze mazeChoice = new RandomMaze();
        if (maze.equalsIgnoreCase("emptymaze")) {
            mazeChoice = new EmptyMaze();
        }
        if (maze.equalsIgnoreCase("simplemaze")) {
            mazeChoice = new SimpleMaze();
        }
        if (maze.equalsIgnoreCase("randommaze")) {
            mazeChoice = new RandomMaze();
        }
        if (maze.equalsIgnoreCase("designedmaze")) {
            mazeChoice = new DesignedMaze();
        }
        this.abstractWorld = world.equalsIgnoreCase("text") ? new TextWorld(mazeChoice) : new TurtleWorld(mazeChoice);
    }

    public void updateDirection(boolean turnedLEFT) {

        if (Direction.NORTH.equals(this.currentDirection) || Direction.UP.equals(this.currentDirection)) {
            if (turnedLEFT) {
                this.currentDirection = Direction.LEFT;
            } else {
                this.currentDirection = Direction.RIGHT;
            }
        } else if (Direction.RIGHT.equals(this.currentDirection)) {
            if (turnedLEFT) {
                this.currentDirection = Direction.UP;
            } else {
                this.currentDirection = Direction.DOWN;
            }
        } else if (Direction.DOWN.equals(this.currentDirection)) {
            if (turnedLEFT) {
                this.currentDirection = Direction.RIGHT;
            } else {
                this.currentDirection = Direction.LEFT;
            }
        } else if (Direction.LEFT.equals(this.currentDirection)) {
            if (turnedLEFT) {
                this.currentDirection = Direction.DOWN;
            } else {
                this.currentDirection = Direction.UP;
            }
        }
    }


    public boolean updatePosition(int nrSteps) {
        message.clear();
        int newX = this.position.getX();
        int newY = this.position.getY();


        if (Direction.NORTH.equals(this.currentDirection) || Direction.UP.equals(this.currentDirection)) {
            newY = newY + nrSteps;

        } else if (Direction.RIGHT.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        } else if (Direction.DOWN.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        } else if (Direction.LEFT.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        statusBloked = abstractWorld.isPathBlocked(getPosition(), newPosition);
        if (newPosition.isIn(TOP_LEFT, BOTTOM_RIGHT)) {
            this.position = newPosition;
            this.abstractWorld.moveForwardRobot(this.position);
            return true;
        }
        return false;
    }

    public boolean updatePositionBack(int nrSteps) {
        message.clear();
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.NORTH.equals(this.currentDirection) || Direction.UP.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        } else if (Direction.RIGHT.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        } else if (Direction.DOWN.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        } else if (Direction.LEFT.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        statusBloked = abstractWorld.isPathBlocked(getPosition(), newPosition);
        if (newPosition.isIn(TOP_LEFT, BOTTOM_RIGHT)) {
            this.position = newPosition;
            this.abstractWorld.moveBackRobot(this.position);
            return true;
        }
        return false;
    }
    public boolean updatePositionForward(int nrSteps){
        message.clear();
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.NORTH.equals(this.currentDirection) || Direction.UP.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        } else if (Direction.RIGHT.equals(this.currentDirection)) {
            newX = newX - nrSteps;
        } else if (Direction.DOWN.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        } else if (Direction.LEFT.equals(this.currentDirection)) {
            newX = newX + nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        statusBloked = abstractWorld.isPathBlocked(getPosition(), newPosition);
        if (newPosition.isIn(TOP_LEFT, BOTTOM_RIGHT)) {
            this.position = newPosition;
            this.abstractWorld.moveForwardRobot(this.position);
            return true;
        }
        return false;

    }
    public Position getPosition() {
        return this.position;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" + getPosition().getX() + "," + getPosition().getY() + "] "
                + this.name + "> " + this.status;
    }

    public void getStatus(String s) {
        System.out.println(s);
    }

    public String getName() {
        return name;
    }

    public AbstractWorld getWorld() {
        return this.abstractWorld;
    }
}