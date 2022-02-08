package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * solved.ac 구현(48분)(5:03-5:51) 9518 로마 카톨릭 미사 풀이 방법: 이중반복문을 돌며, 자리가 비어있을 때 악수할 수 있는
 * 최댓값(emptyShakeHandCnt)과 자리에 사람있을 때 악수의 갯수(answer)를 구하고, emptyShakeHandCnt 와 answer/2 를 더해 출력함.
 */

public class Main_9518 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int R, S;
    public static int[][] maps;
    public static Map<String, Boolean> visited = new HashMap<>();
    public static int[] dx = new int[]{0, 0, 1, -1, 1, 1, -1, -1};
    public static int[] dy = new int[]{1, -1, 0, 0, 1, -1, 1, -1};

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokens.nextToken());
        S = Integer.parseInt(tokens.nextToken());
        maps = new int[R][S];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < S; j++) {
                if (input.charAt(j) == '.') {
                    maps[i][j] = 0;
                } else {
                    maps[i][j] = 1;
                }
            }
        }

        // 2. simulation
        int answer = 0;
        int emptyMaxShakeHandCount = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < S; j++) {
                if (maps[i][j] == 1) {
                    for (int k = 0; k < 8; k++) {
                        int r = i + dx[k];
                        int c = j + dy[k];
                        if (rangeCheck(r, c) && maps[r][c] == 1) {
                            answer += 1;

                        }
                    }
                } else {
                    int cnt = 0;
                    for (int k = 0; k < 8; k++) {
                        int r = i + dx[k];
                        int c = j + dy[k];
                        if (rangeCheck(r, c) && maps[r][c] == 1) {
                            cnt += 1;
                        }
                    }
                    emptyMaxShakeHandCount = Math.max(cnt, emptyMaxShakeHandCount);
                }

            }
        }

        // 3. print
        System.out.println(answer / 2 + emptyMaxShakeHandCount);

    }

    private static boolean rangeCheck(int r, int c) {
        if (0 <= r && 0 <= c && r < R && c < S) {
            return true;
        } else {
            return false;
        }
    }

}
