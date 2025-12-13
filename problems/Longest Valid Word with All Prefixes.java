/**
 * ------------------------------------------------------------
 * Problem: Longest Valid Word with All Prefixes
 * ------------------------------------------------------------
 *
 * Given an array of strings words[], find the longest string such that
 * every prefix of it is also present in the array.
 *
 * If multiple strings have the same maximum length, return the
 * lexicographically smallest one.
 *
 * If no such string exists, return an empty string.
 *
 * ------------------------------------------------------------
 * Example:
 * ------------------------------------------------------------
 * Input:
 * words = ["p", "pr", "pro", "probl", "problem", "pros", "process"]
 *
 * Output:
 * "pros"
 *
 * Explanation:
 * "pros" is valid because all its prefixes
 * ("p", "pr", "pro", "pros") exist in the array.
 *
 * ------------------------------------------------------------
 * Intuition:
 * ------------------------------------------------------------
 * A Trie (Prefix Tree) naturally represents prefixes.
 *
 * If we insert all words into a Trie:
 * - Each node represents a prefix
 * - A word is valid only if all prefixes up to that node are complete words
 *
 * During DFS:
 * - We move forward ONLY if the current node marks the end of a word (eow = true)
 * - This guarantees all prefixes exist
 *
 * We track the longest valid word while traversing.
 *
 * ------------------------------------------------------------
 * Approach:
 * ------------------------------------------------------------
 * 1. Build a Trie by inserting all words.
 * 2. Perform DFS from the root:
 *    - Move to a child only if child.eow == true
 *    - Build the current word using StringBuilder
 *    - Update the answer when a longer word is found
 * 3. Since DFS iterates characters from 'a' to 'z',
 *    lexicographically smallest word is chosen automatically.
 *
 * ------------------------------------------------------------
 * Time Complexity:
 * ------------------------------------------------------------
 * Let:
 * N = number of words
 * L = maximum length of a word
 *
 * Trie Insertion:
 *   O(N * L)
 *
 * DFS Traversal:
 *   Visits each Trie node once → O(N * L)
 *
 * Total Time Complexity:
 *   O(N * L)
 *
 * ------------------------------------------------------------
 * Space Complexity:
 * ------------------------------------------------------------
 * Trie Nodes:
 *   O(N * L)
 *
 * Recursion Stack (DFS):
 *   O(L)
 *
 * Total Space Complexity:
 *   O(N * L)
 * ------------------------------------------------------------
 */

class Solution {

    // Trie Node definition
    static class Node {
        Node[] children = new Node[26];
        boolean eow = false; // End Of Word
    }

    static Node root;
    static String ans;

    // Insert a word into the Trie
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

    // DFS to find the longest valid word
    static void dfs(Node root, StringBuilder temp) {

        for (int i = 0; i < 26; i++) {
            Node child = root.children[i];

            // Move forward only if:
            // 1. Child exists
            // 2. Child marks the end of a word
            if (child != null && child.eow) {

                temp.append((char) (i + 'a'));

                // Update answer if longer word is found
                if (temp.length() > ans.length()) {
                    ans = temp.toString();
                }

                // DFS deeper
                dfs(child, temp);

                // Backtracking
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }

    // Main function
    public String longestValidWord(String[] words) {

        root = new Node();
        ans = "";

        // Step 1: Insert all words into Trie
        for (String word : words) {
            insert(word);
        }

        // Step 2: DFS from root
        dfs(root, new StringBuilder());

        return ans;
    }
}
