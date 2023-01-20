package Maze;

import java.util.*;

public class Maze {
    /**
     * TODO
     * Returns the distance of the shortest path within the maze
     *
     * @param maze 2D table representing the maze
     * @return Distance of the shortest path within the maze, null if not solvable
     */
    public static Integer findShortestPath(ArrayList<ArrayList<Tile>> maze) {
        if (maze.size() == 0) {
            return null;
        }

        ArrayList<ArrayList<MazeTracker>> betterMaze = toMazeTracker(maze);

        MazeTracker mazeGateway = new MazeTracker(Tile.Wall, -1, -1);
        MazeTracker mazeEntrance = new MazeTracker(Tile.Wall, -1, -1);

        findEntrance(betterMaze, mazeEntrance, mazeGateway);

        if (mazeEntrance.tile == Tile.Wall || mazeGateway.tile == Tile.Wall) {
            return null;
        }

        MazeTracker currentTile = betterMaze.get(mazeEntrance.row).get(mazeEntrance.column);
        int distance = 0;
        LinkedList<MazeTracker> queueTracker;
        LinkedList<MazeTracker> nextQueue = new LinkedList<>();

        verifyAndAdd(betterMaze, currentTile, nextQueue);

        queueTracker = nextQueue;
        nextQueue = new LinkedList<>();
        distance++;
        currentTile = queueTracker.poll();
        if (currentTile == null) {
            return null;
        }
        while (currentTile.tile != Tile.Exit) {
            verifyAndAdd(betterMaze, currentTile, nextQueue);
            currentTile = queueTracker.poll();

            if (currentTile == null) {
                queueTracker = nextQueue;
                nextQueue = new LinkedList<>();
                distance++;
                currentTile = queueTracker.poll();
                if (currentTile == null) {
                    return null;
                }
            }
        }
        return distance;
    }
    private static void addToQueue(LinkedList<MazeTracker> queue, ArrayList<ArrayList<MazeTracker>> maze, int row, int column) {
        MazeTracker t = maze.get(row).get(column);
        if (t.tile != Tile.Wall && !t.isVisited) {
            t.isVisited = true;
            queue.add(t);
        }
    }
    private static void verifyAndAdd(ArrayList<ArrayList<MazeTracker>> maze, MazeTracker currentTile, LinkedList<MazeTracker> nextQueue) {
        for (int i = 0; i < maze.size(); i++) {
            if (maze.get(i).contains(currentTile)) {
                int index = maze.get(i).indexOf(currentTile);
                if (index > 0) {
                    addToQueue(nextQueue, maze, i, index - 1);
                }
                if (index < maze.get(i).size() - 1) {
                    addToQueue(nextQueue, maze, i, index + 1);
                }
                if (i > 0) {
                    addToQueue(nextQueue, maze, i - 1, index);
                }
                if (i < maze.size() - 1) {
                    addToQueue(nextQueue, maze, i + 1, index);
                }
            }
        }
    }

    private static void verifyOrExit(MazeTracker entrance, MazeTracker gateway, MazeTracker tile) {
        if (tile.tile.equals(Tile.Exit)) {
            if (!(entrance.tile == Tile.Wall)) {
                gateway.tile = Tile.Exit;
                gateway.column = tile.column;
                gateway.row = tile.row;
                return;
            }
            entrance.tile = tile.tile;
            entrance.column = tile.column;
            entrance.row = tile.row;
            tile.isVisited = true;
        }
    }

    private static void findEntrance(ArrayList<ArrayList<MazeTracker>> maze, MazeTracker entrance, MazeTracker gateway) {

        for (MazeTracker tile : maze.get(0)) {
            verifyOrExit(entrance, gateway, tile);
        }
        for (int i = 0; i < maze.size(); i++) {
            verifyOrExit(entrance, gateway, maze.get(i).get(maze.get(i).size() - 1));
        }

        for (int i = 0; i < maze.size(); i++) {
            verifyOrExit(entrance, gateway, maze.get(i).get(0));
        }
        for (MazeTracker tile : maze.get(maze.size() - 1)) {
            verifyOrExit(entrance, gateway, tile);
        }
    }


    private static ArrayList<ArrayList<MazeTracker>> toMazeTracker(ArrayList<ArrayList<Tile>> maze) {
        ArrayList<ArrayList<MazeTracker>> newMaze = new ArrayList<>();

        for (int i = 0; i < maze.size(); i++) {
            ArrayList<MazeTracker> row = new ArrayList<>();
            for (int j = 0; j < maze.get(i).size(); j++) {
                row.add(new MazeTracker(maze.get(i).get(j), i, j));
            }
            newMaze.add(row);
        }

        return newMaze;
    }

}




