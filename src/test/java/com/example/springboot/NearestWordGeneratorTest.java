package com.example.springboot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NearestWordGeneratorTest {
    @Test
    public void stabilityTest(){
        NearestWordGenerator nwg = new NearestWordGenerator();
        nwg.generateDictionary("words.txt");
        assertTrue(nwg.isWord("care"));
        assertTrue(nwg.isWord("computer"));
    }
    @Test
    public void generateTest(){
        NearestWordGenerator nwg = new NearestWordGenerator();
        nwg.generateDictionary("words.txt");
        String[] test1 = nwg.generateWord("a", 1, 1);
        String[] test2 = nwg.generateWord("a", 3, 3);
        assertEquals("aa", test1[0]);
        assertEquals("aaaa", test2[0]);
    }

    @Test
    public void urlGenerateTest(){
        NearestWordGenerator nwg = new NearestWordGenerator();
        nwg.useOnlineDictionary("https://raw.githubusercontent.com/dwyl/english-words/master/words_alpha.txt");
        String[] test1 = nwg.generateWord("a", 1, 1);
        String[] test2 = nwg.generateWord("a", 3, 3);
        assertEquals("aa", test1[0]);
        assertEquals("baba", test2[0]);
    }
}
