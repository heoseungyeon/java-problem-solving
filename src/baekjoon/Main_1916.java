package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * solved.ac 다익스트라( (8분) > 11:18 - 11:26 ) 1916 최소비용 구하기 풀이 방법: 기본적인 다익스트라 알고리즘 문제
 */

public class Main_1916 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static int start, end;
    public static List<Edge>[] edges;

    public static void main(String[] args) throws Exception {
        // 1. input
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        edges = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            edges[a].add(new Edge(b, c));
        }
        tokens = new StringTokenizer(br.readLine());
        start = Integer.parseInt(tokens.nextToken());
        end = Integer.parseInt(tokens.nextToken());

        // 2. dijkstra
        int answer = dijkstra(start, end);

        System.out.println(answer);
    }

    private static int dijkstra(int start, int end) {
        int[] distance = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (edge.node == end) {
                break;
            }
            if (edge.cost > distance[edge.node]) {
                continue;
            }
            for (Edge info : edges[edge.node]) {
                if (info.cost + edge.cost < distance[info.node]) {
                    distance[info.node] = info.cost + edge.cost;
                    pq.add(new Edge(info.node, info.cost + edge.cost));
                }
            }
        }

        return distance[end];
    }

    public static class Edge implements Comparable<Edge> {

        private int node;
        private int cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.cost > o.cost) {
                return 1;
            } else {
                return -1;
            }
        }

        @Override
        public String toString() {
            return "Edge{" +
                "node=" + node +
                ", cost=" + cost +
                '}';
        }
    }

}
