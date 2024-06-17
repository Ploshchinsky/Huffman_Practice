package org.example;

import java.util.*;

public class TextCompressionHuffman {
    public static Map<Character, Integer> getCharFreq(String text) {
        Map<Character, Integer> charFreq = new HashMap<>();
        for (int i = 0; i < text.length(); i++) {
            charFreq.put(text.charAt(i), charFreq.getOrDefault(text.charAt(i), 0) + 1);
        }
        return charFreq;
    }

    public static List<BinaryTreeNode> getNodesFromMap(Map<Character, Integer> charFreqMap) {
        List<BinaryTreeNode> nodeList = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : charFreqMap.entrySet()) {
            nodeList.add(new BinaryTreeNode(entry.getKey(), entry.getValue()));
        }
        return nodeList;
    }
}
