import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_14891_톱니바퀴_S1_백승훈_80ms {
	
	static StringBuilder sb= new StringBuilder();
	static int N, rotateGear, rotateDir;
	static boolean[] visited = new boolean[4];
	static String[] gears = new String[4];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<4; i++) {
			gears[i] = br.readLine();
		}
		
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			String[] token = str.split(" ");
			rotateGear = Integer.parseInt(token[0])-1;
			rotateDir = Integer.parseInt(token[1]);
			visited = new boolean[4];
			visited[rotateGear] = true;
			rotate(rotateGear, rotateDir);
		}// end of N
		
		
		int value = 1;
		int result = 0; 
		for(int i=0; i<4; i++, value*=2) {
			if(gears[i].charAt(0) == '1') {
				result += value;
			}
		}
		System.out.println(result);
		
	}// end of main
	
	// N극  : 0, S극 : 1, 시계 : 1, 반시계 : -1
	static void rotate(int gear, int dir) {
		// 왼쪽기어 회전
		if(gear>0 && visited[gear-1] == false && gears[gear].charAt(6) != gears[gear-1].charAt(2)) {
			visited[gear-1] = true;
			rotate(gear-1, -1*dir);
		}
		// 오른쪽 기어 회전
		if(gear<3 && visited[gear+1] == false && gears[gear].charAt(2) != gears[gear+1].charAt(6)) {
			visited[gear+1] = true;
			rotate(gear+1, -1*dir);
		}
		String curr = gears[gear];
		if(dir == 1) { // 시계방향 회전
			curr = curr.charAt(7)+curr.substring(0,7);
		} else { // 반시계 방향 회전
			curr = curr.substring(1) + curr.charAt(0);
		}
		gears[gear] = curr;
	}
	// 하이
	
}// end of class
