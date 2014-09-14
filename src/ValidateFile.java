import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ValidateFile {

	public static void main(String[] args) {

		fetch("C:\\proj\\grammar_n2.txt");

	}

	public static void fetch(String inputPath) {
		String s;
		int i = 0;
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(inputPath), "UTF-8"))) {
			boolean isFirstLine = true;
			while ((s = buffer.readLine()) != null) {
				i++;
				if (!s.isEmpty()) {
					String[] ss = s.split("\t");
					if (isFirstLine) {
						if (ss.length != 3)
							System.out.println("Error in string " + i);
						isFirstLine = false;
					} else {
						if (ss.length != 6)
							System.out.println("Error in string " + i);
					}
				} else {
					isFirstLine = true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
