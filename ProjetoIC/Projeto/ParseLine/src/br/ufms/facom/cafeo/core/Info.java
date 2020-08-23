package br.ufms.facom.cafeo.core;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;

import br.ufms.facom.cafeo.git.Commit;
import br.ufms.facom.cafeo.git.Repo;
import br.ufms.facom.cafeo.utils.ManipulationUtils;

public class Info extends tabela {

	static File TEMP;
	static int TEMP2;
	static ArrayList<String> hash = new ArrayList<String>();
	static ArrayList<String> oId = new ArrayList<String>();
	static ArrayList<String> hId = new ArrayList<String>();
	static ArrayList<String> relatorio = new ArrayList<String>();

	static String saida;

	static ArrayList<Repo> listofRepos = new ArrayList<Repo>();

	static String repoList = "";
	private static DiffFormatter df;

	public static void main(String[] args) throws IOException, GitAPIException {

		if (args.length == 0)
			repoList = "repos.txt";
		else {
			repoList = args[0];
		}
		loadRepos(ManipulationUtils.loadRepos(repoList));

		generateVariabilities();

	}

	private static void generateVariabilities() throws IOException, GitAPIException {
		
		 File diretorio = new File("C:\\repoaux\\" + listofRepos.get(0).getName());
         diretorio.mkdir();
		
		// --------------------------------- AUTOR/TIME -------------------------------
		final long comeco = System.currentTimeMillis();
		if (!listofRepos.isEmpty()) {

			for (Repo r : listofRepos) {

				System.out.println("Começando...");

				if (!r.getCommitList().isEmpty()) {

					for (Commit c : r.getCommitList()) {

						hash.add(c.toString());
					}
				}
			}

			try (FileWriter writer = new FileWriter(
					"C:\\repoaux\\" + listofRepos.get(0).getName() + "\\ttaa.csv");
					BufferedWriter bw = new BufferedWriter(writer)) {

				bw.write(hash.toString().replace(",", " ").replace("-", ", "));

			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}

			// ------------------------------------- DIFF -------------------------------
			int difer = hash.size();

			try (FileWriter escrever = new FileWriter("C:\\repoaux\\" + listofRepos.get(0).getName() + "\\hVar.txt");
					BufferedWriter es = new BufferedWriter(escrever)) {

				es.write(hash.toString());

			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}

			ArrayList<String> auth = new ArrayList<String>();
			ArrayList<String> rel = new ArrayList<String>();
			ArrayList<String> oIdc = new ArrayList<String>();
			ArrayList<String> hIdc = new ArrayList<String>();

			System.out.println(difer);

			File gitWorkDir = new File("C:\\repo\\" + listofRepos.get(0).getName() );
			Git git = Git.open(gitWorkDir);

			String old = "HEAD^^";
			String head = "HEAD^";
			String last = null;

			ObjectId idOld = git.getRepository().resolve(old);
			ObjectId idHead = git.getRepository().resolve(head);

			while (idHead != null) {
				idOld = git.getRepository().resolve(old);
				idHead = git.getRepository().resolve(head);

				ObjectId oldId = git.getRepository().resolve(old + "{tree}");
				ObjectId headId = git.getRepository().resolve(head + "{tree}");
				ObjectReader reader = git.getRepository().newObjectReader();
				CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();

				if (git.getRepository().resolve(head) == null) {
					break;
				}

				if (git.getRepository().resolve(old) == null) {
					
					idOld = idHead;
				}
				oldTreeIter.reset(reader, oldId);
				CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
				newTreeIter.reset(reader, headId);
				List<DiffEntry> diffs = git.diff().setNewTree(newTreeIter).setOldTree(oldTreeIter).call();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				df = new DiffFormatter(out);
				df.setRepository(git.getRepository());

				for (DiffEntry diff : diffs) {
					String file = diff.getNewPath();

					String str = idOld.toString();

					removePre(str, '[', 1);
					if (oId.isEmpty()) {
						oId.add(saida);
						str = idHead.toString();
						removePre(str, '[', 1);
						hId.add(saida);
					} else {
						String x = saida;
						String y = oId.get(oId.size() - 1);
						if ((x.contains(y))) {

						} else {
							oId.add(saida);
							last = saida;
							str = idHead.toString();
							removePre(str, '[', 1);
							hId.add(saida);
							
						}
					}

					if (file.endsWith(".c") || file.endsWith(".h") || file.endsWith(".cpp")) {
						final long start = System.currentTimeMillis();
						df.setContext(0);
						df.format(diff);
						diff.getOldId();

						if (oId.size() >= 2) {
							oIdc.add(oId.get(oId.size() - 2));
							hIdc.add(hId.get(hId.size() - 2));
						} else if (oId.size() == 1) {
							oIdc.add(oId.get(oId.size() - 1));
							hIdc.add(hId.get(hId.size() - 1));
						} else {
							oIdc.add(oId.get(oId.size()));
							hIdc.add(hId.get(hId.size()));
						}

						final long finish = System.currentTimeMillis();
						relatorio.add(hId.get(hId.size() - 1) + " e " + oId.get(oId.size() - 1) + ": "
								+ ((finish - start) / 1000) + " segundos" + "\n");
					}
				}
				rel.add(out.toString());
				
				old += "^";
				head += "^";

				if (oldId == null) {
					break;
				}

			}
			//System.out.println(Teste.diffCommit(last, listofRepos.get(0).getName()).toString());
			rel.add(Teste.diffCommit(last, listofRepos.get(0).getName()).toString());

			for (String s : hash) {
				for (int l = 0; l < hIdc.size(); l++) {

					if (s.contains(hIdc.get(l))) {
						auth.add(s);
					}
				}
				if(s.contains(last)) {
					for(int v = 0; v < PegaArquivo.fileName.size(); v++) {
						auth.add(s);
						hIdc.add(last);
					}	
				}
			}
//			System.out.println(auth);
//			System.out.println(hIdc);

			Collections.reverse(auth);

			try (FileWriter writer = new FileWriter(
					"C:\\repoaux\\" + listofRepos.get(0).getName() + "\\analise.txt");
					BufferedWriter bw = new BufferedWriter(writer)) {

				bw.write(rel.toString());

			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}

			getTabela(listofRepos.get(0).getName(), auth, oIdc, hIdc);

			final long fim = System.currentTimeMillis();
			float total = (fim - comeco);
			System.out.println(total);

			Date date = new Date();

			long time = date.getTime();
			Timestamp ts = new Timestamp(time);
			System.out.println("Current Time Stamp: " + ts);

			try (FileWriter escrever = new FileWriter(
					"C:\\repoaux\\" + listofRepos.get(0).getName() + "\\relatorio.txt");
					BufferedWriter es = new BufferedWriter(escrever)) {

				es.write(relatorio.toString());
				es.write("Tempo Total: " + ((fim - comeco) / 3600) + " horas");

			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}
		}
	}

	private static void loadRepos(ArrayList<String> repos) {
		for (String repoURI : repos)
			listofRepos.add(new Repo(repoURI));
	}

	static void removePre(String str, char ch, int count) {
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
			saida = str.substring(i + 1, str.length() - 1);
		}

		else
			System.out.println("String vazia");
	}
}