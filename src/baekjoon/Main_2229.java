package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac dp((오래 걸림 블로그 참고) 18:58 - 13:02) 2229 조짜기 풀이 방법: dp 점화식을 떠올리는게 어려워서 구글링 후 블로그를 읽으며
 * 이해했다. 나이 순으로 정렬된 것을 해치고 그룹핑하면 안되기에 첫번째 요소부터 순서대로 i번째가 들어왔을 때 역순으로 하나씩 그룹핑을 해보며 dp[i] 를 갱신한다.
 * 점화식으로 풀어보면 반복문을 돌며 dp[i] = Math.max(dp[i], max-min+dp[j-1]) 이 된다.
 */

public class Main_2229 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N;
    public static int[] scores;
    public static int[] dp;

    public static void main(String[] args) throws Exception {
        // 1. input
        N = Integer.parseInt(br.readLine());

        scores = new int[N + 1];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scores[i + 1] = Integer.parseInt(tokens.nextToken());
        }
        // 2. dp -> dp[i] 는 앞에 i명이 조가 될 때 최대 값
        dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int max = -1;
            int min = 10001;
            for (int j = i; j > 0; j--) {
                max = Math.max(max, scores[j]);
                min = Math.min(min, scores[j]);
                dp[i] = Math.max(dp[i], max - min + dp[j - 1]);
            }
        }

        // 3. print
        System.out.println(dp[N]);

    }


}
