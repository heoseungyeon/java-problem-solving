package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 플로이드 와샬(18:55 - 19:12) 11404 플로이드 풀이 방법: 비용은 자연수이므로 입력 받은 값을 map[][]에 넣어줄 때 0인지(최초로
 * 넣는지) 아닌지 조건을 통해 Math.min으로 값을 넣을지 안넣을지 결정한다. 또한 플로이드 와샬 알고리즘을 수행할 때도, 경유지를 거치는 2경우가 0이 아닌 경우에만
 * 계산하여 풀이하였다.
 */

public class Main_11404 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int n, m;
    public static int[][] map;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // 1. input
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        map = new int[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            map[a][b] = map[a][b] == 0 ? c : Math.min(map[a][b], c);
        }

        // 2. floyd warshall
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i != j && map[i][k] != 0 && map[k][j] != 0) {
                        map[i][j] = map[i][j] == 0 ? map[i][k] + map[k][j]
                            : Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }
        }

        // 3. print
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

}
