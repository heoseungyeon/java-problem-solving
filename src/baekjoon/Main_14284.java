package baekjoon;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * solved.ac 다익스트라 ( 32 분 > 13:05 - 13:33 / 15:41 - 15:45) 14284 간선 이어가기 2 풀이 방법: 처음에 dfs 로 풀었는데
 * 시간초과..ㅎ 그래서 가중치가 있을 때 시간복잡도를 줄일 수 있는 다익스트라로 풀이.
 */

public class Main_14284 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int n, m; // 정점 및 간선의 갯수
    public static int s, t;
    public static ArrayList<Edge>[] edges;
    public static int[] distance;

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        edges = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }

        tokens = new StringTokenizer(br.readLine());
        s = Integer.parseInt(tokens.nextToken());
        t = Integer.parseInt(tokens.nextToken());

        distance = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        // 2. dijkstra
        distance[1] = 0;
        dijkstra();

        // 3. print
        System.out.println(distance[t]);
    }

    private static void dijkstra() {
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        for (Edge edge : edges[s]) {
            queue.add(new Edge(edge.vertex, edge.cost));
            distance[edge.vertex] = edge.cost;
        }

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            if (edge.vertex == t) {
                return;
            }

            if (edge.cost > distance[edge.vertex]) {
                continue;
            }

            for (Edge nowEdge : edges[edge.vertex]) {

                if (distance[nowEdge.vertex] > nowEdge.cost + edge.cost) {
                    queue.add(new Edge(nowEdge.vertex, nowEdge.cost + edge.cost));
                    distance[nowEdge.vertex] = nowEdge.cost + edge.cost;
                }
            }


        }
    }

//    private static void dfs(int node, int cost) {
//        if (node == t) {
//            answer = Math.min(cost, answer);
//        }
//
//        for (Edge edge : edges[node]) {
//            if (!visited[edge.vertex]) {
//                visited[edge.vertex] = true;
//                dfs(edge.vertex, cost + edge.cost);
//                visited[edge.vertex] = false;
//            }
//        }
//    }

    public static class Edge implements Comparable<Edge> {

        private int vertex;
        private int cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
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
    }

}
