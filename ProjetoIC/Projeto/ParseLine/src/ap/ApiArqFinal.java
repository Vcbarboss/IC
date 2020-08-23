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

public class ApiArqFinal {

	public static void main(String[] args) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String csvPath = "C:\\Users\\Vini\\Desktop\\100UTC\\LibsshTabela.csv";
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


			for (int i = 0; i < arqs.size(); i++) {

				System.out.print(i + " / " + arqs.size() + "  -  ");
				dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				now = LocalDateTime.now();
				System.out.println(dtf.format(now));

				try {

					br = new BufferedReader(new FileReader(csvPath));
					while ((line = br.readLine()) != null) {

						String[] coluna = line.split(cvsSplitBy);

						if (!(line.contains("NULL") || line.contains("EMPTY LINE"))) {

							if (line.contains(arqs.get(i))) {

								if (!(arqAuth.contains(coluna[5]))) {
									arqAuth.add(coluna[5]);

									try {

										tr = new BufferedReader(new FileReader(csvPath));
										while ((lin = tr.readLine()) != null) {

											String[] col = lin.split(cvsSplitBy);

											if (!(col[3].contains("NULL") || col[3].contains("EMPTY LINE"))) {

												if (lin.contains(arqs.get(i)) && col[5].contains(coluna[5])) {

													if (!(arq_authors_varsx.contains(col[3]))) {
														dateLast = col[9];

														System.gc();
														try {

															pr = new BufferedReader(new FileReader(rvPath));
															while ((linein = pr.readLine()) != null) {

																String[] colu = linein.split(cvsSplitBy);
																
						

																if (linein.contains(col[3])) {
																	//System.out.println(linein);
																	dateFirst = colu[9];
																	break;
																}

															}

														} catch (FileNotFoundException e) {
															e.printStackTrace();
														} catch (IOException e) {
															e.printStackTrace();
														}
														arq_authors_varsx.add(col[3]);
														arq_authors_vars.add(col[3] + "   -   (" + dateFirst + " - "
																+ dateLast + ")");
													}

													if (!(arq_authors_commix.contains(col[4]))) {
														arq_authors_commix.add(col[4]);
														arq_authors_commi.add(col[4] + "   -   (" + col[9] + ")");
													}
												}

											}

										}

									} catch (FileNotFoundException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}

									//String jsonFiles = new Gson().toJson(authFiles);
									String jsonv = new Gson().toJson(arq_authors_vars);
									String jsonc = new Gson().toJson(arq_authors_commi);

									arq_authors_vars.clear();
									arq_authors_varsx.clear();
									arq_authors_commi.clear();
									arq_authors_commix.clear();

									aux += ("\n\"" + coluna[5] + "\" : { \n\"Variabilidades\" :  " + jsonv
											+ " , \"Commits\" : " + jsonc + " }");

									json = aux + "\n";

									aux += ",\n";

								}

								if (!(arqCommits.contains(coluna[4]))) {
									arqCommits.add(coluna[4]);

									try {

										tr = new BufferedReader(new FileReader(csvPath));
										while ((lin = tr.readLine()) != null) {

											String[] col = lin.split(cvsSplitBy);

											if (!(col[3].contains("NULL") || col[3].contains("EMPTY LINE"))) {

												if (lin.contains(arqs.get(i)) && lin.contains(coluna[4])) {
													if (!(arq_commi_varsx.contains(col[3]))) {
														dateLast = col[9];

														System.gc();
														try {

															pr = new BufferedReader(new FileReader(rvPath));
															while ((linein = pr.readLine()) != null) {

																String[] colu = linein.split(cvsSplitBy);

																if (linein.contains(col[3])) {
																	dateFirst = colu[9];
																	break;
																}

															}

														} catch (FileNotFoundException e) {
															e.printStackTrace();
														} catch (IOException e) {
															e.printStackTrace();
														}
														arq_commi_varsx.add(col[3]);
														arq_commi_vars.add(col[3] + "   -   (" + dateFirst + " - "
																+ dateLast + ")");
													}
													if (!(arq_commi_authorsx.contains(col[5]))) {

														dateLast = col[9];

														System.gc();
														try {

															pr = new BufferedReader(new FileReader(rvPath));
															while ((linein = pr.readLine()) != null) {

																String[] colu = linein.split(cvsSplitBy);

																if (colu[5].contains(col[5])) {
																	dateFirst = colu[9];
																	break;
																}

															}

														} catch (FileNotFoundException e) {
															e.printStackTrace();
														} catch (IOException e) {
															e.printStackTrace();
														}
														arq_commi_authorsx.add(col[5]);
														arq_commi_authors.add(col[5] + "   -   (" + dateFirst + " - "
																+ dateLast + ")");
													}
												}

											}

										}

									} catch (FileNotFoundException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}

									//String jsonFiles = new Gson().toJson(authFiles);
									String jsonv = new Gson().toJson(arq_commi_vars);
									String jsonc = new Gson().toJson(arq_commi_authors);

									arq_commi_vars.clear();
									arq_commi_varsx.clear();
									arq_commi_authors.clear();
									arq_commi_authorsx.clear();

									autcom += ("\"" + coluna[4] + "\" : { \n\"Variabilidades\" :  " + jsonv
											+ " , \"Autores\" : " + jsonc + " }");

									autcomx = autcom + "\n";

									autcom += ",\n";
								}

								if (!(arqVars.contains(coluna[3]))) {
									arqVars.add(coluna[3]);

									try {

										tr = new BufferedReader(new FileReader(csvPath));
										while ((lin = tr.readLine()) != null) {

											String[] col = lin.split(cvsSplitBy);

											if (!(col[3].contains("NULL") || col[3].contains("EMPTY LINE"))) {

												if (lin.contains(arqs.get(i)) && lin.contains(coluna[3])) {
													if (!(arq_vars_commix.contains(col[4]))) {
														arq_vars_commix.add(col[4]);
														arq_vars_commi.add(col[4]);
													}
													if (!(arq_vars_authorsx.contains(col[5]))) {
														dateLast = col[9];

														System.gc();
														try {

															pr = new BufferedReader(new FileReader(rvPath));
															while ((linein = pr.readLine()) != null) {

																String[] colu = linein.split(cvsSplitBy);

																if (colu[5].contains(col[5])) {
																	dateFirst = colu[9];
																	break;
																}

															}

														} catch (FileNotFoundException e) {
															e.printStackTrace();
														} catch (IOException e) {
															e.printStackTrace();
														}

														arq_vars_authorsx.add(col[5]);
														arq_vars_authors.add(col[5] + "   -   (" + dateFirst + " - "
																+ dateLast + ")");
													}
												}

											}

										}

									} catch (FileNotFoundException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}

									//String jsonFiles = new Gson().toJson(authFiles);
									String jsona = new Gson().toJson(arq_vars_authors);
									String jsonc = new Gson().toJson(arq_vars_commi);

									arq_vars_authors.clear();
									arq_vars_authorsx.clear();
									arq_vars_commi.clear();
									arq_vars_commix.clear();

									autvar += ("\"" + coluna[3] + "\" : { \n\"Autores\" :  " + jsona
											+ " , \"Commits\" : " + jsonc + " }");

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
					arqFinal += "\"Arquivos\" : {\n ";
				}

				if (i + 1 == arqs.size()) {
					aux = json;
					autcom = autcomx;
					autvar = autvarx;
					arqFinal += "\"" + arqs.get(i) + "\" : { \n \"Autor\" : {" + aux + "}, \"Commits\" : { \n "
							+ autcom + " }, \"Variabilidades\" : {" + autvar + "}}";
				} else {
					aux = json;
					autcom = autcomx;
					autvar = autvarx;
					arqFinal += "\"" + arqs.get(i) + "\" : { \n \"Autor\" : {" + aux + "}, \"Commits\" : { \n "
							+ autcom + " }, \"Variabilidades\" : {" + autvar + "}},";
				}

				aux = "";
				autcom = "";
				autvar = "";
				arqVars.clear();
				arqAuth.clear();
				arqCommits.clear();

			}
			arqFinal += "}";
			
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

		try (FileWriter writer = new FileWriter("C:\\Users\\Vini\\Desktop\\" + repo[0] + "jsonARQUIVO.txt");
				BufferedWriter bw = new BufferedWriter(writer)) {

			bw.write(arqFinal);

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		System.out.println("Foi");

		dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		now = LocalDateTime.now();
		System.out.println(dtf.format(now));

	}

}
