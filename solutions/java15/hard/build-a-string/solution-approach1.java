// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/build-a-string/problem?isFullScreen=true
// Problem     Build a String
// Difficulty  Hard
// Subdomain   Strings
// Platform    HackerRank
// Language    java15
// Status      Accepted
// Submitted   2026-10-18, 10:19 p.m.
// ──────────────────────────────────────────────────

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'buildString' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER a
     *  2. INTEGER b
     *  3. STRING s
     */

    public static int buildString(int a, int b, String s) {
        int n = s.length();
        char[] c = s.toCharArray();

        // L[i] = longest substring starting at i that occurs fully inside s[0..i)
        int[] L = new int[n + 1];
        int[] lcp = new int[n + 2];
        for (int i = n - 1; i >= 1; i--) {
            int best = 0;
            for (int j = 0; j < i; j++) {
                lcp[j] = (c[i] == c[j]) ? lcp[j + 1] + 1 : 0;
                int v = Math.min(lcp[j], i - j);
                if (v > best) best = v;
            }
            L[i] = best;
        }

        long[] dp = new long[n + 1];
        int p = 0; // smallest start that can still be used for a copy
        for (int j = 1; j <= n; j++) {
            dp[j] = dp[j - 1] + a;
            while (p < j && p + L[p] < j) p++;
            if (p < j) {
                dp[j] = Math.min(dp[j], dp[p] + b);
            }
        }
        return (int) dp[n];
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);
                int a = Integer.parseInt(firstMultipleInput[1]);
                int b = Integer.parseInt(firstMultipleInput[2]);

                String s = bufferedReader.readLine().trim();

                int result = Result.buildString(a, b, s);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
