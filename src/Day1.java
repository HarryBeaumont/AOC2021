import java.util.stream.Stream;

public class Day1 {
	public static void main(String[] args) {
		String[] input = InputHandler.readMultiLine("input_data/input-1.txt");
		int[] depths = Stream.of(input).mapToInt(Integer::parseInt).toArray();

		int single = 0;
		int triple = 0;

		for (int i = 1; i < depths.length; i++) {
			if (depths[i] > depths[i - 1]) { single++; }

			if (i > 2) {
				int A = depths[i - 3] + depths[i - 2] + depths[i - 1];
				int B = depths[i - 2] + depths[i - 1] + depths[i];
				if (B > A) { triple++; }
			}
		}
		System.out.println("Day 1:");
		System.out.println("No. of increases for single numbers: " + single);
		System.out.println("No. of increases for triplets: " + triple + "\n");
	}
}
