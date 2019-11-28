package br.ufrn.imd.edb2.trie;

import java.util.Comparator;

public class CompareBySizeThenLexi implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        int difference = s1.length() - s2.length();
        if(difference == 0){
            return s1.compareTo(s2);
        }
        return difference;
    }
}
