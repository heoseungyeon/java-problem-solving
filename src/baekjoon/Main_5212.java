package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 구현 5212 지구 온난화 풀이 방법: counts 2차원 배열을 통해 4방향 바다 여부 값을 저장 (row, col을 1씩 증가해서 구현했으면 편했을
 * 듯), counts 배열 값을 통해 출력 범위 및 값 결정하여 풀이.
 */

public class Main_5212 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer tokens;
    public static int R, C;
    public static char[][] maps;
    public static int[][] counts;
    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int leftRow, rightRow, upCol, bottomCol;

    public static void main(String[] args) throws IOException {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        maps = new char[R][C];
        counts = new int[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                maps[i][j] = input.charAt(j);
                if (i == 0) {
                    counts[i][j]++;
                }
                if (i == R - 1) {
                    counts[i][j]++;
                }
                if (j == 0) {
                    counts[i][j]++;
                }
                if (j == C - 1) {
                    counts[i][j]++;
                }
                if (maps[i][j] == '.') {
                    counts[i][j] = 4;
                    for (int k = 0; k < 4; k++) {
                        int row = i + dx[k];
                        int col = j + dy[k];
                        if (0 <= row && row < R && 0 <= col && col < C) {
                            counts[row][col]++;
                        }
                    }
                }
            }
        }

        leftRow = 0;
        rightRow = R - 1;
        upCol = 0;
        bottomCol = C - 1;

        // 2. simulation
        while (checkRow(leftRow)) {
            leftRow++;
        }
        while (checkRow(rightRow)) {
            rightRow--;
        }
        while (checkCol(upCol)) {
            upCol++;
        }
        while (checkCol(bottomCol)) {
            bottomCol--;
        }

        // 3. print
        for (int i = 0; i < rightRow - leftRow + 1; i++) {
            for (int j = 0; j < bottomCol - upCol + 1; j++) {
                if (counts[leftRow + i][upCol + j] < 3) {
                    System.out.print('X');
                } else {
                    System.out.print('.');
                }

            }
            System.out.println();
        }

    }

    public static boolean checkRow(int row) {
        for (int i = 0; i < C; i++) {
            if (counts[row][i] < 3) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkCol(int col) {
        for (int i = 0; i < R; i++) {
            if (counts[i][col] < 3) {
                return false;
            }
        }
        return true;
    }
}
