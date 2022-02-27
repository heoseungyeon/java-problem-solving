package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * solved.ac 해시 (22분 > 14:40 - 15:02 ) 20291 파일 정리 풀이 방법: hashMap을 이용해서 풀었는데 treeMap을 사용하면 자동으로 key
 * 기준으로 정렬해주니 그걸 사용하면 더 편했을 듯 .
 */

public class Main_20291 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int N;

    public static Map<String, Integer> map = new HashMap<>();


    public static void main(String[] args) throws Exception {

        // 1. input
        N = Integer.parseInt(br.readLine());

        // 2. set map
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            String[] splitInput = input.split("\\.");
            map.put(splitInput[1], map.getOrDefault(splitInput[1], 0) + 1);
        }

        // 3. put to PQ
        PriorityQueue<Info> pq = new PriorityQueue<>();

        for (String key : map.keySet()) {
            pq.add(new Info(key, map.get(key)));
        }
        // 4. print
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            System.out.println(info.key + " " + info.value);
        }
    }

    public static class Info implements Comparable<Info> {

        private String key;
        private Integer value;

        public Info(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Info o) {
            if (this.key.compareTo(o.key) > 0) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
