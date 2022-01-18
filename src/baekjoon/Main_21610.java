package baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * solved.ac 구현(풀이시간:약 1시간) 21610 마법사 상어와 비바라기 풀이 방법: 더 집중해서 연산에 대한 실수를 줄여야 한다. 더 생각을 깊고 다양한 시각에서 할
 * 수 있는 연습!! 집중!
 */

public class Main_21610 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static int N, M;
    public static int[][] maps;
    public static int[][] commands;
    public static boolean[][] visited;
    // ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 방향 : 처음 방향이 1부터 시작하므로 0번 인덱스엔 0,0 을 채워둠
    public static int[] dx = new int[]{0, 0, -1, -1, -1, 0, 1, 1, 1};
    public static int[] dy = new int[]{0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        maps = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                maps[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        commands = new int[M][2];
        for (int i = 0; i < M; i++) {
            tokens = new StringTokenizer(br.readLine());
            commands[i][0] = Integer.parseInt(tokens.nextToken());
            commands[i][1] = Integer.parseInt(tokens.nextToken());
        }

        /**
         * 모든 구름이 di 방향으로 si칸 이동한다.
         * 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
         * 구름이 모두 사라진다.
         * 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
         * 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
         * 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
         * 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
         * M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 구해보자.
         */

        // 2. solve
        int iter = 0;
        Queue<Point> queue = new LinkedList<>();

        while (iter != M) {
            visited = new boolean[N + 1][N + 1];

            // 2-1 모든 구름이 di 방향으로 si칸 이동한다.
            if (iter == 0) {
                queue.add(new Point(N, 1));
                queue.add(new Point(N, 2));
                queue.add(new Point(N - 1, 1));
                queue.add(new Point(N - 1, 2));
            }
            // 2-1 & 2-2 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다. 구름이 모두 사라진다.
            int cloudSize = queue.size();
            for (int i = 0; i < cloudSize; i++) {
                Point p = queue.poll();
                p.x = (p.x + dx[commands[iter][0]] * commands[iter][1]) % N;
                p.y = (p.y + dy[commands[iter][0]] * commands[iter][1]) % N;
                p.x = p.x == 0 ? N : p.x;
                p.y = p.y == 0 ? N : p.y;
                p.x = p.x < 0 ? N - Math.abs(p.x) : p.x;
                p.y = p.y < 0 ? N - Math.abs(p.y) : p.y;
                maps[p.x][p.y] += 1;
                visited[p.x][p.y] = true;

            }

            // 2-3 물복사 버그
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (visited[i][j]) {
                        for (int k = 1; k <= 4; k++) {
                            int row = i + dx[k * 2];
                            int col = j + dy[k * 2];
                            if (1 <= row && row <= N && 1 <= col && col <= N
                                && maps[row][col] != 0) {
                                maps[i][j] += 1;
                            }
                        }
                    }
                }
            }

            // 2-4 구름 생성
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (!visited[i][j] && maps[i][j] >= 2) {
                        queue.add(new Point(i, j));
                        maps[i][j] -= 2;
                    }
                }
            }

            iter++;
        }

        // 3. print
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                answer += maps[i][j];
            }
        }
        System.out.println(answer);
    }

}
