package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac dp(11:05 - 11:35) 22857 가장 긴 짝수 부분 수열(small) 풀이 방법: 약간 보고 1600 말이 되고싶은 원숭이 문제가 떠오름. 그리고
 * K를 다 쓸필요 없다는 점도 인지하고 풀어야할듯.
 */

public class Main_22857 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, K;
    public static int[] arr;
    public static int[][] dp;
    public static int answer = 0;


    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        arr = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokens.nextToken());
        }

        // 2. dp
        dp = new int[N][K + 1];
        // init
        for (int i = 0; i < N; i++) {
            if (arr[i] % 2 == 0) {
                dp[i][0] = 1;
            }
        }

        answer = dp[0][0];
        for (int i = 1; i < N; i++) {
            if (arr[i] % 2 == 0) {
                for (int j = 0; j <= K; j++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + 1);
                    answer = Math.max(dp[i][j], answer);
                }
            } else {
                for (int j = 0; j < K; j++) {
                    dp[i][j + 1] = Math.max(dp[i][j], dp[i - 1][j]);
                    answer = Math.max(dp[i][j + 1], answer);
                }
            }
        }

        // 3. print
        System.out.println(answer);
    }

}
