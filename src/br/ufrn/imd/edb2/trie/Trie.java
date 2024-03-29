package br.ufrn.imd.edb2.trie;

import java.util.*;

public class Trie {
    private TrieNode root = new TrieNode();

    public TrieNode getRoot() {
        return root;
    }

    //Inserção funcionando
    public void insert(String word) {
        if(word == null){
            return;
        }
        HashMap<Character, TrieNode> children = root.getChildrens();
        TrieNode node = null;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if(!children.containsKey(currentChar)){
                children.put(currentChar,new TrieNode());
            }
            node = children.get(currentChar);
            children = node.getChildrens();
        }
        node.setWord(true);
    }

    //Busca funcionando
    public boolean search(String word) {
        if(word == null){
            return false;
        }
        StringBuffer resultWord = new StringBuffer();
        String retorno = null;
        retorno = search(root, word, resultWord, 0);
        if (retorno == null) {
            return false;
        } else {
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

    
    //Remoção
    public boolean remove(String word) {
        if(word == null){
            return false;
        }
        if (root.getChildrens().isEmpty()) {
            return false;
        }
        HashMap<Character, TrieNode> children = root.getChildrens();
        TrieNode currentNode;
        TrieNode lastPrefix = null;
        char currentChar;
        char keyDeleted = '\0';
        for (int i = 0; i < word.length(); i++) {
            currentChar = word.charAt(i);

            if (children.containsKey(currentChar)) {
                currentNode = children.get(word.charAt(i));
                children = currentNode.getChildrens();
            } else {
                return false;
            }


            if (i == word.length() - 1) {
                if (currentNode.isWord()) {
                    if (!children.isEmpty()) {
                        currentNode.setWord(false);
                    } else if (lastPrefix == null) {
                        root.getChildrens().remove(word.charAt(0));
                    } else {
                        lastPrefix.getChildrens().remove(keyDeleted);
                    }
                } else {
                    return false;
                }
            } else {
                if (children.size() > 1 || currentNode.isWord()) {
                    lastPrefix = currentNode;
                    keyDeleted = word.charAt(i + 1);
                }
            }
        }
        return true;
    }

    public ArrayList<String> autoComplete(String prefix) {
        if(prefix == null){
            return null;
        }
        if (root.getChildrens().isEmpty()) {
            return null;
        }
        ArrayList<String> retorno = new ArrayList<>();
        HashMap<Character, TrieNode> children = root.getChildrens();
        TrieNode prefixNode = null;
        TrieNode currentNode = root;
        char currentChar;

        for (int i = 0; i < prefix.length(); i++) {
            currentChar = prefix.charAt(i);
            currentNode = children.get(currentChar);

            if (currentNode == null) {
                return null;
            }

            children = currentNode.getChildrens();
        }
        prefixNode = currentNode;

        if (prefixNode.isWord()) {
            retorno.add(prefix);
        }

        if (children == null) {
            return retorno;
        }

        Set<Character> charList = children.keySet();

        goingThroughTrie(charList, retorno, prefix, children);
        CompareBySizeThenLexi comparator = new CompareBySizeThenLexi();
        Collections.sort(retorno, comparator);

        return retorno;
    }

    public ArrayList<String> autoComplete(String prefix, int quantidade) {
        if(prefix == null){
            return null;
        }
        if (root.getChildrens().isEmpty()) {
            return null;
        }
        ArrayList<String> retorno = new ArrayList<>();
        HashMap<Character, TrieNode> children = root.getChildrens();
        TrieNode prefixNode = null;
        TrieNode currentNode = root;
        char currentChar;

        for (int i = 0; i < prefix.length(); i++) {
            currentChar = prefix.charAt(i);
            currentNode = children.get(currentChar);

            if (currentNode == null) {
                return null;
            }

            children = currentNode.getChildrens();
        }
        prefixNode = currentNode;

        if (prefixNode.isWord()) {
            retorno.add(prefix);
        }

        if (children == null) {
            return retorno;
        }

        Set<Character> charList = children.keySet();

        goingThroughTrie(charList, retorno, prefix, children);
        CompareBySizeThenLexi comparator = new CompareBySizeThenLexi();
        Collections.sort(retorno, comparator);
        while(retorno.size() > quantidade){
            retorno.remove(quantidade);
        }
        return retorno;
    }

    private void goingThroughTrie(Set<Character> charList, ArrayList<String> retorno, String prefix, HashMap<Character, TrieNode> children) {
        for (Character c: charList) {
            TrieNode node = children.get(c);
            StringBuffer tempWord = new StringBuffer();
            tempWord.append(prefix);
            tempWord.append(c);
            if(node.isWord()){
                retorno.add(tempWord.toString());
            }
            if(!node.getChildrens().isEmpty()){
                Set<Character> characterTmp = node.getChildrens().keySet();

                goingThroughTrie(characterTmp, retorno, tempWord.toString(), node.getChildrens());
            }
        }
    }
}
