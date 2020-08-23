package ap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.Gson;
//COMMITS
public class ApiTesteFinal {
	
	public static void main(String[] args) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String csvPath = "C:\\Users\\Vini\\Desktop\\100UTC\\GawkTabela.csv";
		String rvPath = "C:\\Users\\Vini\\Desktop\\reverso.txt";
		String sp = csvPath.replace("\\", "/");
		String[] rep = sp.split("/");
		String[] repo = rep[5].split("Tabela");
		ArrayList<String> authors = new ArrayList<String>();
		ArrayList<String> vars = new ArrayList<String>();
		ArrayList<String> arqs = new ArrayList<String>();
		ArrayList<String> commits = new ArrayList<String>();
		ArrayList<String> arq_authors_commi = new ArrayList<String>();
		ArrayList<String> arq_authors_vars = new ArrayList<String>();
		ArrayList<String> arq_commi_authors = new ArrayList<String>();
		ArrayList<String> arq_commi_vars = new ArrayList<String>();
		ArrayList<String> arq_vars_authors = new ArrayList<String>();
		ArrayList<String> arq_vars_commi = new ArrayList<String>();
		ArrayList<String> commi_vars_auth = new ArrayList<String>();
		ArrayList<String> commi_vars_arq = new ArrayList<String>();
		ArrayList<String> commi_auth_vars = new ArrayList<String>();
		ArrayList<String> commi_auth_arq = new ArrayList<String>();
		ArrayList<String> commi_arq_auth = new ArrayList<String>();
		ArrayList<String> commi_arq_vars = new ArrayList<String>();
		ArrayList<String> arq_authors_commix = new ArrayList<String>();
		ArrayList<String> arq_authors_varsx = new ArrayList<String>();
		ArrayList<String> arq_commi_authorsx = new ArrayList<String>();
		ArrayList<String> arq_commi_varsx = new ArrayList<String>();
		ArrayList<String> arq_vars_authorsx = new ArrayList<String>();
		ArrayList<String> arq_vars_commix = new ArrayList<String>();
		ArrayList<String> commi_vars_authx = new ArrayList<String>();
		ArrayList<String> commi_vars_arqx = new ArrayList<String>();
		ArrayList<String> commi_auth_varsx = new ArrayList<String>();
		ArrayList<String> commi_auth_arqx = new ArrayList<String>();
		ArrayList<String> commi_arq_authx = new ArrayList<String>();
		ArrayList<String> commi_arq_varsx = new ArrayList<String>();
		ArrayList<String> arqCommits = new ArrayList<String>();
		ArrayList<String> arqAuth = new ArrayList<String>();
		ArrayList<String> arqVars = new ArrayList<String>();
		ArrayList<String> commiAuth = new ArrayList<String>();
		ArrayList<String> commiVars = new ArrayList<String>();
		ArrayList<String> commiFiles = new ArrayList<String>();
		ArrayList<String> revert = new ArrayList<String>();

		BufferedReader pr = null;
		BufferedReader br = null;
		BufferedReader tr = null;
		String autcom = "";
		String autcomx = "";
		String autvar = "";
		String autvarx = "";
		String line = "";
		String lin = "";
		String linein = "";
		String cvsSplitBy = ";";
		String json = "";
		String aux = "";
		String aux2 = "";
		String dateFirst = "";
		String dateLast = "";
		int x = 0;
		
		String arqFinal = "";
		String commiFinal = "";
		
