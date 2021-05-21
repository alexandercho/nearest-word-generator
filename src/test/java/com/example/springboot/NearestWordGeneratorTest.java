package com.example.springboot;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        ArrayList<String> test1 = nwg.generateWord("a", 1, 1);
        ArrayList<String> test2 = nwg.generateWord("a", 3, 3);
        assertEquals("aa", test1.get(0));
        assertEquals("aaaa", test2.get(0));
    }
}
