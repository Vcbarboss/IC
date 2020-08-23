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
import java.util.List;
 
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
 
public class Diff 
{
  public static void main(String[] args) throws Exception
  {
    File gitWorkDir = new File("C:\\repo\\MPSolve");
    Git git = Git.open(gitWorkDir);
    
    String old = "HEAD^^";
 
    for(int i = 0;i < 10;i++) {
    ObjectId oldId = git.getRepository().resolve(old + "{tree}");
    ObjectId headId = git.getRepository().resolve("HEAD^{tree}");
 
    ObjectReader reader = git.getRepository().newObjectReader();
     
    CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
    oldTreeIter.reset(reader, oldId);
    CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
    newTreeIter.reset(reader, headId);
 
    List<DiffEntry> diffs= git.diff()
            .setNewTree(newTreeIter)
            .setOldTree(oldTreeIter)
            .call();
     
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    DiffFormatter df = new DiffFormatter(out);
    df.setRepository(git.getRepository());
    
 
    for(DiffEntry diff : diffs)
    {
      df.setContext(0);
      df.format(diff);
      diff.getOldId();
      String diffText = out.toString("UTF-8");
      
//      System.out.println(oldHash);
//      System.out.println(newHash);
      System.out.println(diffText);
      
    }
    try (FileWriter writer = new FileWriter("C:\\repoaux\\analise.txt");
			 BufferedWriter bw = new BufferedWriter(writer)) {
   	  	
   	  	
			bw.write(out.toString("UTF-8"));

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
    old += "^";
    }
  }
}