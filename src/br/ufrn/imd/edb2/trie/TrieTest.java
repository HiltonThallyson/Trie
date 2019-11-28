package br.ufrn.imd.edb2.trie;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TrieTest {
    Trie trie;
    @org.junit.Before
    public void setUp() throws Exception {
        trie = new Trie();
    }

    @org.junit.Test
    public void insertIfWordIsntInsideTrie() {
        String word = "Cat";
        trie.insert(word);
        boolean result = false;

        result = trie.search(word);

        assertTrue(result);
    }

    @org.junit.Test
    public void insertIfWordIsPrefixOfAnotherInsideTrie() {
        String prefix = "Cat";
        String word = "Catarro";

        trie.insert(word);
        trie.insert(prefix);
        boolean result = false;

        result = trie.search(prefix);

        assertTrue(result);
    }

    @org.junit.Test
    public void insertIfWordHasPrefixInsideTrie() {
        String prefix = "Cat";
        String word = "Catarro";

        trie.insert(prefix);
        trie.insert(word);
        boolean result = false;

        result = trie.search(word);

        assertTrue(result);
    }

    @org.junit.Test
    public void insertIfNull() {
        String word = null;
        trie.insert(word);
        trie.insert("Cat");
        boolean result = false;

        result = trie.search(word);

        assertFalse(result);
    }

    @org.junit.Test
    public void searchIfTrieIsEmpty() {
        String word = "Cat";

        assertFalse(trie.search(word));
    }

    @org.junit.Test
    public void searchIfWordDoesntExistInsideTrie() {
        String word = "Cat";

        trie.insert("Cachorro");

        assertFalse(trie.search(word));
    }

    @org.junit.Test
    public void searchIfWordExist() {
        String word = "Cat";

        trie.insert("Cachorro");
        trie.insert("Cat");

        assertTrue(trie.search(word));
    }

    @org.junit.Test
    public void searchIfWordIsNull() {
        String word = null;

        trie.insert("Cat");
        assertFalse(trie.search(word));
    }

    @org.junit.Test
    public void removeIfTrieIsEmpty() {
        String word = "Cat";
        boolean result = false;

        result = trie.remove(word);

        assertFalse(result);
    }

    @org.junit.Test
    public void removeIfWordDoesntExistInsideTrie() {
        String word = "Cat";
        boolean result = false;

        trie.insert("Cachorro");
        trie.insert("Amigo");
        result = trie.remove(word);

        assertFalse(result);
    }

    @org.junit.Test
    public void removeIfWordExistAndHasNoChild() {
        String word = "Cat";
        boolean result = false;

        trie.insert("Cachorro");
        trie.insert("Amigo");
        trie.insert(word);
        trie.remove(word);
        result = trie.search(word);

        assertFalse(result);
    }

    @org.junit.Test
    public void removeIfWordExistAndHasChildren() {
        String word = "Cat";
        String child = "Catarro";
        String child2 = "Categoria";
        boolean result = false;
        boolean childrenIsThere = false;
        trie.insert(word);
        trie.insert(child);
        trie.insert(child2);
        trie.insert("Cachorro");
        trie.insert("Amigo");
        result = trie.remove(word);
        childrenIsThere = (trie.search(child) && trie.search(child2));

        assertTrue(childrenIsThere);
    }

    @org.junit.Test
    public void removeIfWordIsNull() {
        String word = null;
        boolean result = false;

        trie.insert("Cachorro");
        trie.insert("Amigo");
        trie.insert("Cat");
        result = trie.remove(word);

        assertFalse(result);
    }

    @org.junit.Test
    public void autoCompleteIfPrefixIsEqualWordOnly() {
        trie.insert("Cat");
        trie.insert("Queijo");
        ArrayList<String> words = new ArrayList<>();

        words = trie.autoComplete("Cat");

        assertEquals(1, words.size());

    }

    @org.junit.Test
    public void autoCompleteIfPrefixHasWordsThatHasNoOtherPrefix() {
        trie.insert("Cat");
        trie.insert("Queijo");
        trie.insert("Catarro");
        trie.insert("Cavalo");
        ArrayList<String> words = new ArrayList<>();

        words = trie.autoComplete("Cat");

        assertEquals(2, words.size());

    }

    @org.junit.Test
    public void autoCompleteIfPrefixHasWordsThatHasOtherPrefix() {
        trie.insert("Cat");
        trie.insert("Queijo");
        trie.insert("Catarro");
        trie.insert("Cavalo");
        trie.insert("Cavalar");
        trie.insert("Cavalaria");
        trie.insert("Cavalarei");
        ArrayList<String> words = new ArrayList<>();

        words = trie.autoComplete("Cav");

        assertEquals(4, words.size());
    }
    @org.junit.Test
    public void autoCompleteIfPrefixIsNull() {
        String word = null;
        trie.insert("Cat");

        ArrayList<String> words = new ArrayList<>();

        words = trie.autoComplete(word);

        assertEquals(null, words);
    }
    @org.junit.Test
    public void autoCompleteIfLimitIsEstablished() {
        trie.insert("Cat");
        trie.insert("Queijo");
        trie.insert("Catarro");
        trie.insert("Cavalo");
        trie.insert("Cavalar");
        trie.insert("Cavalaria");
        trie.insert("Cavalarei");
        ArrayList<String> words = new ArrayList<>();

        words = trie.autoComplete("Cav", 2);

        assertEquals(2, words.size());
    }
}