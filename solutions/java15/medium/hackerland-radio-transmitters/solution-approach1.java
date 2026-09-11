// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/hackerland-radio-transmitters/problem?isFullScreen=true
// Problem     Hackerland Radio Transmitters
// Difficulty  Medium
// Subdomain   Search
// Platform    HackerRank
// Language    java15
// Status      Accepted
// Submitted   2026-09-11, 11:16 p.m.
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
     * Complete the 'hackerlandRadioTransmitters' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY x
     *  2. INTEGER k
     */

    public static int hackerlandRadioTransmitters(List<Integer> x, int k) {
    // Write your code here
    int ans=0;
    Collections.sort(x);
    int l=0;
    int n=x.size();
    while(l<n){
        ans++;
        int cur=x.get(l)+k;
        while(l<n && x.get(l)<=cur){
            l++;
        }
        int transmitterPos = x.get(l - 1);
        while (l < n && x.get(l) <= transmitterPos + k) {
            l++;
        }
    }
    
    return ans;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int k = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.hackerlandRadioTransmitters(x, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
