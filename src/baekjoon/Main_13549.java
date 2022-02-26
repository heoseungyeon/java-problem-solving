package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * solved.ac 다익스트라((분) 11:35 - 12:36) 13549 숨바꼭질 3 풀이 방법: 다익스트라 알고리즘으로 풀었는데 dp 를 통해 풀면 더 시간 복잡도가 적게
 * 나온다. ( pq 삽입 삭제 시간 아낌 )
 */

public class Main_13549 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, K;

    public static void main(String[] args) throws Exception {

        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        // 2. dijkstra
        int answer = dijkstra(N, K);

        // 3. print
        System.out.println(answer);
    }

    private static int dijkstra(int n, int k) {

        int[] distance = new int[1000001];

        for (int i = 0; i < 1000001; i++) {
            distance[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.add(new Info(n, 0));
        distance[n] = 0;

        while (!pq.isEmpty()) {
            Info info = pq.poll();

            if (info.num == k) {
                break;
            }
            if (info.cost > distance[info.num]) {
                continue;
            }
            if (rangeCheck(info.num - 1) && info.cost + 1 < distance[info.num - 1]) {
                distance[info.num - 1] = info.cost + 1;
                pq.add(new Info(info.num - 1, info.cost + 1));
            }
            if (rangeCheck(info.num + 1) && info.cost + 1 < distance[info.num + 1]) {
                distance[info.num + 1] = info.cost + 1;
                pq.add(new Info(info.num + 1, info.cost + 1));
            }
            if (rangeCheck(info.num * 2) && info.cost < distance[info.num * 2]) {
                distance[info.num * 2] = info.cost;
                pq.add(new Info(info.num * 2, info.cost));
            }
        }

        return distance[k];
    }

    public static boolean rangeCheck(int n) {
        return 0 <= n && n <= 1000000;
    }

    public static class Info implements Comparable<Info> {

        int num;
        int cost;

        public Info(int num, int cost) {
            this.num = num;
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
                "num=" + num +
                ", cost=" + cost +
                '}';
        }
    }
}
