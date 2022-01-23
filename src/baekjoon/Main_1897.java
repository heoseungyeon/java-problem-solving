package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac 해시 set 1897(엄청 오래걸림) 토달기 풀이 방법: 문제를 진짜 제대로 확실히!! 이해해야 된다는 것을 깨닫게 해준 문제.. 처음에 내가 이해한 걸로는
 * 그냥 한글자씩 단어에 덧붙여 사전에 등재된 단어만 만들어낼 수 있으면 되는 줄 알았다. 하지만 무한 틀렸습니다로 문제를 여러 번 읽고나니 한글자씩 덧붙일 때 무조건 덧붙인
 * 단어가 사전에 등재되어야 한다는 것 ,,, ㅠ.ㅠ 첫번째 풀이 방법 : BFS를 돌면서 현재 단어보다 길이가 1 더 긴 단어를 탐색한다. 해당 단어가 조건에맞게 하나의
 * 알파벳이 끼어있는 경우 큐에 넣는다. 두번째 리팩토링 : 길이(key) : 단어를 맞는 map 자료형을 사용하여 for 문의 반복 수를 줄여준다. 세번째 리팩토링 : set
 * 자료형에 queue에 add 해준 단어를 저장하여 방문처리를 해준다. 느낀점 : 어떤 문제를 푸든 시간복잡도를 효율적으로 줄일 수 있는 방법을 알아보자.
 */

public class Main_1897 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;

    public static int d;
    public static String saying;
    public static Set<String> dictionarySet = new HashSet<>();
    public static Map<Integer, Set<String>> dict = new HashMap<>();
    public static int maxLength = -1;
    public static String answer;

    public static void main(String[] args) throws Exception {
        // 1. input
        tokens = new StringTokenizer(br.readLine());

        d = Integer.parseInt(tokens.nextToken());
        saying = tokens.nextToken();
        answer = saying;
        // 2. input
        for (int i = 0; i < d; i++) {
            String word = br.readLine();
            dictionarySet.add(word);
            if (dict.containsKey(word.length())) {
                dict.get(word.length()).add(word);
            } else {
                dict.put(word.length(), new HashSet<>(List.of(word)));
            }
        }

        // 3. find By BFS
        findByBFS(saying);

        // 4. print
        System.out.println(answer);

    }

    private static void findByBFS(String saying) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(saying);
        while (!queue.isEmpty()) {
            String curWord = queue.poll();
            for (String word : dict.getOrDefault(curWord.length() + 1, new HashSet<>())) {
                int chanceCnt = 1;
                int idx = 0;
                for (int i = 0; i < curWord.length(); i++) {
                    if (curWord.charAt(i) != word.charAt(idx)) {
                        if (chanceCnt == 1) {
                            i--;
                            chanceCnt = 0;
                        } else {
                            chanceCnt = -1;
                            break;
                        }
                    }
                    idx++;
                }
                if (chanceCnt >= 0) {
                    if (word.length() > maxLength) {
                        answer = word;
                        maxLength = word.length();
                    }
                    if (!visited.contains(word)) {
                        queue.add(word);
                        visited.add(word);
                    }
                }
            }
        }
    }

}
