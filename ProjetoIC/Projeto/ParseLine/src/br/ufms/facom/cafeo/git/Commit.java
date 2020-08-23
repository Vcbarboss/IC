package br.ufms.facom.cafeo.git;

import java.util.ArrayList;
import java.util.Date;

public class Commit {
	
	String id;
	String author;
	String authorEmail;
	String committer;
	String committerEmail;
	Date timestamp;
	ArrayList<RepoFile> touchedFiles;
	

	public Commit(String commitId, String author, String authorEmail, String committer, String committerEmail, Date timestamp) {
		this.id = commitId;
		this.author = author;
		this.authorEmail = authorEmail;
		this.committer = committer;
		this.committerEmail = committerEmail;
		this.timestamp = timestamp;
		
		this.touchedFiles = new ArrayList<RepoFile>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author + "; " + authorEmail;
	}
	
	public String getCommitter() {
		return committer + "; " + committerEmail;
	}

	public void setAuthor(String author, String email) {
		this.author = author;
		this.authorEmail = email;
	}
	
	public void setCommitter(String committer, String email) {
		this.committer = committer;
		this.committerEmail = email;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void associateFile(RepoFile file){
		touchedFiles.add(file);
	}
	
	public ArrayList<RepoFile> getTouchedFiles(){
		return touchedFiles;
	}
	
	public String toString(){
		return getId() + "; " + getAuthor() + "; " + getCommitter() + "; "+ getTimestamp().toString();
	}
	
}
