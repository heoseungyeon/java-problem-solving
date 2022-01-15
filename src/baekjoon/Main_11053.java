package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac dp 11053 LIS 기본 문제.. 아이디어가 굉장히 중요한 것 같음. 2중 반복문을 돌면서 j 반복문에선 0~i-1 를 순회 A[i] > A[j] 의
 * 조건과 dp[i] < dp[j] + 1 의 조건이 만족하면 dp[i]를 최신화하면 되는 것을 잘 알고 있어야 됨! 이분탐색으로 O(NlogN)도 만들 수 있다고 함.
 */

public class Main_11053 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static int N;
    public static int[] A;
    public static int[] dp;
    public static int answer = 1;

    public static void main(String[] args) throws Exception {
        // 1. input
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        dp = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(tokens.nextToken());
        }

        // 2. dp
        for (int i = 0; i < N; i++) {
            dp[i] = 1;

            for (int j = 0; j < i; j++) {
                if (A[j] < A[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    answer = Math.max(answer, dp[i]);
                }
            }
        }

        System.out.println(answer);

    }

}
