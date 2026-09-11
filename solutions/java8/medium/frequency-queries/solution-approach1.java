// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/frequency-queries/problem?isFullScreen=true
// Problem     Frequency Queries
// Difficulty  Medium
// Subdomain   N/A
// Platform    HackerRank
// Language    java8
// Status      Accepted
// Submitted   2026-09-11, 11:41 p.m.
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

public class Solution {

    
static List<Integer> freqQuery(List<List<Integer>> queries) {

    List<Integer> ans = new ArrayList<>();

    // value -> frequency
    HashMap<Integer, Integer> freq = new HashMap<>();

    // frequency -> number of values having that frequency
    HashMap<Integer, Integer> count = new HashMap<>();

    for (List<Integer> q : queries) {

        int option = q.get(0);
        int data = q.get(1);

        if (option == 1) {

            
            int oldFreq = freq.getOrDefault(data, 0);

            
            int newFreq = oldFreq + 1;

            freq.put(data, newFreq);
            
            if (oldFreq > 0) {
                count.put(oldFreq, count.get(oldFreq) - 1);
            }

            
            count.put(newFreq, count.getOrDefault(newFreq, 0) + 1);

        } 
        else if (option == 2) {

            if (freq.containsKey(data)) {

                int oldFreq = freq.get(data);
                int newFreq = oldFreq - 1;

                // Remove from old frequency group
                count.put(oldFreq, count.get(oldFreq) - 1);

                if (newFreq == 0) {
                    freq.remove(data);
                } 
                else {
                    freq.put(data, newFreq);

                    // Add to new frequency group
                    count.put(
                        newFreq,
                        count.getOrDefault(newFreq, 0) + 1
                    );
                }
            }

        } 
        else if (option == 3) {

            // Is there any value whose frequency is 'data'?
            if (count.getOrDefault(data, 0) > 0) {
                ans.add(1);
            } 
            else {
                ans.add(0);
            }
        }
    }

    return ans;
}



    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
