package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * solved.ac 완전탐색(62분 > 16:16 - 17:04 / 17:23 - 17:37) 1107 리모컨 풀이 방법: 백트래킹 방식으로 누를 수 있는 번호 의 조합을
 * N-1 ~ N+1 자릿수에 한해서 구한 뒤, 비교하면서 답을 구했다. 아무래도 메서드 호출이 많아 시간이 오래걸린듯.. 매우..
 */

public class Main_1107 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N, M;
    public static int nSize;

    public static List<Integer> correctButtons = new ArrayList<>();
    public static List<Integer> wrongButtons = new ArrayList<>();
    public static int num = 100;
    public static boolean[] visited = new boolean[10];

    public static void main(String[] args) throws Exception {
        // 1. input
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        nSize = Integer.toString(N).length();

        if (M > 0) {
            tokens = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                wrongButtons.add(Integer.parseInt(tokens.nextToken()));
            }
        }

        // 2. get correct buttons
        for (int i = 0; i < 10; i++) {
            if (!wrongButtons.contains(i)) {
                correctButtons.add(i);
            }
        }

        if (N == 100 || correctButtons.isEmpty()) {
            System.out.println(Math.abs(N - 100));
            return;
        }

        // 3. bruteForce
        int mostNearNum = findMostNearNum(0, "", num);

        // 4. print
        int gap = mostNearNum == 100 ? 0 : Integer.toString(mostNearNum).length();
        System.out.println(Math.abs(N - mostNearNum) + gap);
    }

    private static int findMostNearNum(int cnt, String numString, int rstArg) {
        int rst = rstArg;
        if (nSize - 1 <= cnt && cnt <= nSize + 1 && !Objects.equals(numString, "")) {
            int cmpNum = Integer.parseInt(numString);
            if (checkNum(rst, cmpNum)) {
                rst = cmpNum;
            }
        } else if (cnt >= nSize + 2) {
            return rst;
        }
        for (int i = 0; i < correctButtons.size(); i++) {
            visited[i] = true;
            rst = findMostNearNum(cnt + 1, numString + correctButtons.get(i), rst);
            visited[i] = false;
        }

        return rst;
    }

    private static boolean checkNum(int pre, int post) {
        int preGap = pre == 100 ? 0 : Integer.toString(pre).length();
        return Math.abs(N - pre) + preGap
            > Math.abs(N - post) + Integer.toString(post).length();
    }

}


