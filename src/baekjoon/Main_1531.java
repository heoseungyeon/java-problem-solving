package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1531 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static int[][] map;

    public static void main(String[] args) throws Exception {

        map = new int[101][101];

        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(tokens.nextToken());
            int y1 = Integer.parseInt(tokens.nextToken());
            int x2 = Integer.parseInt(tokens.nextToken());
            int y2 = Integer.parseInt(tokens.nextToken());

            for (int j = x1; j <= x2; j++) {
                for (int k = y1; k <= y2; k++) {
                    map[j][k] += 1;
                }
            }
        }

        int answer = 0;

        for (int i = 1; i < 101; i++) {
            for (int j = 1; j < 101; j++) {
                if (map[i][j] > M) {
                    answer += 1;
                }
            }

        }

        System.out.println(answer);

    }

}
