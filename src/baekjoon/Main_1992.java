package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 재귀(42분 > 16:45 - 17:27) 1992 쿼드 트리 풀이 방법: 재귀 이용해서 풀었는데 코드 중복이 너무 많다.. ㅜ compression
 * 매개변수에 start , end 행 열 정보를 받으면 반복문을 돌며 체크하는 로직의 중복이 없어진다. ㅠ.ㅠ
 */

public class Main_1992 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N;
    public static int[][] video;

    public static void main(String[] args) throws Exception {
        String answer = "";
        // 1. input
        N = Integer.parseInt(br.readLine());

        video = new int[N][N];

        boolean check = true;
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                video[i][j] = Integer.parseInt(String.valueOf(input.charAt(j)));
                if (video[i][j] != video[0][0]) {
                    check = false;
                }
            }
        }

        // 2. recursive method call
        if (check) {
            answer = String.valueOf(video[0][0]);
        } else {
            answer = compression(N, 0, 0);

        }

        // 3. print
        System.out.println(answer);
    }

    private static String compression(int n, int row, int col) {
        if (n == 1) {
            return String.valueOf(video[row][col]);
        }
        String result = "";
        // 왼쪽 위
        boolean check = true;
        for (int i = row; i < row + n / 2; i++) {
            for (int j = col; j < col + n / 2; j++) {
                if (video[i][j] != video[row][col]) {
                    check = false;
                    break;
                }
            }
        }
        if (check) {
            result += String.valueOf(video[row][col]);
        } else {
            result += compression(n / 2, row, col);
        }
        // 오른쪽 위
        check = true;
        for (int i = row; i < row + n / 2; i++) {
            for (int j = col + n - 1; j >= col + n / 2; j--) {
                if (video[i][j] != video[row][col + n - 1]) {
                    check = false;
                    break;
                }
            }
        }
        if (check) {
            result += String.valueOf(video[row][col + n - 1]);
        } else {
            result += compression(n / 2, row, col + n / 2);
        }
        // 왼쪽 아래
        check = true;
        for (int i = row + n - 1; i >= row + n / 2; i--) {
            for (int j = col; j < col + n / 2; j++) {
                if (video[i][j] != video[row + n - 1][col]) {
                    check = false;
                    break;
                }
            }
        }
        if (check) {
            result += String.valueOf(video[row + n - 1][col]);
        } else {
            result += compression(n / 2, row + n / 2, col);
        }
        // 오른쪽 아래
        check = true;
        for (int i = row + n - 1; i >= row + n / 2; i--) {
            for (int j = col + n - 1; j >= col + n / 2; j--) {
                if (video[i][j] != video[row + n - 1][col + n - 1]) {
                    check = false;
                    break;
                }
            }
        }
        if (check) {
            result += String.valueOf(video[row + n - 1][col + n - 1]);
        } else {
            result += compression(n / 2, row + n / 2, col + n / 2);
        }
        return "(" + result + ")";
    }

}
