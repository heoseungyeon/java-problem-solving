package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * solved.ac 이분탐색( 오래걸림 ) 2792 보석 상자 풀이 방법: 와 이 바보 , 0개로 나눌 수 없는데 start 를 0으로 설정하는 실수와 colors 배열을
 * N으로 잘못 초기화 했다. 으이구
 */

public class Main_2792 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static int N, M;
    public static int[] colors;
    public static int maxValue;
    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        colors = new int[M];
        for (int i = 0; i < M; i++) {
            colors[i] = Integer.parseInt(br.readLine());
            maxValue = Math.max(maxValue, colors[i]);
        }

        // 2. binary search
        binarySearch();

        System.out.println(answer);
    }

    private static void binarySearch() {
        int start = 1;
        int end = maxValue;
        while (start <= end) {
            int middle = (start + end) / 2;

            int cnt = calculateCnt(middle);
            if (cnt > N) {
                start = middle + 1;
            } else {
                end = middle - 1;
                answer = middle;
            }


        }
    }

    private static int calculateCnt(int middle) {
        int rtn = 0;
        for (int i = 0; i < M; i++) {
            rtn += colors[i] / middle;
            if (colors[i] % middle != 0) {
                rtn += 1;
            }
        }
        return rtn;
    }

}
