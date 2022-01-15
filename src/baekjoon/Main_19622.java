package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac dp 19622 회의실 배정2 풀이 방법: 시작시간, 끝나는 시간은 중요하지 않음. 임의의 회의 K(1≤ K ≤ N)는 회의 K − 1과 회의 K + 1과는
 * 회의 시간이 겹치고 다른 회의들과는 회의 시간이 겹치지 않는다. 위 조건을 통해 dp로 해결 dp[i] = Math.max(dp[i-2] + dp[i-3]) 점화식을 통해
 * dp[i] 값 최신화
 */

public class Main_19622 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static int N;
    // 회의의 시작시간, 끝나는 시간, 회의 인원
    public static int[][] info;
    public static int[] dp;

    public static void main(String[] args) throws Exception {

        // 1. input
        N = Integer.parseInt(br.readLine());

        info = new int[N + 2][3];
        dp = new int[N + 2];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(tokens.nextToken());
            info[i][1] = Integer.parseInt(tokens.nextToken());
            info[i][2] = Integer.parseInt(tokens.nextToken());
        }

        // 2. dp
        dp[0] = info[0][2];
        dp[1] = info[1][2];
        dp[2] = info[0][2] + info[2][2];
        for (int i = 3; i < N; i++) {
            dp[i] = info[i][2];
            dp[i] += Math.max(dp[i - 2], dp[i - 3]);
        }

        // 3. print
        if (N < 2) {
            System.out.println(dp[N - 1]);
        } else {
            System.out.println(Math.max(dp[N - 1], dp[N - 2]));
        }
    }

}
