import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day4 {
	public static class BingoCard {
		int[][] card = new int[5][5];
		int[][] card_punches = new int[5][5];
		int[] numbers;
		int min_numbers_played = -1;


		public BingoCard(String[] card_input, int[] numbers) {
			this.numbers = numbers;

			for (int i = 0; i < 5; i++) {
				String[] row = (card_input[i].strip()).split("\\s+");
				for (int j = 0; j < 5; j++) {
					row[j].strip();
					card[i][j] = Integer.parseInt(row[j]);
					card_punches[i][j] = 1;
				}
			}

			boolean bingo = false;

			while (!bingo) {
				min_numbers_played++;
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (numbers[min_numbers_played] == card[i][j]) card_punches[i][j] = 0;
					}
				}

				for (int i = 0; i < 5; i++) {
					int number_in_row = 0;
					int number_in_col = 0;
					for (int j = 0; j < 5; j++) {
						if (card_punches[i][j] == 0) number_in_row++;
						if (card_punches[j][i] == 0) number_in_col++;
					}

					if(number_in_col == 5 || number_in_row == 5) {
						bingo = true;
						break;
					}
				}
			}
		}

		public int calculate_score() {
			int score = 0;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (card_punches[i][j] == 1) score += card[i][j];
				}
			}
			score *= numbers[min_numbers_played];
			return score;
		}

		public int getMin_numbers_played() {
			return  min_numbers_played;
		}
	}

	public static void main(String[] args) {
		String[] input = InputHandler.readMultiLine("input_data/input-4.txt");

		int[] numbers = Stream.of(input[0].split(",")).mapToInt(Integer::parseInt).toArray();
		List<BingoCard> Cards = new ArrayList<BingoCard>();

		for (int i = 1; i < input.length; i++) {
			String[] card_input = new String[5];
			if(input[i].length() > 2) {
				card_input[0] = input[i];
				card_input[1] = input[i + 1];
				card_input[2] = input[i + 2];
				card_input[3] = input[i + 3];
				card_input[4] = input[i + 4];
				Cards.add(new BingoCard(card_input, numbers));
				i += 5;
			}
		}

		int min_played = Cards.get(0).getMin_numbers_played();
		int max_played = Cards.get(0).getMin_numbers_played();
		int min_index  = 0;
		int max_index  = 0;
		for (int i = 1; i < Cards.size(); i++) {
			int num_played = Cards.get(i).getMin_numbers_played();
			if (num_played < min_played) {
				min_index = i;
				min_played = num_played;
			}
			if (num_played > max_played) {
				max_index = i;
				max_played = num_played;
			}
		}

		int win_score = Cards.get(min_index).calculate_score();
		int lse_score = Cards.get(max_index).calculate_score();
		System.out.println("Score of first card to get bingo: " + win_score);
		System.out.println("Score of last card to get bingo: " + lse_score);
	}
}
