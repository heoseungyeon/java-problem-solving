package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac 자료구조 9012 괄호 풀이 방법: 이거 어디 기출이었는데.. 카뱅인턴이랑 매우 유사 closeCnt를 두고 얘가 중간에 음수가 되거나 다돌았을 때
 * 0이아니면 Valid 하지 않다고 판별
 */

public class Main_9012 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int T;
    public static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws Exception {

        // 1. input
        T = Integer.parseInt(br.readLine());

        // 2. check VPS
        for (int i = 0; i < T; i++) {
            String input = br.readLine();
            if (isVPS(input)) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        // 3. print
        System.out.println(sb.toString());
    }

    private static boolean isVPS(String input) {
        boolean answer = true;
        // this is necessary cnt
        int closeCnt = 0;

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                closeCnt += 1;
            } else {
                if (closeCnt == 0) {
                    answer = false;
                    break;
                }
                closeCnt -= 1;
            }
        }
        if (closeCnt != 0) {
            answer = false;
        }

        return answer;
    }

}
