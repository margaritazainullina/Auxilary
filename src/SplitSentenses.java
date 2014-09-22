import java.io.*;
import java.util.ArrayList;

public class SplitSentenses {

	public static StringBuffer all = new StringBuffer();

	public static void main(String[] args) {
		String s = "";
		
		fetch("C:\\proj\\grammar_n1_.txt");

		try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream("C:\\proj\\grammar_n1_.txt"), "UTF-8"))) {
			out.write(all.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void fetch(String inputPath) {
		String s;
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(inputPath), "UTF-8"))) {
			boolean isFirstLine = true;
			String rule="";
			int i=0;
			while ((s = buffer.readLine()) != null){
				i++;
				if (!s.isEmpty()) {					
					String[] ss = s.split("\t");
					if (isFirstLine) {
						rule = ss[0];
						all.append(s);
						all.append("\r\n");
						isFirstLine = false;
					} else {
						System.out.println(s);						
						String[] sss = s.split(rule);
						if(sss.length==1)
						{
							all.append(s);						
						all.append("\r\n");}
						else{
							all.append(sss[0]+"\t"+rule+"\t"+sss[1]);						
							all.append("\r\n");
						}
					}
				} else {
					all.append("\r\n");
					isFirstLine = true;
				}}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
