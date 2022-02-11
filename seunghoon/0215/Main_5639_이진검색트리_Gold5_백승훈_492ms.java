package BOJ0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_5639_이진검색트리_Gold5_백승훈_492ms {

	
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int N;
	static Node head;
	
	/** 
	 * MEMO : 	input을 받는데 입력되는 노드의 개수가 정해져있지 않아 반복문의 조건에 입력받은 문자열과 null, ""과 비교 			
	 * 
	 * 아이디어 : 	전위순회로 입력받은 노드 순서대로 차례대로 트리에 삽입하면 트리 완성
	 * 			트리 생성 후 postOrder로 출력
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// N < 10,000 노드의 개수 
		
		head = new Node(Integer.parseInt(br.readLine()));	// 루트노드 따로 설정
		String input;										// 입력 노드의 개수가 정해져있지 않아 반복문에서 확인 
		while((input = br.readLine())!=null && !input.equals("")) {
			push(new Node(Integer.parseInt(input)), head);	// 노드 삽입			
		}
		postorder(head);
		System.out.println(sb);
	
	} // end of main
	
	public static void push(Node pushNode, Node currNode) {
		if(pushNode.value < currNode.value) { 	// 비교노드보다 값이 작은 경우
			if(currNode.left == null) {		// 왼쪽에 서브트리 존재 x
				currNode.left = pushNode; 		// 입력노드 연결
			} else {  					// 왼쪽 서브트리로 이동
				push(pushNode, currNode.left);
			}
		} else { 						// 비교 노드보다 값이 큰경우
			if(currNode.right == null) { 	// 오른쪽 서브트리 존재 x
				currNode.right = pushNode;		// 오른쪽에 노드 연결
			} else {					// 으론쪽 서브트리로 이동
				push(pushNode, currNode.right);
			}
		} // end of while
	}
	
	public static void postorder(Node node) {
		if(node == null) return;
		postorder(node.left);
		postorder(node.right);
		sb.append(node.value).append("\n");
	}
	
	static class Node{
		int value;
		Node left, right;
		
		Node(int value){
			this.value = value;
			this.left = null;
			this.right = null;
		}
		Node(int value, Node left, Node right){
			this.value = value;
			this.left = left;
			this.right = right;
		}
	}
	
} // end of class















