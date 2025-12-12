/*
------------------------------------------------------------
Problem: Word Break (Using Trie + Recursion)
------------------------------------------------------------

Problem Summary:
Given a string `key` and a dictionary `wordDict` containing a list of words,
determine if `key` can be segmented into a space-separated sequence of one
or more dictionary words.

Each word in the dictionary can be used multiple times.

------------------------------------------------------------
Intuition:
------------------------------------------------------------
- We want to check all possible prefixes of the given string.
- If a prefix exists in the dictionary, we recursively check the remaining suffix.
- To make prefix searching efficient, we store all dictionary words in a Trie.
- Trie allows fast prefix lookup in O(length of word).

------------------------------------------------------------
Approach:
------------------------------------------------------------
1. Build a Trie using all words from `wordDict`.
2. Use recursion to try all prefixes of `key`:
   - For each prefix, check if it exists in Trie.
   - If yes, recursively solve for the remaining substring.
3. Base Case:
   - If the remaining string length becomes 0, return true.
4. If no valid segmentation is possible, return false.

------------------------------------------------------------
Time Complexity:
------------------------------------------------------------
Let:
- N = length of the key string
- M = number of words in the dictionary
- L = average length of dictionary words

Trie Construction:
- O(M * L)

Recursive Word Break:
- Worst case: O(N^2)
  (due to checking all prefixes and substring creation)

Overall Time Complexity:
- O(M * L + N^2)

------------------------------------------------------------
Space Complexity:
------------------------------------------------------------
Trie Space:
- O(M * L)

Recursion Stack:
- O(N)

Overall Space Complexity:
- O(M * L + N)

------------------------------------------------------------
*/

import java.util.*;

class Solution {

    // Trie Node definition
    static class Node {
        Node[] children = new Node[26];
        boolean eow = false; // end of word

        Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    // Root of Trie
    static Node root = new Node();

    // Insert a word into Trie
    static void insert(String word) {
        Node curr = root;

        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';

            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }
            curr = curr.children[idx];
        }

        curr.eow = true;
    }

    // Search a word in Trie
    static boolean search(String word) {
        Node curr = root;

        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';

            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }

        return curr.eow;
    }

    // Main Word Break function
    public boolean wordBreak(String key, List<String> wordDict) {

        // Build Trie from dictionary
        for (String word : wordDict) {
            insert(word);
        }

        // Start recursive checking
        return wordBreakUtil(key);
    }

    // Recursive utility function
    static boolean wordBreakUtil(String key) {

        // Base case: successfully segmented entire string
        if (key.length() == 0) {
            return true;
        }

        // Try all prefixes
        for (int i = 1; i <= key.length(); i++) {

            // If prefix exists in Trie and suffix can be segmented
            if (search(key.substring(0, i)) &&
                wordBreakUtil(key.substring(i))) {

                return true;
            }
        }

        return false;
    }
}

