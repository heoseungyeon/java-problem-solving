package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * solved.ac 문자열(오래걸림)(16:28 - 12:37) 1593 문자 해독  풀이 방법: 처음 풀이 방법은 w 에 대한 모든 순열을 구한 뒤 s에서 w 의 길이 만큼
 * 이동하며 체크를 했었다. -> w 순열 구할 때 메모리 초과.. , 2번째 풀이 방식은 w의 알파벳 갯수를 구한 뒤 s의 부분 문자열의 알파벳 숫자와 같아질 경우 cases에
 * 1을 더했다. 슬라이딩 윈도우 방식인데 중복 코드가 너무 많음
 */

public class Main_1593 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static int g, sLength;
    public static String W, S;

    public static int[] counts = new int[52];

    public static Set<Integer> alphas = new HashSet<>();

    public static void main(String[] args) throws Exception {

        // 1. input
        tokens = new StringTokenizer(br.readLine());
        g = Integer.parseInt(tokens.nextToken());
        sLength = Integer.parseInt(tokens.nextToken());

        W = br.readLine();
        S = br.readLine();

        // 2. count number of w's alphabet
        countWordAlphabet();

        // 3. find Number of cases
        int answer = findNumberOfCases();

        // 4. print
        System.out.println(answer);

    }

    private static int findNumberOfCases() {
        int cases = 0;
        int[] sCounts = new int[52];
        Set<Integer> sAlphas = new HashSet<>();

        for (int i = 0; i < S.length(); i++) {
            int idx;
            if (Character.isUpperCase(S.charAt(i))) {
                idx = (int) S.charAt(i) - 65;
            } else {
                idx = (int) S.charAt(i) - 97 + 26;
            }
            sCounts[idx] += 1;

            // W의 길이 만큼 카운트를 구한 경우
            if (i >= W.length()) {
                int preIdx;
                if (Character.isUpperCase(S.charAt(i - W.length()))) {
                    preIdx = (int) S.charAt(i - W.length()) - 65;
                } else {
                    preIdx = (int) S.charAt(i - W.length()) - 97 + 26;
                }
                sCounts[preIdx] -= 1;

            }
            // Arrays 내장 함수로 두 배열의 내용이 같은지 체크
            if (Arrays.equals(sCounts, counts)) {
                cases += 1;
            }

        }

        return cases;
    }

    private static void countWordAlphabet() {
        for (int i = 0; i < W.length(); i++) {
            int idx;
            if (Character.isUpperCase(W.charAt(i))) {
                idx = (int) W.charAt(i) - 65;
            } else {
                idx = (int) W.charAt(i) - 97 + 26;
            }
            counts[idx] += 1;
            alphas.add(idx);

        }

    }

}
