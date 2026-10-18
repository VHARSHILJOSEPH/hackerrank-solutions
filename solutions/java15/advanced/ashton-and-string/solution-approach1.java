// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/ashton-and-string/problem?isFullScreen=true
// Problem     Ashton and String
// Difficulty  Advanced
// Subdomain   Strings
// Platform    HackerRank
// Language    java15
// Status      Accepted
// Submitted   2026-10-18, 10:14 p.m.
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
     * Complete the 'ashtonString' function below.
     *
     * The function is expected to return a CHARACTER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER k
     */

    public static char ashtonString(String s, int k) {
    int n = s.length();
    int[] rank = new int[n];
    int[] sa = new int[n];
    int[] tmp = new int[n];
    for (int i = 0; i < n; i++) rank[i] = s.charAt(i) + 1;
    long M = Math.max(n, 256) + 2;
    long[] keys = new long[n];

    for (int len = 1; ; len <<= 1) {
        for (int i = 0; i < n; i++) {
            long second = (i + len < n) ? rank[i + len] + 1 : 0;
            keys[i] = ((rank[i] * M + second) << 17) | i;
        }
        java.util.Arrays.sort(keys);
        int r = 1;
        long prevKey = -1;
        for (int j = 0; j < n; j++) {
            int idx = (int) (keys[j] & ((1 << 17) - 1));
            long kk = keys[j] >> 17;
            sa[j] = idx;
            if (j > 0 && kk != prevKey) r++;
            tmp[idx] = r;
            prevKey = kk;
        }
        int[] t = rank; rank = tmp; tmp = t;
        if (r == n || len >= n) break;
    }

    // Kasai LCP: lcp[r] = common prefix of sa[r] and sa[r-1]
    int[] pos = new int[n];
    for (int i = 0; i < n; i++) pos[sa[i]] = i;
    int[] lcp = new int[n];
    int h = 0;
    for (int i = 0; i < n; i++) {
        if (pos[i] > 0) {
            int j = sa[pos[i] - 1];
            while (i + h < n && j + h < n && s.charAt(i + h) == s.charAt(j + h)) h++;
            lcp[pos[i]] = h;
            if (h > 0) h--;
        } else {
            h = 0;
        }
    }

    long kk = k;
    for (int r = 0; r < n; r++) {
        long start = lcp[r] + 1;
        long end = n - sa[r];
        if (start > end) continue;
        long total = (start + end) * (end - start + 1) / 2;
        if (kk > total) {
            kk -= total;
        } else {
            long L = start;
            while (kk > L) {
                kk -= L;
                L++;
            }
            return s.charAt(sa[r] + (int) kk - 1);
        }
    }
    return '\0'; // unreachable: k is guaranteed valid
}

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                int k = Integer.parseInt(bufferedReader.readLine().trim());

                char res = Result.ashtonString(s, k);

                bufferedWriter.write(String.valueOf(res));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
