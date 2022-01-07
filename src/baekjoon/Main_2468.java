package baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * solved.ac bfs 2468 안전 영역 풀이 방법: maxHeight를 구한 뒤, maxHeight~1 만큼 bfs를 돌려서 최대 안전 영역 갯수를 갱신한다.
 */

public class Main_2468 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer tokens;

    public static int N;
    public static int[][] maps;
    public static boolean[][] visited;

    public static int[] dx = new int[]{1, -1, 0, 0};
    public static int[] dy = new int[]{0, 0, 1, -1};

    public static Queue<Point> q = new LinkedList<>();
    public static int maxHeight = -1;
    public static int answer = 1;

    public static void main(String[] args) throws IOException {
        // 1. input
        N = Integer.parseInt(br.readLine());

        maps = new int[N][N];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(tokens.nextToken());
                maxHeight = Math.max(maxHeight, value);
                maps[i][j] = value;
            }
        }

        // 2. bfs
        int curHeight = maxHeight;
        while (curHeight != 0) {
            int result = 0;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (maps[i][j] > curHeight && !visited[i][j]) {
                        result += 1;
                        bfs(i, j, curHeight);
                    }
                }
            }
            answer = Math.max(answer, result);
            curHeight--;
        }

        // 3. print
        System.out.println(answer);

    }

    public static void bfs(int row, int col, int curHeight) {
        q.add(new Point(row, col));
        visited[row][col] = true;
        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int r = p.x + dx[i];
                int c = p.y + dy[i];
                if (0 <= r && r < N && 0 <= c && c < N) {
                    if (maps[r][c] > curHeight && !visited[r][c]) {
                        q.add(new Point(r, c));
                        visited[r][c] = true;
                    }
                }
            }
        }

    }

}
