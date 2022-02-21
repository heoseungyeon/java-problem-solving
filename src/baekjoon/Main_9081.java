package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * solved.ac 조합( 오래걸림(블로그 참고) ) 9081 단어 맞추기 풀이 방법: next_permutation 원리를 정확하게 이해하고 있어야지 시간초과나지 않을 수
 * 있다. (C++은 라이브러리로 구현되어있으나 java 는 직접 구현해야 함. 오히려 좋아..ㅋ) 1. 먼저 주어진 문자열의 맨 뒤 인덱스부터 시작해서 str[i] >
 * str[i-1] 인 부분을 찾는다. (만약 ABCD 일 경우 C와 D가 된다) 2. str[i-1]는 교체가 될 대상이 되고, 문자열의 맨 뒤 인덱스부터 시작해서
 * str[i-1] < str[j] 인 j를 찾는다.(만약 ABCD 일 경우 C와 D가 된다) 3. str[i-1]와 str[j] 를 swap 한 뒤, i 부터 맨 뒤까지 정렬을
 * 해준다. 겨우 이해함.. ㅠㅠㅠㅠㅠㅠ
 */

public class Main_9081 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static int T;
    public static String[] words;
    public static String[] answers;

    public static void main(String[] args) throws Exception {

        // 1. input
        T = Integer.parseInt(br.readLine());

        answers = new String[T];
        for (int i = 0; i < T; i++) {
            String string = br.readLine();
            char[] word = string.toCharArray();
            answers[i] = getNextPermutation(word);
        }

        // 3. print
        for (int i = 0; i < T; i++) {
            System.out.println(answers[i]);
        }

    }

    private static String getNextPermutation(char[] word) {
        int length = word.length;
        int swapIdx1 = -1;
        int swapIdx2 = -1;
        for (int i = length - 1; i >= 1; i--) {
            if (word[i] > word[i - 1]) {
                swapIdx1 = i - 1;
                break;
            }
        }
        if (swapIdx1 == -1) {
            return String.valueOf(word);
        }
        for (int i = length - 1; i >= 1; i--) {
            if (word[i] > word[swapIdx1]) {
                swapIdx2 = i;
                break;
            }
        }
        // swap
        char tmp = word[swapIdx1];
        word[swapIdx1] = word[swapIdx2];
        word[swapIdx2] = tmp;

        Arrays.sort(word, swapIdx1 + 1, length);

        return String.valueOf(word);

    }


}
