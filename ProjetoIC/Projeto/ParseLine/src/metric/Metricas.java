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

public class Metricas {

	public static void main(String[] args) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String csvPath = "C:\\Users\\Vini\\Desktop\\100%\\MPSolveTabela.csv";
		String links = "C:\\Users\\Vini\\Desktop\\links.txt";
	
		String metPath = "C:\\Users\\Vini\\Desktop\\Metricas.txt" ;
		
		ArrayList<String> authors = new ArrayList<String>();
		ArrayList<String> authorsVars = new ArrayList<String>();
		ArrayList<String> vars = new ArrayList<String>();
		ArrayList<String> arqs = new ArrayList<String>();
		ArrayList<String> commits = new ArrayList<String>();
		ArrayList<String> link = new ArrayList<String>();

		BufferedReader br = null;
		BufferedReader tr = null;
		int x = 10;
		String percent = "" ;
		String line = "";
		String nome = "";
		String dataInicio = "" ;
		String dataFim = "" ;
		String cvsSplitBy = ";";
		String met = "NomeProjeto;LOC;# arquivos;# variabilidades;# contribuidores;# commits;% contribuidores de variabilidades;inicio;fim\n";
		
		try {

			tr = new BufferedReader(new FileReader(links));
			while ((line = tr.readLine()) != null) {
				
				link.add(line);
				
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
		
		for(int i = 0; i < link.size() ;i++) {
		try {

			br = new BufferedReader(new FileReader(link.get(i)));
			while ((line = br.readLine()) != null) {
				
				String sp = link.get(i).replace("\\", "/");
				String[] rep = sp.split("/");
				String[] repo = rep[5].split("Tabela");
				nome = repo[0];
				String[] coluna = line.split(cvsSplitBy);
				
				if(dataFim.isEmpty()) {
					dataFim = coluna[9];
				}
				
				dataInicio = coluna[9];
				
				if (authorsVars.contains(coluna[5])) {
					
					
				} else {
					
					if(!(coluna[3].contains("TRUE") || coluna[3].contains("EMPTY LINE"))) {
						
						authorsVars.add(coluna[5]);
						
					}
				}
				
				if (authors.contains(coluna[5])) {

				} else {
					authors.add(coluna[5]);
				}

				if (vars.contains(coluna[3])) {

				} else {
					vars.add(coluna[3]);
				}

				if (arqs.contains(coluna[0])) {

				} else {
					arqs.add(coluna[0]);
				}

				if (commits.contains(coluna[4])) {

				} else {
					commits.add(coluna[4]);
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		percent = (authorsVars.size() * 100) / authors.size() + "%" ;	
		
		
		met +=  nome +";"+ commits.get(commits.size()-1) +";"+ arqs.size() +";"+ vars.size() +";"+ authors.size() +";"+ commits.size() +";"+ percent +";"+ dataInicio +";"+ dataFim + "\n";		
		//System.out.println(met);
		dataFim = "";
		commits.clear();
		arqs.clear();
		vars.clear();
		authors.clear();
		authorsVars.clear();
		}

		
		try (FileWriter writer = new FileWriter(metPath);
				BufferedWriter bw = new BufferedWriter(writer)) {

			bw.write(met.toString());

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		
		//System.out.println(authorsVars.toString());
	}
}
