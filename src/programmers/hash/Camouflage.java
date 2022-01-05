package programmers.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 프로그래머스 고득점 kit 위장 해시
 */

/*
 * 풀이 방법 : 백트래킹을 사용해서 nCn~nC1을 했는데 nCn을 할 때, 한번의 메서드 호출로 누적합을 구했다.
 */

public class Camouflage {

    static HashMap<String, Integer> hashMap = new HashMap<>();
    static List<Integer> values;
    static int kindSize = 0;

    public static void main(String[] args) {
        Camouflage instance = new Camouflage();
        System.out.println(
            instance.solution(
                new String[][]{{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"},
                    {"green_turban", "headgear"}})
        );
//        System.out.println(
//            instance.solution(new String[][]{{"crowmask", "face"}, {"bluesunglasses", "face"},
//                {"smoky_makeup", "face"}})
//        );
    }

    public int solution(String[][] clothes) {
        int answer = 0;
        setHashMap(clothes);
        values = new ArrayList<>(hashMap.values());
        answer += backTrackingCombination(0, kindSize, kindSize, 1);

        return answer;
    }

    public void setHashMap(String[][] clothes) {
        for (int i = 0; i < clothes.length; i++) {
            if (hashMap.containsKey(clothes[i][1])) {
                hashMap.replace(clothes[i][1], hashMap.get(clothes[i][1]) + 1);
            } else {
                kindSize += 1;
                hashMap.put(clothes[i][1], 1);
            }
        }
    }

    public int backTrackingCombination(int depth, int n, int r, int result) {
        int rtn = 0;
        if (r != n) {
            rtn += result;
        }
        if (r == 0) {
            return rtn;
        }
        for (int i = depth; i < n; i++) {
            rtn += backTrackingCombination(i + 1, n, r - 1, result * values.get(i));
        }
        return rtn;
    }

}
