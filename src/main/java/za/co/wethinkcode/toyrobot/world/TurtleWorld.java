package za.co.wethinkcode.toyrobot.world;


import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.maze.Maze;


public class TurtleWorld extends AbstractWorld {

    private final Turtle turtle = new Turtle();
    private final Turtle gru;

    public TurtleWorld(Maze maze) {
        super(maze);
        StdDraw.setYscale(-200, 200);
        StdDraw.setXscale(-100, 100);
        gru = new Turtle();
        gru.speed(0);
        plotMaze();
    }

    public void plotMaze() {
        for (Obstacle obstacle : getObstacles()) {
            Position bottomLeft = obstacle.getBottomLeftCorner();
            Position topLeft = obstacle.getTopLeftCorner();

            drawRectangle(bottomLeft, topLeft);
        }
    }

    private void drawRectangle(Position bottomLeft, Position topLeft) {
        gru.up();
        gru.goTo(bottomLeft.getX(), bottomLeft.getY());
        gru.down();
        for (int i = 0; i < 4; i++) {
            int sideLength = i % 2 == 0 ? topLeft.getX() - bottomLeft.getX() : topLeft.getY() - bottomLeft.getY();
            gru.forward(sideLength);
            gru.right(90);
        }
    }

    @Override
    public void moveBackRobot(Position position) {
        moveRobot(position, "blue");
    }

    @Override
    public void moveForwardRobot(Position position) {
        moveRobot(position, "blue");
    }

    private void moveRobot(Position position, String color) {
        turtle.goTo(position.getX(), position.getY());
        turtle.speed(0);
        turtle.penColor(color);
        turtle.dot(2);
    }

    @Override
    public void turtleWorldExit() {
        turtle.bye();
        gru.bye();
    }
}
