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

public class FULL {

	public static void main(String[] args) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String csvPath = "C:\\Users\\Vini\\Desktop\\100%\\KerberosTabela.csv";
		String links = "C:\\Users\\Vini\\Desktop\\links.txt";


		ArrayList<String> authors = new ArrayList<String>();
		ArrayList<String> authorsVars = new ArrayList<String>();
		ArrayList<String> vars = new ArrayList<String>();
		ArrayList<String> arqs = new ArrayList<String>();
		ArrayList<String> commits = new ArrayList<String>();
		ArrayList<String> link = new ArrayList<String>();
		ArrayList<String> arqvar = new ArrayList<String>();
		ArrayList<String> arqauth = new ArrayList<String>();
		ArrayList<String> arqcommi = new ArrayList<String>();
		ArrayList<String> vararq = new ArrayList<String>();
		ArrayList<String> varauth = new ArrayList<String>();
		ArrayList<String> varcommi = new ArrayList<String>();
		ArrayList<String> autharq = new ArrayList<String>();
		ArrayList<String> authvar = new ArrayList<String>();
		ArrayList<String> authcommi = new ArrayList<String>();
		ArrayList<String> commiarq = new ArrayList<String>();
		ArrayList<String> commivar = new ArrayList<String>();
		ArrayList<String> commiauth = new ArrayList<String>();
		ArrayList<Integer> valoresum = new ArrayList<Integer>();
		ArrayList<Integer> valoresdois = new ArrayList<Integer>();
		ArrayList<Integer> valorestres = new ArrayList<Integer>();
		ArrayList<String> nome = new ArrayList<String>();
		ArrayList<String> itensmaiores = new ArrayList<String>();
		ArrayList<String> itensmenores = new ArrayList<String>();
		ArrayList<Integer> posicoes = new ArrayList<Integer>();
		ArrayList<Integer> posicoesmenor = new ArrayList<Integer>();

		BufferedReader br = null;
		BufferedReader tr = null;
		//int x = 10;
		int maior = 0, menor = 100000;
		int min, max, moda, mediana, media;
		int posmaior = 0, posmenor = 0;
		String percent = "";
		String nulo = "-;-;-;-;-";
		String line = "";
		String dataInicio = "";
		String dataFim = "";
		String name = "";
		String cvsSplitBy = ";";
		String med = "- ;;; ARQUIVO ;;;;; VARIABILIDADE ;;;;; CONTRIBUIDORES ;;;;; COMMIT ;;;\n";
		med += " - ; MIN ; MAX ; MODA ; MEDIANA ; MÉDIA ; MIN ; MAX ; MODA ; MEDIANA ; MÉDIA ; MIN ; MAX ; MODA ; MEDIANA ; MÉDIA ; MIN ; MAX ; MODA ; MEDIANA ; MÉDIA\n";

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
		
