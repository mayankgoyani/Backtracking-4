// Time Complexity : O(k^(n/k))
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

import java.util.*;;

class Main {

    // to store inside the string array
    private static int index;
    private static String[] result;

    public static String[] expand(String s) {
        // null case
        if (s == null)
            return new String[] { "" };
        index = 0;
        // make a group of all the blocks
        // then using backtracking create a string and add it inside the string array
        List<List<Character>> blocks = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            // to store single block
            List<Character> block = new ArrayList<>();
            // check if character is openenig braces
            if (s.charAt(i) == '{') {
                i++;
                // while braces closed
                while (s.charAt(i) != '}') {
                    if (s.charAt(i) != ',') {
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            } else {
                block.add(s.charAt(i));
            }
            i++;
            // sort collection for lexicographical order
            Collections.sort(block);
            blocks.add(block);
        }
        // System.out.println(blocks);
        // maximum size of the result array
        int n = 1;
        for (List<Character> b : blocks) {
            n *= b.size();
        }
        // System.out.println(n);
        // result array
        result = new String[n];
        backTrack(blocks, 0, new StringBuilder());
        return result;
    }

    // backtracking
    private static void backTrack(List<List<Character>> blocks, int idx, StringBuilder sb) {
        // base case
        if (idx == blocks.size()) {
            result[index++] = sb.toString();
            return;
        }

        // main logic
        for (char b : blocks.get(idx)) {
            // action
            sb.append(b);
            // recurse
            backTrack(blocks, idx + 1, sb);
            // backtrack
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        String s = "{a,b}cde{a,f}{d,e}f";
        System.out.println(Arrays.deepToString(expand(s)));
    }
}