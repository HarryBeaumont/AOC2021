import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day5 {
	static int[] x1;
	static int[] x2;
	static int[] y1;
	static int[] y2;

	public static void main(String[] args) {
		Path path = Paths.get("input_data/input-5.txt");
		String[] input = new String[0];

		try {
			input = Files.readAllLines(path).toArray(new String[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}

		x1 = new int[input.length];
		x2 = new int[input.length];
		y1 = new int[input.length];
		y2 = new int[input.length];

		for (int i = 0; i < input.length; i++) {
			int start_index = 0;

			for (int j = 1; j < input[i].length(); j++) {
				if (input[i].charAt(j) == ',') {
					if (start_index == 0) {
						x1[i] = Integer.parseInt(input[i].substring(start_index, j-1));
					} else {
						x2[i] = Integer.parseInt(input[i].substring(start_index, j-1));
					}
					j++;
					start_index = j;
				}
				if (input[i].charAt(j) == ' ') {
					y1[i] = Integer.parseInt(input[i].substring(start_index, j-1));
					j += 4;
					start_index = j;
				}
				if (input[i].charAt(j) == '\n') {
					y2[i] = Integer.parseInt(input[i].substring(start_index, j-1));
				}
			}
		}


	}

	public static void partOne() {
		int[][] map = new int[999][999];
		for (int i = 0; i < x1.length; i++) {
			if (x1[i] == x2[i]) {
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
			}
		}
	}
}
