package za.co.wethinkcode.toyrobot.maze;

import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.world.Obstacle;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMaze implements Maze {
    
    public List<Obstacle> getObstaclePath(){
        return new ArrayList<>();
    }

    public boolean blocksPath(Position a, Position b){
        return  false;
    }


}