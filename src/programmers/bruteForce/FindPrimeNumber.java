package programmers.bruteForce;

import java.util.LinkedList;
import java.util.List;

/**
 * 프로그래머스 고득점 kit 소수찾기 완전탐색
 */

/*
 * 풀이 방법 : List nums에 숫자들을 모두 저장한 뒤, DFS를 통해 완전 탐색
 */

public class FindPrimeNumber {

    public static boolean[] visited = new boolean[10000000];
    public static List<Integer> nums = new LinkedList<>();
    public static int cnt = 0;

    public static void main(String[] args) {
        FindPrimeNumber primeNumber = new FindPrimeNumber();

        System.out.println(primeNumber.solution("17"));
    }

    public int solution(String numbers) {
        int answer = 0;

        for (int i = 0; i < numbers.length(); i++) {
            nums.add(Integer.parseInt(Character.toString(numbers.charAt(i))));
        }

        findPrime("");

        answer = cnt;

        return answer;
    }

    public void findPrime(String nowNumString) {
        int size = nums.size();

        for (int i = 0; i < size; i++) {
            int num = nums.remove(i);
            nowNumString += num;
            // 정답 조건
            if (isPrime(Integer.parseInt(nowNumString)) && !visited[Integer.parseInt(
                nowNumString)]) {
                visited[Integer.parseInt(nowNumString)] = true;
                cnt++;
            }
            findPrime(nowNumString);
            // 롤백
            nowNumString = nowNumString.substring(0, nowNumString.length() - 1);
            nums.add(i, num);
        }

    }

    public boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= (int) Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
