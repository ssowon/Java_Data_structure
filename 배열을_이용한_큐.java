import java.io.*;

class Queue {
	private String[] data;
	private static final int QUEUE_SIZE = 5;
	
	private int front = 0;
	private int rear = 0;

	public Queue() {
		data = new String[QUEUE_SIZE];
	}

	// 작성하시오.
	public void enque(String str) {
		data[rear] = str;
		rear = (rear+1)%QUEUE_SIZE;
	}

	// 작성하시오.	
	public String deque() {
		String item = data[front];
		front = (front+1)%QUEUE_SIZE;
		return item;
	}
}

class Main {
	
	// 아래 main 메소드는 수정하지 마시오.
	public static void main(String[] args) throws Exception {
		Queue queue = new Queue();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int no = Integer.parseInt(input);
		
		for(int i = 0; i < no; i++) {
			String cmd = br.readLine();
			
			if (cmd.equals("enque")) {
				String name = br.readLine();
				queue.enque(name);
			} else if (cmd.equals("deque")) {
				System.out.println(queue.deque());
			} else {
				throw new IllegalArgumentException();
			}
		}
		
		br.close();
  }
}