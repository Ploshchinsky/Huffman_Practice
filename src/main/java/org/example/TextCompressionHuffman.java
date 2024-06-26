package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.*;

public class TextCompressionHuffman {
    private static Map<Character, Integer> charFreq;
    private static List<BinaryTreeNode> nodeList;
    private static BinaryTreeNode tree;

    public static void encodeAsFile(String originalText, Path pathFile) throws ParseException {
        String encodedString = encode(originalText);
        byte[] bytes = new byte[encodedString.length()];

        for (int i = 0; i < bytes.length; i++) {
            if (encodedString.charAt(i) == '0') {
                bytes[i] = 0;
            } else if (encodedString.charAt(i) == '1') {
                bytes[i] = 1;
            } else {
                throw new ParseException("String contains invalid characters. Only '0' and '1' are allowed.", i);
            }
        }

        try {
            Files.write(pathFile, bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decodeFile(Path pathFile) {
        StringBuilder result = new StringBuilder();
        byte[] bytes = null;
        try {
            bytes = Files.readAllBytes(pathFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (byte b : bytes) {
            if (b == 0) {
                result.append("0");
            } else if (b == 1) {
                result.append("1");
            }
        }
        return decode(result.toString());
    }

    public static String encode(String originalText) {
        charFreq = getCharFreq(originalText);
        nodeList = getNodesFromMap(charFreq);
        tree = getTreeFromNodes(nodeList);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < originalText.length(); i++) {
            result.append(huffmanSerialized(originalText.charAt(i), new String(), tree));
        }
        return result.toString();
    }

    public static String decode(String encodedText) {
        return huffmanDeserialized(encodedText, tree);
    }

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
        Collections.sort(nodeList);
        return nodeList;
    }

    public static BinaryTreeNode getTreeFromNodes(List<BinaryTreeNode> listOfNodes) {
        while (listOfNodes.size() > 1) {
            BinaryTreeNode right = listOfNodes.remove(listOfNodes.size() - 1);
            BinaryTreeNode left = listOfNodes.remove(listOfNodes.size() - 1);

            BinaryTreeNode parent = new BinaryTreeNode(null, left.getWeight() + right.getWeight(), left, right);
            listOfNodes.add(parent);
        }
        return listOfNodes.get(0);
    }

    public static String huffmanSerialized(Character c, String accumulatingString, BinaryTreeNode tree) {
        if (tree.getData() == c) {
            return accumulatingString;
        } else {
            if (tree.getLeft() != null) {
                String temp = huffmanSerialized(c, accumulatingString + 0, tree.getLeft());
                if (temp != null) {
                    return temp;
                }
            }
            if (tree.getRight() != null) {
                String temp = huffmanSerialized(c, accumulatingString + 1, tree.getRight());
                if (temp != null) {
                    return temp;
                }
            }
        }
        return null;
    }

    public static String huffmanDeserialized(String encodedString, BinaryTreeNode tree) {
        StringBuilder builder = new StringBuilder();
        BinaryTreeNode currentNode = tree;

        for (int i = 0; i < encodedString.length(); i++) {
            currentNode = encodedString.charAt(i) == '0' ? currentNode.getLeft() : currentNode.getRight();
            if (currentNode.getData() != null) {
                builder.append(currentNode.getData());
                currentNode = tree;
            }
        }
        return builder.toString();
    }
}