		// for (int i = 0; i < link.size(); i++) {
		try {

			br = new BufferedReader(new FileReader(csvPath));
			while ((line = br.readLine()) != null) {

				String sp = csvPath.replace("\\", "/");
				String[] rep = sp.split("/");
				String[] repo = rep[5].split("Tabela");
				name = repo[0];
				String[] coluna = line.split(cvsSplitBy);

				if (dataFim.isEmpty()) {
					dataFim = coluna[9];
				}

				dataInicio = coluna[9];

				if (authorsVars.contains(coluna[5])) {

				} else {

					if (!(coluna[3].contains("TRUE") || coluna[3].contains("EMPTY LINE")
							|| coluna[3].contains("NULL"))) {

						authorsVars.add(coluna[5]);

					}
				}

				if (authors.contains(coluna[5])) {

				} else {
					authors.add(coluna[5]);
				}

				if (vars.contains(coluna[3])) {

				} else {
					if (!(coluna[3].contains("TRUE") || coluna[3].contains("EMPTY LINE")
							|| coluna[3].contains("NULL"))) {

						vars.add(coluna[3]);

					}
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
		}
		
		String metPath = "C:\\Users\\Vini\\Desktop\\MEDIDAS\\Medidas"+ name +".csv";
		// System.out.println(met);

		for (int l = 0; l < arqs.size(); l++) {

			System.out.print(l + " / " + arqs.size() + "  -  ");
			dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			now = LocalDateTime.now();
			System.out.println(dtf.format(now));

			try {
				br = new BufferedReader(new FileReader(csvPath));
				while ((line = br.readLine()) != null) {

					String[] coluna = line.split(cvsSplitBy);

					if (line.contains(arqs.get(l))) {
						if (arqvar.contains(coluna[3])) {

						} else {
							if (coluna[3].contains("NULL") || coluna[3].contains("EMPTY LINE")
									|| coluna[3].contains("TRUE")) {

							} else {
								arqvar.add(coluna[3]);
							}

						}

						if (arqauth.contains(coluna[5])) {

						} else {

							arqauth.add(coluna[5]);

						}

						if (arqcommi.contains(coluna[4])) {

						} else {
							arqcommi.add(coluna[4]);
						}

					}

				}
				nome.add(arqs.get(l));
				valoresum.add(arqvar.size());
				arqvar.clear();
				valoresdois.add(arqauth.size());
				arqauth.clear();
				valorestres.add(arqcommi.size());
				arqcommi.clear();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}

		for (int s = 0; s < valoresum.size(); s++) {
			if (valoresum.get(s) > maior) {
				maior = valoresum.get(s);
				posmaior = s;
			}
			if (valoresum.get(s) < menor) {
				menor = valoresum.get(s);
				posmenor = s;
			}

		}
		
		for(int x = 0; x < valoresum.size();x++) {
			if(valoresum.get(x).equals(maior)) {
				posicoes.add(x);
			}
			if(valoresum.get(x).equals(menor)) {
				posicoesmenor.add(x);
			}
		}
		
		for(int c = 0; c < posicoes.size();c++) {
			itensmaiores.add(nome.get(posicoes.get(c)));
		}
		for(int c = 0; c < posicoesmenor.size();c++) {
			itensmenores.add(nome.get(posicoesmenor.get(c)));
		}

		med += "arquivos;" + nulo + ";" + menor + " - " + itensmenores.size() + " itens ;" + maior + " "
				+ itensmaiores.size() + " ;" + Medidas.calcularModa(valoresum) + ";"
				+ Medidas.calcularMediana(valoresum) + ";" + Medidas.calcularMedia(valoresum) + ";";

		posicoes.clear();
		posicoesmenor.clear();
		itensmaiores.clear();
		itensmenores.clear();
		maior = 0;
		menor = 100000;

		for (int s = 0; s < valoresdois.size(); s++) {
			if (valoresdois.get(s) > maior) {
				maior = valoresdois.get(s);
				posmaior = s;
			}
			if (valoresdois.get(s) < menor) {
				menor = valoresdois.get(s);
				posmenor = s;
			}

		}
		
		for(int x = 0; x < valoresdois.size();x++) {
			if(valoresdois.get(x).equals(maior)) {
				posicoes.add(x);
			}
			if(valoresdois.get(x).equals(menor)) {
				posicoesmenor.add(x);
			}
		}
		
		for(int c = 0; c < posicoes.size();c++) {
			itensmaiores.add(nome.get(posicoes.get(c)));
		}
		for(int c = 0; c < posicoesmenor.size();c++) {
			itensmenores.add(nome.get(posicoesmenor.get(c)));
		}
		

		med += menor + " - " + itensmenores.size() + " itens ;" + maior + " - " + itensmaiores.size() + " itens ;"
				+ Medidas.calcularModa(valoresdois) + ";" + Medidas.calcularMediana(valoresdois) + ";"
				+ Medidas.calcularMedia(valoresdois) + ";";
		
		posicoes.clear();
		posicoesmenor.clear();
		itensmaiores.clear();
		itensmenores.clear();
		maior = 0;
		menor = 100000;

		for (int s = 0; s < valorestres.size(); s++) {
			if (valorestres.get(s) > maior) {
				maior = valorestres.get(s);
				posmaior = s;
			}
			if (valorestres.get(s) < menor) {
				menor = valorestres.get(s);
				posmenor = s;
			}

		}
		
		for(int x = 0; x < valorestres.size();x++) {
			if(valorestres.get(x).equals(maior)) {
				posicoes.add(x);
			}
			if(valorestres.get(x).equals(menor)) {
				posicoesmenor.add(x);
			}
		}
		
		for(int c = 0; c < posicoes.size();c++) {
			itensmaiores.add(nome.get(posicoes.get(c)));
		}
		for(int c = 0; c < posicoesmenor.size();c++) {
			itensmenores.add(nome.get(posicoesmenor.get(c)));
		}

		med += menor + " - " + itensmenores.size() + " itens ;" + maior + " - " + itensmaiores.size() + " itens ;"
				+ Medidas.calcularModa(valorestres) + ";" + Medidas.calcularMediana(valorestres) + ";"
				+ Medidas.calcularMedia(valorestres) + "\n";
		
		posicoes.clear();
		posicoesmenor.clear();
		itensmaiores.clear();
		itensmenores.clear();
		maior = 0;
		menor = 100000;
		nome.clear();
		valoresum.clear();
		valoresdois.clear();
		valorestres.clear();
		
		for (int l = 0; l < vars.size(); l++) {

			System.out.print(l + " / " + vars.size() + "  -  ");
			dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			now = LocalDateTime.now();
			System.out.println(dtf.format(now));

			try {
				br = new BufferedReader(new FileReader(csvPath));
				while ((line = br.readLine()) != null) {

					String[] coluna = line.split(cvsSplitBy);

					if (line.contains(vars.get(l))) {
						if (vararq.contains(coluna[0])) {

						} else {

							vararq.add(coluna[0]);

						}

						if (varauth.contains(coluna[5])) {

						} else {

							varauth.add(coluna[5]);

						}

						if (varcommi.contains(coluna[4])) {

						} else {
							varcommi.add(coluna[4]);
						}

					}

				}
				nome.add(vars.get(l));
				valoresum.add(vararq.size());
				vararq.clear();
				valoresdois.add(varauth.size());
				varauth.clear();
				valorestres.add(varcommi.size());
				varcommi.clear();

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
		
		
		for (int s = 0; s < valoresum.size(); s++) {
			if (valoresum.get(s) > maior) {
				maior = valoresum.get(s);
				posmaior = s;
			}
			if (valoresum.get(s) < menor) {
				menor = valoresum.get(s);
				posmenor = s;
			}

		}
		
		for(int x = 0; x < valoresum.size();x++) {
			if(valoresum.get(x).equals(maior)) {
				posicoes.add(x);
			}
			if(valoresum.get(x).equals(menor)) {
				posicoesmenor.add(x);
			}
		}
		
		for(int c = 0; c < posicoes.size();c++) {
			itensmaiores.add(nome.get(posicoes.get(c)));
		}
		for(int c = 0; c < posicoesmenor.size();c++) {
			itensmenores.add(nome.get(posicoesmenor.get(c)));
		}

		med += "Variabilidades;" + menor;
		med += " - " + itensmenores.size() + " itens ;";
		med += maior + " "	+ itensmaiores.size() + " ;";
		med += Medidas.calcularModa(valoresum) + ";" + Medidas.calcularMediana(valoresum) + ";" + Medidas.calcularMedia(valoresum) + ";" + nulo + ";";

		posicoes.clear();
		posicoesmenor.clear();
		itensmaiores.clear();
		itensmenores.clear();
		maior = 0;
		menor = 100000;

		for (int s = 0; s < valoresdois.size(); s++) {
			if (valoresdois.get(s) > maior) {
				maior = valoresdois.get(s);
				posmaior = s;
			}
			if (valoresdois.get(s) < menor) {
				menor = valoresdois.get(s);
				posmenor = s;
			}

		}
		
		for(int x = 0; x < valoresdois.size();x++) {
			if(valoresdois.get(x).equals(maior)) {
				posicoes.add(x);
			}
			if(valoresdois.get(x).equals(menor)) {
				posicoesmenor.add(x);
			}
		}
		
		for(int c = 0; c < posicoes.size();c++) {
			itensmaiores.add(nome.get(posicoes.get(c)));
		}
		for(int c = 0; c < posicoesmenor.size();c++) {
			itensmenores.add(nome.get(posicoesmenor.get(c)));
		}

		med += menor + " - " + itensmenores.size() + " itens ;" + maior + " - " + itensmaiores.size() + " itens ;"
				+ Medidas.calcularModa(valoresdois) + ";" + Medidas.calcularMediana(valoresdois) + ";"
				+ Medidas.calcularMedia(valoresdois) + ";";

		posicoes.clear();
		posicoesmenor.clear();
		itensmaiores.clear();
		itensmenores.clear();
		maior = 0;
		menor = 100000;

		for (int s = 0; s < valorestres.size(); s++) {
			if (valorestres.get(s) > maior) {
				maior = valorestres.get(s);
				posmaior = s;
			}
			if (valorestres.get(s) < menor) {
				menor = valorestres.get(s);
				posmenor = s;
			}

		}
		
		for(int x = 0; x < valorestres.size();x++) {
			if(valorestres.get(x).equals(maior)) {
				posicoes.add(x);
			}
			if(valorestres.get(x).equals(menor)) {
				posicoesmenor.add(x);
			}
		}
		
		for(int c = 0; c < posicoes.size();c++) {
			itensmaiores.add(nome.get(posicoes.get(c)));
		}
		for(int c = 0; c < posicoesmenor.size();c++) {
			itensmenores.add(nome.get(posicoesmenor.get(c)));
		}
		

		med += menor + " - " + itensmenores.size() + " itens ;" + maior + " - " + itensmaiores.size() + " itens ;"
				+ Medidas.calcularModa(valorestres) + ";" + Medidas.calcularMediana(valorestres) + ";"
				+ Medidas.calcularMedia(valorestres) + "\n";
		

		posicoes.clear();
		posicoesmenor.clear();
		itensmaiores.clear();
		itensmenores.clear();
		maior = 0;
		menor = 100000;
		nome.clear();
		valoresum.clear();
		valoresdois.clear();
		valorestres.clear();
		

		for (int a = 0; a < authors.size(); a++) {

			System.out.print(a + " / " + authors.size() + "  -  ");
			dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			now = LocalDateTime.now();
			System.out.println(dtf.format(now));

			try {
				br = new BufferedReader(new FileReader(csvPath));
				while ((line = br.readLine()) != null) {

					String[] coluna = line.split(cvsSplitBy);

					if (line.contains(authors.get(a))) {
						if (autharq.contains(coluna[0])) {

						} else {

							autharq.add(coluna[0]);

						}

						if (authvar.contains(coluna[3])) {

						} else {
							
							if (coluna[3].contains("NULL") || coluna[3].contains("EMPTY LINE")
									|| coluna[3].contains("TRUE")) {

							} else {
								authvar.add(coluna[3]);
							}

							

						}

						if (authcommi.contains(coluna[4])) {

						} else {
							authcommi.add(coluna[4]);
						}

					}

				}
				nome.add(authors.get(a));
				valoresum.add(autharq.size());
				autharq.clear();
				valoresdois.add(authvar.size());
				authvar.clear();
				valorestres.add(authcommi.size());
				authcommi.clear();

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
		
		
		for (int s = 0; s < valoresum.size(); s++) {
			if (valoresum.get(s) > maior) {
				maior = valoresum.get(s);
				posmaior = s;
			}
			if (valoresum.get(s) < menor) {
				menor = valoresum.get(s);
				posmenor = s;
			}

		}
		
		for(int x = 0; x < valoresum.size();x++) {
			if(valoresum.get(x).equals(maior)) {
				posicoes.add(x);
			}
			if(valoresum.get(x).equals(menor)) {
				posicoesmenor.add(x);
			}
		}
		
		for(int c = 0; c < posicoes.size();c++) {
			itensmaiores.add(nome.get(posicoes.get(c)));
		}
		for(int c = 0; c < posicoesmenor.size();c++) {
			itensmenores.add(nome.get(posicoesmenor.get(c)));
		}

		med += "Contribuidores;" + menor;
		med += " - " + itensmenores.size() + " itens ;";
		med += maior + " "	+ itensmaiores.size() + " ;";
		med += Medidas.calcularModa(valoresum) + ";" + Medidas.calcularMediana(valoresum) + ";" + Medidas.calcularMedia(valoresum) + ";" ;

		posicoes.clear();
		posicoesmenor.clear();
		itensmaiores.clear();
		itensmenores.clear();
		maior = 0;
		menor = 100000;

		for (int s = 0; s < valoresdois.size(); s++) {
			if (valoresdois.get(s) > maior) {
				maior = valoresdois.get(s);
				posmaior = s;
			}
			if (valoresdois.get(s) < menor) {
				menor = valoresdois.get(s);
				posmenor = s;
			}

		}
		
		for(int x = 0; x < valoresdois.size();x++) {
			if(valoresdois.get(x).equals(maior)) {
				posicoes.add(x);
			}
			if(valoresdois.get(x).equals(menor)) {
				posicoesmenor.add(x);
			}
		}
		
		for(int c = 0; c < posicoes.size();c++) {
			itensmaiores.add(nome.get(posicoes.get(c)));
		}
		for(int c = 0; c < posicoesmenor.size();c++) {
			itensmenores.add(nome.get(posicoesmenor.get(c)));
		}
		

		med += menor + " - " + itensmenores.size() + " itens ;" + maior + " - " + itensmaiores.size() + " itens ;"
				+ Medidas.calcularModa(valoresdois) + ";" + Medidas.calcularMediana(valoresdois) + ";"
				+ Medidas.calcularMedia(valoresdois) + ";" + nulo + ";";

		posicoes.clear();
		posicoesmenor.clear();
		itensmaiores.clear();
		itensmenores.clear();
		maior = 0;
		menor = 100000;

		for (int s = 0; s < valorestres.size(); s++) {
			if (valorestres.get(s) > maior) {
				maior = valorestres.get(s);
				posmaior = s;
			}
			if (valorestres.get(s) < menor) {
				menor = valorestres.get(s);
				posmenor = s;
			}

		}
		
		for(int x = 0; x < valorestres.size();x++) {
			if(valorestres.get(x).equals(maior)) {
				posicoes.add(x);
			}
			if(valorestres.get(x).equals(menor)) {
				posicoesmenor.add(x);
			}
		}
		
		for(int c = 0; c < posicoes.size();c++) {
			itensmaiores.add(nome.get(posicoes.get(c)));
		}
		for(int c = 0; c < posicoesmenor.size();c++) {
			itensmenores.add(nome.get(posicoesmenor.get(c)));
		}
		

		med += menor + " - " + itensmenores.size() + " itens ;" + maior + " - " + itensmaiores.size() + " itens ;"
				+ Medidas.calcularModa(valorestres) + ";" + Medidas.calcularMediana(valorestres) + ";"
				+ Medidas.calcularMedia(valorestres) + "\n";
		
		posicoes.clear();
		posicoesmenor.clear();
		itensmaiores.clear();
		itensmenores.clear();
		maior = 0;
		menor = 100000;
		nome.clear();
		valoresum.clear();
		valoresdois.clear();
		valorestres.clear();
		
		
		for (int l = 0; l < commits.size(); l++) {

			System.out.print(l + " / " + commits.size() + "  -  ");
			dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			now = LocalDateTime.now();
			System.out.println(dtf.format(now));

			try {
				br = new BufferedReader(new FileReader(csvPath));
				while ((line = br.readLine()) != null) {

					String[] coluna = line.split(cvsSplitBy);

					if (line.contains(commits.get(l))) {
						if (commiarq.contains(coluna[0])) {

						} else {

							commiarq.add(coluna[0]);

						}

						if (commivar.contains(coluna[3])) {

						} else {
							
							if (coluna[3].contains("NULL") || coluna[3].contains("EMPTY LINE")
									|| coluna[3].contains("TRUE")) {

							} else {
								commivar.add(coluna[3]);
							}

						}

						if (commiauth.contains(coluna[5])) {

						} else {
							commiauth.add(coluna[5]);
						}

					}

				}
				nome.add(commits.get(l));
				valoresum.add(commiarq.size());
				commiarq.clear();
				valoresdois.add(commivar.size());
				commivar.clear();
				valorestres.add(commiauth.size());
				commiauth.clear();

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
		
		
		for (int s = 0; s < valoresum.size(); s++) {
			if (valoresum.get(s) > maior) {
				maior = valoresum.get(s);
				posmaior = s;
			}
			if (valoresum.get(s) < menor) {
				menor = valoresum.get(s);
				posmenor = s;
			}

		}
		
		for(int x = 0; x < valoresum.size();x++) {
			if(valoresum.get(x).equals(maior)) {
				posicoes.add(x);
			}
			if(valoresum.get(x).equals(menor)) {
				posicoesmenor.add(x);
			}
		}
		
		for(int c = 0; c < posicoes.size();c++) {
			itensmaiores.add(nome.get(posicoes.get(c)));
		}
		for(int c = 0; c < posicoesmenor.size();c++) {
			itensmenores.add(nome.get(posicoesmenor.get(c)));
		}

		med += "Commits;" + menor;
		med += " - " + itensmenores.size() + " itens ;";
		med += maior + " "	+ itensmaiores.size() + " ;";
		med += Medidas.calcularModa(valoresum) + ";" + Medidas.calcularMediana(valoresum) + ";" + Medidas.calcularMedia(valoresum) + ";" ;

		posicoes.clear();
		posicoesmenor.clear();
		itensmaiores.clear();
		itensmenores.clear();
		maior = 0;
		menor = 100000;

		for (int s = 0; s < valoresdois.size(); s++) {
			if (valoresdois.get(s) > maior) {
				maior = valoresdois.get(s);
				posmaior = s;
			}
			if (valoresdois.get(s) < menor) {
				menor = valoresdois.get(s);
				posmenor = s;
			}

		}
		
		for(int x = 0; x < valoresdois.size();x++) {
			if(valoresdois.get(x).equals(maior)) {
				posicoes.add(x);
			}
			if(valoresdois.get(x).equals(menor)) {
				posicoesmenor.add(x);
			}
		}
		
		for(int c = 0; c < posicoes.size();c++) {
			itensmaiores.add(nome.get(posicoes.get(c)));
		}
		for(int c = 0; c < posicoesmenor.size();c++) {
			itensmenores.add(nome.get(posicoesmenor.get(c)));
		}
		

		med += menor + " - " + itensmenores.size() + " itens ;" + maior + " - " + itensmaiores.size() + " itens ;"
				+ Medidas.calcularModa(valoresdois) + ";" + Medidas.calcularMediana(valoresdois) + ";"
				+ Medidas.calcularMedia(valoresdois) + ";";

		posicoes.clear();
		posicoesmenor.clear();
		itensmaiores.clear();
		itensmenores.clear();
		maior = 0;
		menor = 100000;

		for (int s = 0; s < valorestres.size(); s++) {
			if (valorestres.get(s) > maior) {
				maior = valorestres.get(s);
				posmaior = s;
			}
			if (valorestres.get(s) < menor) {
				menor = valorestres.get(s);
				posmenor = s;
			}

		}
		
		for(int x = 0; x < valorestres.size();x++) {
			if(valorestres.get(x).equals(maior)) {
				posicoes.add(x);
			}
			if(valorestres.get(x).equals(menor)) {
				posicoesmenor.add(x);
			}
		}
		
		for(int c = 0; c < posicoes.size();c++) {
			itensmaiores.add(nome.get(posicoes.get(c)));
		}
		for(int c = 0; c < posicoesmenor.size();c++) {
			itensmenores.add(nome.get(posicoesmenor.get(c)));
		}
		

		med += menor + " - " + itensmenores.size() + " itens ;" + maior + " - " + itensmaiores.size() + " itens ;"
				+ Medidas.calcularModa(valorestres) + ";" + Medidas.calcularMediana(valorestres) + ";"
				+ Medidas.calcularMedia(valorestres) + ";" + nulo  + "\n";

		posicoes.clear();
		posicoesmenor.clear();
		itensmaiores.clear();
		itensmenores.clear();
		maior = 0;
		menor = 100000;
		dataFim = "";
		commits.clear();
		arqs.clear();
		vars.clear();
		authors.clear();
		authorsVars.clear();

		try (FileWriter writer = new FileWriter(metPath); BufferedWriter bw = new BufferedWriter(writer)) {

			bw.write(med.toString());

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		now = LocalDateTime.now();
		System.out.println(dtf.format(now));
		// System.out.println(authorsVars.toString());
	}
}
