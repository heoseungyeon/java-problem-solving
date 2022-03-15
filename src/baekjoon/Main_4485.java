package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * solved.ac 다익스트라(11:00 - 11:16) 4485 녹색 옷 입은 애가 젤다지? 풀이 방법: cost는 링크가 가는 경로를 통해 잃은 비용을 의미하며,
 * distance 배열에 해당 위치에서 최소로 잃은 cost를 갱신하며 다익스트라 알고리즘으로 문제 풀이.
 */


public class Main_4485 {

    public static final int INF = Integer.MAX_VALUE;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int[] dx = new int[]{0, 0, 1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0};
    public static int[][] map;
    public static int[][] distance;
    public static int N;


    public static void main(String[] args) throws Exception {
        // 1. input
        int caseNum = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                break;
            }

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                tokens = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(tokens.nextToken());
                }
            }

            // 2. dijkstra
            distance = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    distance[i][j] = INF;
                }
            }

            dijkstra();

            // 3.print
            System.out.println("Problem " + caseNum + ": " + distance[N - 1][N - 1]);
            caseNum++;
        }
    }

    private static void dijkstra() {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(0, 0, map[0][0]));
        distance[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            Info info = pq.poll();
            if (info.cost > distance[info.x][info.y]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int r = info.x + dx[i];
                int c = info.y + dy[i];
                if (0 <= r && r < N && 0 <= c && c < N) {
                    if (map[r][c] + info.cost < distance[r][c]) {
                        distance[r][c] = map[r][c] + info.cost;
                        pq.add(new Info(r, c, distance[r][c]));
                    }
                }
            }
        }
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
