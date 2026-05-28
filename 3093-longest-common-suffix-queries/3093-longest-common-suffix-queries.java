class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[26];

        // best index for this suffix
        int idx = -1;

        // smallest length
        int len = Integer.MAX_VALUE;
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {

        TrieNode root = new TrieNode();

        // Build Trie using reversed strings
        for (int i = 0; i < wordsContainer.length; i++) {
            String word = wordsContainer[i];
            int n = word.length();

            TrieNode node = root;

            // update root
            if (n < node.len) {
                node.len = n;
                node.idx = i;
            }

            for (int j = n - 1; j >= 0; j--) {
                int c = word.charAt(j) - 'a';

                if (node.child[c] == null) {
                    node.child[c] = new TrieNode();
                }

                node = node.child[c];

                // store smallest length index
                if (n < node.len) {
                    node.len = n;
                    node.idx = i;
                }
            }
        }

        int[] ans = new int[wordsQuery.length];

        // Process queries
        for (int i = 0; i < wordsQuery.length; i++) {
            String q = wordsQuery[i];

            TrieNode node = root;

            for (int j = q.length() - 1; j >= 0; j--) {
                int c = q.charAt(j) - 'a';

                if (node.child[c] == null) {
                    break;
                }

                node = node.child[c];
            }

            ans[i] = node.idx;
        }

        return ans;
    }
}