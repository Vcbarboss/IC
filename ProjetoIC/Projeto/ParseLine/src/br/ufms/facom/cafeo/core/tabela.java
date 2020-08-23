package br.ufms.facom.cafeo.core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import br.ufms.facom.cafeo.utils.ProgressBar;

public class tabela extends GeraTabela{

	static String saida;
	static int auxarq = -1;
	static int fux = 0;
	static String var;
	static String line;
	static String barra = "/";
	static ArrayList<String> mudanca = new ArrayList<String>();
	static ArrayList<String> arquivo = new ArrayList<String>();
	static ArrayList<String> arquivoaux = new ArrayList<String>();
	static ArrayList<String> listaLinhaRem = new ArrayList<String>();
	static ArrayList<String> hVar = new ArrayList<String>();
	static ArrayList<String> pastaaux = new ArrayList<String>();
	static ArrayList<String> test2 = new ArrayList<String>();
	static ArrayList<String> pasta = new ArrayList<String>();
	private static BufferedReader br;

	public static void getTabela(String repoName, ArrayList<String> auth, ArrayList<String> oIdc,
			ArrayList<String> hIdc) throws FileNotFoundException {
		String fileName = "C:\\repoaux\\" + repoName + "\\analise.txt";
		File file = new File(fileName);
		FileReader fr = new FileReader(file);
		br = new BufferedReader(fr);

		try {

			while ((line = br.readLine()) != null) {

				if (line.startsWith("@@")) {

					String str = line;
					removeArroba(str, ' ', 1);

					String rem = saida.substring(0, saida.indexOf(" "));

					String[] splitRem = rem.split(",");
					int sizeR = splitRem.length;
					int[] linhasRem = new int[sizeR];
					for (int i = 0; i < sizeR; i++) {
						
						linhasRem[i] = Integer.parseInt(splitRem[i]);
					}

					if (sizeR == 1) {
						int aux = linhasRem[0];
						String lux = Integer.toString(aux);
						listaLinhaRem.add(lux);

					} else if (sizeR == 2 && linhasRem[1] == 0) {
					}

					else {

						int aux = linhasRem[0];
						String lux = Integer.toString(aux);
						listaLinhaRem.add(lux);

						for (int i = 0; i < linhasRem[1] - 1; i++) {
							aux = aux + 1;
							String bux = Integer.toString(aux);

							listaLinhaRem.add(bux);
						}
					}

					removeArroba(str, ' ', 2);
					String adi = saida;

					String[] splitAdi = adi.split(",");
					int sizeA = splitAdi.length;
					int[] linhasAdi = new int[sizeA];
					for (int i = 0; i < sizeA; i++) {
						linhasAdi[i] = Integer.parseInt(splitAdi[i]);
					}
					if (sizeA == 1) {
						int aux = linhasAdi[0];
						String lux = Integer.toString(aux);
						listaLinhaRem.add(lux);
					} else if (sizeA == 2 && linhasAdi[1] == 0) {

					} else {

						int aux = linhasAdi[0];
						String lux = Integer.toString(aux);
						listaLinhaRem.add(lux);

						for (int i = 0; i < linhasAdi[1] - 1; i++) {
							aux = aux + 1;
							String bux = Integer.toString(aux);

							listaLinhaRem.add(bux);
						}
					}

					fux++;

				} else if (line.contains("--git")) {
					String[] arq = line.split(barra);

					arquivoaux.add(arq[arq.length - 1]);
					String[] arq2 = line.split(" b/");
					String auxarq2 = arq2[0];
					String[] arq3 = auxarq2.split(barra);
					if (arq3.length >= 4) {
						String tamanhoPasta = arq3[1];
						for(int i = 2; i < (arq3.length-1); i++) {
							tamanhoPasta += "\\" + arq3[i];
						}
						pastaaux.add(tamanhoPasta);
					} else if (arq3.length == 2) {
						pastaaux.add("pastavazia");
					} else
						pastaaux.add(arq3[1]);

					auxarq++;
				} else if (line.startsWith("-")) {
					if (line.startsWith("--- ")) {

					} else {
						mudanca.add("-");
						arquivo.add(arquivoaux.get(arquivoaux.size() - 1));
						pasta.add(pastaaux.get(pastaaux.size() - 1));
						test2.add(auth.get(auxarq));
						hVar.add(oIdc.get(auxarq));
					}
				} else if (line.startsWith("+") || line.startsWith(", +")) {
					if (line.startsWith("+++ ")) {

					} else {
						mudanca.add("+");
						arquivo.add(arquivoaux.get(arquivoaux.size() - 1));
						pasta.add(pastaaux.get(pastaaux.size() - 1));
						test2.add(auth.get(auxarq));
						hVar.add(hIdc.get(auxarq));					}
				}

			}
			writeCsv(repoName, listaLinhaRem, arquivo, mudanca, listaLinhaRem, test2, pasta, hVar);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	static void removeArroba(String str, char ch, int count) {

		int occ = 0, i;
		if (count == 0) {
			return;
		}
		for (i = 0; i < str.length(); i++) {
			if (str.charAt(i) == ch)
				occ++;
			if (occ == count)
				break;
		}
		if (i < str.length() - 1) {
			saida = str.substring(i + 2, str.length() - 3);
		} else {
			System.out.println("String vazia");
		}
	}

}
