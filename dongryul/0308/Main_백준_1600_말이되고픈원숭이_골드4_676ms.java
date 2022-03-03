import java.util.*;
import java.io.*;

/** Main_백준_1600_말이되고픈원숭이_골드4_676ms*/
class Main_백준_1600_말이되고픈원숭이_골드4_676ms{
    
    private static class Node{
        int row, col, jumped;
        public Node(int row, int col, int jumped){
            this.row = row;
            this.col = col;
            this.jumped = jumped;
        }
    }
    
    private static int K, W, H;
    private static int[][] map;
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};
    private static int[] kr = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static int[] kc = {1, 2, 2, 1, -1, -2, -2, -1};
    
    
    private static void go(){
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0));
        // 거리를 저장할 배열 ( 방문 처리 ) 
        int[][][] distance = new int[H][W][K+1];
        for(int i=0; i<K; i++) distance[0][0][i]=1;
        
        while(!q.isEmpty()){
            Node curr = q.poll();
            int row = curr.row;
            int col = curr.col;
            int jumped = curr.jumped;
            // 여기서 처리 안하면 아래 2개의 반복문에서 확인을 해야하는데, 중복이 발생해서 옮겼습니다.
            if(row==H-1 && col==W-1){
                // 1부터 시작했으니까 1을 뺀다.
                System.out.println(distance[row][col][jumped]-1);
                return;
            }
            // 인접 좌표 먼저 탐색
            for(int i=0; i<4; i++){
                int nr = row + dr[i];
                int nc = col + dc[i];
                // 배열의 범위를 벗어났거나, 이미 방문된 좌표이거나, 장애물이 있는 좌표라면
                if(nr<0 || nr>=H || nc<0 || nc>=W || distance[nr][nc][jumped]!=0 || map[nr][nc]==1) continue;
                // 이동할 수 있는 좌표라면
                distance[nr][nc][jumped] = distance[row][col][jumped]+1;
                q.offer(new Node(nr,nc,jumped));
            } // end of move 4 dir
            // 점프 횟수가 남았다면
            if(curr.jumped<K){
                // 나이트처럼 이동
                for(int i=0; i<8; i++){
                    int nr = row + kr[i];
                    int nc = col + kc[i];
                    int jumped1 = curr.jumped;
                    // 배열의 범위를 벗어났거나, 이미 방문된 좌표이거나, 장애물이 있는 좌표라면
                    if(nr<0 || nr>=H || nc<0 || nc>=W || distance[nr][nc][jumped1+1]!=0 || map[nr][nc]==1) continue;
                    distance[nr][nc][jumped1+1] = distance[row][col][jumped1]+1;
                    q.offer(new Node(nr,nc,jumped1+1));
                } // end of for knight move
            } // end of if can jump
        } // end of while q.isEmpty
        // 목적지까지 갈 수 없다면
        System.out.println(-1);
        
    } // end of go()
    
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        /*
		 * BFS 문제 원숭이는 장애물을 넘을 수 없고 장애물을 피해 0, 0 -> W-1, H-1 로 가야한다. 
		 * 원숭이가 나이트처럼 K번 움직일 수 있을 때, 최소 몇 번의 이동으로 목표 지점으로 갈 수 있는가?
		 * class Node{row col jumped}을 만들어 데이터를 저장하고 점프 횟수가 남아있는 경우 말처럼 이동할 수 있게 해보자
		 * 
		 * + 구현하다보니, 방문처리를 점프 횟수별로 해야함을 느꼈습니다..
		 */
        
        // Initialize
        map = new int[H][W];
        for(int i=0; i<H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // BFS
        go();
    }
}
