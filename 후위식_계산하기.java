import java.io.*;
import java.util.Scanner;

class Stack {
	private String stack[];
	private int top;
	
	public Stack() {
		stack = new String[50];
		top = -1;
	}
	
	public boolean isEmpty() {
		return top < 0;
	}
	
	public void push(String item) {
		if(top>=stack.length)
			throw new IndexOutOfBoundsException();
		top++;
		stack[top] = item;
	}
	
	public String pop() {
		if (top >= stack.length)
			throw new IndexOutOfBoundsException();
		String item = stack[top];
		top--;
		return item;
	}
	
	public void delete(){
		if(top<0)
			throw new IndexOutOfBoundsException();
		top--;
	}
	public String peek(){
		if(top<0)
			throw new IndexOutOfBoundsException();
		return stack[top];
	}
}

class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		Stack poststack = new Stack();
		String str = scan.next();
		while (!str.equals("$")) {
			if (str.equals("+")) {
				double operand1 = Double.parseDouble(poststack.pop());
				double operand2 = Double.parseDouble(poststack.pop());
				String plusoper = Double.toString(operand1 + operand2);
				
				poststack.push(plusoper);
			} else if (str.equals("-")) {
				double operand1 = Double.parseDouble(poststack.pop());
				double operand2 = Double.parseDouble(poststack.pop());
				String minusoper = Double.toString(operand2 - operand1);
				
				poststack.push(minusoper);
				
			} else if (str.equals("*")) {
				double operand1 = Double.parseDouble(poststack.pop());
				double operand2 = Double.parseDouble(poststack.pop());
				String muloper = Double.toString(operand1 * operand2);
				
				poststack.push(muloper);
				
			} else if (str.equals("/")) {
				double operand1 = Double.parseDouble(poststack.pop());
				double operand2 = Double.parseDouble(poststack.pop());
				String divoper = Double.toString(operand2 / operand1);
				
				poststack.push(divoper);
			} else {
				double operand = Double.parseDouble(str);	
				poststack.push(str);
			}
			
			str = scan.next();
		}
		System.out.print(poststack.peek());
	}
}