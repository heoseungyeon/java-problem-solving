package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * solved.ac MST( 39분 > 14:26 - 15:05) 6497 전력난 풀이 방법: 프림 알고리즘이 제일 먼저 떠올라서 프림으로 풀이 하였다. 프림일 경우 최소
 * 간선을 뽑을 경우, 자료구조(PQ)의 성능에 영향을 받는다. 프림은 정점을 기준으로한 MST 알고리즘.
 */

public class Main_6497 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int m, n; // 집의 수 m과 길의 수 n
    public static List<Info>[] graph;

    public static int answer;

    public static void main(String[] args) throws Exception {

        // 1. input
        while (true) {
            tokens = new StringTokenizer(br.readLine());
            m = Integer.parseInt(tokens.nextToken());
            n = Integer.parseInt(tokens.nextToken());
            if (m == 0 && n == 0) {
                break;
            }

            answer = 0;

            graph = new ArrayList[m];

            for (int i = 0; i < m; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < n; i++) {
                tokens = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(tokens.nextToken());
                int y = Integer.parseInt(tokens.nextToken());
                int z = Integer.parseInt(tokens.nextToken());
                graph[x].add(new Info(y, z));
                graph[y].add(new Info(x, z));
                answer += z;
            }

            // 2. MST
            int cnt = 0;
            boolean[] visited = new boolean[m];
            PriorityQueue<Info> queue = new PriorityQueue<>();
            queue.add(new Info(0, 0));
            while (cnt < m) {
                Info info = queue.poll();
                while (visited[info.vertex]) {
                    info = queue.poll();
                }
                cnt += 1;
                answer -= info.cost;
                visited[info.vertex] = true;
                for (Info edge : graph[info.vertex]) {
                    queue.add(edge);
                }
            }
            // 3. print
            System.out.println(answer);
        }

    }

    public static class Info implements Comparable<Info> {

        private int cost;
        private int vertex;

        public Info(int vertex, int cost) {
            this.vertex = vertex;
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

        @Override
        public String toString() {
            return "Info{" +
                "cost=" + cost +
                ", vertex=" + vertex +
                '}';
        }
    }

}
