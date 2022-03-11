package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 완탐(10분) 19949 영재의 시험 풀이 방법: 문제 조건에 맞게 재귀를 통해 풀이
 */

public class Main_19949 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int[] answers = new int[10];

    public static void main(String[] args) throws Exception {
        // 1.input
        tokens = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            answers[i] = Integer.parseInt(tokens.nextToken());
        }

        // 2. find
        int result = findCase(0, 0, 0, 0);

        // 3. print
        System.out.println(result);
    }

    private static int findCase(int now, int cnt, int order, int correct) {
        int rst = 0;
        // 탈출 조건
        if (order == 10) {
            if (correct >= 5) {
                return 1;
            } else {
                return 0;
            }
        }
        // recursion
        for (int i = 1; i <= 5; i++) {
            if (cnt == 2) {
                if (i != now) {
                    int nextCorrect = answers[order] == i ? correct + 1 : correct;
                    rst += findCase(i, 1, order + 1, nextCorrect);
                }
            } else {
                int nextCnt = i == now ? cnt + 1 : cnt;
                if (now == 0) {
                    nextCnt = 1;
                }
                int nextCorrect = answers[order] == i ? correct + 1 : correct;
                rst += findCase(i, nextCnt, order + 1, nextCorrect);
            }
        }

        return rst;
    }

}
