package br.ufrn.imd.edb2.trie;

import java.util.HashMap;

public class Trie {
    private TrieNode root = new TrieNode();

    public TrieNode getRoot() {
        return root;
    }

    //Inserção funcionando
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.getChildrens();

        for (int i = 0; i < word.length(); i++) {
            if (children.containsKey(word.charAt(i))) {
                children = children.get(word.charAt(i)).getChildrens();
            } else {
                children.put(word.charAt(i), new TrieNode());
                if (i < word.length() - 1) {
                    children = children.get(word.charAt(i)).getChildrens();
                } else {
                    children.get(word.charAt(i)).setWord(true);
                }
            }
        }
    }

    //Busca funcionando
    public boolean search(String word) {
        StringBuffer resultWord = new StringBuffer();
        String retorno = null;
        retorno = search(root, word, resultWord, 0);
        if(retorno == null){
            return false;
        }else{
            return true;
        }
    }

    private String search(TrieNode node, String word, StringBuffer resultWord, int posicao) {
        String retorno = null;
        if (node.getChildrens().isEmpty()) {
            return retorno;
        }
        HashMap<Character, TrieNode> children = node.getChildrens();
        char currentChar = word.charAt(posicao);

        if (children.containsKey(currentChar)) {
            resultWord.append(currentChar);
            if ((posicao == word.length() - 1)) {
                if (children.get(currentChar).isWord()) {
                    return resultWord.toString();
                } else {
                    return retorno;
                }
            } else {
                retorno = search(children.get(currentChar), word, resultWord, posicao + 1);
            }
        }

        return retorno;
    }

    //TODO fazer remoção e autocompletar
    //Remoção
    public boolean remove(String word) {
        HashMap<Character, TrieNode> parent = root.getChildrens();
        TrieNode currentNode;
        TrieNode lastPrefix = null;
        char currentChar;
        char keyDeleted = '\0';
        if (parent.isEmpty()) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            currentChar = word.charAt(i);

            if (parent.containsKey(currentChar)) {
                currentNode = parent.get(word.charAt(i));
                parent = currentNode.getChildrens();
            }else{
                return false;
            }


            if(i == word.length()-1){
                if(currentNode.isWord()){
                    if(!parent.isEmpty()){
                        currentNode.setWord(false);
                    }else if(lastPrefix == null){
                        root.getChildrens().remove(word.charAt(0));
                    }else{
                        lastPrefix.getChildrens().remove(keyDeleted);
                    }
                }else{
                    return false;
                }
            }else{
                if(parent.size()>1 || currentNode.isWord()){
                    lastPrefix = currentNode;
                    keyDeleted = word.charAt(i+1);
                }
            }
        }
        return true;
    }
}
