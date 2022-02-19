package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac dp( 84분 > 17:10 - 18:00 / 21:10 - 21:44) 4883 삼각그래프 풀이 방법: 0행의 dp 값을 초기화 시켜준 뒤, 1행 부터
 * 열을 기준으로 dp 값 최신화.
 */

public class Main_4883 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N;
    public static int[][] graph;
    public static int[][] dp;
    public static int[][] caseZero = new int[][]{{1, 0}, {1, 1}, {0, 1}};
    public static int[][] caseOne = new int[][]{{1, -1}, {1, 0}, {1, 1}, {0, 1}};
    public static int[][] caseTwo = new int[][]{{1, -1}, {1, 0}};


    public static void main(String[] args) throws Exception {
        // 1. input
        int k = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            graph = new int[N][3];
            dp = new int[N][3];

            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    graph[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }

            // 2. dp
            dp[0][0] = Integer.MAX_VALUE;
            dp[0][1] = graph[0][1];
            dp[0][2] = graph[0][2] + graph[0][1];

            for (int i = 1; i < N; i++) {
                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]) + graph[i][j];
                    }
                    if (j == 1) {
                        dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i - 1][j + 1]))) + graph[i][j];
                    }
                    if (j == 2) {
                        dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j - 1],
                            dp[i - 1][j])) + graph[i][j];
                    }

                }
            }

            // 3. print
            System.out.println(k + ". " + (dp[N - 1][1]));
            k++;
        } // while
    }

    private static boolean rangeCheck(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < 3;
    }

}
