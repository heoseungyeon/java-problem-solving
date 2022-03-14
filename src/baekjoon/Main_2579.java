package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * solved.ac dp(10:39 - 11:31) 2579 계단 오르기 풀이 방법: dp[i][j] 는 i번째 계단에 j+1번 연속된 움직임으로 도착했을 경우 최대 값을
 * 의미한다. 문제의 조건에 맞게 1-n까지 반복문으로 dp값을 갱신하여 풀이
 */

public class Main_2579 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static void main(String[] args) throws Exception {
        // 1. input
        int n = Integer.parseInt(br.readLine());

        int[] stairs = new int[n + 3];
        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        // 2. dp

        int[][] dp = new int[n + 3][2];

        dp[1][0] = stairs[1];
        dp[2][0] = stairs[2];

        for (int i = 1; i <= n; i++) {
            dp[i + 1][1] = Math.max(dp[i + 1][1], dp[i][0] + stairs[i + 1]);
            dp[i + 2][0] = Math.max(dp[i + 2][0], dp[i][0] + stairs[i + 2]);
            dp[i + 2][0] = Math.max(dp[i + 2][0], dp[i][1] + stairs[i + 2]);
        }

        // 3. print
        int answer = Math.max(dp[n][0], dp[n][1]);

        System.out.println(answer);
    }

}
