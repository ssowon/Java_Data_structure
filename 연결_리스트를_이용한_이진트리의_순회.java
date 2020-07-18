import java.io.*;
import java.util.Scanner;

class BinaryTree {
	String data;
	BinaryTree left;
	BinaryTree right;
	
	public BinaryTree(String data) {
		this.data = data;
		left = right = null;
	}
	
	public void setChildren(BinaryTree left, BinaryTree right) {
		this.left = left;
		this.right = right;
	}
}

class Queue {
	class ListNode{
		private BinaryTree data;
		private ListNode link;
	}
	private ListNode front;
	private ListNode rear;
	private int count;
	
	public boolean isEmpty(){
		return (count == 0);
	}
	
	public BinaryTree dequeue(){
		if(count == 0){
			return null;
		}
		BinaryTree item = front.data;
		front = front.link;
		if(front == null){
			rear = null;
		}
		count--;
		return item;
	}
	public void enqueue(BinaryTree item){
		ListNode newNode = new ListNode();
		newNode.data = item;
		
		if(count == 0) {
			front = rear = newNode;
		}else{
			rear.link = newNode;
			rear = newNode;
		}
		count++;
	}
}
// Stack 클래스에 필요한 필드와 메소드를 추가하고, push, pop, isEmpty를 수정하여 완성하시오.
class Stack {
	class StackNode{
		private BinaryTree data;
		private StackNode link;
	}
	private StackNode top;
	
		public void push(BinaryTree item) {
			StackNode n = new StackNode();
			n.data = item;
			if(top == null)
				top = n;
			else{
				n.link = top;
				top = n;
			}
		}
	
		public BinaryTree pop() {
			if(top == null)
				return null;
			else{
				BinaryTree output = top.data;
				top = top.link;
				return output;
			}
		}
	
		public boolean isEmpty() {
			return (top == null);
		}
}

// 추가적으로 필요한 클래스(예를 들어 큐)가 있으면 작성하시오.




class Main {
	
	
	/***********************************
	 * 아래 4개의 메소드를 작성하시오. *
	***********************************/

	public static void inorder(BinaryTree node) {
		Stack stack = new Stack();
		stack.push(node);
		while(!(stack.isEmpty())){
			if(node != null){
				node = node.left;
				while(node != null){
					stack.push(node);
					node = node.left;
				}
			}
			node= stack.pop();
			System.out.print(node.data + " ");
			node = node.right;
			if(node != null)
				stack.push(node);
		}	
	}
	public static void preorder(BinaryTree node) {
		if(node != null){
			System.out.print(node.data + " ");
			preorder(node.left);
			preorder(node.right);
		}
	}
	
	public static void postorder(BinaryTree node) {
		if(node != null){
			postorder(node.left);
			postorder(node.right);
			System.out.print(node.data + " ");
		}
	}
	
	public static void levelorder(BinaryTree node) {
		Queue queue = new Queue();
		queue.enqueue(node);
		while(!(queue.isEmpty())) {
			node = queue.dequeue();
			if(node != null) {
				System.out.print(node.data + " ");
				queue.enqueue(node.left);
				queue.enqueue(node.right);
			}
		}
	}
	
	// 아래 main 메소드는 수정하지 마시오.
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		Stack stack = new Stack();
		
		BinaryTree root = null;
		
		// 이진 트리 생성
		while(scan.hasNext()) {
			String token = scan.next();
			if (token.equals("null")) {
				stack.push(null);
			} else if (token.equals(")")) {
				BinaryTree right = stack.pop();
				if (stack.isEmpty()) {
					root = right;
					break;
				}
				BinaryTree left = stack.pop();
				root = stack.pop();
				root.setChildren(left, right);
				stack.push(root);
			} else if (token.equals("(")) {
				continue;
			} else {
				stack.push(new BinaryTree(token));
			}
		}
		
		scan.close();
		
		inorder(root);
		System.out.println();
		preorder(root);
		System.out.println();
		postorder(root);
		System.out.println();
		levelorder(root);
	}
}