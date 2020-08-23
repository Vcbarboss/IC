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

public class Violin {

	public static void main(String[] args) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String csvPath = "C:\\Users\\Vini\\Desktop\\100%\\MPSolveTabela.csv";
		String sp = csvPath.replace("\\", "/");
		String[] rep = sp.split("/");
		String[] repo = rep[5].split("Tabela");
		String violin= "C:\\Users\\Vini\\Desktop\\Violin.txt";
		String links = "C:\\Users\\Vini\\Desktop\\links.txt";

		ArrayList<String> linha = new ArrayList<String>();

		BufferedReader tr = null;
		String line ="";

			try {

				tr = new BufferedReader(new FileReader(csvPath));
				while ((line = tr.readLine()) != null) {
					
					linha.add(repo[0] + line + "\n");


				}

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
			
			String lin = linha.toString().replace("[" , "");
			lin = lin.toString().replace("]" , "");
			lin = lin.replace(", ", "");
			
			try (FileWriter writer = new FileWriter(violin); 
					BufferedWriter bw = new BufferedWriter(writer)) {

				bw.write(lin);

			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}
			
			
		

	}

}
