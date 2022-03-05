package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_24479 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M, R;
    public static int[] visited;
    public static int order = 1;
    public static List<Integer>[] edges;

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        R = Integer.parseInt(tokens.nextToken());

        edges = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(tokens.nextToken());
            int v = Integer.parseInt(tokens.nextToken());
            edges[u].add(v);
            edges[v].add(u);
        }

        for (int i = 0; i < N + 1; i++) {
            Collections.sort(edges[i]);
        }

        visited = new int[N + 1];
        // 2. dfs
        visited[R] = order;
        order++;
        dfs(R);

        // 3. print
        for (int i = 1; i < N + 1; i++) {
            System.out.println(visited[i]);
        }
    }

    private static void dfs(int r) {
        for (int node : edges[r]) {
            if (visited[node] == 0) {
                visited[node] = order++;
                dfs(node);
            }
        }
    }


}
