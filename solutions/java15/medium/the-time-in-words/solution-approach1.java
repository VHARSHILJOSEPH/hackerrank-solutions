// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/the-time-in-words/problem?isFullScreen=true
// Problem     The Time in Words
// Difficulty  Medium
// Subdomain   Implementation
// Platform    HackerRank
// Language    java15
// Status      Accepted
// Submitted   2026-09-01, 12:28 a.m.
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
     * Complete the 'timeInWords' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER h
     *  2. INTEGER m
     */

    public static String timeInWords(int h, int m) {
    // Write your code here
    String[] words = {
        "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
        "eleven", "twelve", "thirteen", "fourteen", "quarter", "sixteen", "seventeen", 
        "eighteen", "nineteen", "twenty", "twenty one", "twenty two", "twenty three", 
        "twenty four", "twenty five", "twenty six", "twenty seven", "twenty eight", "twenty nine"
    };
    
    String currentHour = words[h];
    String nextHour = words[(h % 12) + 1]; // Handles wrapping from 12 to 1
    
    if (m == 0) {
        return currentHour + " o' clock";
    } else if (m == 15) {
        return "quarter past " + currentHour;
    } else if (m == 30) {
        return "half past " + currentHour;
    } else if (m == 45) {
        return "quarter to " + nextHour;
    } else if (m < 30) {
        String minLabel = (m == 1) ? " minute" : " minutes";
        return words[m] + minLabel + " past " + currentHour;
    } else {
        int minutesTo = 60 - m;
        String minLabel = (minutesTo == 1) ? " minute" : " minutes";
        return words[minutesTo] + minLabel + " to " + nextHour;
    }

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int h = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.timeInWords(h, m);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
