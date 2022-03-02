package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * solved.ac 자료구조 9322 철벽 보안 알고리즘 풀이 방법: Map 자료형에 1 공개키, 2공개키의 위치를 저장한 뒤, 암호화가 평문이 될때 배치 패턴을 int[]에
 * 저장하여 풀이.
 */

public class Main_9322 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static StringBuilder sb = new StringBuilder();
    public static int T;

    public static void main(String[] args) throws Exception {
        // 1. input
        T = Integer.parseInt(br.readLine());

        // 2. simulation
        while (T > 0) {
            int wordCnt = Integer.parseInt(br.readLine());

            Map<String, Integer> firstOpenKeys = new HashMap<>();

            tokens = new StringTokenizer(br.readLine());
            for (int i = 0; i < wordCnt; i++) {
                firstOpenKeys.put(tokens.nextToken(), i);
            }

            Map<String, Integer> secondOpenKeys = new HashMap<>();

            tokens = new StringTokenizer(br.readLine());
            for (int i = 0; i < wordCnt; i++) {
                secondOpenKeys.put(tokens.nextToken(), i);
            }

            int[] indexes = new int[wordCnt];

            for (String key : secondOpenKeys.keySet()) {
                indexes[secondOpenKeys.get(key)] = firstOpenKeys.get(key);
            }

            String[] answer = new String[wordCnt];

            tokens = new StringTokenizer(br.readLine());
            for (int i = 0; i < wordCnt; i++) {
                answer[indexes[i]] = tokens.nextToken();
            }

            for (int i = 0; i < wordCnt; i++) {
                sb.append(answer[i] + " ");
            }
            sb.append("\n");
            T--;
        }

        // 3. print
        System.out.println(sb.toString());
    }

}
