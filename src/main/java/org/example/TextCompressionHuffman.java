package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TextCompressionHuffman {
    public static Map<Character, Integer> getCharFreq(String text) {
        Map<Character, Integer> charFreq = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            charFreq.put(text.charAt(i), charFreq.getOrDefault(text.charAt(i), 1) + 1);
        }
        return charFreq;
    }
}
