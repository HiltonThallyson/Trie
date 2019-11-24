package br.ufrn.imd.edb2.trie;

import java.util.HashMap;

public class Trie {
    private TrieNode root = new TrieNode();


    //Inserção funcionando
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.getChildrens();

        for(int i = 0; i <  word.length(); i++){
           if(children.containsKey(word.charAt(i))){
               children = children.get(word.charAt(i)).getChildrens();
           }else{
               children.put(word.charAt(i), new TrieNode());
               if(i < word.length() - 1) {
                   children = children.get(word.charAt(i)).getChildrens();
               }else{
                   children.get(word.charAt(i)).setWord(true);
               }
           }
        }
    }

    //Busca funcionando
    public String search(String word){
        StringBuffer resultWord = new StringBuffer();
        return search(root, word, resultWord, 0);
    }

    private String search(TrieNode node, String word, StringBuffer resultWord, int posicao) {
        HashMap <Character, TrieNode> children = node.getChildrens();
        char currentChar = word.charAt(posicao);
        String retorno = null;
        if(children.isEmpty()){
            return retorno;
        }

        if(children.containsKey(currentChar)){
            resultWord.append(currentChar);
            if((posicao == word.length()-1)){
                if(children.get(currentChar).isWord()) {
                    return resultWord.toString();
                }else{
                    return retorno;
                }
            }else {
                retorno = search(children.get(currentChar), word, resultWord, posicao+1);
            }
        }

        return retorno;
    }

    //TODO fazer remoção e autocompletar
//    public boolean remove(String word) {
//        String thereIsWorld = null;
//        if(root.getChildrens().isEmpty()){
//            return false;
//        }
//
//        //Primeiro caso - palavra não existe
//        thereIsWorld = search(word);
//        if(thereIsWorld == null){
//            return false;
//        }
//
//        //Segundo caso - palavra é prefixo
//        HashMap <Character, TrieNode> childrens = root.getChildrens();
//        TrieNode node = new TrieNode();
//        for(int i = 0; i < word.length(); i ++){
//            node = childrens.get(word.charAt(i));
//            if(node.isWord())
//            childrens = node.getChildrens();
//
//
//        }
//    }
}
