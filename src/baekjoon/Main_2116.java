package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 구현(3시간44분... 12:37 - 16:21) 2116 주사위 쌓기 풀이 방법: 진짜 진짜 삽질하다가.. 의도치않게 리팩토링까지 하게돼서 좋긴 하지만..
 * find 메서드에 인자를 잘못 전달하여 틀렸다.. 풀이 방식은 재귀를 사용하여 1번째 주사위의 6가지 경우의 수에 대해 총 합을 return 받아 풀었다...
 */

public class Main_2116 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N;
    public static int[][] dices;
    public static int[] cases = new int[]{0, 2, 4, 3, 5, 1}; // 0,5 2,4 1,3
    public static int answer = 0;

    public static void main(String[] args) throws Exception {

        // 1. input
        N = Integer.parseInt(br.readLine());

        dices = new int[N][6];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dices[i][cases[j]] = Integer.parseInt(tokens.nextToken());
            }
        }

        // 2. find Answer
        for (int i = 0; i < 6; i++) {
            answer = Math.max(answer, find(dices[0][i], 0, calculateMaxNum(i, 0)));
        }

        // 3. print
        System.out.println(answer);
    }

    private static int calculateMaxNum(int bottomIdx, int order) {
        int maxNum = 0;
        for (int j = 0; j < 6; j++) {
            if ((j / 2) != (bottomIdx / 2)) {
                maxNum = Math.max(maxNum, dices[order][j]);
            }
        }
        return maxNum;
    }

    private static int find(int upperNum, int cnt, int sum) {
        if (cnt == N - 1) {
            return sum;
        }
        int bottomIdx = findBottomIdx(upperNum, cnt);
        // 아래 주사위의 윗면과 같은 숫자일 경우
        if (bottomIdx % 2 == 0) {
            sum = find(dices[cnt + 1][bottomIdx + 1], cnt + 1,
                sum + calculateMaxNum(bottomIdx, cnt + 1));
        } else {
            sum = find(dices[cnt + 1][bottomIdx - 1], cnt + 1,
                sum + calculateMaxNum(bottomIdx, cnt + 1));
        }

        return sum;
    }

    private static int findBottomIdx(int upperNum, int cnt) {
        int idx = 0;
        for (int i = 0; i < 6; i++) {
            if (upperNum == dices[cnt + 1][i]) {
                idx = i;
                break;
            }
        }
        return idx;
    }

}
