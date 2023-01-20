package Maze;

public class MazeTracker {
    Tile tile;
    boolean isVisited;
    int row;
    int column;

    MazeTracker(Tile tile, int row, int column) {
        this.tile = tile;
        this.row = row;
        this.column = column;
        isVisited = false;
    }

    MazeTracker(MazeTracker t) {
        this(t.tile, t.row, t.column);
    }

}
