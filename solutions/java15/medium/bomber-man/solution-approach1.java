// ──────────────────────────────────────────────────
// Link        https://www.hackerrank.com/challenges/bomber-man/problem?isFullScreen=true
// Problem     The Bomberman Game
// Difficulty  Medium
// Subdomain   Implementation
// Platform    HackerRank
// Language    java15
// Status      Accepted
// Submitted   2026-09-06, 12:53 a.m.
// ──────────────────────────────────────────────────

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'bomberMan' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING_ARRAY grid
     */

    public static List<String> bomberMan(int n, List<String> grid) {

        int r = grid.size();
        int c = grid.get(0).length();

        // After 1 second, nothing changes.
        if (n == 1) {
            return grid;
        }

        // Every even second the entire grid is filled with bombs.
        if (n % 2 == 0) {
            List<String> full = new ArrayList<>();

            String row = "O".repeat(c);

            for (int i = 0; i < r; i++) {
                full.add(row);
            }

            return full;
        }

        /*
         * For odd n >= 3, there are only two repeating states.
         *
         * n % 4 == 3 -> explode the original bombs
         * n % 4 == 1 -> explode the bombs from the previous state
         */
        List<String> first = explode(grid);

        if (n % 4 == 3) {
            return first;
        }

        return explode(first);
    }

    /*
     * Creates a completely filled grid, then clears:
     * - every bomb position from the input
     * - its four neighboring cells
     *
     * This simulates the explosion of all bombs in the input grid.
     */
    private static List<String> explode(List<String> grid) {

        int r = grid.size();
        int c = grid.get(0).length();

        char[][] result = new char[r][c];

        // Initially, all cells contain bombs.
        for (int i = 0; i < r; i++) {
            Arrays.fill(result[i], 'O');
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (int i = 0; i < r; i++) {

            for (int j = 0; j < c; j++) {

                if (grid.get(i).charAt(j) == 'O') {

                    // Clear the bomb itself.
                    result[i][j] = '.';

                    // Clear its four neighbors.
                    for (int d = 0; d < 4; d++) {

                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if (nr >= 0 && nr < r &&
                            nc >= 0 && nc < c) {

                            result[nr][nc] = '.';
                        }
                    }
                }
            }
        }

        List<String> ans = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            ans.add(new String(result[i]));
        }

        return ans;
    }
}

public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(System.in));

        BufferedWriter bufferedWriter =
                new BufferedWriter(
                        new FileWriter(
                                System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput =
                bufferedReader
                        .readLine()
                        .replaceAll("\\s+$", "")
                        .split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);
        int c = Integer.parseInt(firstMultipleInput[1]);
        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid =
                IntStream.range(0, r)
                        .mapToObj(i -> {
                            try {
                                return bufferedReader.readLine();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        })
                        .collect(toList());

        List<String> result =
                Result.bomberMan(n, grid);

        bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
