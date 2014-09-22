import java.io.*;

public class FormKanjiAndTranslatonList {
	public static StringBuffer kanji = new StringBuffer();
	public static StringBuffer translation = new StringBuffer();

	public static void main(String[] args) {
		fetch("C:\\proj\\grammar_n1.txt");
		try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("C:\\proj\\kanji.txt"), "UTF-8"))) {
			out.write(kanji.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("C:\\proj\\translation.txt"), "UTF-8"))) {
			out.write(translation.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void fetch(String inputPath) {
		String s;	
		
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(inputPath), "UTF-8"))) {
			boolean isFirstLine = true;
			while ((s = buffer.readLine()) != null)
				if (s.isEmpty()) {
					isFirstLine = true;
					continue;
				} else {
					System.out.println(s);
					if (!s.isEmpty()) {
						String[] ss = s.split("\t");
						if (isFirstLine) {
							translation.append(ss[1]);
							translation.append("\r\n");
							isFirstLine = false;
						}else{
							kanji.append(ss[0]);
							kanji.append("\r\n");
							translation.append(ss[1]);
							translation.append("\r\n");
						}
					}
				}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
