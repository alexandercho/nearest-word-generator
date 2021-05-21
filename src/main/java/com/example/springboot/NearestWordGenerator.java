package com.example.springboot;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class NearestWordGenerator {
    private HashSet<String> dictionary;
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz.'-";

    public NearestWordGenerator() {
        this.dictionary = new HashSet<>();
    }
    public Boolean generateDictionary(String pathname, Boolean isLocal) {
        if (isLocal) {
            try {
                File myObj = new File(pathname);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine().toLowerCase();
                    this.dictionary.add(data);
                }
                myReader.close();
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                return false;
            }
        } else {
            try {
                URL url = new URL(pathname);
                Scanner myReader = new Scanner(url.openStream());
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine().toLowerCase();
                    this.dictionary.add(data);
                }
                myReader.close();
                return true;
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                return false;
            }
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
        String[] output = new String[number];
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
        int count = 0;
        for (String nearestWord : neighbors) {
            if (count == number) {
                break;
            }
            if (this.isWord(nearestWord)) {
                output[count] = nearestWord;
                count++;
            }

        }

        return output;
    }
}
