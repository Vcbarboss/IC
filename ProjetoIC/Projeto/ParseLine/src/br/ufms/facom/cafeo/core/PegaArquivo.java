package br.ufms.facom.cafeo.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathSuffixFilter;

public class PegaArquivo {
	
	static ArrayList<String> fileName = new ArrayList<String>();
	private static TreeWalk treeWalk;
	private static RevWalk revWalk;

	public static void main(String[] args) throws IOException, GitAPIException {
		getFile("44b3b248a102bba54235bed40fdffd214e8ecfd0", "MPSolve");

	}

	static ArrayList<String> getFile(String hashID, String repoName) throws IOException, GitAPIException {
		String[] sufix = {".c", ".h", ".cpp"};
		File gitDir = new File("C:/repo/" + repoName);
		Git git = Git.open(gitDir);
		Repository repo = git.getRepository();

		for (int i = 0; i < 3; i++) {

			ObjectId lastCommitId = repo.resolve(hashID);
			revWalk = new RevWalk(repo);
			RevCommit commit = revWalk.parseCommit(lastCommitId);
			RevTree tree = commit.getTree();
			treeWalk = new TreeWalk(repo);

			treeWalk.addTree(tree);
			treeWalk.setRecursive(true);
			treeWalk.setFilter(PathSuffixFilter.create(sufix[i]));
			if (!treeWalk.next()) {
				
			}else {
			//System.out.println(treeWalk.getPathString());
			fileName.add(treeWalk.getPathString());
			}
			while (treeWalk.next()) {
				treeWalk.setFilter(PathSuffixFilter.create(sufix[i]));
				if(treeWalk.getNameString().endsWith(sufix[i]))
				//System.out.println(treeWalk.getPathString());
				fileName.add(treeWalk.getPathString());

			}
			
		}
		return fileName;
	}
}
