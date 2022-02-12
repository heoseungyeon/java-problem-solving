package baekjoon;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac bfs((1시간 5분) 15:58 - 17:03) 16932 모양 만들기 풀이 방법: 1 모양의 집합을 숫자로 그룹화를 진행하고, 0인 좌표의 상하좌우를
 * 보고 다른 모양의 그룹의 합을 더해 그 값이 최대가 되는 경우를 찾았다.
 */

public class Main_16932 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static int[][] map;

    public static int[] dx = new int[]{0, 0, 1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0};

    public static Map<Integer, Integer> sums = new HashMap<>();

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        // 2. numbering one and calculate Sum
        oneNumbering();

        // 3. find point for max
        int answer = findMaxZero();

        // 4. print
        System.out.println(answer);
    }

    private static int findMaxZero() {
        int rtn = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    int sum = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int k = 0; k < 4; k++) {
                        int r = i + dx[k];
                        int c = j + dy[k];
                        if (rangeCheck(r, c) && map[r][c] > 0 && !set.contains(map[r][c])) {
                            sum += sums.get(map[r][c]);
                            set.add(map[r][c]);
                        }
                    }
                    rtn = Math.max(sum, rtn);
                }
            }
        }
        return rtn;
    }

    private static void oneNumbering() {
        boolean[][] visited = new boolean[N][M];
        Queue<Point> queue = new LinkedList<>();
        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    int sum = 0;
                    num += 1;
                    queue.add(new Point(i, j));
                    map[i][j] = num;
                    sum += 1;
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        Point point = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int r = point.r + dx[k];
                            int c = point.c + dy[k];
                            if (rangeCheck(r, c) && !visited[r][c] && map[r][c] == 1) {
                                queue.add(new Point(r, c));
                                map[r][c] = num;
                                sum += 1;
                                visited[r][c] = true;
                            }
                        }
                    }
                    sums.put(num, sum);
                }
            }
        }
    }

    private static boolean rangeCheck(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

    public static class Point {

        private int r;
        private int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

}
