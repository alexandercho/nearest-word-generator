package com.example.springboot;

import java.util.ArrayList;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class NearestWordGenerator {
    private HashSet<String> dictionary;
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz.'-";

    public NearestWordGenerator() {
        this.dictionary = new HashSet<>();
    }
    public void generateDictionary(String pathname) {
        try {
            File myObj = new File(pathname);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine().toLowerCase();
                this.dictionary.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public Boolean isWord(String word) {
        return this.dictionary.contains(word);
    }
    public ArrayList<String> getNeighbors(String word, HashSet<String> seen) {
        ArrayList<String> output = new ArrayList<>();

        for (int i = 0; i < word.length(); i++) {
            String removedLetter = word.substring(0,i) + word.substring(i+1);
            if (!seen.contains(removedLetter))
                output.add(removedLetter);

            for (int j = 0; j < alphabet.length(); j++) {
                String replacedLetter = word.substring(0,i) + alphabet.charAt(j) + word.substring(i+1);
                if (!seen.contains(replacedLetter)) {
                    output.add(replacedLetter);
                }

                String addedLetter = word.substring(0,i) + alphabet.charAt(j)  + word.substring(i);
                if (!seen.contains(addedLetter)) {
                    output.add(addedLetter);
                }
            }
        }
        for (int j = 0; j < alphabet.length(); j++) {

            String addedLetter = word + alphabet.charAt(j);
            output.add(addedLetter);
        }

        return output;
    }

    public String[] generateWord(String word, int delta, int number) {
        ArrayList<String> output = new ArrayList<>(number);
        HashSet<String> seen = new HashSet<>();
        seen.add(word);
        seen.add("");
        ArrayList<String> neighbors = new ArrayList<>();
        neighbors.add(word);

        for (int i = 0; i < delta; i++) {
            ArrayList<String> newNeighbors = new ArrayList<>();
            for (String neighbor : neighbors) {
                ArrayList<String> nextNeighbors = getNeighbors(neighbor, seen);
                for (String nextNeighbor: nextNeighbors) {
                    newNeighbors.add(nextNeighbor);
                    seen.add(nextNeighbor);
                }
            }
            neighbors = newNeighbors;
        }

        for (String nearestWord : neighbors) {
            if (this.isWord(nearestWord)) {
                output.add(nearestWord);
            }
            if (output.size() == number) {
                break;
            }
        }
        return (String[]) output.toArray();
    }
}
