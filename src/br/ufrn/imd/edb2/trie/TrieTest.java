package br.ufrn.imd.edb2.trie;

import static org.junit.Assert.*;

public class TrieTest {
    Trie trie;
    @org.junit.Before
    public void setUp() throws Exception {
        trie = new Trie();
    }

    @org.junit.Test
    public void insert() {
        String word = "Cat";
        trie.insert(word);
        boolean result = false;

        result = trie.search(word);

        assertTrue(result);
    }

    @org.junit.Test
    public void searchIfTrieIsEmpty() {
        String word = "Cat";

        assertFalse(trie.search(word));
    }

    @org.junit.Test
    public void searchIfWordDoesntExist() {
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
    public void removeIfTrieIsEmpty() {
        String word = "Cat";
        boolean result = false;

        result = trie.remove(word);

        assertFalse(result);
    }

    @org.junit.Test
    public void removeIfWordDoesntExist() {
        String word = "Cat";
        boolean result = false;

        trie.insert("Cachorro");
        trie.insert("Amigo");
        result = trie.remove(word);

        assertFalse(result);
    }

    @org.junit.Test
    public void removeIfWordExist() {
        String word = "Cat";
        boolean result = false;

        trie.insert("Cachorro");
        trie.insert("Amigo");
        trie.insert("Cat");
        result = trie.remove(word);

        assertTrue(result);
    }
}