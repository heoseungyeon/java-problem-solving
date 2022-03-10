package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * solved.ac 해시() 1394 암호 풀이 방법: 너무너무 어려웟다..이것저것 다시도해보고 안됐는데 answer 연산을 증감 연산자를 사용하지 않으니깐
 * 통과됨..왓!!!!!!!!!!
 */
public class Main_1394 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static String availableString;
    public static String password;
    public static Map<Character, Integer> idxMap = new HashMap<>();
    public static long answer = 0;

    public static void main(String[] args) throws Exception {

        // 1. input
        availableString = br.readLine();
        password = br.readLine();

        // find idx
        for (int i = 0; i < availableString.length(); i++) {
            idxMap.put(availableString.charAt(i), i + 1);
        }

        // calculate abcdefg  bdg  136 L^pL *  abc ./ ab / bacb ab 4 4 ab
        long buf = 1;
        for (int i = password.length() - 1; i >= 0; i--) {
            answer = (answer + (buf * (idxMap.get(password.charAt(i))))) % 900528;
            buf = (buf * availableString.length()) % 900528;
        }
        //tryPass("", 0, password.length());

        System.out.println(((answer) % 900528));
    }

    private static boolean tryPass(String str, int cnt, int target) {
        boolean rtn = false;
        if (cnt == target) {
            answer += 1;
            if (str.equals(password)) {
                return true;
            } else {
                return false;
            }
        }

        for (int i = 0; i < availableString.length(); i++) {
            rtn = tryPass(str + availableString.charAt(i), cnt + 1, target);
            if (rtn) {
                break;
            }
        }

        return rtn;
    }

}