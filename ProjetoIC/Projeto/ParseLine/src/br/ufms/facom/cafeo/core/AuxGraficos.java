package br.ufms.facom.cafeo.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import org.eclipse.jgit.diff.DiffFormatter;

import br.ufms.facom.cafeo.git.Commit;
import br.ufms.facom.cafeo.git.Repo;
import br.ufms.facom.cafeo.utils.ManipulationUtils;

public class AuxGraficos {

	static String commit = "";
	static String rep = "Hexchat";
	static ArrayList<String> dados = new ArrayList<String>();
	static ArrayList<String> dados2 = new ArrayList<String>();
	static ArrayList<String> dados3 = new ArrayList<String>();
	static ArrayList<String> dados4 = new ArrayList<String>();
	static ArrayList<String> auth = new ArrayList<String>();
	static ArrayList<String> qtdVar = new ArrayList<String>();
	static ArrayList<String> authVar = new ArrayList<String>();
	static ArrayList<String> qtdArq = new ArrayList<String>();
	static ArrayList<String> modVar = new ArrayList<String>();
	static ArrayList<String> modArq = new ArrayList<String>();
	static String rvPath = "C:\\Users\\Vini\\Desktop\\graficos\\" + rep;
	static String taPath = "C:\\Users\\Vini\\Desktop\\100UTC\\" + rep + "Tabela.csv";

	static BufferedReader br = null;
	static BufferedReader tr = null;
	static String line = "";
	static String lin = "";
	static int aux = 0;
	static int auxAuth = 0;
	static int auxAuthVar = 0;
	static int addvar = 0;
	static int antigovar = 0;
	static int remvar = 0;
	static int antigoarq = 0;
	static int addarq = 0;
	static int remarq = 0;
	static int i = 0;
	static float conVarAdd = 0;
	static float conVarRem = 0;
	static float conVarMod = 0;
	static float conArqAdd = 0;
	static float conArqRem = 0;
	static float conArqMod = 0;

	static ArrayList<String> hash = new ArrayList<String>();
	static ArrayList<Repo> listofRepos = new ArrayList<Repo>();

	static String repoList = "";
	
	static DecimalFormat dc = new DecimalFormat("0.0");

	private static void loadRepos(ArrayList<String> repos) {
		for (String repoURI : repos)
			listofRepos.add(new Repo(repoURI));
	}

