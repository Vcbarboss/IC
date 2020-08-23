package br.ufms.facom.cafeo.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;

import br.ufms.facom.cafeo.utils.ManipulationUtils;

public class Vector {
	
	static String code = "";
	static File s = new File("C:\\Users\\Vini\\Desktop\\semcomentario.txt");
	
	
	public static void main(String [] args) {
		
		
		try (FileWriter writer = new FileWriter(s); 
				BufferedWriter bw = new BufferedWriter(writer)) {

			bw.write(removeComments(code).toString());

		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
		
		
	}
	
	public static String removeComments(String code) {
	    StringBuilder newCode = new StringBuilder();
	    try (StringReader sr = new StringReader(code)) {
	        boolean inBlockComment = false;
	        boolean inLineComment = false;
	        boolean out = true;

	        int prev = sr.read();
	        int cur;
	        for(cur = sr.read(); cur != -1; cur = sr.read()) {
	            if(inBlockComment) {
	                if (prev == '*' && cur == '/') {
	                    inBlockComment = false;
	                    out = false;
	                }
	            } else if (inLineComment) {
	                if (cur == '\r') {
	                    sr.mark(1);
	                    int next = sr.read();
	                    if (next != '\n') {
	                        sr.reset();
	                    }
	                    inLineComment = false;
	                    out = false; 
	                } else if (cur == '\n') {
	                    inLineComment = false;
	                    out = false;
	                }
	            } else {
	                if (prev == '/' && cur == '*') {
	                    sr.mark(1); 
	                    int next = sr.read();
	                    if (next != '*') {
	                        inBlockComment = true; 
	                    }
	                    sr.reset(); 
	                } else if (prev == '/' && cur == '/') {
	                    inLineComment = true;
	                } else if (out){
	                    newCode.append((char)prev);
	                } else {
	                    out = true;
	                }
	            }
	            prev = cur;
	        }
	        if (prev != -1 && out && !inLineComment) {
	            newCode.append((char)prev);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    return newCode.toString();
	}
}
