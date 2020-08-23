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

public class Removedor {

	public static void main(String[] args) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String jsonPath = "C:\\Users\\Vini\\Desktop\\JSONS\\FALTA REMOVER\\OpenvpnJson.json";
		String sp = jsonPath.replace("\\", "/");
		String[] rep = sp.split("/");
		String[] repo = rep[6].split("Json");
		String jsonPath1 = "C:\\Users\\Vini\\Desktop\\JSONS\\FALTA REMOVER\\foi\\" + repo[0] + "Json.json";

		ArrayList<String> linha = new ArrayList<String>();

		BufferedReader tr = null;
		String line ="";
		String linhas = "";
		String lastLine = "";

			try {

				tr = new BufferedReader(new FileReader(jsonPath));
				while ((line = tr.readLine()) != null) {
					
					if(line.contains("\"NULL ") || line.contains("\"EMPTY LINE ")) {
						
						
					}else {
						
						if(line.contains("virg")) {
							line = line.replace("virg", ",");
						}
						
						if(line.contains("! ")) {
							line = line.replace("! ", "!");
						}
						
						if(line.contains("!	")) {
							line = line.replace("!	", "!");
						}
						
						
						if(line.contains("]") && lastLine.contains("\",")) {
							linha.remove(linha.size()-1);
							linha.add(lastLine.substring(0, lastLine.length() - 1) + "\n" + line + "\n");
						}else {
							lastLine = line;
							linha.add(line + "\n");
						}
						
						//linhas += line + "\n";
					}
					
					


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
			
//			String lin = linha.toString().replace("[" , "");
//			lin = lin.toString().replace("]" , "");
//			lin = lin.replace(", ", "");
			
			try (FileWriter writer = new FileWriter(jsonPath1); 
					BufferedWriter bw = new BufferedWriter(writer)) {

				bw.write(linha.toString());

			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}
			
			
		

	}

}
