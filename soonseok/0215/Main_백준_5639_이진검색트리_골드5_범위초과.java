package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 트리 만든 후 postorder로 출력
 * 이진트리이므로 배열을 사용해보자
 * 
 *       1       좌  n*2, n*2+1 우
 *     2   3
 *    4 5 6 7
 *    
 * 노드의 개수가 10000개이므로
 * 20000+1 까지는 검사를 해야한다... ( 10000번째 노드의 오른쪽 자식 )
 * 
 * 
 * 구현 불가능, 완전이진트리가 아니기 때문임, 만약 트리의 노드 개수가 10000개인데 왼쪽으로 1자로 붙은 경우에는 2^10000 인덱스가 필요..
 * 
 */

public class Main_백준_5639_이진검색트리_골드5_범위초과 {

	private static int[] tree;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		tree = new int[10001]; // N≤10000 이므로 1부터 root로 사용

		// VLR 순서대로 전위순회했으니 맨 처음 값이 root 이다.
		// 우선 root 노드를 만들어 준다
		tree[1] = Integer.parseInt(br.readLine());
		// 입력값은 양의정수이므로 0은 아무것도 없는 상태

		String temp;
		// 배열의 최대 크기는 10000, null이 들어올때까지 입력 받는다
		for (int i = 2; i <= 10000; i++) {
			temp = br.readLine();
			if (temp == null || temp.length() == 0)
				break;
			// 노드 추가
			putNode(1, Integer.parseInt(temp));
		}

		postOrder(1);

	} // end of main

	// root를 받고 후위순회(LRV) 하는 메소드
	public static void postOrder(int idx) {
		// 무조건 자식이 없는 놈이다
		if (idx > 10001) {
			System.out.println(tree[idx]);
			return;
		}

		int left = idx * 2;
		int right = idx * 2 + 1;

		if (tree[left] != 0)
			postOrder(left);
		if (tree[right] != 0)
			postOrder(right);
		System.out.println(tree[idx]);
	} // postOrder()

	// tree에 node 추가
	public static void putNode(int idx, int newNode) {
		int left = idx * 2;
		int right = idx * 2 + 1;

		if (tree[idx] > newNode) { // 새 노드가 더 작을때
			if (tree[left] == 0) { // 왼쪽칸 비어있으면
				tree[left] = newNode; // 새 노드가 그 자리로
			} else { // 비어있지 않으면 왼쪽칸으로 가서 반복
				putNode(left, newNode);
			}
		}
		// key값이 같은 경우는 없다
		else { // 새 노드가 더 클때
			if (tree[right] == 0) { // 오른쪽칸 비어있으면
				tree[right] = newNode;
			} else { // 비어있지 않으면 오른쪽칸으로 가서 반복
				putNode(right, newNode);
			}
		}
	} // end of putNode()

} // end of class
