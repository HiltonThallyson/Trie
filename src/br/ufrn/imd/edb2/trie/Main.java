package br.ufrn.imd.edb2.trie;

public class Main {

    public static void main(String[] args) {
	    Trie trie = new Trie();
	    trie.insert("Cat");
	    trie.insert("Cat");
	    trie.insert("Queijo");
	    trie.insert("Catarro");
	    trie.insert("Cavalo");
	    trie.remove("Cavalo");
    }
}
