package baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * solved.ac bfs 9205 맥주 마시면서 걸어가기 풀이 방법: 모든 노드에 대해서 거리를 계산 후 이동 가능한 경우일 경우 queue에 삽입하는 방식으로 bfs 수행
 * , 플로이드 와샬로 어떻게 풀까?
 */

public class Main_9205 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer tokens;

    public static int MAX_DRINK = 20;
    public static int MAX_DISTANCE = 50;

    public static int t, n;
    // 0: 상근이네 집, 편의점, n+1: 펜타포트 락 페스티벌 좌표
    public static Point[] points;
    public static boolean[] visited;
    public static Queue<Point> q;

    public static void main(String[] args) throws IOException {

        // 1. input
        t = Integer.parseInt(br.readLine());

        while (t > 0) {
            n = Integer.parseInt(br.readLine());
            points = new Point[n + 2];
            visited = new boolean[n + 2];
            int x, y;
            for (int i = 0; i < n + 2; i++) {
                tokens = new StringTokenizer(br.readLine());
                x = Integer.parseInt(tokens.nextToken());
                y = Integer.parseInt(tokens.nextToken());
                points[i] = new Point(x, y);
            }

            // 2. bfs
            q = new LinkedList<>();
            sb.append(bfs() + "\n");
            t--;
        }
        // 3. print
        System.out.println(sb.toString());
    }

    public static String bfs() {
        q.add(new Point(points[0].x, points[0].y));
        visited[0] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();
            for (int i = 0; i < n + 2; i++) {
                if (!visited[i]
                    && calculateDistance(point, points[i]) <= MAX_DRINK * MAX_DISTANCE) {
                    if (i == n + 1) {
                        return "happy";
                    }
                    q.add(new Point(points[i].x, points[i].y));
                    visited[i] = true;
                }
            }
        }
        return "sad";
    }

    public static int calculateDistance(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

}
