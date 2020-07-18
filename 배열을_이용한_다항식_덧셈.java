import java.io.*;
import java.util.Scanner;

class Polynomial {
	private int[][] term;
	private int curNoTerm = 0;

	// @param noTerm 항의 개수
	public Polynomial(int noTerm) {
		term = new int[noTerm][2];
	}

	public Polynomial() {
		this(20);  // default로 최대 20개의 항을 저장함
	}

	/**
	 * @param coef 계수
   * @param exp 지수
	 */
	public void addTerm(int coef, int exp) {
		term[curNoTerm][0] = exp;      //
		term[curNoTerm][1] = coef;
		curNoTerm++;
	}

	// @param exp
	// 작성하시오
	public void delTerm(int exp) {
		for(int i = 0; i <curNoTerm; i++) {
			term[i][0] = term[i+1][0];
			term[i][1] = term[i+1][1];
		}
	}

	/**
	 * 출력할 때 사용
	 * @return 객체를 문자열로 반환 (예: 3x^15+2x^3+4x^2+x+5 )
	 * 작성하시오.
	 */
	public String toString() {
		String str = "";
		//첫번째 항
		if(term[0][1]>1 || term[0][1]<0)
			str=str+term[0][1];
		else if(term[0][1]==1)
			str=str;
		else return str = "0";
		
		if(term[0][0]>1)
			str=str+"x^"+term[0][0];
		else if(term[0][0]==1)
			str=str+"x";
		
		for(int i=1;i<curNoTerm;i++){
			if(term[i][1]>1 || term[i][1]<0)
				str = str+"+"+term[i][1];
			else str = str + "+";
			if(term[i][0]>1)
				str = str+"x^"+term[i][0];
			else if(term[i][0]==1)
				str = str+"x";
		}
		return str;
	}
	
	public Boolean isZeroP() {
		if(term[0][1]==0)
			return true;
		else return false;
	}
	
	public int maxExp(){
		return term[0][0];
	}
	
	public int coef(int exp){
		for(int i=0; i<curNoTerm; i++){
			if(term[i][0] == exp)
				return term[i][1];
		}
		return 0;
	}

	/**
	 * 두 개의 다항식을 더한다.
	 * @param p1 첫번째 다항식
	 * @param p2 두번째 다항식
	 * @return 두 개의 다항식을 더한 결과
	 * 작성할 것 
	 */
	public static Polynomial polyAdd(Polynomial p1, Polynomial p2) {
		Polynomial p3 = new Polynomial();
		while(!p1.isZeroP() && !p2.isZeroP()){
			if(p1.maxExp() < p2.maxExp()){
				p3.addTerm(p2.coef(p2.maxExp()), p2.maxExp());
				p2.delTerm(p2.maxExp());
			}else if(p1.maxExp() == p2.maxExp()){
				int sum = p1.coef(p1.maxExp()) + p2.coef(p2.maxExp());
				if (sum!=0)
					p3.addTerm(sum, p1.maxExp());
				p1.delTerm(p1.maxExp());
				p2.delTerm(p2.maxExp());
			}else {
				p3.addTerm(p1.coef(p1.maxExp()), p1.maxExp());
				p1.delTerm(p1.maxExp());
			}
		}
		while(!p1.isZeroP()){
			p3.addTerm(p1.coef(p1.maxExp()), p1.maxExp());
			p1.delTerm(p1.maxExp());
		}
		while(!p2.isZeroP()){
			p3.addTerm(p2.coef(p2.maxExp()), p2.maxExp());
			p2.delTerm(p2.maxExp());
		}
		return p3;
	}
}

class Main {
	
	// main 메소드는 수정하지 마시오.
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);

		// 첫번째 다항식 입력
		Polynomial p1 = new Polynomial();
		int no = scan.nextInt();
		for(int i = 0; i < no; i++) {
			int coef = scan.nextInt();
			int exp  = scan.nextInt();
			p1.addTerm(coef, exp);
		}

		Polynomial p2 = new Polynomial();
		int no1 = scan.nextInt();
		for(int i = 0; i < no1; i++) {
			int coef = scan.nextInt();
			int exp  = scan.nextInt();
			p2.addTerm(coef, exp);
		}

		// 두번째 다항식 입력 코드 작성할 것


		// 두개의 다항식 덧셈
		Polynomial p3 = Polynomial.polyAdd(p1, p2);

		System.out.print(p3);  // 이것은 System.out.print(p3.toString())과 동일
	}
}