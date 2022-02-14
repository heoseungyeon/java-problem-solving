package baekjoon;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac dp((1시간 7분) 17:42 - 18:49 16194 카드 구매하기 2 풀이 방법: dp[i] 는 dp[i-j] + dp[j]와 비교하여 최솟값을
 * 대입시키는 방식으로 점화식을 세웠다. 분명 간단한 문제이기에 20분안에 풀이를 했어야 했다.. dp 익숙해지자
 */

public class Main_16194 {

    public static final int INF = Integer.MAX_VALUE;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N;
    public static int[] cards;
    public static int[] dp;

    public static void main(String[] args) throws Exception {
        // 1. input
        N = Integer.parseInt(br.readLine());

        cards = new int[N + 1];
        dp = new int[N + 1];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i + 1] = Integer.parseInt(tokens.nextToken());
            dp[i + 1] = cards[i + 1];
        }

        // 2. dp
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j] + dp[j]);
            }
        }

        // 3. print
        System.out.println(dp[N]);
    }

}
