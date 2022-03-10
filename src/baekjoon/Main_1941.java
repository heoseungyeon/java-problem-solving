package baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * solved.ac dfs(음청 오래 걸림) 1941 소문난 칠공주 풀이 방법: 순열으로 전체 경우를 구하고,  7가지 칸에서 S가 4개 이상이고, 서로 연결 되어있으면
 * 정답으로 처리하였음. 연결 가능 여부는 기준점에서 bfs를 통해 나머지 6가지 점을 방문 가능한지 탐색하여 찾음.
 */

public class Main_1941 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static char[][] map = new char[5][5];
    public static boolean[][] visited = new boolean[5][5];

    public static int[] dx = new int[]{0, 0, 1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0};

    public static int answer = 0;

    public static void main(String[] args) throws Exception {

        // 1. input
        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        // 2. combination by dfs
        dfs(0, 0);

        // 3. print
        System.out.println(answer);
    }

    public static void dfs(int idx, int cnt) {
        if (cnt == 7) {
            answer += isAnswer();
            return;
        }

        for (int i = idx; i < 25; i++) {
            int r = i / 5;
            int c = i % 5;
            visited[r][c] = true;
            dfs(i + 1, cnt + 1);
            visited[r][c] = false;
        }
    }

    private static int isAnswer() {
        int startX = 0, startY = 0;
        int answer = 0;
        int sCnt = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visited[i][j] && map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                    sCnt += 1;
                }
            }
        }

        if (sCnt >= 4) {
            if (isConnected(startX, startY)) {
                answer = 1;
            }
        }
        return answer;
    }

    private static boolean isConnected(int startX, int startY) {
        boolean isConnected = false;
        boolean[][] checked = new boolean[5][5];
        int count = 1;
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(startX, startY));
        checked[startX][startY] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            for (int i = 0; i < 4; i++) {
                int r = point.x + dx[i];
                int c = point.y + dy[i];
                if (rangeCheck(r, c) && !checked[r][c] && visited[r][c]) {
                    count++;
                    checked[r][c] = true;
                    queue.add(new Point(r, c));
                }
            }
        }

        if (count == 7) {
            isConnected = true;
        }

        return isConnected;
    }

    public static boolean rangeCheck(int r, int c) {
        return 0 <= r && r < 5 && 0 <= c && c < 5;
    }

}
