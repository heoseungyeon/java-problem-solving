package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 투 포인터 14921 용액 합성하기 풀이 방법: 두 용액 문제랑 동일하게 투포인터로 풀이, 단 정답이 두 용액을 더했을 때의 값이므로 출력 값을 위한 변수를
 * 따로 선언함.
 */

public class Main_14921 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static int N;
    public static int[] solutions;
    public static int absMinValue = Integer.MAX_VALUE;
    public static int minValue;

    public static void main(String[] args) throws Exception {
        // 1. input
        N = Integer.parseInt(br.readLine());
        solutions = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(tokens.nextToken());
        }

        // 2. two pointer
        int start = 0, end = N - 1;

        while (start < end) {
            int sum = solutions[start] + solutions[end];
            if (absMinValue > Math.abs(sum)) {
                minValue = sum;
                absMinValue = Math.abs(sum);
            }
            if (sum == 0) {
                absMinValue = 0;
                break;
            }
            if (sum < 0) {
                start++;
            } else {
                end--;
            }
        }
        System.out.println(minValue);
    }

}
