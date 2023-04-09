package article;



import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		Scanner sc = new Scanner(System.in);
		String command;

		System.out.printf("명령어) ");
		command = sc.next();
		System.out.printf("입력된 명령어 : %s\n", command);

		System.out.printf("명령어) ");
		int num = sc.nextInt();
		System.out.printf("입력된 숫자 : %d\n", num);
		sc.close();

		System.out.println("== 프로그램 끝 ==");
	}
}