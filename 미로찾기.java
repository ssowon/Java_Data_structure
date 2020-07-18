import java.io.*;
import java.util.Scanner;

class Stack {
	private String stack[];
	private int top;
	
	public Stack(int n) {
		stack = new String[n];
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
	public void printstack(){
		for (int i = 0; i < stack.length; i++) {
			System.out.print(stack[i] + " ");
		}
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

class Maze {
	int sizeX, sizeY;
	int startX, startY;
	int endX, endY;
	int next_i, next_j;
	int[][] maze;
	int[][] mark;
	int[][] move = {{-1,0},{0,1},{1,0},{0,-1}};

	public void readMazeData() {
		Scanner scan = new Scanner(System.in);
		
		sizeY = scan.nextInt();
		sizeX = scan.nextInt();
		
		startY = scan.nextInt();
		startX = scan.nextInt();
		
		endY = scan.nextInt();
		endX = scan.nextInt();
		
		maze = new int[sizeY][sizeX];
		mark = new int[sizeY][sizeX];
		
		for(int i = 0; i < sizeY; i++){
			for (int j = 0; j < sizeX; j++){
				maze[i][j] = scan.nextInt();
				mark[i][j] = 0;
			}
		}
		scan.close();
	}
	
	public void printmark(Stack getstack){
		Stack stack = getstack;
		int num = 1;

		while(!stack.isEmpty()){
			stack.pop();
			String j = stack.pop();
			String i = stack.pop();
			maze[Integer.parseInt(i)][Integer.parseInt(j)] = 4;
		}
		maze[startX][startY] = 2;
		maze[endX][endY] = 3;

		for (int a = 0; a < maze.length; a++) {
			for (int b = 0; b < maze[0].length; b++) {
				if(maze[a][b] == 2)
					System.out.print("S ");
				else if(maze[a][b] == 3)
					System.out.print("F ");
				else if(maze[a][b] == 4)
					System.out.print("* ");
				else
					System.out.print(maze[a][b]+ " ");
			}
			System.out.println();//줄을 바꾼다
		}

	}
	
	public void findExit(){
		Stack stack = new Stack((sizeX)*(sizeY));
		mark[1][1] = 1;
		stack.push("1");
		stack.push("1");
		stack.push("1");
		while(!stack.isEmpty()){
			int dir = Integer.parseInt(stack.pop());
			int j = Integer.parseInt(stack.pop());
			int i = Integer.parseInt(stack.pop());
			
			while(dir<=3){
				next_i = i + move[dir][0];
				next_j = j + move[dir][1];
				if(next_i == endX && next_j == endY){
					stack.push(Integer.toString(i));
					stack.push(Integer.toString(j));
					stack.push(Integer.toString(dir));
					printmark(stack);
					return;
				}
				else if(maze[next_i][next_j]==0 && mark[next_i][next_j]==0){
					mark[next_i][next_j] = 1;
					stack.push(Integer.toString(i));
					stack.push(Integer.toString(j));
					stack.push(Integer.toString(dir));
					i = next_i;
					j = next_j;
					dir = 0;
				}else{
					dir = dir + 1;
				}
			}
		}
	}
}

class Main {
		
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);

		Maze m = new Maze();
		m.readMazeData();
		m.findExit();
	}
}