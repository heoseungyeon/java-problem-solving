package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 완탐(11:48 - 12:48) 3711 학번 풀이 방법: 와 처음엔 m값을 1씩 증가시킬 때마다 visited 배열을 초기화 했지만 그럴 필요없이 m값을
 * visited에 넣으면 맨 처음에만 visited 배열을 초기화시키면 된다.. ㄷ ㄷ
 */


public class Main_3711 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, G;
    public static int[] studentIds;

    public static void main(String[] args) throws Exception {
        // 1. input
        N = Integer.parseInt(br.readLine());

        while (N > 0) {
            G = Integer.parseInt(br.readLine());

            studentIds = new int[G];
            for (int i = 0; i < G; i++) {
                studentIds[i] = Integer.parseInt(br.readLine());
            }

            // 2. bruteforce
            int m = 1;
            int[] visited = new int[1000000];
            while (true) {
                boolean isAnswer = true;

                for (int i = 0; i < G; i++) {
                    if (visited[studentIds[i] % m] == m) {
                        isAnswer = false;
                        break;
                    }
                    visited[studentIds[i] % m] = m;
                }
                if (isAnswer) {
                    break;
                }
                m++;
            }

            System.out.println(m);
            N--;
        }

    }

}
