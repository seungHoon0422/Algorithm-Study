import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/** 1966 프린터큐*/
public class Main_백준_1966_프린터큐_실버3_148ms {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		// 큐와 배열을 사용하자
		// 큐에는 인덱스를 저장하고
		// 배열은 점수를 내림차순 정렬하여 저장하자
		// 인덱스가 갖는 값이 가장 작은 값인가? 를 구별하여 아니면 맨 끝으로 이동
		
		int TC = Integer.parseInt(br.readLine());
		for(int testcase=0; testcase<TC; testcase++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());
			
			
			Integer[] score = new Integer[n];
			Queue<Integer> q = new LinkedList<Integer>();
			Queue<Integer> result = new LinkedList<Integer>();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				score[i] = Integer.parseInt(st.nextToken());
				q.offer(i);
			}
			// 내림차순 정렬
			Integer[] oriScore = score.clone();
			Arrays.sort(score, Collections.reverseOrder());
			
			int idx = 0;
			while(idx!=n) {
				// 어떤 기준으로 탐색할지
				int curr = q.poll();
				if(oriScore[curr]<score[idx]) {
					q.offer(curr);
				}
				else if(oriScore[curr]==score[idx]) {
					result.offer(curr);
					idx++;
				}
			} // end of while
			
			int cnt = 1;
			while(result.peek()!=target) {
				result.poll();
				cnt++;
			}
			sb.append(cnt).append("\n");
		} // end of for
		
		System.out.println(sb.toString());
	} // end of main
} // end of class
