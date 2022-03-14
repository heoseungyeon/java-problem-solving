package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * solved.ac dp(7분) 1463 1로 만들기 풀이 방법: dp를 X+1 크기를 갖게 하고 INF로 초기화 시켜준뒤 조건에 맞을 경우 최소값으로 dp 갱신(dp[i]는
 * X에서 i가 되는데 사용되는 최소 횟수) INF로 초기화 한뒤 X->2까지 반복문 하는 것보다 1에서 X까지 도는게 더 빠르겠다.
 */

public class Main_1463 {


    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static int X;

    public static void main(String[] args) throws Exception {
        // 1. input
        X = Integer.parseInt(br.readLine());

        int cnt = 0;
        // 2. dp
        int[] dp = new int[X + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[X] = 0;
        for (int i = X; i >= 2; i--) {
            if (i % 3 == 0) {
                dp[i / 3] = Math.min(dp[i / 3], dp[i] + 1);
            }
            if (i % 2 == 0) {
                dp[i / 2] = Math.min(dp[i / 2], dp[i] + 1);
            }
            dp[i - 1] = Math.min(dp[i - 1], dp[i] + 1);
        }

        // 3. print
        System.out.println(dp[1]);
    }


}
