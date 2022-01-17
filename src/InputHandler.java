import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputHandler {
	public static String[] readInput(String filepath) {
		Path path = Paths.get(filepath);

		String[] input = new String[0];
		try {
			input = Files.readAllLines(path).toArray(new String[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}
}
