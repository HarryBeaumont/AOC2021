import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6 {
	public static void main(String[] args) {
		String input = InputHandler.readSingleLine("input_data/input-6.txt");

		// convert to integer array
		String[] split_input = input.split(",");
		Integer[] initial_cycles = new Integer[split_input.length];
		for (int i = 0; i < split_input.length; i++) {
			initial_cycles[i] = Integer.parseInt(split_input[i]);
		}

		partOne(80, initial_cycles);   // part one is 80 days
		//partOne(256, initial_cycles); 	// part two is 256 days, doing it the first method is very slow

		//partTwo(80, initial_cycles); 		// test to see if same answer
		partTwo(256, initial_cycles);  // part two is 256
	}

	public static void partOne(int days, Integer[] initial_state) {
		// convert array to list with modifiable size
		List<Integer> fish = Stream.of(initial_state).collect(Collectors.toCollection(ArrayList::new));

		for (int day = 0; day < days; day++) {
			int births = 0;
			for (int n = 0; n < fish.size(); n++) {
				int temp = fish.get(n);
				temp--;
				fish.set(n, temp);
				if (temp < 0) {
					births ++;
					fish.set(n, 6);
				} else {
					fish.set(n, temp);
				}
			}

			for (int i = 0; i < births; i++) {
				fish.add(8);
			}
		}

		System.out.println("After " + days + " days, there are " + fish.size());
	}

	public static void partTwo(int days, Integer[] initial_state) {
		// day number can only exist in the range 0-8
		// split into groups with a day number attached
		long[] groups = new long[9];
		// the index represents day number and the value the number of fish

		// initial population
		for (int i = 0; i < initial_state.length; i++) {
			groups[initial_state[i]]++;
		}

		for (int day = 0; day < days; day++) {
			long births = groups[0];

			for (int i = 0; i < groups.length - 1; i++) {
				groups[i] = groups[i + 1];
			}
			groups[8] = births;  // the births
			groups[6] += births; // fish restarting cycle after birth
		}

		long total = 0;
		for (long n : groups) total += n;
		System.out.println("After " + days + " days, there are " + total);
	}
}
