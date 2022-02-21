package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac dp( > 18:00 - 18:26) 11055 가장 큰 증가 부분 수열 풀이 방법: 가장 긴 증가 부분 수열(LIS) 알고리즘을 찾아 이해한 뒤 부분
 * 수열의 합을 구하는 것은 dp배열에 추가로 저장하며 문제를 풀이하였음.
 */

public class Main_11055 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N;
    public static int[] A;
    public static int[] length;
    public static int[] dp;
    public static int answer;

    public static void main(String[] args) throws Exception {
        // 1. input 1 100 2 50 60 3 5 6 7 8
        N = Integer.parseInt(br.readLine());

        A = new int[N];
        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(tokens.nextToken());
        }

        length = new int[N];
        dp = new int[N];

        // 모든 dp[i]의 최솟 값은 자기 자신
        for (int i = 0; i < N; i++) {
            dp[i] = A[i];
        }

        // 길이가 1일 때는 반복문에 들어가지 않음.
        answer = dp[0];
        // 2. dp
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    length[i] = Math.max(length[i], length[j] + 1);
                    dp[i] = Math.max(dp[i], dp[j] + A[i]);
                }
            }
            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);

    }
}
