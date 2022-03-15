import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main_9466_텀프로젝트_G3_1076ms {

    static final int CYCLE = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        // test-case
        for (int i = 0; i < t; i++) {
            int cnt = 0;
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            // 입력
            for (int j = 1; j <= N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            } // end of for 입력


            // 0이면 짝 대기, -1 이면 아직 순서 X
            int[] team = new int[N + 1];

            for (int j = 1; j <= N; j++) {
                if (team[j] == 0) buildTeam(j, team, arr);
            }

            for (int j = 1; j <= N; j++) {
                if (team[j]!=CYCLE) cnt++;
            }
            sb.append(cnt).append('\n');
        } // end of for test-case
        System.out.println(sb);
    } // end of main

    // 사이클 돌면 팀임
    static void buildTeam(int x, int[] team,int[] arr) {
        int cur = x;
        while (true) {
            // 현재 팀 후보 표시
            team[cur] = x;
            // 다음으로
            cur = arr[cur];
            // 현재 팀 후보에서 사이클(지나간 곳 다시 오면)이 나면 얘부터 팀임
            if (team[cur] == x) {
                // 해당 팀에 포함되는 얘들 다 팀이라는 표시 처리
                do {
                    team[cur] = CYCLE;
                    cur = arr[cur];
                } while (team[cur] != CYCLE);
                return;
            } else if (team[cur] != 0) {    // 이전에 결과를 냈던 곳이면 프루닝
                return;
            }
        }
    }
}
