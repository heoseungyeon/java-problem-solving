package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac dp(20분 > 21:47 - 10:07) 11051 이항 계수 2 풀이 방법 : 이항 계수는 이항 정리를 따르며, 파스칼의 삼각형 원리를 갖고 있다는 점을
 * 활용하여 dp[0][0] 부터 dp 값을 바텀업 방식으로 갱신해나갔음.
 */

public class Main_11051 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, K;
    public static int[][] dp;

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        // 2. dp
        dp = new int[N + 1][N + 2];
        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i + 1; j++) {
                dp[i + 1][j] += dp[i][j] % 10007;
                dp[i + 1][j + 1] += dp[i][j] % 10007;
            }
        }

        System.out.println(dp[N][K]);


    }
}
