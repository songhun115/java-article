package article;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 == ");
		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;
		List<Article> articles = new ArrayList<>();

		while (true) {

			System.out.printf("명령어 : ");
			String command = sc.nextLine();
			command = command.trim();
			int num = 0;

			if (command.length() == 0) {
				continue;
			}
			if (command.equals("exit")) {
				System.out.println("== 프로그램 종료 ==");
				break;
			} else if (command.equals("article write")) {

				int id = lastArticleId + 1;
				lastArticleId = id;
				System.out.printf("제목 : ");
				String title = sc.nextLine();

				System.out.printf("내용 : ");
				String body = sc.nextLine();
				String regDate = Util.getRegDate();
			
				
				Article article = new Article(id, title, body, regDate);
				articles.add(article);

				System.out.printf("%d번째 글이 생성되었습니다.%n", lastArticleId);
			} else if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 없습니다.");
				}
				System.out.println("번호 | 제목 ");
				for (int i = 0; i < articles.size(); i++) {

					System.out.printf("%2d %4s%n", articles.get(i).id, articles.get(i).title);

				}
			} else if (command.startsWith("article detail")) {
				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (id == article.id) {
						foundArticle = article;
						break;
					}
				}
				if (foundArticle == null) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}
				System.out.printf("번호 : %d%n", foundArticle.id);
				System.out.printf("제목 : %s%n", foundArticle.title);
				System.out.printf("내용 : %s%n", foundArticle.body);
				System.out.printf("날짜 : %s%n", foundArticle.regDate);

			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
		}

	}
}

class Article {
	int id;
	String title;
	String body;
	String regDate;
	public Article(int id, String title, String body, String regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;

	}
}

