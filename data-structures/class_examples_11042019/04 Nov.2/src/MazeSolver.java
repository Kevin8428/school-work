
public class MazeSolver {
	private MazeNode maze[][];
	private static final int MAZE_SIZE = 4;

	public MazeSolver() {
		maze = new MazeNode[MAZE_SIZE][MAZE_SIZE];
		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[0].length; col++) {
				maze[row][col] = new MazeNode();
			}
		}

		maze[0][0].setNorth(MazeNode.ENTRANCE);
		maze[0][0].setEast(MazeNode.OPEN);
		maze[0][0].setSouth(MazeNode.WALL);
		maze[0][0].setWest(MazeNode.WALL);

		maze[0][1].setNorth(MazeNode.WALL);
		maze[0][1].setEast(MazeNode.OPEN);
		maze[0][1].setSouth(MazeNode.OPEN);
		maze[0][1].setWest(MazeNode.OPEN);

		maze[0][2].setNorth(MazeNode.WALL);
		maze[0][2].setEast(MazeNode.WALL);
		maze[0][2].setSouth(MazeNode.WALL);
		maze[0][2].setWest(MazeNode.OPEN);

		maze[0][3].setNorth(MazeNode.WALL);
		maze[0][3].setEast(MazeNode.WALL);
		maze[0][3].setSouth(MazeNode.OPEN);
		maze[0][3].setWest(MazeNode.WALL);

		maze[1][0].setNorth(MazeNode.WALL);
		maze[1][0].setEast(MazeNode.WALL);
		maze[1][0].setSouth(MazeNode.OPEN);
		maze[1][0].setWest(MazeNode.EXIT);

		maze[1][1].setNorth(MazeNode.OPEN);
		maze[1][1].setEast(MazeNode.WALL);
		maze[1][1].setSouth(MazeNode.OPEN);
		maze[1][1].setWest(MazeNode.WALL);

		maze[1][2].setNorth(MazeNode.WALL);
		maze[1][2].setEast(MazeNode.OPEN);
		maze[1][2].setSouth(MazeNode.WALL);
		maze[1][2].setWest(MazeNode.WALL);

		maze[1][3].setNorth(MazeNode.OPEN);
		maze[1][3].setEast(MazeNode.WALL);
		maze[1][3].setSouth(MazeNode.OPEN);
		maze[1][3].setWest(MazeNode.OPEN);

		maze[2][0].setNorth(MazeNode.OPEN);
		maze[2][0].setEast(MazeNode.OPEN);
		maze[2][0].setSouth(MazeNode.WALL);
		maze[2][0].setWest(MazeNode.WALL);

		maze[2][1].setNorth(MazeNode.OPEN);
		maze[2][1].setEast(MazeNode.WALL);
		maze[2][1].setSouth(MazeNode.OPEN);
		maze[2][1].setWest(MazeNode.OPEN);

		maze[2][2].setNorth(MazeNode.WALL);
		maze[2][2].setEast(MazeNode.OPEN);
		maze[2][2].setSouth(MazeNode.WALL);
		maze[2][2].setWest(MazeNode.WALL);

		maze[2][3].setNorth(MazeNode.OPEN);
		maze[2][3].setEast(MazeNode.WALL);
		maze[2][3].setSouth(MazeNode.OPEN);
		maze[2][3].setWest(MazeNode.OPEN);

		maze[3][0].setNorth(MazeNode.WALL);
		maze[3][0].setEast(MazeNode.OPEN);
		maze[3][0].setSouth(MazeNode.WALL);
		maze[3][0].setWest(MazeNode.WALL);

		maze[3][1].setNorth(MazeNode.OPEN);
		maze[3][1].setEast(MazeNode.OPEN);
		maze[3][1].setSouth(MazeNode.WALL);
		maze[3][1].setWest(MazeNode.OPEN);

		maze[3][2].setNorth(MazeNode.WALL);
		maze[3][2].setEast(MazeNode.OPEN);
		maze[3][2].setSouth(MazeNode.WALL);
		maze[3][2].setWest(MazeNode.OPEN);

		maze[3][3].setNorth(MazeNode.OPEN);
		maze[3][3].setEast(MazeNode.WALL);
		maze[3][3].setSouth(MazeNode.WALL);
		maze[3][3].setWest(MazeNode.OPEN);

	}


	public boolean solve(int row, int col, int whereWeCameFrom) {

		System.out.println("Solving " + row +", " + col);

		if ((maze[row][col].getNorth() == MazeNode.EXIT) || 
				(maze[row][col].getEast() == MazeNode.EXIT) || 
				(maze[row][col].getSouth() == MazeNode.EXIT) ||
				(maze[row][col].getWest() == MazeNode.EXIT)) {
			System.out.println("Solved");
			System.out.println("Exit at " + row +", " + col);
			return true;
		}

		boolean solved = false;
		if (whereWeCameFrom != MazeNode.NORTH) {
			if (maze[row][col].getNorth() == MazeNode.OPEN) { 
				solved = solve(row-1, col, MazeNode.SOUTH);
			}
		}

		if (whereWeCameFrom != MazeNode.EAST) {
			if (!solved) {
				if (maze[row][col].getEast() == MazeNode.OPEN) { 
					solved = solve(row, col+1, MazeNode.WEST);
				}

			}
		}

		if (whereWeCameFrom != MazeNode.SOUTH) {
			if (!solved) {
				if (maze[row][col].getSouth() == MazeNode.OPEN) { 
					solved = solve(row+1, col, MazeNode.NORTH);
				}

			}
		}

		if (whereWeCameFrom != MazeNode.WEST) {
			if (!solved) {
				if (maze[row][col].getWest() == MazeNode.OPEN) { 
					solved = solve(row, col-1, MazeNode.EAST);
				}

			}
		}

		if (solved == true) {
			System.out.println("Solved " + row +", " + col);
		}
		return solved;
	}


}
