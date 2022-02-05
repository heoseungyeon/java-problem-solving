package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac 트리 1240 노드사이의 거리 풀이 방법: bfs를 통해 거리 계산, LCA(최소공통조상)으로 풀이하는게 더 빠를 듯.
 */


public class Main_1240 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static List[] edges;

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        edges = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            edges[i] = new ArrayList<Edge>();
        }

        for (int i = 0; i < N - 1; i++) {
            tokens = new StringTokenizer(br.readLine());
            int one = Integer.parseInt(tokens.nextToken());
            int two = Integer.parseInt(tokens.nextToken());
            int dist = Integer.parseInt(tokens.nextToken());
            edges[one].add(new Edge(two, dist));
            edges[two].add(new Edge(one, dist));
        }

        // 2. find By BFS and print
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            bfs(a, b);
        }
    }

    private static void bfs(int a, int b) {
        Queue<Edge> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(new Edge(a, 0));
        visited[a] = true;
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int nowNode = edge.node;
            int nowCost = edge.cost;
            for (int i = 0; i < edges[nowNode].size(); i++) {
                Edge curEdge = (Edge) edges[nowNode].get(i);
                if (curEdge.node == b) {
                    System.out.println(nowCost + curEdge.cost);
                } else {
                    if (!visited[curEdge.node]) {
                        queue.add(new Edge(curEdge.node, nowCost + curEdge.cost));
                        visited[curEdge.node] = true;
                    }
                }
            }
        }

    }

    public static class Edge {

        private int node;
        private int cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

    }

}
