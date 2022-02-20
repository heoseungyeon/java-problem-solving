package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 완전탐색(24분(참고) > 11:25 - 11:49) 16464 가주아 풀이 방법: 처음엔 a~b 의 합은 1~b - 1~(a+1) 합의 차인 점을 이용해
 * 이중 반복문을 돌렸으나 시간초과로 실패.. 좀 찾아보니 2의 제곱수면 답이 아닌 거였다.. 수학 대박..
 */

public class Main_16464 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, K;


    public static void main(String[] args) throws Exception {
        // 1. input
        N = Integer.parseInt(br.readLine());

        while (N > 0) {
            K = Integer.parseInt(br.readLine());
            // 2. check possible 3~8
            boolean answer = checkAnswer(K);
            if (answer) {
                System.out.println("Gazua");
            } else {
                System.out.println("GoHanGang");
            }
            N--;
        }
    }

    private static boolean checkAnswer(int k) {
        int idx = 0;
        while (Math.pow(2, idx) <= k) {
            if (Math.pow(2, idx) == k) {
                return false;
            }
            idx++;
        }
        return true;
    }


}
