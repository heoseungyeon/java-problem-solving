package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * solved.ac 투포인터 2470 두 용액 풀이 방법: 투 포인터 사용하여 두 용액의 합이 음수,양수일 경우 분리해서 포인터 이동 나중에 이분탐색 풀이도 해볼 필요가 있을
 * 듯.
 */

public class Main_2470 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static int N;

    public static int[] solutions;

    public static int lowAnswer, highAnswer, minSum;

    public static void main(String[] args) throws Exception {
        // 1. input
        N = Integer.parseInt(br.readLine());

        solutions = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(tokens.nextToken());
        }

        // 2. sort
        Arrays.sort(solutions);

        // 3. two pointer algorithm
        minSum = Integer.MAX_VALUE;
        int start = 0, end = N - 1;
        while (start < end && start <= N - 2 && end >= 1) {
            int tmp = solutions[start] + solutions[end];
            if (tmp == 0) {
                lowAnswer = solutions[start];
                highAnswer = solutions[end];
                break;
            }
            if (tmp < 0) {
                if (Math.abs(tmp) < minSum) {
                    lowAnswer = solutions[start];
                    highAnswer = solutions[end];
                    minSum = Math.abs(tmp);
                }
                start++;
            } else {
                if (Math.abs(tmp) < minSum) {
                    lowAnswer = solutions[start];
                    highAnswer = solutions[end];
                    minSum = Math.abs(tmp);
                }
                end--;
            }

        }// while

        System.out.println(lowAnswer + " " + highAnswer);

    }

}
