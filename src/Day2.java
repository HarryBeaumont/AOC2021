public class Day2 {
	public static void main(String[] args) {
		String[] input = InputHandler.readMultiLine("input_data/input-2.txt");

		int xpos = 0;
		int ypos = 0;
		int aim = 0;

		for (String command : input) {
			int space_index = command.indexOf(" ");
			int distance = Integer.parseInt(command.substring(space_index + 1));

			if (command.contains("forward")) {
				xpos += distance;
				ypos += distance*aim;
			}
			if (command.contains("down")) aim -= distance;
			if (command.contains("up")) aim += distance;
		}

		System.out.println("Day 2:");
		System.out.println("Depth: " + (-ypos));
		System.out.println("Horizontal: " + xpos);
		System.out.println("Depth*Horizontal = " + (xpos*(-ypos)) + "\n");
	}
}
