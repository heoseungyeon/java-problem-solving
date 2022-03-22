package baekjoon;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac dp(15:00 - 15:44) 4097 수익 풀이 방법: dp 배열에 i 인덱스까지 제일 수익이 많은 값을 갱신하고, answer에 최댓값을
 * 최신화하였음.
 */

public class Main_4097 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N;
    public static int[] revenue;
    public static int[] dp;

    public static void main(String[] args) throws Exception {
        // 1. input
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }
            revenue = new int[N];
            for (int i = 0; i < N; i++) {
                revenue[i] = Integer.parseInt(br.readLine());
            }

            // 2. dp
            dp = new int[N];
            for (int i = 0; i < N; i++) {
                dp[i] = revenue[i];
            }
            int answer = dp[0];

            for (int i = 1; i < N; i++) {
                if (revenue[i] + dp[i - 1] > dp[i]) {
                    dp[i] = revenue[i] + dp[i - 1];
                }
                answer = Math.max(dp[i], answer);
            }

            // 3. print
            System.out.println(answer);
        }

    }

}

