/**
 * ============================================================
 * Problem: Count of Distinct Substrings (Using Trie)
 * Platform: GFG / Interview Standard Problem
 * Difficulty: Medium
 * ============================================================
 *
 * Problem Summary:
 * ----------------
 * Given a string s consisting of lowercase English letters,
 * count the total number of DISTINCT NON-EMPTY substrings.
 *
 * A substring is a contiguous sequence of characters.
 * The empty substring is NOT counted.
 *
 * Example 1:
 * Input:  "ababa"
 * Output: 9
 *
 * Example 2:
 * Input:  "aaa"
 * Output: 3
 *
 * ============================================================
 *
 * Intuition:
 * ----------
 * Any substring of a string is a prefix of one of its suffixes.
 *
 * So instead of generating all substrings directly:
 *   1. Generate all suffixes of the string
 *   2. Insert them into a Trie
 *
 * Key Insight:
 * ------------
 * Every NEW Trie node created while inserting suffixes
 * represents a NEW distinct substring.
 *
 * Why?
 * ----
 * Each unique path from root → node corresponds to a unique substring.
 *
 * Root node represents EMPTY string → NOT counted.
 *
 * ============================================================
 *
 * Approach:
 * ---------
 * 1. Create a Trie with 26 children (a–z)
 * 2. Initialize a global counter = 0
 * 3. For every suffix of the string:
 *      - Insert it into the Trie
 *      - Whenever a new Trie node is created, increment counter
 * 4. Return counter
 *
 * ============================================================
 *
 * Time Complexity:
 * ----------------
 * Let n = length of string
 *
 * Number of suffixes = n
 * Inserting each suffix takes O(n) in worst case
 *
 * Overall Time Complexity: O(n^2)
 *
 * ============================================================
 *
 * Space Complexity:
 * -----------------
 * In worst case (all characters unique),
 * Trie stores O(n^2) nodes.
 *
 * Space Complexity: O(n^2)
 *
 * ============================================================
 */

class Solution {

    /* -------------------- Trie Node Definition -------------------- */
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];

        TrieNode() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    /* -------------------- Global Variables -------------------- */
    static TrieNode root;
    static int count; // counts distinct non-empty substrings

    /* -------------------- Insert a Suffix into Trie -------------------- */
    static void insertSuffix(String s) {
        TrieNode curr = root;

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';

            // If new node is created, it represents a new substring
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
                count++; // count only new nodes (distinct substrings)
            }

            curr = curr.children[idx];
        }
    }

    /* -------------------- Main Function -------------------- */
    public static int countSubs(String s) {
        root = new TrieNode(); // root represents empty string (not counted)
        count = 0;

        // Insert all suffixes
        for (int i = 0; i < s.length(); i++) {
            insertSuffix(s.substring(i));
        }

        return count;
    }
}
