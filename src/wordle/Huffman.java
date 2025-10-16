package wordle;

import project20280.hashtable.ChainHashMap;
import project20280.interfaces.Entry;
import project20280.interfaces.Position;
import project20280.priorityqueue.HeapPriorityQueue;
import project20280.tree.LinkedBinaryTree;

import java.util.*;

public class Huffman {

    public LinkedBinaryTree<Object> huffman(List<String> dictionary) {
        // Computing the frequency of each character in the word list
        ChainHashMap<Character, Integer> frequencies = findFrequencies(dictionary);

        // Initialize a Heap Priority Queue Q
        HeapPriorityQueue<Integer, LinkedBinaryTree<Object>> Q = new HeapPriorityQueue<>();

        // Create Single-Node Binary Tree T to store each character
        for (char c = 'a'; c <= 'z'; c++) {
            // Single Node Binary Tree for the current character
            LinkedBinaryTree<Object> T = new LinkedBinaryTree<>();
            // Setting the character as the root of the tree
            T.addRoot("" + c);
            // Insert the tree into the priority queue with its frequency as the key
            Q.insert(frequencies.get(c), T);
        }

        // Merging Trees
        while (Q.size() > 1) {
            // Remove the two trees with the smallest frequencies from the queue
            Entry<Integer, LinkedBinaryTree<Object>> e1 = Q.removeMin();
            Entry<Integer, LinkedBinaryTree<Object>> e2 = Q.removeMin();

            // Create a new Binary Tree T with a combined root and subtrees
            LinkedBinaryTree<Object> T = new LinkedBinaryTree<>();
            // Combine the characters of the two trees
            String combinedCharacters = e1.getValue().root().getElement().toString() + e2.getValue().root().getElement().toString();
            // Set the combined characters as the root of the new tree
            T.addRoot(combinedCharacters);
            // Attach the two removed trees as subtrees of the new tree
            T.attach(T.root(), e1.getValue(), e2.getValue());

            // Insert the new tree into the priority queue with the sum of frequencies as the key
            int totalFreq = e1.getKey() + e2.getKey();
            Q.insert(totalFreq, T);
        }
        // Returning the resulting Huffman tree
        return Q.removeMin().getValue();
    }

    // Method to compute the frequency of each letter
    public static ChainHashMap<Character, Integer> findFrequencies(List<String> dictionary) {
        // Initialize a Chain Map with letters and their initial frequency set to 0
        ChainHashMap<Character, Integer> frequencies = new ChainHashMap<>();
        for (char letter = 'a'; letter <= 'z'; letter++) {
            frequencies.put(letter, 0);
        }

        // Loop through each word
        for (String word : dictionary) {
            // Loop through letters in each word
            for (char letter : word.toCharArray()) {
                // Incrementing the frequency count of the letter
                Integer count = frequencies.get(letter);
                frequencies.put(letter, count + 1);
            }
        }

        return frequencies;
    }

    // Method to generate Huffman codes for each character in the word list
    public ChainHashMap<Character, String> compress(List<String> dictionary) {
        // Construct the Huffman tree
        LinkedBinaryTree<Object> huffmanTree = huffman(dictionary);

        // Generate Huffman codes from the tree
        ChainHashMap<Character, String> huffCodes = new ChainHashMap<>();
        if (huffmanTree != null) {
            huffCodesHelper(huffmanTree, huffmanTree.root(), "", huffCodes);
        }

        return huffCodes;
    }

    // Assign Huffman codes to each character in the tree
    private void huffCodesHelper(LinkedBinaryTree<Object> huffmanTree, Position<Object> node, String code, ChainHashMap<Character, String> huffCodes) {
        if (huffmanTree.isInternal(node)) {
            // Traverse left and append '0' to the code
            huffCodesHelper(huffmanTree, huffmanTree.left(node), code + "0", huffCodes);
            // Traverse right and append '1' to the code
            huffCodesHelper(huffmanTree, huffmanTree.right(node), code + "1", huffCodes);
        } else {
            // Node is a leaf (external) that represents a character
            String character = (String) node.getElement();
            // Assigning the Huffman code to the character
            huffCodes.put(character.charAt(0), code);
        }
    }

    // Calculate the total number of bits required to encode the wordlist using ASCII
    public static int bitLength(List<String> dictionary) {
        // Each letter requires 8 bits in ASCII
        return 8 * dictionary.size();
    }

    // Calculate the total number of bits required to encode the word list using Huffman coding
    public static int huffmanTotalBits(List<String> dictionary, ChainHashMap<Character, String> huffCodes) {
        int total = 0;

        // Loop through words in the word list
        for (String word : dictionary) {
            // Loop through characters in each word and add up the lengths of their Huffman codes
            for (char c : word.toCharArray()) {
                total += huffCodes.get(c).length();
            }
        }

        return total;
    }

    // Find the longest word
    public static String findLongestWord(List<String> dictionary, ChainHashMap<Character, String> huffCodes) {
        int maxLength = Integer.MIN_VALUE;
        String longestWord = "";

        // Iterate through each word in the dictionary
        for (String word : dictionary) {
            // Retrieve the Huffman code for the current word
            String code = getCode(word, huffCodes);

            // Update the maximum length and longest word if the current word's Huffman code is longer
            if (code.length() > maxLength) {
                maxLength = code.length();
                longestWord = word;
            }
        }

        return longestWord;
    }

    // Method to find the shortest word in the dictionary and return it
    public static String findShortestWord(List<String> dictionary, ChainHashMap<Character, String> huffCodes) {
        int minLength = Integer.MAX_VALUE;
        String shortestWord = "";

        // Iterate through each word in the dictionary
        for (String word : dictionary) {
            // Retrieve the Huffman code for the current word
            String code = getCode(word, huffCodes);

            // Update the minimum length and shortest word if the current word's Huffman code is shorter
            if (code.length() < minLength) {
                minLength = code.length();
                shortestWord = word;
            }
        }

        return shortestWord;
    }

    // Retrieve the Huffman code for a given word
    private static String getCode(String word, ChainHashMap<Character, String> huffCodes) {
        StringBuilder encoded = new StringBuilder();

        // Loop through each character in the word and append its Huffman code
        for (char c : word.toCharArray()) {
            encoded.append(huffCodes.get(c));
        }

        return encoded.toString();
    }
}
