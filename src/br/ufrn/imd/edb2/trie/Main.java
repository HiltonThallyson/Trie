package br.ufrn.imd.edb2.trie;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
    	File file = new File(args[0]);
    	String prefix = args[1];
    	int limit = -1;
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String str;
		Trie trie = new Trie();
		ArrayList<String> words;

		while((str = br.readLine()) != null){
			trie.insert(str);
		}
		if(args.length == 3){
			limit = Integer.parseInt(args[2]);
			words = trie.autoComplete(prefix, limit);
		}else{
			words = trie.autoComplete(prefix);
		}

		if(words == null){
			System.out.println("Nenhuma palavra encontrada!");
		}else{
			for (String string: words) {
				System.out.println(string);
			}
		}
    }
}
