package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

public class TextCompressionTest {
    static String text = "Nested classes are classes defined inside other classes. " +
            "They can be static (static nested classes) or non-static (inner classes)";
    static String text2 = "aa ba aab caa";

    @Test
    public void TextCompressionHuffmanTest() {
        Map<Character, Integer> charFreqMap = TextCompressionHuffman.getCharFreq(text2);
        for (Map.Entry<Character, Integer> entry : charFreqMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println("----");

        List<BinaryTreeNode> nodeList = TextCompressionHuffman.getNodesFromMap(charFreqMap);
        Collections.sort(nodeList);
        System.out.println(nodeList);
        System.out.println("----");

        BinaryTreeNode tree = TextCompressionHuffman.getTreeFromNodes(nodeList);
        Map<Character, String> codes = new HashMap<>();
        for (Character c : charFreqMap.keySet()) {
            codes.put(c, TextCompressionHuffman.huffmanSerialized(c, new String(), tree));
        }
        System.out.println(codes);
        System.out.println("----");

        String encoded = "";
        for (int i = 0; i < text2.length(); i++) {
            encoded += codes.get(text2.charAt(i));
        }
        System.out.println(encoded);
        System.out.println("----");
        System.out.println(TextCompressionHuffman.huffmanDeserialized(encoded, tree));
    }

    @Test
    public void Test() {
        String encodeString = TextCompressionHuffman.encode(text2);
        String decodeString = TextCompressionHuffman.decode(encodeString);
        System.out.println(encodeString);
        System.out.println(decodeString);
        System.out.println("----");
        encodeString = TextCompressionHuffman.encode(text);
        decodeString = TextCompressionHuffman.decode(encodeString);
        System.out.println(encodeString);
        System.out.println(decodeString);
        System.out.println("----");
    }

    @Test
    public void encodeDecodeFileTest() {
        Path filePath = Paths.get("src/main/resources/encoded.bin");
        String expected = text;
        String actual = "";
        try {
            TextCompressionHuffman.encodeAsFile(text, filePath);
            actual = TextCompressionHuffman.decodeFile(filePath);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(expected, actual);
    }
}
