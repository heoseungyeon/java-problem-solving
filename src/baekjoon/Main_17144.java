package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 구현((53분)  22:12 - 23:05)  17144 미세먼지 안녕!  풀이 방법: 미세먼지가 ' 동시에 ' 확산되는 것이 키 포인트 좀 더
 * 객체지향적으로 풀려고 노력해봐야 겠다.
 */


public class Main_17144 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int R, C, T;
    public static int[][] map;
    public static int[][] savedDust;
    public static int sum = 0;
    public static AirCleaner airCleaner;
    public static int[] dx = new int[]{0, 0, 1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());
        T = Integer.parseInt(tokens.nextToken());

        map = new int[R][C];
        savedDust = new int[R][C];
        int[] rowValue = new int[2];
        int idx = 0;
        for (int i = 0; i < R; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int value = Integer.parseInt(tokens.nextToken());
                if (value == -1) {
                    map[i][j] = value;
                    rowValue[idx] = i;
                    idx++;
                } else {
                    map[i][j] = value;
                    sum += value;
                }
            }
        }

        airCleaner = new AirCleaner(rowValue[0], rowValue[1]);

        // 2. spread out dust clean the map
        while (T > 0) {
            spreadDust();

            airCleaner.start();

            T--;
        }

        System.out.println(sum);

    }

    private static void spreadDust() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    int spreadAmount = map[i][j] / 5;
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int r = i + dx[k];
                        int c = j + dy[k];
                        if (rangeCheck(r, c)) {
                            cnt += 1;
                            savedDust[r][c] += spreadAmount;
                        }
                    }
                    savedDust[i][j] -= cnt * spreadAmount;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += savedDust[i][j];
                savedDust[i][j] = 0;
            }
        }
    }

    private static boolean rangeCheck(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C && !(r == airCleaner.bottomRow && c == 0) && !(
            r == airCleaner.upRow && c == 0);
    }

    public static class AirCleaner {

        private int upRow;
        private int bottomRow;

        public AirCleaner(int upRow, int bottomRow) {
            this.upRow = upRow;
            this.bottomRow = bottomRow;
        }

        public void start() {
            //upRow rotate
            int tmp = map[upRow][1];
            map[upRow][1] = 0;
            for (int i = 1; i < C - 1; i++) {
                int nextDust = map[upRow][i + 1];
                map[upRow][i + 1] = tmp;
                tmp = nextDust;
            }
            for (int i = upRow; i > 0; i--) {
                int nextDust = map[i - 1][C - 1];
                map[i - 1][C - 1] = tmp;
                tmp = nextDust;
            }
            for (int i = C - 1; i > 0; i--) {
                int nextDust = map[0][i - 1];
                map[0][i - 1] = tmp;
                tmp = nextDust;
            }
            for (int i = 0; i < upRow - 1; i++) {
                int nextDust = map[i + 1][0];
                map[i + 1][0] = tmp;
                tmp = nextDust;
            }
            sum -= tmp;
            //bottomRow
            tmp = map[bottomRow][1];
            map[bottomRow][1] = 0;
            for (int i = 1; i < C - 1; i++) {
                int nextDust = map[bottomRow][i + 1];
                map[bottomRow][i + 1] = tmp;
                tmp = nextDust;
            }
            for (int i = bottomRow; i < R - 1; i++) {
                int nextDust = map[i + 1][C - 1];
                map[i + 1][C - 1] = tmp;
                tmp = nextDust;
            }
            for (int i = C - 1; i > 0; i--) {
                int nextDust = map[R - 1][i - 1];
                map[R - 1][i - 1] = tmp;
                tmp = nextDust;
            }
            for (int i = R - 1; i > bottomRow + 1; i--) {
                int nextDust = map[i - 1][0];
                map[i - 1][0] = tmp;
                tmp = nextDust;
            }
            sum -= tmp;
        }
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
