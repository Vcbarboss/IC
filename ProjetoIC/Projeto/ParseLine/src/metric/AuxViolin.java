package metric;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AuxViolin {

	public static void main(String[] args) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String csvPath = "C:\\Users\\Vini\\Desktop\\100%\\LibExpatTabela.csv";
		String sp = csvPath.replace("\\", "/");
		String[] rep = sp.split("/");
		String[] repo = rep[5].split("Tabela");
		String violin = "C:\\Users\\Vini\\Desktop\\ViolinAux\\Arquivo\\Violin"+ repo[0] +".csv";

		ArrayList<String> authors = new ArrayList<String>();
		ArrayList<String> vars = new ArrayList<String>();
		ArrayList<String> arqs = new ArrayList<String>();
		ArrayList<String> commits = new ArrayList<String>();
		ArrayList<String> auxCommits = new ArrayList<String>();
		ArrayList<String> linha = new ArrayList<String>();
		ArrayList<String> numVar = new ArrayList<String>();
		ArrayList<String> numArqs = new ArrayList<String>();
		ArrayList<String> numAuth = new ArrayList<String>();
		ArrayList<String> numCommit = new ArrayList<String>();

		BufferedReader br = null;
		BufferedReader tr = null;
		String line = "";
		String cvsSplitBy = ";";
		
		
		try {

			br = new BufferedReader(new FileReader(csvPath));
			while ((line = br.readLine()) != null) {

				String[] coluna = line.split(cvsSplitBy);

				if (authors.contains(coluna[5])) {

				} else {
					if (!(coluna[3].contains("TRUE") || coluna[3].contains("EMPTY LINE")
							|| coluna[3].equals("NULL"))) {

						authors.add(coluna[5]);

					}
				}

				if (vars.contains(coluna[3])) {

				} else {
					if (!(coluna[3].contains("TRUE") || coluna[3].contains("EMPTY LINE")
							|| coluna[3].equals("NULL"))) {

						vars.add(coluna[3]);

					}
				}

				if (arqs.contains(coluna[0])) {

				} else {
					if (!(coluna[3].contains("TRUE") || coluna[3].contains("EMPTY LINE")
							|| coluna[3].equals("NULL"))) {

						arqs.add(coluna[0]);

					}
				}

				if (commits.contains(coluna[4])) {

				} else {
					if (!(coluna[3].contains("TRUE") || coluna[3].contains("EMPTY LINE")
							|| coluna[3].equals("NULL"))) {

						commits.add(coluna[4]);

					}
				}
			}

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		for (int i = 0; i < arqs.size(); i++) {

			System.out.print(i + 1 + " / " + arqs.size() + "  -  ");
			dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			now = LocalDateTime.now();
			System.out.println(dtf.format(now));

			try {

				tr = new BufferedReader(new FileReader(csvPath));
				while ((line = tr.readLine()) != null) {

					String[] coluna = line.split(cvsSplitBy);

					if (coluna[0].equals(arqs.get(i))) {

						if (numVar.contains(coluna[3])) {

						} else {
							numVar.add(coluna[3]);
						}

						if (numAuth.contains(coluna[5])) {

						} else {
							numAuth.add(coluna[5]);
						}

						if (numCommit.contains(coluna[4])) {

						} else {
							numCommit.add(coluna[4]);
						}

					}

				}

				linha.add(repo[0] + ";" + arqs.get(i) + ";" + numVar.size() + ";" + numAuth.size() + ";" + numCommit.size() + "\n");
				numVar.clear();
				numArqs.clear();
				numAuth.clear();
				numCommit.clear();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (tr != null) {
					try {
						tr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		String lin = linha.toString().replace("[", "");
		lin = lin.toString().replace("]", "");
		lin = lin.replace(", ", "");

		try (FileWriter writer = new FileWriter(violin); BufferedWriter bw = new BufferedWriter(writer)) {

			bw.write(lin);

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		now = LocalDateTime.now();
		System.out.println(dtf.format(now));

	}

}
