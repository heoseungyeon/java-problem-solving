package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * solved.ac 구현(약 1시간) 3699 주차빌딩 풀이 방법: 문제-테스트 케이스를 이해하는데 시간이 좀 걸렸다. person에 맞는 층과 위치 를 저장하는 2개의
 * Map자료형과 index를 층으로 갖으며 현재 층의 컨베이어 벨트 위치를 저장하는 int[] 배열을 통해 시뮬레이션하여 문제를 풀이함. 사람의 번호가 기다리는 순서임을
 * 인지하지 못해 오래걸림. 또한, 현재위치에서 타겟 위치로 가는데 걸리는 시간은 최소여야 하는 것도 문제조건에 필요할 듯.
 */

public class Main_3699 {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer tokens;
    public static StringBuilder sb = new StringBuilder();
    public static int t, h, l; // 주차 빌딩의 높이 h와 컨베이어 벨트의 길이 l
    public static Map<Integer, Integer> floors = new HashMap<>(); // key(person) - value(층)
    public static Map<Integer, Integer> locations = new HashMap<>(); // key(person) - value(위치)
    public static int[] curLocations; // idx == floor


    public static void main(String[] args) throws Exception {
        // 1. input
        t = Integer.parseInt(br.readLine());
        while (t > 0) {
            tokens = new StringTokenizer(br.readLine());
            h = Integer.parseInt(tokens.nextToken());
            l = Integer.parseInt(tokens.nextToken());
            Map<Integer, Integer> floors = new HashMap<>(); // key(person) - value(층)
            Map<Integer, Integer> locations = new HashMap<>(); // key(person) - value(위치)
            int[] curLocations = new int[h + 1];
            int maxPerson = 0;

            for (int i = 0; i < h; i++) {
                tokens = new StringTokenizer(br.readLine());
                for (int j = 0; j < l; j++) {
                    int person = Integer.parseInt(tokens.nextToken());
                    if (person != -1) {
                        floors.put(person, i + 1);
                        locations.put(person, j);
                        maxPerson = Math.max(maxPerson, person);
                    }
                }
            }

            // 2. simulation
            int answer = 0;
            for (int i = 1; i <= maxPerson; i++) {
                int floor = floors.get(i);
                int targetLocation = locations.get(i);
                int curLocation = curLocations[floor];
                if (floor != 1) {
                    answer += 10 * (floor - 1) * 2;
                }

                // 이동
                answer += 5 * Math.min(Math.abs(curLocation - targetLocation),
                    l - Math.abs(curLocation - targetLocation));
                curLocations[floor] = targetLocation;
            }

            // 3. append to sb
            sb.append(answer + "\n");
            t--;
        }

        // 4. print
        System.out.println(sb.toString());
    }

}
