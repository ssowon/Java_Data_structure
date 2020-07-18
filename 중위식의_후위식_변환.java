import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

class Stack {
	private String stack[];
	private int top;
	
	public Stack() {
		stack = new String[50];
		top = -1;
	}
	public void printstack(){
		String a = Arrays.toString(this.stack);
		System.out.println("	"+ Integer.toString(this.top) +"	"+ a.substring(1,13));
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
			return "0";
		return stack[top];
	}
}

class Main {
	public static int PIS(String t){
		if("^".equals(t)) 
			return 3;
		else if("*".equals(t) || "/".equals(t)) 
			return 2;
		else if("+".equals(t) || "-".equals(t)) 
			return 1;
		else if("(".equals(t))
			return 0;
		return 0;
	}
	
	public static int PIE(String t){
		if("^".equals(t)) 
			return 3;
		else if("*".equals(t) || "/".equals(t)) 
			return 2;
		else if("+".equals(t) || "-".equals(t)) 
			return 1;
		else if("(".equals(t))
			return 4;
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		Stack pstack = new Stack();
		
		String str = scan.next();
		while (!str.equals("$")) {
			if(str.equals("1")||str.equals("2")||str.equals("3")||str.equals("4")||
				 str.equals("5")||str.equals("6")||str.equals("7")||str.equals("8")||str.equals("9")){
				System.out.print(str +" ");
			}else if(str.equals(")")){
				while(!(pstack.peek().equals("("))){
					System.out.print(pstack.pop()+" ");
				}
				pstack.pop();
			}else{
				while(PIS(pstack.peek())>=PIE(str)){
					System.out.print(pstack.pop()+" ");
				}
				pstack.push(str);
			}
			str = scan.next();
		}
		while(!pstack.isEmpty()){
			System.out.print(pstack.pop()+" ");
		}
	}
}