package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 완전탐색 2502 떡 먹는 호랑이 풀이 방법: 첫째 날이 A 둘째 날이 B 라고 했을 떄, A와 B의 계수는 피보나치 수열(1 1 2 3 5 8 13
 * ...) 에서 각각 d-2, d-1 을 갖는다. 피보나치 수열을 저장한 dValue엔 0부터 시작하므로 d-3, d-2 로 계수를 설정해 정답을 탐색하였다.
 */

public class Main_2502 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int[] dValue = new int[31];
    public static int D, K;
    public static int A, B;

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());

        D = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        // 2. calculate dValue
        setDValue();

        // 3. calculate Answer
        findAnswer();

        // 4. print
        System.out.println(A);
        System.out.println(B);

    }

    private static void setDValue() {
        dValue[0] = 1;
        dValue[1] = 1;
        for (int i = 2; i < 31; i++) {
            dValue[i] = dValue[i - 2] + dValue[i - 1];
        }
    }

    private static void findAnswer() {
        int a = 1;
        while (true) {
            int fourB = (K - dValue[D - 3] * a);
            if (fourB % dValue[D - 2] == 0 && fourB / dValue[D - 2] >= a) {
                break;
            }
            a++;
        }
        A = a;
        B = (K - dValue[D - 3] * a) / dValue[D - 2];
    }

}
