package br.ufms.facom.cafeo.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;

public class Teste {

	private static Repository repo;
	static ArrayList<String> lastCommitLog = new ArrayList<String>();

	public static void main(String[] args) throws IOException, GitAPIException {
		diffCommit("44b3b248a102bba54235bed40fdffd214e8ecfd0", "MPSolve");
	}

	public static ArrayList<String> diffCommit(String hashID, String repoName) throws IOException, GitAPIException {
		
		PegaArquivo.getFile(hashID, repoName);
		
		for(int i = 0; i<PegaArquivo.fileName.size(); i++) {
			//System.out.println(readContentOfFileAtCommit(hashID, Test.fileName.get(i), repoName));
			lastCommitLog.add(readContentOfFileAtCommit(hashID, PegaArquivo.fileName.get(i), repoName));
		}
		return lastCommitLog;
	}

	public static String readContentOfFileAtCommit(String commitStr, String fileName, String repoName)
			throws IOException, GitAPIException {
		
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		repo = builder.setGitDir(new File("C:\\repo\\"+ repoName + "/.git")).setMustExist(true).build();
		new Git(repo);
		
		String content = null;
		ObjectId lastCommitId = repo.resolve(commitStr);

		try (RevWalk revWalk = new RevWalk(repo)) {
			RevCommit commit = revWalk.parseCommit(lastCommitId);
			RevTree tree = commit.getTree();

			try (TreeWalk treeWalk = new TreeWalk(repo)) {
				treeWalk.addTree(tree);
				treeWalk.setRecursive(true);
				treeWalk.setFilter(PathFilter.create(fileName));
				if (!treeWalk.next()) {
					throw new IllegalStateException("Did not find expected file:" + fileName);
				}

				ObjectId objectId = treeWalk.getObjectId(0);
				ObjectLoader loader = repo.open(objectId);
				content = new String(loader.getBytes());
			}

			revWalk.dispose();
		}

		String fileName1 = "C:\\repoaux\\primeiroCommit.txt";
		


		try (FileWriter writer = new FileWriter(fileName1);
				BufferedWriter bw = new BufferedWriter(writer)) {

			bw.write("\n" + content.toString() );

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		
		File file = new File(fileName1);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		ArrayList<String> escritaDeLinhas = new ArrayList<String>();

		try {
			while ((line = br.readLine()) != null) {
				escritaDeLinhas.add(("+" + line + "\n"));
			}

			String log = escritaDeLinhas.toString();
			try (FileWriter writer = new FileWriter("C:\\repoaux\\primeiroLog.txt");
					BufferedWriter bw = new BufferedWriter(writer)) {

				bw.write("diff --git a/" + fileName + " b/" + fileName + "\n");
				bw.write("@@ -0,0 +1," + (escritaDeLinhas.size()) + " @@\n");
				bw.write(log.replace("[*", "+").replace(", *", "+"));

			} catch (IOException e) {
				System.err.format("IOException: %s%n", e);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "diff --git a/" + fileName + " b/" + fileName + "\n@@ -0,0 +1," + (escritaDeLinhas.size()-1) + " @@\n" + escritaDeLinhas;
	}
}
