package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

    public static Set<String> permutations = new HashSet<>();
    public static boolean[] visited;

    public static int[] counts = new int[52];

    public static Set<Integer> alphas = new HashSet<>();

    public static void main(String[] args) throws Exception {

        // 1. input
        tokens = new StringTokenizer(br.readLine());
        g = Integer.parseInt(tokens.nextToken());
        sLength = Integer.parseInt(tokens.nextToken());

        W = br.readLine();
        S = br.readLine();

//        // 2. init permutations
//        visited = new boolean[W.length()];
//        addPermutations("", 0);
//
//        // 3. find number of cases
//        int answer = findNumberOfCases();

        // 2. count number of w's alphabet
        countWordAlphabet();

        // 3. find Number of cases
        int answer = findNumberOfCases();

        // 4. print
        System.out.println(answer);

    }

    private static int findNumberOfCases() {
        int cases = 0;
        int diffCnt = 0;
        int[] sCounts = new int[52];
        Set<Integer> sAlphas = new HashSet<>();

        for (int i = 0; i < W.length(); i++) {
            int idx;
            if (Character.isUpperCase(S.charAt(i))) {
                idx = (int) S.charAt(i) - 65;
            } else {
                idx = (int) S.charAt(i) - 97 + 26;
            }
            sCounts[idx] += 1;

            if (sCounts[idx] == counts[idx]) {
                if (sAlphas.remove(idx)) {
                    diffCnt -= 1;
                }

            } else {
                if (sAlphas.add(idx)) {
                    diffCnt += 1;
                }
            }

        }

        if (diffCnt == 0) {
            cases += 1;
        }

        for (int i = W.length(); i < S.length(); i++) {
            int preIdx, nextIdx;
            if (Character.isUpperCase(S.charAt(i - W.length()))) {
                preIdx = (int) S.charAt(i - W.length()) - 65;
            } else {
                preIdx = (int) S.charAt(i - W.length()) - 97 + 26;
            }
            sCounts[preIdx] -= 1;

            if (sCounts[preIdx] == counts[preIdx]) {
                if (sAlphas.remove(preIdx)) {
                    diffCnt -= 1;
                }

            } else {
                if (sAlphas.add(preIdx)) {
                    diffCnt += 1;
                }
            }

            if (Character.isUpperCase(S.charAt(i))) {
                nextIdx = (int) S.charAt(i) - 65;
            } else {
                nextIdx = (int) S.charAt(i) - 97 + 26;
            }
            sCounts[nextIdx] += 1;

            if (sCounts[nextIdx] == counts[nextIdx]) {
                if (sAlphas.remove(nextIdx)) {
                    diffCnt -= 1;
                }

            } else {
                if (sAlphas.add(nextIdx)) {
                    diffCnt += 1;
                }
            }

            if (diffCnt == 0) {
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

//
//    private static int findNumberOfCases() {
//        int rtn = 0;
//
//        for (int i = 0; i < S.length() - W.length(); i++) {
//            if (permutations.contains(S.substring(i, i + W.length()))) {
//                rtn += 1;
//            }
//        }
//
//        return rtn;
//    }
//
//    private static void addPermutations(String str, int cnt) {
//        if (cnt == 4) {
//            permutations.add(str);
//            return;
//        }
//        for (int i = 0; i < W.length(); i++) {
//            visited[i] = true;
//            addPermutations(str + W.charAt(i), cnt + 1);
//            visited[i] = false;
//        }
//    }

}
