import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class InputHandler {
	public static String[] readMultiLine(String filepath) {
		Path path = Paths.get(filepath);

		String[] input = new String[0];
		try {
			input = Files.readAllLines(path).toArray(new String[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return input;
	}

	public static String readSingleLine(String filepath) {
		Path path = Paths.get(filepath);

		String input = new String();
		try {
			input = Files.readAllLines(path).toArray(new String[0])[0];
		} catch (IOException e) {
			e.printStackTrace();
		}

		return input;
	}
}
