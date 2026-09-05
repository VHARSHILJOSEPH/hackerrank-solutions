// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/string-function-calculation/problem?isFullScreen=true
// Problem     String Function Calculation
// Difficulty  Advanced
// Subdomain   Strings
// Platform    HackerRank
// Language    java15
// Status      Accepted
// Submitted   2026-09-06, 12:44 a.m.
// ──────────────────────────────────────────────────

import java.io.*;
import java.util.*;

class Result {

    /*
     * Complete the 'maxValue' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING t as parameter.
     */

    public static int maxValue(String t) {

        int n = t.length();

        // Suffix Automaton
        int maxStates = 2 * n;

        int[][] next = new int[maxStates][26];
        int[] len = new int[maxStates];
        int[] link = new int[maxStates];
        int[] count = new int[maxStates];

        // -1 means transition does not exist
        for (int i = 0; i < maxStates; i++) {
            Arrays.fill(next[i], -1);
            link[i] = -1;
        }

        int size = 1;
        int last = 0;

        // Build suffix automaton
        for (int i = 0; i < n; i++) {

            int c = t.charAt(i) - 'a';

            int cur = size++;
            len[cur] = len[last] + 1;
            count[cur] = 1;

            int p = last;

            while (p != -1 && next[p][c] == -1) {
                next[p][c] = cur;
                p = link[p];
            }

            if (p == -1) {
                link[cur] = 0;

            } else {

                int q = next[p][c];

                if (len[p] + 1 == len[q]) {
                    link[cur] = q;

                } else {

                    int clone = size++;

                    len[clone] = len[p] + 1;
                    link[clone] = link[q];

                    // Copy transitions
                    for (int j = 0; j < 26; j++) {
                        next[clone][j] = next[q][j];
                    }

                    // Clone does not represent a new occurrence
                    count[clone] = 0;

                    while (p != -1 && next[p][c] == q) {
                        next[p][c] = clone;
                        p = link[p];
                    }

                    link[q] = clone;
                    link[cur] = clone;
                }
            }

            last = cur;
        }

        /*
         * Sort states by length.
         * We need to propagate occurrence counts from longer
         * states to their suffix links.
         */
        int[] bucket = new int[n + 1];

        for (int i = 0; i < size; i++) {
            bucket[len[i]]++;
        }

        for (int i = 1; i <= n; i++) {
            bucket[i] += bucket[i - 1];
        }

        int[] order = new int[size];

        for (int i = size - 1; i >= 0; i--) {
            order[--bucket[len[i]]] = i;
        }

        /*
         * Propagate occurrence counts from longer substrings
         * to their suffix-link states.
         */
        for (int i = size - 1; i > 0; i--) {
            int v = order[i];
            int parent = link[v];

            if (parent >= 0) {
                count[parent] += count[v];
            }
        }

        /*
         * Every suffix-automaton state represents substrings
         * having the same number of occurrences.
         *
         * The longest substring represented by state v has
         * length len[v], so its value is:
         *
         *     len[v] * count[v]
         */
        long answer = 0;

        for (int v = 1; v < size; v++) {
            long value = (long) len[v] * count[v];
            answer = Math.max(answer, value);
        }

        return (int) answer;
    }
}

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bufferedWriter =
                new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String t = bufferedReader.readLine();

        int result = Result.maxValue(t);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