	public static void main(String[] args) throws IOException {
		
		dados.add(
				"commit ; qtdVar ; qtdArq ; modVar ; modArq ; addVar ; addArq ; remVar ; remArq ; qtdAuth ; qtdAuthVar \n");

		if (args.length == 0)
			repoList = "repos.txt";
		else {
			repoList = args[0];
		}
		loadRepos(ManipulationUtils.loadRepos(repoList));

		if (!listofRepos.isEmpty()) {

			for (Repo r : listofRepos) {

				System.out.println("Começando...");

				if (!r.getCommitList().isEmpty()) {

					for (Commit c : r.getCommitList()) {

						String[] commitAr = c.toString().split(";");
						hash.add(commitAr[0]);

					}
				}
			}
		}

		System.out.println(hash);

		for (i = 1; i < hash.size(); i++) {
			
			String dir = "D:\\analysis\\" + rep + "\\" + hash.get(i);
			File f = new File("D:\\analysis\\" + rep + "\\" + hash.get(i));
			
			if(!(f.isDirectory()))

			{
				
			}else {

			try (Stream<Path> filePathStream = Files.walk(Paths.get("D:\\analysis\\" + rep + "\\" + hash.get(i)))) {

				filePathStream.forEach(filePath -> {

					if (Files.isRegularFile(filePath)) {

						String pat = filePath.toString();
						pat = pat.replace("\\", "/");
						// System.out.println(pat);
						String[] p = pat.split("/");
						
						if (!(p[3].equals(commit))) {
							commit = p[3];
							System.out.println(i + "/" + hash.size() + "   -   " + commit);
						}

						aux++;
						try {

							br = new BufferedReader(new FileReader(pat));
							while ((line = br.readLine()) != null) {

								if (line.equals("TRUE") || line.equals("NULL") || line.equals("EMPTY LINE")) {

								} else {

									if (!(qtdVar.contains(line))) {
										qtdVar.add(line);
									}
									

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

					}
				});
				
				

				try {

					tr = new BufferedReader(new FileReader(taPath));
					while ((lin = tr.readLine()) != null) {

						String[] coluna = lin.split(";");

						if (coluna[4].equals(hash.get(i))) {
							
							if (!(modArq.contains(coluna[0]))) {
								modArq.add(coluna[0]);
							}
							
							if(!(auth.contains(coluna[5]))){
								auth.add(coluna[5]);
								auxAuth++;
							}
							
							if(coluna[3].equals("TRUE") || coluna[3].equals("EMPTY LINE") || coluna[3].equals("NULL")) {
								
								if(!(authVar.contains(coluna[5]))){
									authVar.add(coluna[5]);
									auxAuthVar++;
								}
								
								if (!(modVar.contains(coluna[3]))) {
									modVar.add(coluna[3]);
								}
								
							}
							
							
						}

					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			
			
//			if (!(p[3].equals(commit))) {
//				commit = p[3];
//				System.out.println(i + "/" + hash.size() + "   -   " + commit);
//				if(p[3].equals("16e9434927be6d833b4c4d6484ec41253ec70804")) {
//					System.out.println("debug");
//				}
//
//				if (dados.isEmpty()) {
//					dados.add(
//							"commit ; qtdVar ; qtdArq ; modVar ; modArq ; addVar ; addArq ; remVar ; remArq\n");
//				} else {
					if(qtdVar.size() == 8) {
						System.out.println("debug");
					}
			
					if (qtdVar.size() > antigovar) {
						addvar = qtdVar.size() - antigovar;
						antigovar = qtdVar.size();
						remvar = 0;
					} else if (qtdVar.size() < antigovar) {
						remvar = antigovar - qtdVar.size();
						antigovar = qtdVar.size();
						addvar = 0;
					} else {
						remvar = 0;
						addvar = 0;
					}

					if (aux > antigoarq) {
						addarq = aux - antigoarq;
						antigoarq = aux;
						remarq = 0;
					} else if (aux < antigoarq) {
						remarq = antigoarq - aux;
						antigoarq = aux;
						addarq = 0;
					} else {
						remarq = 0;
						addarq = 0;
					}
					
					if(addvar != 0) {
						conVarAdd++;
					}
					if(remvar != 0) {
						conVarRem++;
					}
					if(modVar.size() != 0) {
						conVarMod++;
					}
					if(addarq != 0) {
						conArqAdd++;
					}
					if(remarq != 0) {
						conArqRem++;
					}
					if(modArq.size() != 0) {
						conArqMod++;
					}
					
					dados.add(hash.get(i) + ";" + qtdVar.size() + ";" + aux + ";" + modVar.size() + ";" + modArq.size() + ";" 
					+ addvar + " ; " + addarq + ";"+ remvar + " ; " + remarq  + ";" + auxAuth + ";" + auxAuthVar +"\n" );
					
					qtdVar.clear();
					qtdArq.clear();
					modVar.clear();
					modArq.clear();
					aux = 0;


		}
		}

		String dado = dados.toString().replace("[", "");
		dado = dado.replace(", ", "");
		dado = dado.replace("]", "");

		try (FileWriter writer = new FileWriter(rvPath + ".txt"); BufferedWriter bw = new BufferedWriter(writer)) {

			bw.write(dado.toString());

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		String tabela = "Sistema ; VarAdd ; VarRem ; VarMod ; ArqAdd ; ArqRem ; ArqMod \n"
				+ rep + ";" + dc.format((conVarAdd / hash.size())*100) + "%;" + dc.format((conVarRem / hash.size())*100) + "%;" + dc.format((conVarMod / hash.size())*100) + "%;" 
				+ dc.format((conArqAdd / hash.size())*100) + "%;" + dc.format((conArqRem / hash.size())*100) + "%;" + dc.format((conArqMod / hash.size())*100) + "%\n" ;
		
		try (FileWriter writer = new FileWriter(rvPath + "Porcentagem.txt"); BufferedWriter bw = new BufferedWriter(writer)) {

			bw.write(tabela);

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
	}
}
