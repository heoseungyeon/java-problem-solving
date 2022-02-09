package baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * solved.ac 구현(56분 > 16:27 - 17:23) 11559 Puyo Puyo 풀이 방법: bfs를 통해 연쇄가 작용하는지 체크하고, 만약 연쇄일 경우 '.'을
 * 대입해 bfs 탐색 완료 후 setting 메서드를 통해 구슬들을 내려준다.
 */

public class Main_11559 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static char[][] puyoPuyo = new char[12][6];
    public static int[] dx = new int[]{0, 0, 1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        // 1. input
        for (int i = 0; i < 12; i++) {
            String input = br.readLine();
            for (int j = 0; j < 6; j++) {
                puyoPuyo[i][j] = input.charAt(j);
            }
        }

        // 2. simulation by bfs
        int answer = 0;
        boolean isBomb = true;

        while (isBomb) {
            isBomb = checkBomb();
            if (isBomb) {
                answer += 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean checkBomb() {
        boolean[][] visited = new boolean[12][6];
        boolean isBomb = false;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (!visited[i][j] && puyoPuyo[i][j] != '.') {
                    Queue<Point> tmpQueue = new LinkedList<>();
                    Queue<Point> queue = new LinkedList<>();
                    tmpQueue.add(new Point(i, j));
                    queue.add(new Point(i, j));
                    visited[i][j] = true;
                    int cnt = 1;
                    while (!queue.isEmpty()) {
                        Point point = queue.poll();
                        char color = puyoPuyo[i][j];
                        for (int k = 0; k < 4; k++) {
                            int r = point.x + dx[k];
                            int c = point.y + dy[k];
                            if (rangeCheck(r, c) && puyoPuyo[r][c] == color && !visited[r][c]) {
                                cnt += 1;
                                queue.add(new Point(r, c));
                                tmpQueue.add(new Point(r, c));
                                visited[r][c] = true;

                            }
                        }
                    }
                    if (cnt >= 4) {
                        isBomb = true;
                        for (Point p : tmpQueue) {
                            puyoPuyo[p.x][p.y] = '.';
                        }
                    }
                }
            }
        }// for

        settingPuyoPuyo();

        return isBomb;
    }

    private static void settingPuyoPuyo() {
        for (int i = 0; i < 6; i++) {
            for (int j = 11; j >= 0; j--) {
                int curIdx = j;
                if (puyoPuyo[j][i] == '.') {
                    while (curIdx - 1 >= 0 && puyoPuyo[curIdx - 1][i] == '.') {
                        curIdx--;
                    }
                    if (curIdx - 1 >= 0) {
                        puyoPuyo[j][i] = puyoPuyo[curIdx - 1][i];
                        puyoPuyo[curIdx - 1][i] = '.';
                    }
                }
            }

        }
    }

    private static boolean rangeCheck(int r, int c) {
        if (0 <= r && r < 12 && 0 <= c && c < 6) {
            return true;
        } else {
            return false;
        }
    }

}
