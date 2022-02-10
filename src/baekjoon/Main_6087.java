package baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * solved.ac 다익스트라(12:23 - 1:37) 6087 레이저통신 풀이 방법: 다익스트라로 문제 풀이, 두가지 키 포인트가 있음. 1. 하나의 노드 최소 가중치에 대해
 * 4가지 방향으로 들어왔을 때에 대한 경우를 고려해야 한다는 점. 2. 최단거리가 아니기에 삥삥 돌아 오는 경우도 고려해야 한다는 점.
 */


public class Main_6087 {

    public static final int INF = Integer.MAX_VALUE;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int[][] map;
    public static int H, W;
    public static Point[] cPoints = new Point[2];
    public static int[] dx = new int[]{0, 0, 1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0};
    public static int[][][] turnabout;

    public static void main(String[] args) throws Exception {

        // 1. input
        tokens = new StringTokenizer(br.readLine());
        W = Integer.parseInt(tokens.nextToken());
        H = Integer.parseInt(tokens.nextToken());

        map = new int[H][W];

        int idx = 0;
        for (int i = 0; i < H; i++) {
            String input = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'C') {
                    cPoints[idx] = new Point(i, j);
                    idx++;
                }
            }
        }

        // 2. dijkstra
        int answer = dijkstra();

        // 3. print
        System.out.println(answer);

    }

    private static int dijkstra() {
        int minCnt = INF;
        // init turnabout
        turnabout = new int[H][W][4];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                for (int k = 0; k < 4; k++) {
                    turnabout[i][j][k] = INF;
                }
            }
        }
        PriorityQueue<Info> queue = new PriorityQueue<>();
        for (int i = 0; i < 4; i++) {
            int r = cPoints[0].x + dx[i];
            int c = cPoints[0].y + dy[i];
            if (rangeCheck(r, c)) {
                if (map[r][c] == '.') {
                    queue.add(new Info(r, c, 0, i));
                    turnabout[r][c][i] = 0;
                }
                if (r == cPoints[1].x && c == cPoints[1].y) {
                    return 0;
                }
            }
        }
        while (!queue.isEmpty()) {
            Info info = queue.poll();
            if (turnabout[info.x][info.y][info.direction] < info.cost) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int r = info.x + dx[i];
                int c = info.y + dy[i];
                if (rangeCheck(r, c)) {
                    if (map[r][c] == '.') {
                        int tmp = (info.direction / 2 == i / 2) ? 0 : 1;
                        if (turnabout[r][c][i] > info.cost + tmp) {
                            turnabout[r][c][i] = info.cost + tmp;
                            queue.add(new Info(r, c, info.cost + tmp, i));
                        }
                    }
                    if (r == cPoints[1].x && c == cPoints[1].y) {
                        int tmp = (info.direction / 2 == i / 2) ? 0 : 1;
                        minCnt = Math.min(minCnt, info.cost + tmp);
                    }

                }
            }
        } // while

        return minCnt;
    }

    private static boolean rangeCheck(int r, int c) {
        return 0 <= r && 0 <= c && r < H && c < W;
    }

    public static class Info implements Comparable<Info> {

        private int x;
        private int y;
        private int cost;
        private int direction;

        public Info(int x, int y, int cost, int direction) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.direction = direction;
        }

        @Override
        public int compareTo(Info o) {
            if (this.cost > o.cost) {
                return 1;
            } else {
                return -1;
            }
        }

        @Override
        public String toString() {
            return "Info{" +
                "x=" + x +
                ", y=" + y +
                ", cost=" + cost +
                ", direction=" + direction +
                '}';
        }
    }

}