		try {

			br = new BufferedReader(new FileReader(csvPath));
			while ((line = br.readLine()) != null) {

				revert.add(line + "\n");

				String[] coluna = line.split(cvsSplitBy);

				if (arqs.contains(coluna[0])) {

				} else {
					arqs.add(coluna[0]);
				}

				if (commits.contains(coluna[4])) {

				} else {
					commits.add(coluna[4]);
				}
				x++;
			}
			
			Collections.reverse(revert);
			
			String rever = revert.toString().replace("[" , "");
			String rv = rever.replace(", ", "");
			try (FileWriter writer = new FileWriter(rvPath);
					BufferedWriter bw = new BufferedWriter(writer)) {

				bw.write(rv.toString());

			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}

			
			for (int i = 0; i < commits.size(); i++) {

				System.out.print(i + " / " + commits.size() + "  -  ");
				dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				now = LocalDateTime.now();
				System.out.println(dtf.format(now));

				try {

					br = new BufferedReader(new FileReader(csvPath));
					while ((line = br.readLine()) != null) {

						String[] coluna = line.split(cvsSplitBy);

						if (!(coluna[3].equals("NULL") || coluna[3].equals("EMPTY LINE"))) {

							if (coluna[4].equals(commits.get(i))) {

								if (!(commiFiles.contains(coluna[0]))) {
									commiFiles.add(coluna[0]);

									try {

										tr = new BufferedReader(new FileReader(csvPath));
										while ((lin = tr.readLine()) != null) {

											String[] col = lin.split(cvsSplitBy);

											if (col[4].equals(commits.get(i)) && col[0].equals(coluna[0])) {

												if (!(commi_arq_authx.contains(col[5]))) {
													
													dateLast = col[9];
													
													System.gc();
													try {

														pr = new BufferedReader(new FileReader(rvPath));
														while ((linein = pr.readLine()) != null) {
															
															
															String[] colu = linein.split(cvsSplitBy);
															
															if(colu[5].equals(col[5])) {
																dateFirst = colu[9];
																break;
															}

														}
														
													} catch (FileNotFoundException e) {
														e.printStackTrace();
													} catch (IOException e) {
														e.printStackTrace();
													}
													
													commi_arq_authx.add(col[5]);
													commi_arq_auth.add(col[5] + "   -   (" + dateFirst + " - " + dateLast + ")");
												}
												if (!(commi_arq_varsx.contains(col[3]))) {
													
													commi_arq_varsx.add(col[3]);
													commi_arq_vars.add(col[3] + "   -   (" + col[9] + ")");
												}
											}

										}

									} catch (FileNotFoundException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}

									//String jsonFiles = new Gson().toJson(authFiles);
									String jsona = new Gson().toJson(commi_arq_auth);
									String jsonc = new Gson().toJson(commi_arq_vars);

									commi_arq_auth.clear();
									commi_arq_authx.clear();
									commi_arq_vars.clear();
									commi_arq_varsx.clear();

									aux += ("\n\"" + coluna[0] + "\" : { \n\"Autores\" :  " + jsona
											+ " , \"Variabilidades\" : " + jsonc + " }");

									json = aux + "\n";

									aux += ",\n";

								}

								if (!(commiVars.contains(coluna[3]))) {
									commiVars.add(coluna[3]);

									try {

										tr = new BufferedReader(new FileReader(csvPath));
										while ((lin = tr.readLine()) != null) {

											String[] col = lin.split(cvsSplitBy);

											if (col[4].equals(commits.get(i)) && col[3].equals(coluna[3])) {
												if (!(commi_vars_authx.contains(col[5]))) {
													dateLast = col[9];
													
													System.gc();
													try {

														pr = new BufferedReader(new FileReader(csvPath));
														while ((linein = pr.readLine()) != null) {
															
															
															String[] colu = linein.split(cvsSplitBy);
															
															if(colu[5].equals(col[5])) {
																dateFirst = colu[9];
																break;
															}

														}
														
													} catch (FileNotFoundException e) {
														e.printStackTrace();
													} catch (IOException e) {
														e.printStackTrace();
													}
													
													commi_vars_authx.add(col[5]);
													commi_vars_auth.add(col[5] + "   -   (" + dateFirst + " - " + dateLast + ")");
												}
												if (!(commi_vars_arqx.contains(col[0]))) {
													
													dateLast = col[9];
													
													System.gc();
													try {

														pr = new BufferedReader(new FileReader(csvPath));
														while ((linein = pr.readLine()) != null) {
															
															
															String[] colu = linein.split(cvsSplitBy);
															
															if(colu[0].equals(col[0])) {
																dateFirst = colu[9];
																break;
															}

														}
														
													} catch (FileNotFoundException e) {
														e.printStackTrace();
													} catch (IOException e) {
														e.printStackTrace();
													}
													
													commi_vars_arqx.add(col[0]);
													commi_vars_arq.add(col[0] + "   -   (" + dateFirst + " - " + dateLast + ")");
												}
											}

										}

									} catch (FileNotFoundException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}

									//String jsonFiles = new Gson().toJson(authFiles);
									String jsona = new Gson().toJson(commi_vars_auth);
									String jsonc = new Gson().toJson(commi_vars_arq);

									commi_vars_auth.clear();
									commi_vars_authx.clear();
									commi_vars_arq.clear();
									commi_vars_arqx.clear();

									autcom += ("\"" + coluna[3] + "\" : { \n\"Autores\" :  " + jsona
											+ " , \"Arquivos\" : " + jsonc + " }");

									autcomx = autcom + "\n";

									autcom += ",\n";
								}

								if (!(commiAuth.contains(coluna[5]))) {
									commiAuth.add(coluna[5]);

									try {

										tr = new BufferedReader(new FileReader(csvPath));
										while ((lin = tr.readLine()) != null) {

											String[] col = lin.split(cvsSplitBy);

											if (col[4].equals(commits.get(i)) && col[5].equals(coluna[5])) {
												if (!(commi_auth_arqx.contains(col[0]))) {
													
													commi_auth_arqx.add(col[0]);
													commi_auth_arq.add(col[0] + "   -   (" + col[9] + ")");
												}
												if (!(commi_auth_varsx.contains(col[3]))) {
													
													dateLast = col[9];
													
													System.gc();
													try {

														pr = new BufferedReader(new FileReader(csvPath));
														while ((linein = pr.readLine()) != null) {
															
															
															String[] colu = linein.split(cvsSplitBy);
															
															if(colu[3].equals(col[3])) {
																dateFirst = colu[9];
																break;
															}

														}
														
													} catch (FileNotFoundException e) {
														e.printStackTrace();
													} catch (IOException e) {
														e.printStackTrace();
													}
													
													commi_auth_varsx.add(col[3]);
													commi_auth_vars.add(col[3] + "   -   (" + dateFirst + " - " + dateLast + ")");
												}
											}

										}

									} catch (FileNotFoundException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}

									//String jsonFiles = new Gson().toJson(authFiles);
									String jsona = new Gson().toJson(commi_auth_vars);
									String jsonc = new Gson().toJson(commi_auth_arq);

									commi_auth_vars.clear();
									commi_auth_varsx.clear();
									commi_auth_arq.clear();
									commi_auth_arqx.clear();

									autvar += ("\"" + coluna[5] + "\" : { \n\"Variabilidades\" :  " + jsona
											+ " , \"Arquivos\" : " + jsonc + " }");

									autvarx = autvar + "\n";

									autvar += ",\n";

								}

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

				if (i == 0) {
					commiFinal += "\"Commits\" : {\n ";
				}

				if (i + 1 == commits.size()) {
					aux = json;
					autcom = autcomx;
					autvar = autvarx;
					commiFinal += "\"" + commits.get(i) + "\" : { \n \"Arquivos\" : {" + aux + "}, \"Variabilidades\" : { \n "
							+ autcom + " }, \"Autores\" : {" + autvar + "}}";
				} else {
					aux = json;
					autcom = autcomx;
					autvar = autvarx;
					commiFinal += "\"" + commits.get(i) + "\" : { \n \"Arquivos\" : {" + aux + "}, \"Variabilidades\" : { \n "
							+ autcom + " }, \"Autores\" : {" + autvar + "}},";
				}

				aux = "";
				autcom = "";
				autvar = "";
				commiVars.clear();
				commiAuth.clear();
				commiFiles.clear();

			}
			commiFinal += "}";
			dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			now = LocalDateTime.now();
			System.out.println("fim  " + dtf.format(now));

			
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

		String resultado = "{ \""+ repo[0] +"\" : {" + commiFinal + "}}";

		try (FileWriter writer = new FileWriter("C:\\Users\\Vini\\Desktop\\COMMIT\\"+ repo[0] + "json.txt");
				BufferedWriter bw = new BufferedWriter(writer)) {

			bw.write(resultado);

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		System.out.println("Foi");
		
		
	}

}
