package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * solved.ac bfs 17413 단어 뒤집기2 풀이 방법: 태그 open 기준으로 분기하여 단어와 태그를 구분해서 reverse 여부 판단함.
 */

public class Main_17413 {


    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static StringTokenizer tokens;


    public static void main(String[] args) throws IOException {
        // 1. input
        String input = br.readLine();
        StringBuilder curWord = new StringBuilder();

        boolean tagOpen = false;

        // 2. solution
        for (int i = 0; i < input.length(); i++) {
            if (tagOpen) {
                if (input.charAt(i) != '>') {
                    curWord.append(input.charAt(i));
                    continue;
                } else {
                    tagOpen = false;
                    sb.append("<");
                    sb.append(curWord);
                    sb.append(">");
                    curWord = new StringBuilder();
                }

            } else {
                if (input.charAt(i) == '<') {
                    if (!curWord.toString().isEmpty()) {
                        sb.append(curWord.reverse());
                        curWord = new StringBuilder();
                    }

                    tagOpen = true;
                } else if (input.charAt(i) == ' ') {

                    sb.append(curWord.reverse() + " ");
                    curWord = new StringBuilder();
                } else {
                    curWord.append(input.charAt(i));
                }
            }
        } // for

        if (!curWord.toString().isEmpty()) {
            sb.append(curWord.reverse());
        }

        // 3. print
        System.out.println(sb.toString());
    }
}
