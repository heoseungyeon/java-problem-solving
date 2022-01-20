package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 구현(엄청 오래 걸림) 3019 테트리스 풀이 방법: 아무리 생각해도 번뜩한 아이디어가 생각나지 않아 힘들었던 문제 ... 각 조각이 올바르게 끼워지기 위한
 * 빈칸 수를 3차원 배열에 저장하고, 입력받은 각 열의 높이와의 차가 모두 동일한 경우 정답으로 간주하여 문제 풀이
 */

public class Main_3019 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int C, P;
    public static int[] map;
    public static int[][][] cases = new int[][][]{
        {{}},
        {{0}, {0, 0, 0, 0}},
        {{0, 0}},
        {{0, 0, 1}, {1, 0}},
        {{1, 0, 0}, {0, 1}},
        {{0, 0, 0}, {1, 0, 1}, {1, 0}, {0, 1}},
        {{0, 0, 0}, {0, 1, 1}, {0, 0}, {2, 0}},
        {{0, 0, 0}, {1, 1, 0}, {0, 0}, {0, 2}}
    };


    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        C = Integer.parseInt(tokens.nextToken());
        P = Integer.parseInt(tokens.nextToken());
        map = new int[C];

        tokens = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            int height = Integer.parseInt(tokens.nextToken());
            map[i] = height;
        }

        // 2. solve
        int answer = 0;
        for (int i = 0; i < cases[P].length; i++) { // 블럭 경우의 수만큼 순회 

            for (int j = 0; j <= C - cases[P][i].length; j++) {
                boolean isSame = true;
                int comparedValue = map[j] - cases[P][i][0];
                for (int k = 1; k < cases[P][i].length; k++) {
                    if (comparedValue != map[j + k] - cases[P][i][k]) {
                        isSame = false;
                    }
                }
                if (isSame) {
                    answer += 1;
                }
            }
        }

        // 3. print answer
        System.out.println(answer);

    }


}
