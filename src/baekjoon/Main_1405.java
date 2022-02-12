package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac dfs((1시간 10분)14:39 - 15:49)  1405 미친 로봇 풀이 방법: N,N 좌표 기준 N번 이동해도 배열 밖을 벗어나지 않도록 하기 위해
 * 2*N+1, 2*N+1 크기의 배열을 선언하고 dfs를 돌려 정답을 구함.
 */

public class Main_1405 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N;
    public static int[][] map;
    public static int[] dx = new int[]{0, 0, 1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0};
    public static double[] percentages = new double[4];
    public static boolean[][] visited;

    public static void main(String[] args) throws Exception {

        // 1.input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        map = new int[2 * N + 1][2 * N + 1];

        for (int i = 0; i < 4; i++) {
            percentages[i] = Integer.parseInt(tokens.nextToken()) / (double) 100;
        }
        // 2.dfs
        visited = new boolean[2 * N + 1][2 * N + 1];
        visited[N][N] = true;
        double answer = dfs(0, N, N, 1);

        // 3. print
        System.out.println(String.format("%.12f", answer));
    }

    private static double dfs(int cnt, int row, int col, double per) {
        double sum = 0;
        if (cnt == N) {
            return per;
        }
        for (int i = 0; i < 4; i++) {
            int r = row + dx[i];
            int c = col + dy[i];
            if (!visited[r][c]) {
                visited[r][c] = true;
                sum += dfs(cnt + 1, r, c, per * percentages[i]);
                visited[r][c] = false;
            }

        }

        return sum;
    }

}
