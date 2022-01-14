package programmers.dp;

import java.util.HashSet;
import java.util.Set;

/**
 * 프로그래머스 고득점 kit N으로 표현 DP
 */

/*
 * 풀이 방법 : 후 파이썬으로 풀었던 문제이었는데 푸는데 1시간이 넘게 걸렸다.
 *  dp[i] +*-/ dp[cnt-i] 을 dp[cnt]에 넣고, number가 만들어졌는지 체크한다. dp[i]를 Set 자료형으로 만들어
 *  시간복잡도를 줄이는게 핵심!
 */

public class ExpressWithN {

    public static Set[] dp;
    public static int cnt = 1;

    public static void main(String[] args) {
        ExpressWithN expressWithN = new ExpressWithN();
        System.out.println(expressWithN.solution(5, 12));
    }

    // 5+55*5 300
    public int solution(int N, int number) {
        int answer = 0;

        dp = new Set[9];

        for (int i = 0; i < 9; i++) {
            dp[i] = new HashSet<>();
        }

        while (true) {
            if (cnt == 9) {
                return -1;
            }
            dp[cnt].add(sequenceNum(cnt, N));
            for (int i = 1; i <= cnt / 2; i++) {
                for (Object one : dp[i]) {
                    for (Object second : dp[cnt - i]) {
                        dp[cnt].add((int) one * (int) second);
                        dp[cnt].add((int) one + (int) second);
                        if ((int) second != 0) {
                            dp[cnt].add((int) one / (int) second);
                        }
                        if ((int) one != 0) {
                            dp[cnt].add((int) second / (int) one);
                        }
                        dp[cnt].add((int) one - (int) second);
                        dp[cnt].add((int) second - (int) one);
                    }
                }
            }
            if (dp[cnt].contains(number)) {
                answer = cnt;
                break;
            }
            cnt++;
        } // while
        answer = cnt;
        return answer;
    }

    public int sequenceNum(int iter, int num) {
        int rtn = 0;
        for (int i = 0; i < iter; i++) {
            rtn = rtn * 10 + num;
        }
        return rtn;
    }


}
