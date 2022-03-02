package BOJ0302;

import java.io.*;
import java.util.*;
public class Main_2665_미로만들기_Gold4_백승훈_132ms {
    static int dx [] = {0,0,1,-1};
    static int dy[] = {1,-1,0,0};
    static int map[][];
    static boolean visited[][][];
    static int n;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        map = new int[n][n];
        visited = new boolean[n][n][n*n];
        
        for(int i=0; i<n; i++) {
            String str= br.readLine();
            for(int j=0; j<n; j++) {
                map[i][j] = str.charAt(j)-'0';
            }
        }
        
        bfs();
    }
    public static void bfs() {
        PriorityQueue<Node>q = new PriorityQueue<>();
        q.add(new Node(0,0,0));
        
        while(!q.isEmpty()){
            Node a= q.poll();
            if(a.x ==n-1 && a.y== n-1) {
                System.out.println(a.num);
                return ;
            }
            for(int i=0; i<4; i++) {
                int nx = a.x+dx[i];
                int ny = a.y+dy[i];
                if(isRange(nx,ny))  {
                    if(map[nx][ny]==1 && !visited[nx][ny][a.num]) {
                        q.add(new Node(nx,ny,a.num));
                        visited[nx][ny][a.num] = true;
                    }
                    else if(map[nx][ny]==0 && !visited[nx][ny][a.num+1]){
                        q.add(new Node(nx,ny,a.num+1));
                        visited[nx][ny][a.num+1] = true;
                    }
                }
            }
        }
    }
    public static boolean isRange(int x, int y) {
        if(x>=0 && y>=0 && x<n && y<n) {
            return true;
        }
        return false;
    }
}
class Node implements Comparable<Node>{
    int x,y,num;
    Node(int x, int y, int num){
        this.x=x;
        this.y=y;
        this.num=num;
    }
    public int compareTo(Node o) {
        return this.num - o.num;
    }
}
