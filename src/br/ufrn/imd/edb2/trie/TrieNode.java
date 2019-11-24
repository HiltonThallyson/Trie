package br.ufrn.imd.edb2.trie;

import java.util.HashMap;

public class TrieNode {
    private HashMap<Character, TrieNode> childrens = new HashMap<>();
    boolean isWord = false;

    public HashMap<Character, TrieNode> getChildrens() {
        return childrens;
    }

    public void setChildrens(HashMap<Character, TrieNode> childrens) {
        this.childrens = childrens;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }
}
