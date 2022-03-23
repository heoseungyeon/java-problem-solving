package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac dp(16:39 - 16:51) 1149 RGB거리 풀이 방법: 기본적인 dp 문제, 3가지 규칙이 있지만 결국 하나로 통합하면 이전꺼랑 다른 색깔을
 * 고르라는 규칙임. 해당 규칙에 맞게 min 값을 dp 에 넣어주면서 풀이하였음.
 */

public class Main_1149 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N;
    public static int[][] rgb;
    public static int[][] dp;
    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        // 1. input
        N = Integer.parseInt(br.readLine());

        rgb = new int[N][3];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(tokens.nextToken());
            int g = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            rgb[i][0] = r;
            rgb[i][1] = g;
            rgb[i][2] = b;
        }

        // 2. dp
        /*
         * 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
         * N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
         * i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
         */
        dp = new int[N][3];
        dp[0][0] = rgb[0][0];
        dp[0][1] = rgb[0][1];
        dp[0][2] = rgb[0][2];

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1] + rgb[i][0], dp[i - 1][2] + rgb[i][0]);
            dp[i][1] = Math.min(dp[i - 1][0] + rgb[i][1], dp[i - 1][2] + rgb[i][1]);
            dp[i][2] = Math.min(dp[i - 1][0] + rgb[i][2], dp[i - 1][1] + rgb[i][2]);
        }

        // 3. print
        answer = Math.min(dp[N - 1][2], Math.min(dp[N - 1][0], dp[N - 1][1]));
        System.out.println(answer);


    }

}
