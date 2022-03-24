package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac dp(오래 걸림) 9465 스티커 풀이 방법: 열과 행 순으로 반복문을 돌며 왼쪽 아래, 왼쪽 위 대각선에 대해 dp 값을 갱신 시켜주었으며, 현재 바라보는
 * 스티커 왼쪽 값과 비교하여 현재 바라보는 스티커를 떼지 않을 경우와 값을 비교해 dp 값을 갱신시켜, [0][n-1], [1][n-1] 중 큰 값을 정답으로 설정하여
 * 풀이하였음.
 */

public class Main_9465 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int T, n;
    public static int[][] stickers;
    public static int[][] dp;
    public static int[] dx = new int[]{0, 1, -1}; // 하, 좌, 대각(왼아), 대각(왼위)
    public static int[] dy = new int[]{-1, -1, -1};
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // 1. input
        T = Integer.parseInt(br.readLine());

        while (T > 0) {
            n = Integer.parseInt(br.readLine());
            stickers = new int[2][n];

            for (int i = 0; i < 2; i++) {
                tokens = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    stickers[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }

            // 2. dp
            int answer = 0;
            dp = new int[2][n];

            for (int j = 0; j < n; j++) {
                for (int i = 0; i < 2; i++) {
                    int nowValue = dp[i][j];
                    for (int k = 0; k < 3; k++) {
                        int r = i + dx[k];
                        int c = j + dy[k];
                        if (k < 1) {
                            if (rangeCheck(r, c)) {
                                dp[i][j] = Math.max(dp[i][j], dp[r][c]);
                            }
                        } else {
                            if (rangeCheck(r, c)) {
                                dp[i][j] = Math.max(dp[i][j], dp[r][c] + stickers[i][j]);
                            } else {
                                dp[i][j] = Math.max(dp[i][j], stickers[i][j]);
                            }
                        }
                    }
                }
            }
            answer = Math.max(dp[0][n - 1], dp[1][n - 1]);

            sb.append(answer + "\n");

            T--;
        } // while

        // 3. print
        System.out.println(sb.toString());

    }

    public static boolean rangeCheck(int r, int c) {
        return 0 <= r && r < 2 && 0 <= c && c < n;
    }

}
