// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/sock-merchant/problem?isFullScreen=true
// Problem     Sales by Match
// Difficulty  Easy
// Subdomain   Implementation
// Platform    HackerRank
// Language    java15
// Status      Accepted
// Submitted   2026-09-01, 12:23 a.m.
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
     * Complete the 'sockMerchant' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY ar
     */

    public static int sockMerchant(int n, List<Integer> ar) {
    // Write your code here
    HashMap<Integer,Integer> freq=new HashMap<>();
    int ans=0;
    for(int n1 :ar){
        int count=freq.getOrDefault(n1,0)+1;
        freq.put(n1,count);
        if(count%2==0){
            ans++;
        }
    }
    return ans;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> ar = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.sockMerchant(n, ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
