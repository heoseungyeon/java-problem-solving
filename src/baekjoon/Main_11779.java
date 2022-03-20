package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac 다익스트라 11779(19:17 - 20:55) 최소 비용 구하기 2 풀이 방법: Edge가 LinkedList를 갖게 한 담에 경로를 저장햇는디.. int
 * 1차원 배열에 직전 노드를 저장해서 마지막에 추적을 통해 경로를 계산하는게 훨씬 더 좋은 아이디어인 듯하다. ㅎ
 */

public class Main_11779 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int n, m;
    public static int A, B;
    public static List<Edge>[] edges;
    public static long[] distance;

    public static long cost;
    public static int count;
    public static List<Integer> way = new LinkedList<>();

    public static void main(String[] args) throws Exception {

        // 1. input
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

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
        }
        tokens = new StringTokenizer(br.readLine());
        A = Integer.parseInt(tokens.nextToken());
        B = Integer.parseInt(tokens.nextToken());
        // 2. dijkstra
        distance = new long[n + 1];
        for (int i = 0; i < n + 1; i++) {
            distance[i] = Long.MAX_VALUE;
        }
        dijkstra();

        // 3. print
        System.out.println(cost);
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            System.out.print(way.get(i) + " ");
        }
    }

    private static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
//        for (int i = 0; i < edges[A].size(); i++) {
//            Edge inputEdge = new Edge(edges[A].get(i).end, edges[A].get(i).cost);
//            inputEdge.way.add(A);
//            pq.add(inputEdge);
//            distance[edges[A].get(i).end] = Math.min(distance[edges[A].get(i).end],
//                edges[A].get(i).cost);
//        }
        Edge startEdge = new Edge(A, 0);
        pq.add(startEdge);

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (edge.end == B) {
                cost = edge.cost;
                count = edge.way.size() + 1;
                edge.way.add(B);
                way = edge.way;
                break;
            }
            if (edge.cost > distance[edge.end]) {
                continue;
            }
            for (int i = 0; i < edges[edge.end].size(); i++) {
                long newCost = edge.cost + edges[edge.end].get(i).cost;
                if (newCost < distance[edges[edge.end].get(i).end]) {
                    Edge newEdge = new Edge(edges[edge.end].get(i).end, newCost);
                    newEdge.way = new LinkedList<>(edge.way);
                    newEdge.way.add(edge.end);
                    pq.add(newEdge);
                    distance[edges[edge.end].get(i).end] = newCost;
                }
            }

        }


    }

    public static class Edge implements Comparable<Edge> {

        private int end;
        private long cost;
        private List<Integer> way = new LinkedList<>();

        public Edge(int end, long cost) {
            this.end = end;
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
                "end=" + end +
                ", cost=" + cost +
                ", way=" + way +
                '}';
        }
    }

}
