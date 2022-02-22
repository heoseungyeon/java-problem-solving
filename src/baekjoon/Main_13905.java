package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * solved.ac MST ( 63분 > 15:10 - 15:20 / 15:58 - 16:51 ) 13905 세부 풀이 방법: 좀 좋은 문제인듯.. MST 알고리즘은 크루스칼
 * 알고리즘을 선택하여 풀었고 Edge 는 cost로 내림차순하여 s와 e가 같은 싸이클일 경우를 반복문의 탈출조건으로 두었다. 처음엔 엥 s와 e 싸이클에 없어도 되는 간선이
 * 있으면 답이 아니지 않나? 라고 생각했지만 내림차순으로 되어 있기에 무조건 큰놈만 넣기때문에 상관이 없다. 그리고 연결이 되지 않을 경우를 고려하여 isPossible 변수로
 * 연결되지 않았을 경우 0을 출력하였다.
 */

public class Main_13905 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M, s, e;
    public static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
    public static int[] parents;

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        tokens = new StringTokenizer(br.readLine());
        s = Integer.parseInt(tokens.nextToken());
        e = Integer.parseInt(tokens.nextToken());

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(tokens.nextToken());
            int h2 = Integer.parseInt(tokens.nextToken());
            int c = Integer.parseInt(tokens.nextToken());
            pq.add(new Edge(h1, h2, c));
        }

        // 2. MST
        parents = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parents[i] = i;
        }

        int answer = Integer.MAX_VALUE;
        boolean isPossible = false;
        for (int i = 0; i < M; i++) {
            Edge edge = pq.poll();
            int parentA = findParent(edge.x);
            int parentB = findParent(edge.y);

            if (parentA != parentB) {
                union(edge.x, edge.y);
                answer = Math.min(answer, edge.cost);
            }
            if (findParent(s) == findParent(e)) {
                isPossible = true;
                break;
            }
        }

        // 3. print
        if (!isPossible) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }


    }

    public static int findParent(int a) {
        if (parents[a] != a) {
            parents[a] = findParent(parents[a]);
        }
        return parents[a];
    }

    public static void union(int a, int b) {
        int parentA = findParent(a);
        int parentB = findParent(b);

        if (parentA != parentB) {
            parents[parentA] = b;
        }

    }

    public static class Edge implements Comparable<Edge> {

        private int x;
        private int y;
        private int cost;

        public Edge(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.cost < o.cost) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
