import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_BOJ_2457_공주님의정원_G3_박지호_512ms {

static int n;
	
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.valueOf(br.readLine());
		StringTokenizer st;
		Flower[] flowers = new Flower[n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int startMonth = Integer.valueOf(st.nextToken());
			int startDay = Integer.valueOf(st.nextToken());
			int endMonth = Integer.valueOf(st.nextToken());
			int endDay = Integer.valueOf(st.nextToken());
			
			int start = startMonth * 100 + startDay;
			int end = endMonth * 100 + endDay;
			flowers[i] = new Flower(start, end);
		}
		
		Arrays.parallelSort(flowers);	// 조건에 맞게 정렬 => 배열 크기가 크고 값이 들쭉날쭉일 때 parrelsort가 효율적 아니면 그냥 sort쓰자
		
		int endDay = 1201;
		int start = 301;
		int count = 0;
		int max = 0;
		int index = 0;
		
		// 현재 종료일이 12월 1일보다 적은 경우만 가능(12월 1일 초과면 이미 조건 만족했기 때문에)
		while(start < endDay) {
			boolean isFinded = false;	// 새 꽃 찾은지 여부 확인
			
			for(int i = index; i < n; i++) {
				if(flowers[i].start > start) {	// 종료일보다 시작일이 이후면 의미없음. 종료일에는 시작해야 이어지기 때문에
					break;
				}
				
				if(max < flowers[i].end) {
					isFinded = true;
					max = flowers[i].end;
					index = i + 1;
				}
			}
			
			if(isFinded) {
				start = max;
				count++;
			}
			else {
				break;
			}
		}
				
		if(max < endDay) {
			System.out.println(0);
		}
		else {
			System.out.println(count);
		}
	}
}

class Flower implements Comparable<Flower> {
	int start;
	int end;
	
	Flower(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	// 1) 시작일 낮은 순
	// 2) 종료일 높은 순
	@Override
	public int compareTo(Flower f) {
		if(this.start < f.start) {
			return -1;
		}
		else if(this.start == f.start) {
			if(this.end > f.end) {
				return -1;
			}
			else if(this.end == f.end) {
				return 0;
			}
			return 1;
		}
		else {
			return 1;
		}
	}
}
// 시작일자가 3월 1일 이전 그룹 / 끝나는 일자가 11월 30일 이후 그룹 / 사이에 있는 그룹
// 30일 : 4 6 9 11
// 31일 : 3 5 7 8 10
//
// 3월 1일 ~ 11월 30일까지 배열을 만들고 true로 마킹해두자
//
// A : 끝나는 일자 기준으로 정렬해서 앞에서부터 선택하고 해당 종료 날짜에 가장 근접한 날짜 뽑는다?
// B : 가운데 그룹에서 가장 범위가 넓은 꽃 뽑고 해당 시작 끝 날짜 기준으로 양옆 그룹에서 뽑는다?

// 1. 첫 그룹에서 끝나는 일자 기준으로 정렬해서 가장 늦게 끝나는 꽃 선택
// 2. 두 번째 그룹에서 시작 일자 기준으로 정렬해서 가장 일찍 피는 꽃 선택
// 3. 세 번째 그룹에서 피어있는 일자 수가 큰 순서대로 정렬해서 하나씩 선택해가며 방문처리 
