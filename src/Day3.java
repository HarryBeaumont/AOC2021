import java.util.ArrayList;
import java.util.List;

public class Day3 {
	public static void main(String[] args) {
		String[] input = InputHandler.readMultiLine("input_data/input-3.txt");
		int number_of_inputs = input.length;
		int[] ones  = new int[12];
		int[] zeros = new int[12];

		for (String string : input) {
			char[] bits = string.toCharArray();
			for (int i = 0; i < bits.length; i++) {
				if (bits[i] == '0') {
					zeros[i]++;
				} else {
					ones[i]++;
				}
			}
		}

		char[] gamma = new char[ones.length];
		char[] epsilon = new char[ones.length];

		for (int i = 0; i < ones.length; i++) {
			if (ones[i] > zeros[i]) {
				gamma[i] = '1';
				epsilon[i] = '0';
			} else {
				gamma[i] = '0';
				epsilon[i] = '1';
			}
		}

		String gamma_string = new String(gamma);
		String epsilon_string = new String(epsilon);

		System.out.println("Day 3:");
		System.out.println("gamma: " + gamma_string);
		System.out.println("epsilon: " + epsilon_string);

		int gamma_decimal = Integer.parseInt(gamma_string, 2);
		int epsilon_decimal = Integer.parseInt(epsilon_string, 2);

		System.out.println("gamma * epsilon: " + (gamma_decimal*epsilon_decimal) + "\n");

		String[] generator_rate = input;
		String[] scrubber_rate = input;
		for (int i = 0; i < 12; i++) {
			List<String> generator_ones = new ArrayList<String>();
			List<String> generator_zero = new ArrayList<String>();
			List<String> scrubber_ones = new ArrayList<String>();
			List<String> scrubber_zero = new ArrayList<String>();

			for (String string : generator_rate) {
				if (string.charAt(i) == '1') {
					generator_ones.add(string);
				} else {
					generator_zero.add(string);
				}
			}

			for (String string : scrubber_rate) {
				if (string.charAt(i) == '1') {
					scrubber_ones.add(string);
				} else {
					scrubber_zero.add(string);
				}
			}

			if (generator_rate.length > 1) {
				if (generator_zero.size() > generator_ones.size()) {
					generator_rate = new String[generator_zero.size()];
					generator_zero.toArray(generator_rate);
				} else {
					generator_rate = new String[generator_ones.size()];
					generator_ones.toArray(generator_rate);
				}
			}

			if (scrubber_rate.length > 1) {
				if (scrubber_ones.size() < scrubber_zero.size()) {
					scrubber_rate = new String[scrubber_ones.size()];
					scrubber_ones.toArray(scrubber_rate);
				} else {
					scrubber_rate = new String[scrubber_zero.size()];
					scrubber_zero.toArray(scrubber_rate);
				}
			}
		}

		int generator_rate_decimal = Integer.parseInt(generator_rate[0], 2);
		int scrubber_rate_decimal = Integer.parseInt(scrubber_rate[0], 2);

		System.out.println("Oxygen generator rate: " + generator_rate[0]);
		System.out.println("CO2 scrubber rate: " + scrubber_rate[0]);
		System.out.println("generator * scrubber: " + generator_rate_decimal*scrubber_rate_decimal);
	}
}
