// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/minimum-loss/problem?isFullScreen=true
// Problem     Minimum Loss
// Difficulty  Medium
// Subdomain   Search
// Platform    HackerRank
// Language    java15
// Status      Accepted
// Submitted   2026-10-04, 10:13 p.m.
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
     * Complete the 'minimumLoss' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts LONG_INTEGER_ARRAY price as parameter.
     */

    public static int minimumLoss(List<Long> price) {
    // Write your code here
    long ans = Long.MAX_VALUE;
    TreeSet<Long> seen = new TreeSet<>();

    for (int i = 0; i < price.size(); i++) {
        long currentPrice = price.get(i);
        
        // Find the smallest past price that is strictly greater than currentPrice
        Long higherPrice = seen.higher(currentPrice);
        
        if (higherPrice != null) {
            ans = Math.min(ans, higherPrice - currentPrice);
        }
        
        seen.add(currentPrice);
    }

    return (int) ans;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Long> price = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        int result = Result.minimumLoss(price);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
