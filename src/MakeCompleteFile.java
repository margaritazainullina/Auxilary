import java.io.*;
import java.util.ArrayList;

public class MakeCompleteFile {
	public static ArrayList<String> translation = new ArrayList<String>();
	public static ArrayList<String> romaji = new ArrayList<String>();
	public static ArrayList<String> translationRus = new ArrayList<String>();

	public static StringBuffer all = new StringBuffer();

	public static void main(String[] args) {
		String s = "";
		// fetch romaji
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream("C:\\proj\\kanji1.txt"), "UTF-8"))) {
			while ((s = buffer.readLine()) != null) {
				romaji.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// fetch translation
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream("C:\\proj\\translation.txt"), "UTF-8"))) {
			while ((s = buffer.readLine()) != null) {
				translation.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// fetch russian translation
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream("C:\\proj\\translation1.txt"), "UTF-8"))) {
			while ((s = buffer.readLine()) != null) {
				translationRus.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		fetch("C:\\proj\\grammar_n1.txt");

		try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("C:\\proj\\grammar_n1_.txt"), "UTF-8"))) {
			out.write(all.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void fetch(String inputPath) {
		String s;
		int i = 0, k = 0;
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(inputPath), "UTF-8"))) {
			boolean isFirstLine = true;
			while ((s = buffer.readLine()) != null)

				if (!s.isEmpty()) {					
					String[] ss = s.split("\t");
					if (isFirstLine) {
						all.append(ss[0] + "\t" + translation.get(i) + "\t"
								+ translationRus.get(i));
						i++;
						System.out.println("i=" + i);
						all.append("\r\n");
						isFirstLine = false;
					} else {
						all.append(ss[0] + "\t" + romaji.get(k) + "\t"
								+ translation.get(i) + "\t"
								+ translationRus.get(i));
						k++;
						i++;
						System.out.println("k=" + k);
						all.append("\r\n");
					}
				} else {
					isFirstLine = true;
					all.append("\r\n");
				}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
