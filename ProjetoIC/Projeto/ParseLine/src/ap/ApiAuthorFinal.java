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

public class ApiAuthorFinal {

	public static void main(String[] args) {

		String csvPath = "C:\\Users\\Vini\\Desktop\\100%\\MPSolveTabela.csv";
		String rvPath = "C:\\Users\\Vini\\Desktop\\reverso.txt";
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
		String aux2 = "";
		String dateFirst = "";
		String dateLast = "";
		int x = 0;
		int y = 0;

		try {

			br = new BufferedReader(new FileReader(csvPath));
			while ((line = br.readLine()) != null) {
				
				revert.add(line);
				
				
				revert.add(line + "\n");

				String[] coluna = line.split(cvsSplitBy);

				if (authors.contains(coluna[6])) {

				} else {
					authors.add(coluna[6]);
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
			
			System.out.println(authors.size());
			
			Collections.reverse(revert);
			
			String rever = revert.toString().replace("[" , "");
			String rv = rever.replace(", ", "");
			try (FileWriter writer = new FileWriter(rvPath);
					BufferedWriter bw = new BufferedWriter(writer)) {

				bw.write(rv.toString());

			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}

			for (int i = 0; i < authors.size(); i++) {

				System.out.println(i + " / " + authors.size());

				try {

					br = new BufferedReader(new FileReader(csvPath));
					while ((line = br.readLine()) != null) {
						
						System.out.println("y" + y);
						x=0;
						y++;

						String[] coluna = line.split(cvsSplitBy);

						if (line.contains(authors.get(i))) {

							if (!(authFiles.contains(coluna[0]))) {
								authFiles.add(coluna[0]);

								try {

									tr = new BufferedReader(new FileReader(csvPath));
									while ((lin = tr.readLine()) != null) {

										String[] col = lin.split(cvsSplitBy);

										if (!(col[3].contains("NULL") || col[3].contains("EMPTY LINE"))) {

											if (lin.contains(authors.get(i)) && lin.contains(coluna[0])) {

												if (!(authors_arqs_vars.contains(col[3]))) {
													dateLast = col[9];
													
													System.gc();
													try {

														pr = new BufferedReader(new FileReader(csvPath));
														while ((linein = pr.readLine()) != null) {
															
															
															String[] colu = linein.split(cvsSplitBy);
															
															if(linein.contains(col[3])) {
																dateFirst = colu[9];
															}

														}
														
													} catch (FileNotFoundException e) {
														e.printStackTrace();
													} catch (IOException e) {
														e.printStackTrace();
													}
													
													authors_arqs_vars.add(
															col[3] + "   -   (" + dateFirst + " - " + dateLast + ")");
												}
												
												if (!(authors_arqs_commi.contains(col[4]))) {
													authors_arqs_commi.add(col[4] + "   -   (" + col[9] + ")");
												}
											}

										}

									}

								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}

								String jsonFiles = new Gson().toJson(authFiles);
								String jsonv = new Gson().toJson(authors_arqs_vars);
								String jsonc = new Gson().toJson(authors_arqs_commi);

								authors_arqs_vars.clear();
								authors_arqs_commi.clear();

								aux += ("\n\"" + coluna[0] + "\" : { \n\"Variabilidades\" :  " + jsonv
										+ " , \"Commits\" : " + jsonc + " }");

								json = aux + "\n";

								aux += ",\n";

							}

							if (!(authCommits.contains(coluna[4]))) {
								authCommits.add(coluna[4]);

								try {

									tr = new BufferedReader(new FileReader(csvPath));
									while ((lin = tr.readLine()) != null) {

										String[] col = lin.split(cvsSplitBy);

										if (!(col[3].contains("NULL") || col[3].contains("EMPTY LINE"))) {

											if (lin.contains(authors.get(i)) && lin.contains(coluna[4])) {
												if (!(authors_commi_vars.contains(col[3]))) {
													dateLast = col[9];
													
													System.gc();
													try {

														pr = new BufferedReader(new FileReader(csvPath));
														while ((linein = pr.readLine()) != null) {
															
															
															String[] colu = linein.split(cvsSplitBy);
															
															if(linein.contains(col[3])) {
																dateFirst = colu[9];
																break;
															}

														}
														
													} catch (FileNotFoundException e) {
														e.printStackTrace();
													} catch (IOException e) {
														e.printStackTrace();
													}
													
													authors_commi_vars.add(col[3] + "   -   (" + dateFirst + " - " + dateLast + ")");
												}
												if (!(authors_commi_arqs.contains(col[0]))) {
													
													dateLast = col[9];
													
													System.gc();
													try {

														pr = new BufferedReader(new FileReader(csvPath));
														while ((linein = pr.readLine()) != null) {
															
															
															String[] colu = linein.split(cvsSplitBy);
															
															if(linein.contains(col[0])) {
																dateFirst = colu[9];
															}

														}
														
													} catch (FileNotFoundException e) {
														e.printStackTrace();
													} catch (IOException e) {
														e.printStackTrace();
													}
													
													authors_commi_arqs.add(col[0] + "   -   (" + dateFirst + " - " + dateLast + ")");
												}
											}

										}

									}

								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}

								String jsonFiles = new Gson().toJson(authFiles);
								String jsonv = new Gson().toJson(authors_commi_vars);
								String jsonc = new Gson().toJson(authors_commi_arqs);

								authors_commi_vars.clear();
								authors_commi_arqs.clear();

								autcom += ("\"" + coluna[4] + "\" : { \n\"Variabilidades\" :  " + jsonv
										+ " , \"Arquivos\" : " + jsonc + " }");

								autcomx = autcom + "\n";

								autcom += ",\n";
							}

							if (!(authVars.contains(coluna[3])) && line.contains(authors.get(i))) {
								authVars.add(coluna[3]);

								try {

									tr = new BufferedReader(new FileReader(csvPath));
									while ((lin = tr.readLine()) != null) {
										
										if(y >= 12) {
											System.out.println(x);
											x++;
										}

										String[] col = lin.split(cvsSplitBy);

										if (!(col[3].contains("NULL") || col[3].contains("EMPTY LINE"))) {

											if (lin.contains(authors.get(i)) && lin.contains(coluna[3])) {
												if (!(authors_vars_commi.contains(col[4]))) {
													authors_vars_commi.add(col[4] + "   -   (" + col[9] + ")");
												}
												if (!(authors_vars_arqs.contains(col[0]))) {
													
													dateLast = col[9];
													
													System.gc();
													try {

														pr = new BufferedReader(new FileReader(csvPath));
														while ((linein = pr.readLine()) != null) {
															
															
															String[] colu = linein.split(cvsSplitBy);
															
															if(linein.contains(col[0])) {
																dateFirst = colu[9];
															}

														}
														
													} catch (FileNotFoundException e) {
														e.printStackTrace();
													} catch (IOException e) {
														e.printStackTrace();
													}
													
													
													authors_vars_arqs.add(col[0] + "   -   (" + dateFirst + " - " + dateLast + ")");
												}
											}

										}

									}

								} catch (FileNotFoundException e) {
									e.printStackTrace();
								} catch (IOException e) {
									e.printStackTrace();
								}

								String jsonFiles = new Gson().toJson(authFiles);
								String jsona = new Gson().toJson(authors_vars_arqs);
								String jsonc = new Gson().toJson(authors_vars_commi);

								authors_vars_arqs.clear();
								authors_vars_commi.clear();

								autvar += ("\"" + coluna[3] + "\" : { \n\"Arquivos\" :  " + jsona + " , \"Commits\" : "
										+ jsonc + " }");

								autvarx = autvar + "\n";

								autvar += ",\n";

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
					aux2 += "{\"Autores\" : {\n ";
				}

				if (i + 1 == authors.size()) {
					aux = json;
					autcom = autcomx;
					autvar = autvarx;
					aux2 += "\"" + authors.get(i) + "\" : { \n \"Arquivos\" : {" + aux + "}, \"Commits\" : { \n "
							+ autcom + " }, \"Variabilidades\" : {" + autvar + "}}";
				} else {
					aux = json;
					autcom = autcomx;
					autvar = autvarx;
					aux2 += "\"" + authors.get(i) + "\" : { \n \"Arquivos\" : {" + aux + "}, \"Commits\" : { \n "
							+ autcom + " }, \"Variabilidades\" : {" + autvar + "}},";
				}

				aux = "";
				autcom = "";
				autvar = "";
				authVars.clear();
				authFiles.clear();
				authCommits.clear();

			}
			aux2 += "}}";
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

		try (FileWriter writer = new FileWriter("C:\\Users\\Vini\\Desktop\\json.txt");
				BufferedWriter bw = new BufferedWriter(writer)) {

			bw.write(aux2);

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		System.out.println("Foi");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));

	}

}
