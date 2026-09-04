// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/matrix-rotation-algo/problem?isFullScreen=true
// Problem     Matrix Layer Rotation 
// Difficulty  Hard
// Subdomain   Implementation
// Platform    HackerRank
// Language    java15
// Status      Accepted
// Submitted   2026-09-04, 07:39 p.m.
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
     * Complete the 'matrixRotation' function below.
     *
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY matrix
     *  2. INTEGER r
     */

    public static void matrixRotation(List<List<Integer>> matrix, int r) {
    // Write your code here
    int m = matrix.size();
    int n = matrix.get(0).size();

    int layers = Math.min(m, n) / 2;

    for (int layer = 0; layer < layers; layer++) {

        List<Integer> elements = new ArrayList<>();

        int top = layer;
        int left = layer;
        int bottom = m - 1 - layer;
        int right = n - 1 - layer;

        // Top row: left -> right
        for (int j = left; j <= right; j++) {
            elements.add(matrix.get(top).get(j));
        }

        // Right column: top+1 -> bottom
        for (int i = top + 1; i <= bottom; i++) {
            elements.add(matrix.get(i).get(right));
        }

        // Bottom row: right-1 -> left
        for (int j = right - 1; j >= left; j--) {
            elements.add(matrix.get(bottom).get(j));
        }

        // Left column: bottom-1 -> top+1
        for (int i = bottom - 1; i > top; i--) {
            elements.add(matrix.get(i).get(left));
        }

        int len = elements.size();
        int shift = r % len;

        int index = 0;

        // Top row
        for (int j = left; j <= right; j++) {
            matrix.get(top).set(j, elements.get((index++ + shift) % len));
        }

        // Right column
        for (int i = top + 1; i <= bottom; i++) {
            matrix.get(i).set(right, elements.get((index++ + shift) % len));
        }

        // Bottom row
        for (int j = right - 1; j >= left; j--) {
            matrix.get(bottom).set(j, elements.get((index++ + shift) % len));
        }

        // Left column
        for (int i = bottom - 1; i > top; i--) {
            matrix.get(i).set(left, elements.get((index++ + shift) % len));
        }
    }

    // Print matrix
    for (List<Integer> row : matrix) {
        System.out.println(
            row.stream()
               .map(String::valueOf)
               .collect(Collectors.joining(" "))
        );
    }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int n = Integer.parseInt(firstMultipleInput[1]);

        int r = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Result.matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
