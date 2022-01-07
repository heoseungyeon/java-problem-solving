package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * solved.ac bfs 14716 현수막 풀이 방법: 글자 가능 노드가 있을 경우 answer++ 후, bfs 돌려서 방문처리
 */

public class Main_14716 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer tokens;
    public static int M, N;
    public static Queue<Node> queue = new LinkedList<>();
    public static int[][] maps;
    public static boolean[][] visited;
    public static int[] dx = new int[]{1, -1, 0, 0, 1, 1, -1, -1};
    public static int[] dy = new int[]{0, 0, 1, -1, -1, 1, -1, 1};
    public static int answer = 0;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(br.readLine());

        M = Integer.parseInt(tokens.nextToken());
        N = Integer.parseInt(tokens.nextToken());

        maps = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (maps[i][j] == 1 && !visited[i][j]) {
                    answer += 1;
                    bfs(i, j);
                }
            }

        }
        System.out.println(answer);
    }

    public static void bfs(int row, int col) {
        queue.add(new Node(row, col));
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 8; i++) {
                int r = node.row + dx[i];
                int c = node.col + dy[i];
                if (0 <= r && r < M && 0 <= c && c < N && maps[r][c] == 1 && !visited[r][c]) {
                    queue.add(new Node(r, c));
                    visited[r][c] = true;
                }
            }
        }
    }

    static class Node {

        private int row;
        private int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}

