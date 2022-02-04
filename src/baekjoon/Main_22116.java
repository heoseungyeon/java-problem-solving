package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * solved.ac MST(크루스칼) 22116 창영이와 퇴근 풀이 방법: MST에서 고르긴했지만 문제의 핵심은 BFS + 이진 탐색이었다. 창영이 자전거의 경사를 이진탐색으로
 * 찾고 BFS를 통해 조건을 분기시켜 문제를 풀이했다. 풀이하면서 궁금했던 점은 while 문안에 poll을 한 뒤 정답 체크를 하는 것과 add 하기 전 정답체크하는 것의
 * 차이점... 꼭알아보고 다시 주석을 달아야겠다.
 */

public class Main_22116 {

    public static final int MIN_HEIGHT = 0;
    public static final int MAX_HEIGHT = 999999999;
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N;
    public static int[][] map;
    public static int[] dx = new int[]{0, 0, 1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws Exception {

        // 1. input
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        // 2. binarySearch and print
        System.out.println(binarySearch());

    }

    public static int binarySearch() {
        int answer = 0, middle;
        int start = MIN_HEIGHT;
        int end = MAX_HEIGHT;

        while (start <= end) {
            middle = (start + end) / 2;

            boolean isCompleted = findByBfs(middle);

            if (isCompleted) {
                answer = middle;
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }

        return answer;
    }

    public static boolean findByBfs(int middle) {
        Queue<Info> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        queue.add(new Info(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Info point = queue.poll();
            visited[point.x][point.y] = true;

            // isComplete Condition
            if (point.x == N - 1 && point.y == N - 1) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int row = point.x + dx[i];
                int col = point.y + dy[i];
                if (0 <= row && row < N && 0 <= col && col < N && !visited[row][col]) {
                    int heightDifference = Math.abs(map[row][col] - map[point.x][point.y]);
                    if (heightDifference <= middle) {
                        queue.add(new Info(row, col));
                    }
                }
            }
        }

        return false;
    }

    public static class Info {

        private int x, y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
