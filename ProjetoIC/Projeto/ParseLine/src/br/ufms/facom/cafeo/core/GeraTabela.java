package br.ufms.facom.cafeo.core;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class GeraTabela {

	static ArrayList<String> hVar = new ArrayList<String>();
	static ArrayList<String> variabilidade = new ArrayList<String>();
	static ArrayList<String> tabela = new ArrayList<String>();
	static String var;

	public static void writeCsv(String repoName, ArrayList<String> range, ArrayList<String> file,
			ArrayList<String> change, ArrayList<String> line, ArrayList<String> auth, ArrayList<String> pasta,
			ArrayList<String> hVar) throws IOException {

		Path tabelaCsv = Paths
				.get("C:\\repoaux\\" + repoName + "\\tabela.txt");

		for (int i = 0; i < range.size(); i++) {
			
			System.out.println("o range é: " + range.size());
			if(hVar.get(i).contains("6d6a41b89445bf9e14d7d59e1275aa1ae9195619")) {
				
				System.out.println(hVar.get(i));
				variabilidade.add("TRUE");
				long aux = Long.valueOf(line.get(i));
				
			}else {
			
			
			
				long aux = Long.valueOf(line.get(i));
			
			if (pasta.get(i).contains("pastavazia")) {

					//System.out.println("C:\\analysis\\" + repoName + "\\" + hVar.get(i) + "\\"
					//		+ file.get(i) + ".var");
					//System.out.println(aux);
					try (Stream<String> linhaVar = Files.lines(Paths
							.get("D:\\analysis\\" + repoName + "\\" + hVar.get(i) + "\\" + file.get(i) + ".var"))) {
						
						double tamanho = countLinesNew("D:\\analysis\\" + repoName + "\\" + hVar.get(i) + "\\" + file.get(i) + ".var");
						
						if(aux > tamanho) {
							variabilidade.add("TRUE");
						}else {
							var = linhaVar.skip(aux - 1).findFirst().get();
							System.out.println(var);
							variabilidade.add(var);
					
						}
						

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}

					
				
			} else {
				
					//System.out.println("C:\\analysis\\" + repoName + "\\" + hVar.get(i) + "\\" + pasta.get(i) + "\\"
					//		+ file.get(i) + ".var");
					//System.out.println(aux - 1);
					try (Stream<String> linhaVar = Files.lines(Paths.get("D:\\analysis\\" + repoName + "\\"
							+ hVar.get(i) + "\\" + pasta.get(i) + "\\" + file.get(i) + ".var"))) {
						
						
						double tamanho = countLinesNew("D:\\analysis\\" + repoName + "\\"
								+ hVar.get(i) + "\\" + pasta.get(i) + "\\" + file.get(i) + ".var");
						
		
						
						if(aux > tamanho) {
							variabilidade.add("TRUE");
						}else {
							var = linhaVar.skip(aux - 1).findFirst().get();
							System.out.println(var);
							variabilidade.add(var);
					
						}
						
							

					} catch (FileNotFoundException e) {
						
						e.printStackTrace();
						
					}
				
				
					
				
			}
			

			tabela.add(file.get(i) + "; " 
			+ change.get(i) + "; " 
					+ line.get(i) + "; " 
			+ variabilidade.get(i) + "; "
					+ auth.get(i));
			}
		}

		
		Files.write(tabelaCsv, tabela, Charset.forName("UTF-8"));
	}
	
	public static int countLinesNew(String filename) throws IOException {
	    InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    try {
	        byte[] c = new byte[1024];

	        int readChars = is.read(c);
	        if (readChars == -1) {
	            return 0;
	        }

	       
	        int count = 0;
	        while (readChars == 1024) {
	            for (int i=0; i<1024;) {
	                if (c[i++] == '\n') {
	                    ++count;
	                }
	            }
	            readChars = is.read(c);
	        }

	        while (readChars != -1) {
	            System.out.println(readChars);
	            for (int i=0; i<readChars; ++i) {
	                if (c[i] == '\n') {
	                    ++count;
	                }
	            }
	            readChars = is.read(c);
	        }

	        return count == 0 ? 1 : count;
	    } finally {
	        is.close();
	    }
	}
}
