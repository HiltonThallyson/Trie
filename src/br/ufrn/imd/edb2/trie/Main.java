package br.ufrn.imd.edb2.trie;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	    Trie trie = new Trie();
	    trie.insert("Cat");
	    trie.insert("Cat");
	    trie.insert("Queijo");
	    trie.insert("Queijo Minas");
	    trie.insert("Catarro");
	    trie.insert("Cavalar");
	    trie.insert("Cavalaria");
	    trie.insert("Cavalo");
	    trie.insert("Cavalarei");

		ArrayList<String> words = new ArrayList<>();

		words = trie.autoComplete("Ca", 3);

		for (String str: words) {
			System.out.println(str);
		}
    }
}
