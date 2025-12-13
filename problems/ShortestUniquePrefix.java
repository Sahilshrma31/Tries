package problems;
//asked in google and microsoft in interviews
public class ShortestUniquePrefix {
    /*
=========================================================
Problem: Shortest Unique Prefix for Every Word
Difficulty: Hard
=========================================================

Problem Summary:
----------------
Given an array of N words, find the shortest unique prefix
for each word such that:
- The prefix uniquely identifies the word
- No word is a prefix of another word

Example:
Input:
["zebra", "dog", "duck", "dove"]

Output:
["z", "dog", "du", "dov"]

---------------------------------------------------------

Intuition:
----------
If two words share the same prefix, that prefix is NOT unique.
Using a Trie (Prefix Tree), we can:
- Track how many words pass through each node
- The first node where frequency becomes 1 gives us a unique prefix

---------------------------------------------------------

Approach:
---------
1. Build a Trie where:
   - Each node has 26 children (a–z)
   - Each node stores a frequency counter (freq)

2. Insert all words into the Trie:
   - For every character, increment freq

3. For each word:
   - Traverse the Trie again
   - The first character where freq == 1
     marks the shortest unique prefix

---------------------------------------------------------

Time Complexity:
----------------
Let:
N = number of words
L = average length of each word

Insertion into Trie: O(N * L)
Finding prefixes:   O(N * L)

Total Time Complexity: O(N * L)

---------------------------------------------------------

Space Complexity:
-----------------
Trie stores all characters of all words

Space Complexity: O(N * L)

---------------------------------------------------------
*/

class Solution {

    // Trie Node definition
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int freq = 0; // frequency of words passing through this node

        TrieNode() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    static TrieNode root;

    // Insert a word into the Trie
    static void insert(String word) {
        TrieNode curr = root;

        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';

            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }

            curr = curr.children[idx];
            curr.freq++; // increment frequency
        }
    }

    // Get shortest unique prefix for a word
    static String getPrefix(String word) {
        TrieNode curr = root;
        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            curr = curr.children[idx];
            prefix.append(word.charAt(i));

            // First unique point
            if (curr.freq == 1) {
                break;
            }
        }
        return prefix.toString();
    }

    // Main function
    static String[] findPrefixes(String[] arr, int N) {

        root = new TrieNode();

        // Step 1: Insert all words into Trie
        for (String word : arr) {
            insert(word);
        }

        // Step 2: Find prefix for each word
        String[] result = new String[N];
        for (int i = 0; i < N; i++) {
            result[i] = getPrefix(arr[i]);
        }

        return result;
    }
}

}
