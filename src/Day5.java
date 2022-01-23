public class Day5 {
	static int[] x1;
	static int[] x2;
	static int[] y1;
	static int[] y2;

	public static void main(String[] args) {
		String[] input = InputHandler.readMultiLine("input_data/input-5.txt");

		// arrays of starting (1) and ending (2) positions of lines
		x1 = new int[input.length];
		x2 = new int[input.length];
		y1 = new int[input.length];
		y2 = new int[input.length];

		// populating arrays from input strings
		for (int i = 0; i < input.length; i++) {
			int start_index = 0;

			for (int j = 1; j < input[i].length(); j++) {
				if (input[i].charAt(j) == ',') {
					if (start_index == 0) {
						x1[i] = Integer.parseInt(input[i].substring(start_index, j));
					} else {
						x2[i] = Integer.parseInt(input[i].substring(start_index, j));
					}
					j++;
					start_index = j;
				}
				if (input[i].charAt(j) == ' ') {
					y1[i] = Integer.parseInt(input[i].substring(start_index, j));
					j += 4;
					start_index = j;
				}
				if (j == input[i].length() - 1) {
					y2[i] = Integer.parseInt(input[i].substring(start_index, j + 1));
				}
			}
		}

		//printAns(partOne()); // horizontal and vertical lines only
		printAns(partTwo()); // diagonal too
	}

	public static int[][] partOne() {
		int[][] map = new int[1000][1000];

		// loop to fill in map using only vertical or horizontal lines
		for (int i = 0; i < x1.length; i++) {
			if (x1[i] == x2[i]) { //vertical line
				int bot;
				int top;
				if(y1[i] < y2[i]) {
					bot = y1[i];
					top = y2[i];
				} else {
					bot = y2[i];
					top = y1[i];
				}
				for (int j = bot; j <= top; j++) {
					map[x1[i]][j]++;
				}
			} else if (y1[i] == y2[i]) {
				int bot;
				int top;
				if (x1[i] < x2[i]) {
					bot = x1[i];
					top = x2[i];
				} else {
					bot = x2[i];
					top = x1[i];
				}
				for (int j = bot; j <= top; j++) {
					map[j][y1[i]]++;
				}
			}
		}

		return map;
	}

	public static int[][] partTwo() {
		int[][] map = partOne();

		// adding diagonal lines (45 degrees only)
		for (int i = 0; i < x1.length; i++) {
			if (Math.abs(x1[i] - x2[i]) == Math.abs(y1[i] - y2[i])) {
				int xdir = 1;
				int ydir = 1;
				if (x2[i] - x1[i] < 0) xdir = -1;
				if (y2[i] - y1[i] < 0) ydir = -1;

				for (int j = 0; j <= Math.abs(x1[i] - x2[i]); j++) {
					map[x1[i] + (j * xdir)][y1[i] + (j * ydir)]++;
				}
			}
		}

		return map;
	}

	public static void printAns(int[][] map) {
		// now map is filled find how many positions have >1
		int total = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] > 1) total++;
			}
		}

		System.out.println("Number of positions with more than one crossing: " + total);
	}
}
