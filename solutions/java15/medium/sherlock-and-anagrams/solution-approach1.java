// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem?isFullScreen=true
// Problem     Sherlock and Anagrams
// Difficulty  Medium
// Subdomain   Strings
// Platform    HackerRank
// Language    java15
// Status      Accepted
// Submitted   2026-10-18, 10:15 p.m.
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
     * Complete the 'sherlockAndAnagrams' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int sherlockAndAnagrams(String s) {
    int n = s.length();
    java.util.Map<String, Integer> freq = new java.util.HashMap<>();
    int result = 0;

    for (int i = 0; i < n; i++) {
        int[] cnt = new int[26];
        for (int j = i; j < n; j++) {
            cnt[s.charAt(j) - 'a']++;
            String key = java.util.Arrays.toString(cnt);
            int c = freq.getOrDefault(key, 0);
            result += c;              // pairs with every earlier substring of same signature
            freq.put(key, c + 1);
        }
    }
    return result;
}

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = Result.sherlockAndAnagrams(s);

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
