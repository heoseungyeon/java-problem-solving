package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 백트래킹((7분) 11:37 - 11:44 15650 N과 M (3) 풀이 방법: N과 M (1) 과 동일하게 백트래킹으로 하되 중복 허용이므로 방문처리는
 * 하지 않았음.
 */

public class Main_15651 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        // 2. get permutation
        permutation("", 0);

        // 3. print
        System.out.println(sb.toString());
    }

    private static void permutation(String s, int cnt) {
        if (cnt == M) {
            sb.append(s + "\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            permutation(s + i + " ", cnt + 1);
        }
    }
}
