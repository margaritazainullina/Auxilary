package tests;

import java.io.*;
import java.util.ArrayList;

public class Part1 {
	public static ArrayList<String> answers = new ArrayList<String>();
	public static StringBuffer all = new StringBuffer();
	public static int k = 0;

	public static void main(String[] args) {
		fetchAnswers("C:\\proj\\n5_answers.txt");
		all.append("<test level=\"5\" name=\"n5\">" + "\r\n"
				+ "<section name=\"文字・語彙\">");

		type1("C:\\proj\\n5_1.txt");
		type2("C:\\proj\\n5_1.2.txt");
		type3("C:\\proj\\n5_1.3.txt");
		type4("C:\\proj\\n5_2.txt");
		all.append("</section>" + "\r\n");

		all.append("<section name=\"聴解\">");
		audio("C:\\proj\\n5_audio.txt");
		all.append("</section>" + "\r\n");
		all.append("</test>" + "\r\n");
		System.out.println(all);
	}

	public static void fetchAnswers(String path) {
		String s = "";
		// fetch answers
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(path), "UTF-8"))) {
			while ((s = buffer.readLine()) != null) {
				if (s.equals("A")) {
					answers.add("<answer isCorrect=\"1\">\t</answer>");
					answers.add("<answer isCorrect=\"0\">\t</answer>");
					answers.add("<answer isCorrect=\"0\">\t</answer>");
					answers.add("<answer isCorrect=\"0\">\t</answer>");
				}
				if (s.equals("B")) {
					answers.add("<answer isCorrect=\"0\">\t</answer>");
					answers.add("<answer isCorrect=\"1\">\t</answer>");
					answers.add("<answer isCorrect=\"0\">\t</answer>");
					answers.add("<answer isCorrect=\"0\">\t</answer>");
				}
				if (s.equals("C")) {
					answers.add("<answer isCorrect=\"0\">\t</answer>");
					answers.add("<answer isCorrect=\"0\">\t</answer>");
					answers.add("<answer isCorrect=\"1\">\t</answer>");
					answers.add("<answer isCorrect=\"0\">\t</answer>");
				}
				if (s.equals("D")) {
					answers.add("<answer isCorrect=\"0\">\t</answer>");
					answers.add("<answer isCorrect=\"0\">\t</answer>");
					answers.add("<answer isCorrect=\"0\">\t</answer>");
					answers.add("<answer isCorrect=\"1\">\t</answer>");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void type1(String path) {
		String s = "";
		// 1 part

		boolean isFirstLine = true;
		String title = "";
		int t = 0;
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(path), "UTF-8"))) {
			while ((s = buffer.readLine()) != null) {
				t++;
				// if(t==24){
				// System.out.println(t);
				// }
				if (isFirstLine) {
					all.append("<task caption=\"" + s + "\">" + "\r\n");
					isFirstLine = false;
				} else if (s.isEmpty()) {
					isFirstLine = true;
					all.append("</task>" + "\r\n");
				} else {
					if (s.startsWith("問")) {
						title = s;
					} else {
						String[] ss = s.split("（?[0-9１２３４]+）?.\\s?");
						all.append("<question weight=\"1\" title=\"" + title
								+ "\"");
						all.append(" text=\"" + ss[1] + "\">" + "\r\n");
						for (int i = 0; i < 4; i++) {
							String sss[] = answers.get(k).split("\t");
							k++;
							all.append(sss[0] + ss[i + 2] + sss[1] + "\r\n");
						}
						all.append("</question>" + "\r\n");
					}
				}
			}
			all.append("</task>" + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void type2(String path) {
		// 2 part
		String s = "";
		int t = 0;
		boolean isFirstLine = true;
		boolean isSecondLine = false;
		boolean x = false;
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(path), "UTF-8"))) {
			while ((s = buffer.readLine()) != null) {
				t++;
				// if(t==24){
				// System.out.println(t);
				// }
				if (isFirstLine) {
					all.append("<task caption=\"" + s + "\">" + "\r\n");
					// System.out.print("<task caption=\"" + s + "\">" +
					// "\r\n");
					x = false;
					isFirstLine = false;
					isSecondLine = true;
				} else if (s.isEmpty()) {
					isFirstLine = true;
					isSecondLine = false;
					all.append("</question>" + "\r\n");
					// System.out.print("</question>" + "\r\n");
					all.append("</task>" + "\r\n");
					// System.out.print("</task>" + "\r\n");
				} else if (isSecondLine) {
					String[] s1 = s.split("（?[0-9]+）?.\\s?");
					if (x) {
						all.append("</question>" + "\r\n");
						// System.out.print("</question>" + "\r\n");
					}
					x = true;
					all.append("<question weight=\"1\" title=\"\" text=\""
							+ s1[1] + "\">\r\n");
					// System.out.print("<question weight=\"1\" title=\"\" text=\""
					// + s1[1] + "\">\r\n");
					isSecondLine = false;
				} else {
					String[] ss = s.split("（?[0-9１２３４]+）?.\\s?");
					for (int i = 0; i < 4; i++) {
						String sss[] = answers.get(k).split("\t");
						k++;
						all.append(sss[0] + ss[i + 1] + sss[1] + "\r\n");
						// System.out.print(sss[0] + ss[i + 1] + sss[1] +
						// "\r\n");
						isSecondLine = true;
					}

				}
			}
			all.append("</question>" + "\r\n");
			all.append("</task>" + "\r\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void type3(String path) {
		// 3 part
		int t = 0;
		String s = "";
		boolean isFirstLine = true;
		int  a = 0;
		boolean x = false;
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream("C:\\proj\\n5_1.3.txt"), "UTF-8"))) {
			while ((s = buffer.readLine()) != null) {
				t++;
				a++;
				// if(t==24){
				// System.out.println(t);
				if (isFirstLine) {
					all.append("<task caption=\"" + s + "\">" + "\r\n");
					isFirstLine = false;
					x = false;
					a = 0;
				} else if (a == 1) {
					String[] s1 = s.split("（?[0-9]+）?.\\s?");
					if (x) {
						all.append("</question>" + "\r\n");
					}
					x = true;
					all.append("<question weight=\"1\" title=\"\" text=\""
							+ s1[1] + "\">\r\n");
				} else if (a > 1 || a < 6) {
					String sss[] = answers.get(k).split("\t");
					k++;
					all.append(sss[0] + s + sss[1] + "\r\n");
					if (a == 5)
						a = 0;
				}
				if (s.isEmpty()) {
					isFirstLine = true;
				}
			}
			all.append("</question>" + "\r\n");
			all.append("</task>" + "\r\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void type4(String path) {
		// 4 part
		int t = 0;
		String s = "";
		String mondai = "";
		boolean isMondai = true;
		int a = 0;
		boolean x = false;
		String w[];
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(path), "UTF-8"))) {
			while ((s = buffer.readLine()) != null) {
				if (s.startsWith("問題")) {
					all.append("<task caption=\"" + s + "\">" + "\r\n");
					// all.append("<question weight=\"1\" title=\"");
					isMondai = true;
				} else if (s.startsWith("問")) {
					a = 0;
					isMondai = false;
					all.append("<question weight=\"1\" title=\"");
					all.append(mondai + "\" text=\"" + s + "\">\r\n");
				} else if ((w = s.split("\\(\\d\\)")).length > 1) {
					isMondai = true;
					mondai += s + "\r\n";
				} else if (isMondai) {
					mondai += s + "\r\n";
				} else if (s.isEmpty())
					all.append("</task>" + "\r\n");
				else {
					String sss[] = answers.get(k).split("\t");
					k++;
					a++;
					all.append(sss[0] + s + sss[1] + "\r\n");
					if (a == 4)
						all.append("</question>" + "\r\n");
				}

			}
			all.append("</task>" + "\r\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void audio(String path) {
		String s = "";
		all.append("<task caption=\"\">"+ "\r\n");
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(
				new FileInputStream(path), "UTF-8"))) {
			while ((s = buffer.readLine()) != null) {
				String[] ss = s.split("\\t");
				String audio = ss[0];
				String img = "";
				if (ss.length > 1)
					img = ss[1];
				all.append("<question weight=\"1\" title=\"\" text=\"\" imgRef=\""+img+"\""+" audioRef=\""+audio+"\">"+ "\r\n");
				for (int i = 0; i < 4; i++) {
					String sss[] = answers.get(k).split("\t");
					k++;
					all.append(sss[0] + (i+1) + sss[1] + "\r\n");					
				}
				all.append("</question>"+ "\r\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		all.append("</task>"+ "\r\n");
	}
}