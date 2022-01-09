package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac bfs 16401 과자 나눠주기 풀이 방법: 1~maxLength(과자 최대 갯수) 범위에서 이분탐색(조카에게 균등히 나눠 줄 수 있을 경우를 조건으로
 * 함)하여 문제 풀이
 */

public class Main_16401 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer tokens;

    public static int M, N;
    public static int[] snacks;
    public static int maxLength = -1;

    public static void main(String[] args) throws IOException {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        M = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());

        snacks = new int[N];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            snacks[i] = Integer.parseInt(tokens.nextToken());
            maxLength = Math.max(maxLength, snacks[i]);
        }

        // 2. binary search and print
        System.out.println(binarySearch(1, maxLength));
    }

    /**
     * sample input 3 10 1 2 3 4 5 6 7 8 9 10
     */
    private static int binarySearch(int start, int end) {
        int answer = 0;
        int sum;
        while (start <= end) {
            sum = 0;
            int middle = (start + end) / 2;
            for (int i = 0; i < N; i++) {
                sum += snacks[i] / middle;
            }
            if (sum >= M) {
                start = middle + 1;
                answer = middle;
            } else if (sum < M) {
                end = middle - 1;
            }
        }

        return answer;
    }

}
