import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
/**
 * 
 * DP로 접근해서 풀이???
 * 
 * DFS활용한 브루트포스
 * 파이프 방향에 따라 이동 제한
 * 가로 방향 => 가로, 대각선
 * 세로 방향 => 세로, 대각선
 * 대각선 방향 => 가로, 세로, 대각선
 * 
 * 결국 대각선 방향은 모든 케이스에서 다뤄줘야한다.
 * 
 * 
 *
 */
public class Main_BOJ_17070_파이프옮기기1_220ms {
    static int N;
    static int[][] map;
    static int ans;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
 
        ans = 0;
        DFS(1, 2, 0);
        sb.append(ans);
        System.out.println(sb);
        br.close();
    }
 
    // direction이 0일 때는 파이프가 가로 방향, 1일 때는 세로 방향, 2일 때는 대각선 방향.
    public static void DFS(int r, int c, int d) {
        if (r == N && c == N) { // 종료 조건
            ans++;
            return;
        }
 
        switch (d) {
        case 0: 
            if (c + 1 <= N && map[r][c + 1] == 0) { // 동쪽
                DFS(r, c + 1, 0);
            }
            break;
        case 1:
            if (r + 1 <= N && map[r + 1][c] == 0) { // 남쪽
                DFS(r + 1, c, 1);
            }
            break;
        case 2: // 파이프가 대각선일 경우, 갈 수 있는 경우는 동쪽과 남쪽, 대각선임.
            if (c + 1 <= N && map[r][c + 1] == 0) { // 동쪽
                DFS(r, c + 1, 0);
            }
 
            if (r + 1 <= N && map[r + 1][c] == 0) { // 남쪽
                DFS(r + 1, c, 1);
            }
            break;
        }
        // 파이프가 어떤 방향이든지, 대각선은 검사해서 가장 아래로 뺐음.
        if (c + 1 <= N && r + 1 <= N && map[r][c + 1] == 0 && map[r + 1][c] == 0 && map[r + 1][c + 1] == 0) {
            DFS(r + 1, c + 1, 2);
        }
    }
 
}
