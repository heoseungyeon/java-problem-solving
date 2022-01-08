package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac 다익스트라 1753 최단경로 풀이 방법: priorityQueue 를 활용한 dijkstra 알고리즘을 통해 풀이.
 */

public class Main_1753 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer tokens;
    public static int V, E, K;
    public static List[] graph;
    public static int[] distance;
    public static int INF = Integer.MAX_VALUE;
    public static PriorityQueue<Node> pq = new PriorityQueue<>();


    public static void main(String[] args) throws IOException {

        // 1. input
        tokens = new StringTokenizer(br.readLine());
        V = Integer.parseInt(tokens.nextToken());
        E = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(br.readLine());

        graph = new ArrayList[V + 1];

        for (int i = 0; i < V + 1; i++) {
            graph[i] = new ArrayList<Node>();
        }

        int u, v, w;
        for (int i = 0; i < E; i++) {
            tokens = new StringTokenizer(br.readLine());
            u = Integer.parseInt(tokens.nextToken());
            v = Integer.parseInt(tokens.nextToken());
            w = Integer.parseInt(tokens.nextToken());
            graph[u].add(new Node(v, w));
        }

        // 2. dijkstra
        distance = new int[V + 1];
        Arrays.fill(distance, INF);

        dijkstra(K);

        for (int i = 1; i <= V; i++) {
            if (distance[i] == INF) {
                sb.append("INF" + "\n");
            } else {
                sb.append((distance[i] + "\n"));
            }
        }
        System.out.println(sb.toString());

    }

    private static void dijkstra(int start) {
        pq.add(new Node(K, 0));
        distance[K] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (distance[node.vertex] < node.cost) {
                continue;
            }
            for (int i = 0; i < graph[node.vertex].size(); i++) {
                Node next = (Node) graph[node.vertex].get(i);
                if (distance[next.vertex] > next.cost + node.cost) {
                    distance[next.vertex] = next.cost + node.cost;
                    pq.add(new Node(next.vertex, distance[next.vertex]));
                }
            }
        }

    }

    public static class Node implements Comparable<Node> {

        private int vertex;
        private int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            if (this.cost > other.cost) {
                return 1;
            } else if (this.cost == other.cost) {
                return 0;
            } else {
                return -1;
            }
        }
    }

}
