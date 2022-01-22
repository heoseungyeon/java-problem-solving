package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1897 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static int d;
    public static String saying;
    public static String[] dictionary;
    public static Map<Integer, String> dictionaryLengthMap = new HashMap<>();
    public static int answer = -1;

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());

        d = Integer.parseInt(tokens.nextToken());
        saying = tokens.nextToken();

        // 2. input & calculate
        for (int i = 0; i < d; i++) {
            String word = br.readLine();
            findByBFS(word);
        }

        // 3. print
        System.out.println(dictionaryLengthMap.get(answer));

    }

    private static void findByBFS(String word) {
        Queue<Info> queue = new LinkedList<>();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == saying.charAt(0)) {
                queue.add(new Info(i, 1, Character.toString(word.charAt(i))));
            }
        }

        while (!queue.isEmpty()) {
            Info info = queue.poll();

            for (int i = info.idx + 1; i < word.length(); i++) {
                if (info.sayingIdx < saying.length() && word.charAt(i) == saying.charAt(
                    info.sayingIdx)) {
                    String str = info.string + word.charAt(i);
                    if (Objects.equals(str, saying)) {
                        if (answer <= word.length()) {
                            answer = word.length();
                            dictionaryLengthMap.put(word.length(), word);
                        }
                        return;
                    }
                    queue.add(new Info(i, info.sayingIdx + 1, str));
                }
            }
        }
    }

    static class Info {

        private int idx;
        private int sayingIdx;
        private String string;

        public Info(int idx, int sayingIdx, String string) {
            this.idx = idx;
            this.sayingIdx = sayingIdx;
            this.string = string;
        }

        @Override
        public String toString() {
            return "Info{" +
                "idx=" + idx +
                ", sayingIdx=" + sayingIdx +
                ", string='" + string + '\'' +
                '}';
        }
    }

}
