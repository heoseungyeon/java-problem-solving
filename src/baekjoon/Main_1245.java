package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * solved.ac bfs/dfs(18:48 - 19:19) 1245 농장관리 풀이 방법: 좋은 문제인듯. dfs로 푸는게 제일 간편하지만 bfs로 풀이해도 되는 걸 알려주는
 * 문제. bfs 탐색 시, 기준이 되는 위치의 값보다 큰 놈이 범위에 있으면 카운트를 하지 않고, (값이 같고+ 방문하지 않았을 경우)만 카운팅
 */

public class Main_1245 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static int[][] maps;
    public static boolean[][] visited;
    public static int[] dx = new int[]{0, 0, 1, -1, 1, 1, -1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0, -1, 1, -1, 1};


    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        maps = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        // 2. bfs
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i][j] > 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    if (bfs(i, j, maps[i][j])) {
                        cnt += 1;
                    }
                }
            }
        }

        // 3. print
        System.out.println(cnt);

    }

    private static boolean bfs(int row, int col, int value) {
        boolean isPossible = true;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col));
        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for (int i = 0; i < 8; i++) {
                int r = point.x + dx[i];
                int c = point.y + dy[i];
                if (rangeCheck(r, c)) {
                    if (maps[r][c] > value) {
                        isPossible = false;
                    }
                    if (!visited[r][c] && maps[r][c] == value) {
                        queue.add(new Point(r, c));
                        visited[r][c] = true;
                    }

                }
            }
        }

        return isPossible;
    }

    private static boolean rangeCheck(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

    public static class Point {

        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
