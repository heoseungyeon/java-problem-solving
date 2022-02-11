package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 문자열((23분) 16:01 - 16:24) 13413 오셀로 재배치 풀이 방법: 각 위치별 색깔이 다른 경우 2가지 (흰 - 검 , 검 - 흰) 의 수를
 * 구하고 둘의 합(말을 뒤집는 경우)에서 둘 중 작은 값(말의 위치를 바꾸는 경우)를 빼서 정답을 구함.
 */

public class Main_13413 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static StringBuilder sb = new StringBuilder();
    public static int T, N;

    public static void main(String[] args) throws Exception {
        // 1. input
        T = Integer.parseInt(br.readLine());

        while (T > 0) {
            N = Integer.parseInt(br.readLine());
            String firstWord = br.readLine();
            String targetWord = br.readLine();

            int diffBlackCnt = 0;
            int diffWhiteCnt = 0;

            // 2. find different count
            for (int i = 0; i < N; i++) {
                if (firstWord.charAt(i) != targetWord.charAt(i)) {
                    if (firstWord.charAt(i) == 'B') {
                        diffBlackCnt += 1;
                    } else {
                        diffWhiteCnt += 1;
                    }
                }
            }
            int answer = findMinAnswer(diffBlackCnt, diffWhiteCnt);

            // 3. append to StringBuilder
            sb.append(answer + "\n");

            T--;
        }

        // 4. print
        System.out.println(sb.toString());

    }

    private static int findMinAnswer(int diffBlackCnt, int diffWhiteCnt) {
        int gap = Math.min(diffBlackCnt, diffWhiteCnt);

        return diffBlackCnt + diffWhiteCnt - gap;
    }

}
