package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 백트래킹((6분) 14:53 - 14:59 15650 N과 M (2) 풀이 방법: N과 M (1) 과 동일하게 백트래킹으로 하되 추가 조건인 오름차순을 위해
 * 이전 숫자(maxNum)을 인자로 추가하고 visited 과 함께 maxNum보다 큰 지 검사하여 출력했다.
 */

public class Main_15650 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static StringBuilder sb = new StringBuilder();
    public static boolean[] visited;

    public static void main(String[] args) throws Exception {

        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        // 2. get permutation
        visited = new boolean[N + 1];
        permutation("", -1, 0);

        // 3. print
        System.out.println(sb.toString());

    }

    private static void permutation(String word, int maxNum, int depth) {
        if (depth == M) {
            sb.append(word + "\n");
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i] && i > maxNum) {
                visited[i] = true;
                permutation(word + i + " ", i, depth + 1);
                visited[i] = false;
            }

        }
    }

}
