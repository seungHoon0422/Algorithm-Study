import java.io.*;
import java.util.*;

public class Main_백준_2660_회장뽑기_골드5_76ms {
	
	public static void main(String[] args) throws Exception{
		
		/*
		 * 플로이드 와샬 기본 문제 느낌
		 * 플로이드 와샬로 친구 관계를 먼저 정리? 구한다.
		 * map을 읽으면서 친구 관계 중 최대값이 얼마인지를 확인한다.
		 * 
		 * 이후 친구 점수를 보고, 회장 후보를 list에 담고
		 * list를 출력하면 끝
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N+1][N+1];
		// 친구가 총 50명이 올 수 있으니, 친구 점수의 최대값을 50으로 설정했다.
		for(int i=1; i<=N; i++) Arrays.fill(map[i], 50);
		StringTokenizer st = null;
		int a, b;
		while(true) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if(a==-1 && b==-1) break;
			map[a][b] = 1;
			map[b][a] = 1;
		}
		
		// 플로이드 와샬
		int temp = 0;
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(i==k || i==j) continue;
					temp = map[i][k] + map[k][j];
					if(map[i][j] > temp) map[i][j] = temp;
				}
			}
		}
		
		// 회장 후보를 담고 있을 리스트
		ArrayList<Integer> list = new ArrayList<>();
		int min = 50; // 최대 점수 50점으로
		for(int i=1; i<=N; i++) {
			temp = 0;
			for(int j=1; j<=N; j++) {
				if(i==j) continue;
				// 해당 후보의 친구 점수 중 최대값을 저장
				temp = Math.max(temp, map[i][j]);
			}
			// 최저 점수라면 갱신
			if(temp<min) {
				min = temp;
				list.clear();
				list.add(i);
			}
			// 동일한 점수면 리스트에 추가
			else if(temp==min) list.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(min).append(" ").append(list.size()).append("\n");
		for(Integer i:list) sb.append(i).append(" ");
		
		System.out.println(sb.toString());
		
		
	} // end of main 
	
	
} // end of class 
