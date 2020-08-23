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

public class testgeral {

	public static void main(String[] args) {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String csvPath = "C:\\Users\\Vini\\Desktop\\100UTC\\HexchatTabela.csv";
		String rvPath = "C:\\Users\\Vini\\Desktop\\reverso.txt";
		String sp = csvPath.replace("\\", "/");
		String[] rep = sp.split("/");
		String[] repo = rep[5].split("Tabela");
		ArrayList<String> authors = new ArrayList<String>();
		ArrayList<String> vars = new ArrayList<String>();
		ArrayList<String> arqs = new ArrayList<String>();
		ArrayList<String> commits = new ArrayList<String>();
		ArrayList<String> authors_arqs_vars = new ArrayList<String>();
		ArrayList<String> authors_arqs_commi = new ArrayList<String>();
		ArrayList<String> authors_commi_arqs = new ArrayList<String>();
		ArrayList<String> authors_commi_vars = new ArrayList<String>();
		ArrayList<String> authors_vars_arqs = new ArrayList<String>();
		ArrayList<String> authors_vars_commi = new ArrayList<String>();
		ArrayList<String> vars_commi_authors = new ArrayList<String>();
		ArrayList<String> vars_commi_arqs = new ArrayList<String>();
		ArrayList<String> vars_authors_commi = new ArrayList<String>();
		ArrayList<String> vars_authors_arqs = new ArrayList<String>();
		ArrayList<String> vars_arqs_commi = new ArrayList<String>();
		ArrayList<String> vars_arqs_authors = new ArrayList<String>();
		ArrayList<String> vars_commi_authorsx = new ArrayList<String>();
		ArrayList<String> vars_commi_arqsx = new ArrayList<String>();
		ArrayList<String> vars_authors_commix = new ArrayList<String>();
		ArrayList<String> vars_authors_arqsx = new ArrayList<String>();
		ArrayList<String> vars_arqs_commix = new ArrayList<String>();
		ArrayList<String> vars_arqs_authorsx = new ArrayList<String>();
		ArrayList<String> authFiles = new ArrayList<String>();
		ArrayList<String> authCommits = new ArrayList<String>();
		ArrayList<String> authVars = new ArrayList<String>();
		ArrayList<String> varCommits = new ArrayList<String>();
		ArrayList<String> varAuth = new ArrayList<String>();
		ArrayList<String> varFiles = new ArrayList<String>();
		ArrayList<String> arqCommits = new ArrayList<String>();
		ArrayList<String> arqAuth = new ArrayList<String>();
		ArrayList<String> arqVars = new ArrayList<String>();
		ArrayList<String> revert = new ArrayList<String>();

		BufferedReader br = null;
		BufferedReader tr = null;
		BufferedReader pr = null;
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
		String varsFinal = "";
		String dateFirst = "";
		String dateLast = "";
		int x = 0;

		try {

			br = new BufferedReader(new FileReader(csvPath));
			while ((line = br.readLine()) != null) {
				
				revert.add(line + "\n");

				String[] coluna = line.split(cvsSplitBy);

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

			}
			
			Collections.reverse(revert);
			
			
			String rever = revert.toString().replace("[" , "");
			String rv = rever.replace(", ", "");
			try (FileWriter writer = new FileWriter(rvPath);
					BufferedWriter bw = new BufferedWriter(writer)) {

				bw.write(rever.toString());

			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}

			for (int i = 0; i < vars.size(); i++) {

				System.out.print(i + " / " + vars.size() + "  -  ");
				dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				now = LocalDateTime.now();
				System.out.println(dtf.format(now));

				try {

					br = new BufferedReader(new FileReader(csvPath));
					while ((line = br.readLine()) != null) {

						String[] coluna = line.split(cvsSplitBy);

						if (!(coluna[3].equals("NULL") || coluna[3].equals("EMPTY LINE"))) {

							if (coluna[3].contains(vars.get(i))) {

								if (!(varFiles.contains(coluna[0]))) {
									varFiles.add(coluna[0]);

									try {

										tr = new BufferedReader(new FileReader(csvPath));
										while ((lin = tr.readLine()) != null) {

											String[] col = lin.split(cvsSplitBy);

											if (col[3].equals(vars.get(i)) && col[0].equals(coluna[0])) {

												if (!(vars_arqs_authorsx.contains(col[5]))) {
													
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
													
													vars_arqs_authorsx.add(col[5]);
													vars_arqs_authors.add(col[5] + "   -   (" + dateFirst + " - " + dateLast + ")");
												}
												if (!(vars_arqs_commix.contains(col[4]))) {
													
													vars_arqs_commix.add(col[4]);
													vars_arqs_commi.add(col[4] + "   -   (" + col[9] + ")");
												}
											}

										}

									} catch (FileNotFoundException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}

									String jsonFiles = new Gson().toJson(authFiles);
									String jsona = new Gson().toJson(vars_arqs_authors);
									String jsonc = new Gson().toJson(vars_arqs_commi);

									vars_arqs_authors.clear();
									vars_arqs_authorsx.clear();
									vars_arqs_commi.clear();
									vars_arqs_commix.clear();

									aux += ("\n\"" + coluna[0] + "\" : { \n\"Autores\" :  " + jsona
											+ " , \"Commits\" : " + jsonc + " }");

									json = aux + "\n";

									aux += ",\n";

								}

								if (!(varCommits.contains(coluna[4]))) {
									varCommits.add(coluna[4]);

									try {

										tr = new BufferedReader(new FileReader(csvPath));
										while ((lin = tr.readLine()) != null) {

											String[] col = lin.split(cvsSplitBy);

											if (col[3].equals(vars.get(i)) && col[4].equals(coluna[4])) {
												if (!(vars_commi_authorsx.contains(col[5]))) {
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
													
													vars_commi_authorsx.add(col[5]);
													vars_commi_authors.add(col[5] + "   -   (" + dateFirst + " - " + dateLast + ")");
												}
												if (!(vars_commi_arqsx.contains(col[0]))) {
													
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
													
													vars_commi_arqsx.add(col[0]);
													vars_commi_arqs.add(col[0] + "   -   (" + dateFirst + " - " + dateLast + ")");
												}
											}

										}

									} catch (FileNotFoundException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}

									String jsonFiles = new Gson().toJson(authFiles);
									String jsona = new Gson().toJson(vars_commi_authors);
									String jsonc = new Gson().toJson(vars_commi_arqs);

									vars_commi_authors.clear();
									vars_commi_authorsx.clear();
									vars_commi_arqs.clear();
									vars_commi_arqsx.clear();

									autcom += ("\"" + coluna[4] + "\" : { \n\"Autores\" :  " + jsona
											+ " , \"Arquivos\" : " + jsonc + " }");

									autcomx = autcom + "\n";

									autcom += ",\n";
								}

								if (!(varAuth.contains(coluna[5]))) {
									varAuth.add(coluna[5]);

									try {

										tr = new BufferedReader(new FileReader(csvPath));
										while ((lin = tr.readLine()) != null) {

											String[] col = lin.split(cvsSplitBy);

											if (col[3].equals(vars.get(i)) && col[5].equals(coluna[5])) {
												if (!(vars_authors_commix.contains(col[4]))) {
													
													vars_authors_commix.add(col[4]);
													vars_authors_commi.add(col[4] + "   -   (" + col[9] + ")");
												}
												if (!(vars_authors_arqsx.contains(col[0]))) {
													
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
													
													vars_authors_arqsx.add(col[0]);
													vars_authors_arqs.add(col[0] + "   -   (" + dateFirst + " - " + dateLast + ")");
												}
											}

										}

									} catch (FileNotFoundException e) {
										e.printStackTrace();
									} catch (IOException e) {
										e.printStackTrace();
									}

									String jsonFiles = new Gson().toJson(authFiles);
									String jsona = new Gson().toJson(vars_authors_arqs);
									String jsonc = new Gson().toJson(vars_authors_commi);

									vars_authors_arqs.clear();
									vars_authors_arqsx.clear();
									vars_authors_commi.clear();
									vars_authors_commix.clear();

									autvar += ("\"" + coluna[5] + "\" : { \n\"Arquivos\" :  " + jsona
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
					varsFinal += "\"Variabilidades\" : {\n ";
				}

				if (i + 1 == vars.size()) {
					aux = json;
					autcom = autcomx;
					autvar = autvarx;
					varsFinal += "\"" + vars.get(i) + "\" : { \n \"Arquivos\" : {" + aux + "}, \"Commits\" : { \n "
							+ autcom + " }, \"Autores\" : {" + autvar + "}}";
				} else {
					aux = json;
					autcom = autcomx;
					autvar = autvarx;
					varsFinal += "\"" + vars.get(i) + "\" : { \n \"Arquivos\" : {" + aux + "}, \"Commits\" : { \n "
							+ autcom + " }, \"Autores\" : {" + autvar + "}},";
				}

				aux = "";
				autcom = "";
				autvar = "";
				varCommits.clear();
				varAuth.clear();
				varFiles.clear();

			}
			varsFinal += "}";

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

		try (FileWriter writer = new FileWriter("C:\\Users\\Vini\\Desktop\\var\\"+ repo[0] +"Var.txt");
				BufferedWriter bw = new BufferedWriter(writer)) {

			bw.write(varsFinal);

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		System.out.println("Foi");

		dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		now = LocalDateTime.now();
		System.out.println(dtf.format(now));

	}

}
