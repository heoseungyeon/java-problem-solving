package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * solved.ac 시뮬레이션(1시간 22분 > 18:08 -  19:30) 2638 치즈 풀이 방법: 치즈로 둘러쌓인 부분을 구분하는 로직을 생각하느라 오래걸렸다.. 이
 * 부분에 대한 깔끔한 방법을 잘 숙지하고 있어야 할 듯! 문제 풀이는 치즈로 둘러쌓인 부분을 찾고, 치즈를 녹이는 것을 반복하여 풀이하였음!
 */

public class Main_2638 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static int cheezeSum = 0;
    public static int hour = 0;
    public static int[][] map;
    public static int[] dx = new int[]{0, 0, 1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0};
    public static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        // 1.input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
                if (map[i][j] == 1) {
                    cheezeSum += 1;
                }
            }
        }

        // 2. simulation
        while (cheezeSum != 0) {
            hour += 1;
            initInnerSpace();
            List<Point> disappearCheezes = findDisappearCheeze();
            for (Point cheeze : disappearCheezes) {
                map[cheeze.x][cheeze.y] = 0;
            }
            cheezeSum -= disappearCheezes.size();
        }

        System.out.println(hour);

    }

    private static List<Point> findDisappearCheeze() {
        List<Point> disappearCheezes = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int r = i + dx[k];
                        int c = j + dy[k];
                        if (rangeCheck(r, c) && map[r][c] != -hour && map[r][c] != 1) {
                            cnt += 1;
                        }
                    }
                    if (cnt >= 2) {
                        disappearCheezes.add(new Point(i, j));
                    }
                }
            }
        }
        return disappearCheezes;
    }

    private static void initInnerSpace() {
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] <= 0) {

                    if (!bfs(i, j)) {
                        for (int k = 0; k < N; k++) {
                            for (int l = 0; l < M; l++) {
                                if (map[k][l] == hour * (-10)) {
                                    map[k][l] = 0;
                                }
                            }
                        }
                    } else {
                        for (int k = 0; k < N; k++) {
                            for (int l = 0; l < M; l++) {
                                if (map[k][l] == hour * (-10)) {
                                    map[k][l] = -hour;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static boolean bfs(int row, int col) {
        boolean rtn = true;
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col));
        map[row][col] = hour * (-10);
        visited[row][col] = true;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            for (int i = 0; i < 4; i++) {
                int r = point.x + dx[i];
                int c = point.y + dy[i];
                if (!rangeCheck(r, c)) {
                    rtn = false;
                } else {
                    if (map[r][c] <= 0 && !visited[r][c]) {
                        map[r][c] = hour * (-10);
                        queue.add(new Point(r, c));
                        visited[r][c] = true;
                    }
                }
            }
        }
        return rtn;
    }

    public static boolean rangeCheck(int r, int c) {
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
