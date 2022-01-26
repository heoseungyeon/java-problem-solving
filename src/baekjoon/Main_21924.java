package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * solved.ac MST(크루스칼) 21924 도시 건설 풀이 방법: 최소 신장 트리 - 크루스칼 알고리즘으로 풀이. 오랜만에 MST 알고리즘을 다루니 머리가 하얘지고
 * 낯설다... ㅋㅋㅋ 익숙해지자.. visited 배열은 전체 노드 방문 여부를 검사하기 위해 사용하였다. 모든 노드의 연결 여부를 findParent 를 통해 계산하였다.
 */

public class Main_21924 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static List<Edge> edges = new LinkedList<>();
    public static long maxCost = 0;
    public static int[] parents;
    public static boolean[] visited;
    public static int cnt = 0;

    public static void main(String[] args) throws Exception {
        // 1. input and init variable
        tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        parents = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }

        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(tokens.nextToken());
            int b = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            maxCost += c;
            edges.add(new Edge(a, b, c));
        }

        // 2. sort Edge
        // Collections.sort(edges);

        // 3. Cruskal
        long result = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>(edges);

        for (int i = 0; i < M; i++) {
            Edge edge = pq.poll();
            int start = edge.start;
            int end = edge.end;

            int startParent = findParent(start);
            int endParent = findParent(end);

            if (startParent == endParent) { // 사이클 발생
                continue;
            }

            union(start, end);

            if (!visited[start]) {
                visited[start] = true;
                cnt += 1;
            }
            if (!visited[end]) {
                visited[end] = true;
                cnt += 1;
            }

            int cost = edge.cost;
            result += cost;
        }

        boolean isConnected = true;
        int parent = findParent(1);
        for (int i = 2; i <= N; i++) {
            if (parent != findParent(i)) {
                isConnected = false;
                break;
            }
        }

        // 4. print
        if (cnt == N && isConnected) {
            System.out.println(maxCost - result);
        } else {
            System.out.println(-1);
        }
    }

    public static void union(int a, int b) {
        int aParent = findParent(a);
        int bParent = findParent(b);
        if (aParent != bParent) {
            parents[aParent] = b;
        }
    }

    public static int findParent(int a) {
        if (parents[a] != a) {
            parents[a] = findParent(parents[a]);
            return parents[a];
        } else {
            return parents[a];
        }
    }

    static class Edge implements Comparable<Edge> {

        private int start;
        private int end;
        private int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.cost > o.cost) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
