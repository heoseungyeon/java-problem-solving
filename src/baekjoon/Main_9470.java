package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac 위상정렬 9470 Strahler 순서 풀이 방법: 위상 정렬을 하면서 들어오는 노드가 없을 경우 maxNodeCountMap 의 값을 계산하였음. 개선할
 * 수 있는 방법으론 들어오는 노드를 따로 저장하는 List<Integer>[] innerEdges를 두고 계산하면 더 간단하게 할 수 있을 듯.
 */

public class Main_9470 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int T;
    public static int K, M, P;
    public static List<Integer>[] edges;
    public static Map<Integer, Info> maxNodeCountMap; //들어오는 노드의 최대 순서 값의 갯수를 저장하는 Map
    public static int[] orders;
    public static int[] counts;
    public static int maxOrder;


    public static void main(String[] args) throws Exception {
        // 1. input
        T = Integer.parseInt(br.readLine());

        while (T > 0) {
            tokens = new StringTokenizer(br.readLine());
            K = Integer.parseInt(tokens.nextToken());
            M = Integer.parseInt(tokens.nextToken());
            P = Integer.parseInt(tokens.nextToken());

            counts = new int[M + 1];
            orders = new int[M + 1];
            edges = new ArrayList[M + 1];
            maxNodeCountMap = new HashMap<>();

            for (int i = 0; i < M + 1; i++) {
                edges[i] = new ArrayList<>();
            }

            for (int i = 0; i < P; i++) {
                tokens = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(tokens.nextToken());
                int b = Integer.parseInt(tokens.nextToken());
                edges[a].add(b);
                counts[b] += 1;
            }

            // 2. 위상 정렬
            maxOrder = 0;
            tonopoly();

            // 3. print
            System.out.println(K + " " + maxOrder);
            T--;
        }

    }

    private static void tonopoly() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= M; i++) {
            if (counts[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            // 순서 구하기
            if (!maxNodeCountMap.containsKey(node)) {
                orders[node] = 1;
            } else if (maxNodeCountMap.get(node).count > 1) {
                orders[node] = maxNodeCountMap.get(node).maxNum + 1;
            } else {
                orders[node] = maxNodeCountMap.get(node).maxNum;
            }
            maxOrder = Math.max(maxOrder, orders[node]);

            // 선수 노드 뺴기
            for (int curNode : edges[node]) {
                counts[curNode] -= 1;
                // 노드로 들어오는 최대 순서 노드의 갯수 계산
                if (!maxNodeCountMap.containsKey(curNode)) {
                    maxNodeCountMap.put(curNode, new Info(orders[node], 1));
                } else if (maxNodeCountMap.get(curNode).maxNum < orders[node]) {
                    maxNodeCountMap.put(curNode, new Info(orders[node], 1));
                } else if (maxNodeCountMap.get(curNode).maxNum == orders[node]) {
                    maxNodeCountMap.get(curNode).increaseCount();
                }
                // 선수 노드 없는 경우 queue에 더해주기
                if (counts[curNode] == 0) {
                    queue.add(curNode);
                }
            }
        }
    }

    private static class Info {

        private int maxNum;
        private int count;

        public Info(int maxNum, int count) {
            this.maxNum = maxNum;
            this.count = count;
        }

        public void increaseCount() {
            count++;
        }
    }

}
