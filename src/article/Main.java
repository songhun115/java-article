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
				System.out.println("  번호| 제목| 조회수");
				for (int i = 0; i < articles.size(); i++) {

					System.out.printf("%4d %4s %4d%n", articles.get(i).id, articles.get(i).title, articles.get(i).hit);

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
				foundArticle.increasHit();
				System.out.printf("번호 : %d%n", foundArticle.id);
				System.out.printf("제목 : %s%n", foundArticle.title);
				System.out.printf("내용 : %s%n", foundArticle.body);
				System.out.printf("조회수 : %d%n", foundArticle.hit);
				System.out.printf("날짜 : %s%n", foundArticle.regDate);

			} else if(command.startsWith("article delete")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				
				
				int foundIndex = -1;
				
				for(int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (article.id == id) {
						foundIndex = i;
						break;
					}
				}
				
				
				if(foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.%n",id);
					continue;
				}
				articles.remove(foundIndex);
				System.out.printf("%d번 게시물이 삭제되었습니다.%n",id);
				
				
			} else if(command.startsWith("article modify")) {
				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);
				Article foundArticle = null;
				int foundIndex = -1;
				
				for(int i = 0; i < articles.size(); i++) {
					 foundArticle = articles.get(i);
					if(foundArticle.id == id) {
						foundIndex = i;
						break;
					}
				}
				
				if(foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.%n",id);
					continue;
				}
				
				
				
				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				foundArticle.title = title;
				
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine();
				foundArticle.body = body;
				
				System.out.printf("%d번 게시물이 수정되었습니다",id);
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
	int hit;
	public Article(int id, String title, String body, String regDate) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.hit = 0;
	}
	public void increasHit() {
		hit++;
	}
}

