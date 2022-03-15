package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * solved.ac 다익스트라(10:40 - 10:57) 2665 미로 만들기 풀이 방법: cost 를 경로에서 검은 방을 지나친 갯수로 설정하고 다익스트라 알고리즘을
 * 통해을문제 풀이
 */

public class Main_2665 {

    public static final int INF = Integer.MAX_VALUE;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int n;
    public static int[][] map;
    public static int[][] distance;
    public static int[] dx = new int[]{0, 0, 1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        // 1.input
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(Character.toString(input.charAt(j)));
            }
        }

        distance = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = INF;
            }
        }

        // 2. dijkstra
        int result = dijkstra(0, 0);

        // 3. print
        System.out.println(result);
    }

    private static int dijkstra(int startX, int startY) {
        int result = 0;

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(startX, startY, 0));
        distance[startX][startY] = 0;

        while (!pq.isEmpty()) {
            Info info = pq.poll();

            if (info.cost > distance[info.x][info.y]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int r = info.x + dx[i];
                int c = info.y + dy[i];
                if (checkRange(r, c)) {
                    int hasWall = (map[r][c] + 1) % 2;
                    if (info.cost + hasWall < distance[r][c]) {
                        distance[r][c] = info.cost + hasWall;
                        pq.add(new Info(r, c, distance[r][c]));
                    }
                }
            }

        }

        result = distance[n - 1][n - 1];

        return result;
    }

    private static boolean checkRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }

    public static class Info implements Comparable<Info> {

        private int x;
        private int y;
        private int cost;

        public Info(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            if (this.cost > o.cost) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}
