// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/absolute-permutation/problem?isFullScreen=true
// Problem     Absolute Permutation
// Difficulty  Medium
// Subdomain   Implementation
// Platform    HackerRank
// Language    java15
// Status      Accepted
// Submitted   2026-09-06, 12:46 a.m.
// ──────────────────────────────────────────────────

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;

class Result {

    /*
     * Complete the 'absolutePermutation' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER k
     */

    public static List<Integer> absolutePermutation(int n, int k) {

        List<Integer> result = new ArrayList<>();

        // k = 0 -> identity permutation
        if (k == 0) {
            for (int i = 1; i <= n; i++) {
                result.add(i);
            }
            return result;
        }

        // No absolute permutation possible
        if (n % (2 * k) != 0) {
            result.add(-1);
            return result;
        }

        // Create blocks of size k
        boolean add = true;

        for (int start = 1; start <= n; start += k) {

            if (add) {
                // [start + k, ..., start + 2k - 1]
                for (int i = start; i < start + k; i++) {
                    result.add(i + k);
                }
            } else {
                // [start - k, ..., start - 1]
                for (int i = start; i < start + k; i++) {
                    result.add(i - k);
                }
            }

            add = !add;
        }

        return result;
    }
}

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));

        BufferedWriter bufferedWriter =
                new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput =
                        bufferedReader.readLine()
                                .replaceAll("\\s+$", "")
                                .split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);
                int k = Integer.parseInt(firstMultipleInput[1]);

                List<Integer> result =
                        Result.absolutePermutation(n, k);

                bufferedWriter.write(
                        result.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                                + "\n"
                );

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
