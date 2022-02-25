package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac bfs/dfs(74분 > 17:36 - 18:50) 2583 영역 구하기 풀이 방법: 어우 입력 좌표값이 정사각형이 아니라 점이여서 푸는데 좀 오래 걸렸다.
 * 입력받은 값으로 map을 채우고 bfs를 통해 넓이를 구함.
 */

public class Main_2583 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int M, N, K;
    public static int[][] map;
    public static boolean[][] visited;
    public static int[] dx = new int[]{0, 0, 1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        M = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        map = new int[M][N];
        // 2. fill map
        for (int i = 0; i < K; i++) {
            tokens = new StringTokenizer(br.readLine());
            int ly = Integer.parseInt(tokens.nextToken());
            int lx = (M - 1) - Integer.parseInt(tokens.nextToken());
            int ry = Integer.parseInt(tokens.nextToken());
            int rx = (M) - Integer.parseInt(tokens.nextToken());

            fill(ly, lx, ry, rx);
        }

        // 3. bfs
        List<Integer> answers = new ArrayList<>();
        visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    answers.add(bfs(i, j));
                }
            }
        }

        // 4. print
        Collections.sort(answers);
        System.out.println(answers.size());
        for (int i = 0; i < answers.size(); i++) {
            System.out.print(answers.get(i) + " ");
        }
    }

    private static int bfs(int row, int col) {
        int cnt = 1;
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(row, col));
        visited[row][col] = true;
        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int r = point.x + dx[i];
                int c = point.y + dy[i];

                if (rangeCheck(r, c) && !visited[r][c] && map[r][c] == 0) {
                    visited[r][c] = true;
                    queue.add(new Point(r, c));
                    cnt += 1;
                }
            }
        }

        return cnt;
    }

    private static boolean rangeCheck(int r, int c) {
        return r >= 0 && r < M && c >= 0 && c < N;
    }

    private static void fill(int ly, int lx, int ry, int rx) {
        for (int i = rx; i <= lx; i++) {
            for (int j = ly; j < ry; j++) {
                map[i][j] = 1;
            }
        }
    }

    public static class Point {

        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
        }
    }
}
