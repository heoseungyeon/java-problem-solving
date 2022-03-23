package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 트리(15:50 - 16:18) 2078 무한이진트리 풀이 방법: 일반 재귀로 짰을 때, 타임아웃 났는데 풀이 보고 소름돋았다... (A,B)에서
 * (1,1)이 될때까지 반복문을 도는데 2가지 규칙을 사용한다. A>B일 경우, 부모 노드에서 왼쪽 방향으로 이동한 경우이므로, A/B(왼쪽 방향 이동횟수)를 추가하고,
 * A%B를 A값으로 갱신하여 위로 올라간다. 오른쪽도 마찬가지. 그리고 둘 중 하나가 1에 도달하면 (다른 값 - 1) 만큼 다른 방향으로의 이동횟수를 추가하여 문제를
 * 풀이한다.
 */

public class Main_2078 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static long A, B;
    public static int L, R;

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());

        A = Long.parseLong(tokens.nextToken());
        B = Long.parseLong(tokens.nextToken());

        // left move -> a + b , b
        // right move -> a , a + b
        // 2. move
        reverseMove(A, B);

        // 3. print
        System.out.println(L + " " + R);

    }

    private static void reverseMove(long x, long y) {
        while (true) {
            if (x == 1) {
                R += (y - 1);
                break;
            } else if (y == 1) {
                L += (x - 1);
                break;
            }
            if (x > y) {
                L += x / y;
                x = x % y;
            } else {
                R += y / x;
                y = y % x;
            }
        }
    }

}
