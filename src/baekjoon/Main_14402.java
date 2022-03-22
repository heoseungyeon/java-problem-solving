package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * solved.ac 시뮬레이션 (51분 : 11:55 - 12:24 && 14:17 - 14:39) 14402 야근 풀이 방법: 블로그 살짝 참고함. 동명이인처리 카운트 하는
 * 아이디어를 생각하지 못했음. Map<String, Integer> pMap에 회사의 있는 직원의 수를 저장하여 문제를 풀이하였음.
 */

public class Main_14402 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int q;
    public static Map<String, Integer> pMap = new HashMap<>();
    public static int count = 0;


    public static void main(String[] args) throws Exception {
        // 1. input
        q = Integer.parseInt(br.readLine());

        // 2. input and simulation
        // ‘+’일 때 들어간 것이고, ‘-‘ 나간 것 -> 정답 케이스 : +(!-) 와 (!+)-
        for (int i = 0; i < q; i++) {
            tokens = new StringTokenizer(br.readLine());
            String s = tokens.nextToken();
            char p = tokens.nextToken().charAt(0);
            if (p == '-') {
                if (pMap.containsKey(s)) {
                    if (pMap.get(s) == 0) {
                        count += 1;
                    } else {
                        pMap.replace(s, pMap.get(s) - 1);
                    }
                } else {
                    count += 1;
                }
            } else if (p == '+') {
                if (pMap.containsKey(s)) {
                    pMap.replace(s, pMap.get(s) + 1);
                } else {
                    pMap.put(s, 1);
                }
            }
        }

        // 돔명이인 처리 (확실한 경우만 카운트)
        for (int value : pMap.values()) {
            if (value > 0) {
                count += value;
            }
        }

        // 3. print
        System.out.println(count);
    }

}
