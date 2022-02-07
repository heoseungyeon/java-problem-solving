package baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * solved.ac 다익스트라 1584 게임 풀이 방법: 다익스트라 + PQ를 통해 문제 풀이, 개선 사항 : 위험 구간 + 죽음 구간을 계속 계산하지 않고 처음에 입력 받았을
 * 경우 2차원 정수형 배열에 flag 값을 초기화 시켜 나중의 시간복잡도를 줄임.
 */

public class Main_1584 {

    public static final int INF = Integer.MAX_VALUE;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    //    public static List<Zone> dangerousZones = new ArrayList<>();
//    public static List<Zone> deathZones = new ArrayList<>();
    public static int[][] maps = new int[501][501];
    public static int[][] distances;
    public static int[] dx = new int[]{1, -1, 0, 0};
    public static int[] dy = new int[]{0, 0, 1, -1};


    public static void main(String[] args) throws Exception {
        // 1. input
        N = Integer.parseInt(br.readLine());
        // dangerous Zone
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            int d = Integer.parseInt(tokens.nextToken());
            setMaps(a, b, c, d, 1);
        }
        // death Zone
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            int d = Integer.parseInt(tokens.nextToken());
            setMaps(a, b, c, d, -1);
        }

        moveDijkstra();

        if (distances[500][500] == INF) {
            System.out.println(-1);
        } else {
            System.out.println(distances[500][500]);
        }

    }

    private static void setMaps(int a, int b, int c, int d, int flag) {
        int minX = Math.min(a, c);
        int minY = Math.min(b, d);
        int maxX = Math.max(a, c);
        int maxY = Math.max(b, d);
        int cnt = 0;
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                maps[i][j] = flag;
                cnt += 1;
            }
        }
    }

    private static void moveDijkstra() {
        // distance init
        distances = new int[501][501];
        for (int i = 0; i < 501; i++) {
            for (int j = 0; j < 501; j++) {
                distances[i][j] = INF;
            }
        }
        Queue<Info> queue = new PriorityQueue<>();
        queue.add(new Info(0, new Point(0, 0)));
        while (!queue.isEmpty()) {
            Info info = queue.poll();
            if (info.point.x == 500 && info.point.y == 500) {
                break;
            }
            if (distances[info.point.x][info.point.y] < info.cost) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int r = info.point.x + dx[i];
                int c = info.point.y + dy[i];

                if (0 <= r && r < 501 && 0 <= c && c < 501) {
                    if (maps[r][c] == -1) {
                        continue;
                    } else {
                        if (maps[r][c] == 1) {
                            if (distances[r][c] > info.cost + 1) {
                                distances[r][c] = Math.min(distances[r][c], info.cost + 1);
                                queue.add(new Info(info.cost + 1, new Point(r, c)));
                            }

                        } else {
                            if (distances[r][c] > info.cost) {
                                distances[r][c] = Math.min(distances[r][c], info.cost);
                                queue.add(new Info(info.cost, new Point(r, c)));
                            }
                        }
                    }
                }
            }
        }

    }

//    private static boolean checkDangerousRange(int r, int c) {
//        for (Zone zone : dangerousZones) {
//            int minX = Math.min(zone.start.x, zone.end.x);
//            int minY = Math.min(zone.start.y, zone.end.y);
//            int maxX = Math.max(zone.start.x, zone.end.x);
//            int maxY = Math.max(zone.start.y, zone.end.y);
//            if (minX <= r && minY <= c && maxX >= r && maxY >= c) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean checkDeathRange(int r, int c) {
//        for (Zone zone : deathZones) {
//            int minX = Math.min(zone.start.x, zone.end.x);
//            int minY = Math.min(zone.start.y, zone.end.y);
//            int maxX = Math.max(zone.start.x, zone.end.x);
//            int maxY = Math.max(zone.start.y, zone.end.y);
//            if (minX <= r && minY <= c && maxX >= r && maxY >= c) {
//                return true;
//            }
//        }
//        return false;
//    }

    public static class Info implements Comparable<Info> {

        int cost;
        Point point;

        public Info(int cost, Point point) {
            this.cost = cost;
            this.point = point;
        }

        @Override
        public int compareTo(Info o) {
            if (this.cost > o.cost) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "Info{" +
                "cost=" + cost +
                ", point=" + point +
                '}';
        }
    }

    public static class Zone {

        Point start;
        Point end;

        public Zone(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Zone{" +
                "start=" + start +
                ", end=" + end +
                '}';
        }
    }

}
