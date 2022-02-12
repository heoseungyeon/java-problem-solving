package baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * solved.ac bfs(12:56 -  9347 울타리 풀이 방법:
 */

public class Main_9347 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int[][] map;
    public static int[] dx = new int[]{0, 0, 1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0};
    public static int R, C;

    public static void main(String[] args) throws Exception {
        // 1. input
        int T = Integer.parseInt(br.readLine());

        while (T > 0) {
            tokens = new StringTokenizer(br.readLine());
            R = Integer.parseInt(tokens.nextToken());
            C = Integer.parseInt(tokens.nextToken());

            map = new int[R][C];

            for (int i = 0; i < R; i++) {
                tokens = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    map[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }

            wallNumbering();

            System.out.println();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }

            T--;
        }
    }


    private static void wallNumbering() {
        int cnt = 1;
        boolean[][] visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    Queue<Point> queue = new LinkedList<>();
                    queue.add(new Point(i, j));
                    visited[i][j] = true;
                    map[i][j] = cnt;

                    while (!queue.isEmpty()) {
                        Point point = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int r = point.x + dx[k];
                            int c = point.y + dy[k];

                            if (rangeCheck(r, c) && map[r][c] == 1 && !visited[r][c]) {
                                queue.add(new Point(r, c));
                                visited[r][c] = true;
                                map[r][c] = cnt;
                            }
                        }
                    }
                    cnt += 1;
                }
            }
        }


    }

    private static boolean rangeCheck(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    private int countFlowers(int row, int col) {
        int count = 0;
        boolean[][] visited = new boolean[R][C];
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(row, col));
        visited[row][col] = true;
        count += 1;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            for (int i = 0; i < 4; i++) {
                int r = point.x + dx[i];
                int c = point.y = dy[i];
                if (rangeCheck(r, c) && map[r][c] == 0 && !visited[r][c]) {
                    queue.add(new Point(r, c));
                    visited[r][c] = true;
                    count += 1;
                }
            }
        }

        return count;
    }

}
