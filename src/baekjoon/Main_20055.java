package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 구현((6시간 54분ㅋㅋ) 13:11 - 20:07) 20055 컨베이어 벨트 위의 로봇 풀이 방법: 문제 이해하느라 너무너무 오래 걸렸다.. A 배열과
 * robots 배열의 idx 는 현재 위치를 고정으로 갖으며 로봇의 위치와 컨베이어 벨트 한칸의 수용량만 바뀌는 것을 고려하여 풀면 된다.
 */

public class Main_20055 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, K;
    public static int[] A;
    public static int[] robots;
    public static int zeroCnt = 0;

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokens.nextToken());
        K = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(br.readLine());
        A = new int[2 * N];
        for (int i = 0; i < 2 * N; i++) {
            A[i] = Integer.parseInt(tokens.nextToken());
        }

        robots = new int[2 * N];
        // 2. move Conveyor
        int answer = moveConveyor();

        // 3. print
        System.out.println(answer);
    }

    // 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
    // 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
    // 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
    // 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
    // 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.

    // 0 1 2 3 4 5 6 7
    //
    private static int moveConveyor() {
        int step = 0;

        while (zeroCnt < K) {
            step += 1;
            // 1. rotate
            rotate();

            // 2. move
            robotMove();

            // 3. put robot to zero Index
            putRobot();

        }

        return step;

    }

    private static void putRobot() {
        if (robots[0] == 0 && A[0] > 0) {
            A[0] -= 1;
            if (A[0] == 0) {
                zeroCnt += 1;
            }
            robots[0] = 1;
        }
    }

    private static void robotMove() {
        for (int i = 2 * N - 1; i >= 0; i--) {
            int nextIdx = (i + 1) % (2 * N);
            if (robots[i] == 1 && robots[nextIdx] == 0 && A[nextIdx] > 0) {
                A[nextIdx] -= 1;
                if (A[nextIdx] == 0) {
                    zeroCnt += 1;
                }
                robots[i] = 0;
                if (nextIdx != N - 1) {
                    robots[nextIdx] = 1;
                }
            }
        }

    }

    private static void rotate() {
        // 컨베이어 회전
        int tmp = A[0];
        A[0] = A[2 * N - 1];
        for (int i = 0; i < 2 * N - 1; i++) {
            int nextVal = A[i + 1];
            A[i + 1] = tmp;
            tmp = nextVal;
        }

        // 로봇 회전
        tmp = robots[0];
        robots[0] = robots[2 * N - 1];
        for (int i = 0; i < 2 * N - 1; i++) {
            int nextVal = robots[i + 1];
            robots[i + 1] = tmp;
            tmp = nextVal;
        }

        if (robots[N - 1] == 1) {
            robots[N - 1] = 0;
        }


    }

}
