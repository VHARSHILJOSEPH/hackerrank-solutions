// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/recursive-digit-sum/problem?isFullScreen=true
// Problem     Recursive Digit Sum
// Difficulty  Medium
// Subdomain   Recursion
// Platform    HackerRank
// Language    java15
// Status      Accepted
// Submitted   2026-10-24, 11:12 p.m.
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
     * Complete the 'superDigit' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING n
     *  2. INTEGER k
     */
    
    public static int superDigit(String n, int k) {
    // Write your code here
    long ans = 0;
    for (int i = 0; i < n.length(); i++) {
        ans += n.charAt(i) - '0';
    }

    ans*=k;
    while(ans>=10){
        long tempsum=0;       
        while(ans>0){
            long temp=ans%10;
            tempsum+=temp;
            ans/=10;
        }
        ans=tempsum;
    }
    return (int) ans;
    
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        String n = firstMultipleInput[0];

        int k = Integer.parseInt(firstMultipleInput[1]);

        int result = Result.superDigit(n, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
