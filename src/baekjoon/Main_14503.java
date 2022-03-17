package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 시뮬레이션(85분; 16:45 - 18:20) 14503 로봇 청소기  풀이방법 : 시뮬레이션.. 좀 복잡하게 품
 */

public class Main_14503 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static int[][] map;
    public static int[] dx = new int[]{-1, 0, 1, 0};
    public static int[] dy = new int[]{0, 1, 0, -1};
    public static int count = 0;

    /**
     * 1. 현재 위치를 청소한다. 2. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 인접한 칸을 탐색한다. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면,
     * 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다. 네 방향 모두 청소가 이미
     * 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라
     * 후진도 할 수 없는 경우에는 작동을 멈춘다.
     */

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(br.readLine());

        int startX = Integer.parseInt(tokens.nextToken());
        int startY = Integer.parseInt(tokens.nextToken());
        int startD = Integer.parseInt(tokens.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            tokens = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        // 2. simulation
        int nowR = startX;
        int nowC = startY;
        int nowD = startD;
        while (true) {
            // 현재 있는 칸을 청소한다.
            if (map[nowR][nowC] == 0) {
                count += 1;
                map[nowR][nowC] = -1;
            }

            boolean isContinue = false;
            boolean isSecondContinue = true;
            while (isSecondContinue) {
                for (int i = 0; i < 4; i++) {
                    nowD = (nowD + 3) % 4;
                    int leftR = nowR + dx[nowD];
                    int leftC = nowC + dy[nowD];
                    if (rangeCheck(leftR, leftC) && map[leftR][leftC] == 0) {
                        isContinue = true;
                        nowR = leftR;
                        nowC = leftC;
                        break;
                    }
                }
                if (isContinue) {
                    break;
                }
                int backR = nowR + dx[(nowD + 2) % 4];
                int backC = nowC + dy[(nowD + 2) % 4];
                if (rangeCheck(backR, backC) && map[backR][backC] != 1) {
                    nowR = backR;
                    nowC = backC;
                } else {
                    isSecondContinue = false;
                }
            }
            if (isContinue) {
                continue;
            }
            break;
        }

        // 3. print
        System.out.println(count);

    }

    public static boolean rangeCheck(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

}
