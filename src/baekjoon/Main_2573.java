package baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * solved.ac bfs/dfs 2573 빙산 풀이 방법: 1. oneYearLater에서 빙산은 동시에 녹기 때문에 녹아야 할 count를 배열에 저장 후, 2. 녹는 것을
 * 계산하고, countMountain 함수에서 빙산의 갯수를 bfs를 통해 계산함. while 에서 위 과정을 반복하여 정답을 구함.
 */

public class Main_2573 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static int N, M;
    public static int[][] map;
    public static int[] dx = new int[]{0, 0, 1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0};

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

        // 2. solve and check mountain amount by bfs
        int answer = 0;
        while (true) {
            if (answer == 0 && countMountain() > 1) {
                break;
            }
            oneYearLater();
            System.out.println("++++++++++++");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
            answer += 1;
            int checkNum = countMountain();
            if (checkNum == 0) {
                answer = 0;
                break;
            } else if (checkNum > 1) {
                break;
            } else {
                continue;
            }
        }

        // 3. print
        System.out.println(answer);

    }

    private static void oneYearLater() {
        int[][] count = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    for (int k = 0; k < 4; k++) {
                        int row = i + dx[k];
                        int col = j + dy[k];
                        if (0 <= row && row < N && 0 <= col && col < M && map[row][col] != 0) {
                            count[row][col]++;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] - count[i][j] < 0) {
                    map[i][j] = 0;
                } else {
                    map[i][j] -= count[i][j];
                }
            }
        }
    }

    private static int countMountain() {
        int cnt = 0;
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // bfs all nodes
                if (!visited[i][j] && map[i][j] != 0) {
                    cnt += 1;
                    queue.add(new Point(i, j));
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        Point point = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int row = point.x + dx[k];
                            int col = point.y + dy[k];
                            if (0 <= row && row < N && 0 <= col && col < M && map[row][col] != 0
                                && !visited[row][col]) {
                                queue.add(new Point(row, col));
                                visited[row][col] = true;
                            }
                        }
                    }
                }

            }
        }
        return cnt;
    }

}
