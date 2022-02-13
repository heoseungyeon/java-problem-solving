package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 백트래킹((44분) 14:04 - 14:48) 15649 N과 M (1) 풀이 방법: 백트래킹 방식으로 visited 처리를 하고 백트래킹 함수에 word
 * (String)을 전달해주는 방식으로 문제 풀이
 */

public class Main_15649 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static boolean[] visited;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        // 2. back - tracking
        visited = new boolean[N + 1];
        backTracking("", 0);

        // 3. print
        System.out.println(sb.toString());
    }

    private static void backTracking(String word, int cnt) {
        if (cnt == M) {
            sb.append(word + "\n");
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backTracking(word + i + " ", cnt + 1);
                visited[i] = false;
            }
        }
    }

}
