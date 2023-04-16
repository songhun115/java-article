package article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import article.util.Util;
import dto.Article;
import dto.Member;


public class App {

	private static List<Article> articles;
	private static List<Member> members;
	
	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}
	
	
	public void start() {
	
			System.out.println("== 프로그램 시작 == ");
			
			makeTestData();
			int memberLastId = members.size() + 1;
			
			Scanner sc = new Scanner(System.in);

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
				}  else if (command.equals("member join")) {

					int id = memberLastId;
					
					
					
					String loginId = null;
					String loginPw = null;
					String name = null;
					String loginConfrimPw = null;
					
					while(true) {
					System.out.printf("아이디 : ");
					loginId = sc.nextLine();
					
					if(loginId.length() < 6) {
						System.out.println("아이디는 6자 이상 입력해주세요.");
						continue;
					}
					if(isJoindId(loginId) == false) {
						System.out.printf("%s은(는) 이미 사용중인 아이디입니다.%n",loginId);
						continue;
					}
					break;					
					}
					while(true) {
						System.out.printf("비밀번호 : ");
						loginPw = sc.nextLine();
						if(loginPw.length() < 8) {
							System.out.println("비밀번호는 8자 이상 입력해주세요.");
							continue;
						}
						System.out.printf("비밀번호 확인 : ");
						loginConfrimPw = sc.nextLine();
						
						
						
					if(loginPw.equals(loginConfrimPw)) {
						break;
					}
					if(loginPw.equals(loginConfrimPw) == false){
						System.out.println("비밀번호가 일치하지 않습니다 다시 입력해주세요.");
						continue;
					}
					}
					String regDate = Util.getRegDate();
					System.out.printf("이름 : ");
					name = sc.nextLine();

					
					Member member = new Member( id,  loginId,  loginPw,  regDate,  name);
					members.add(member);
					
					
				System.out.printf("%d번 회원 생생되었습니다%n",memberLastId);
				memberLastId++; 
				
				}else if(command.equals("member list")) {
					if(members.size() == 0) {
						System.out.println("회원정보가 없습니다.");
						continue;
					}	
					System.out.println("번호  |  이름  |  아이디  | 비밀번호");
					for(int i = 0; i < members.size(); i++) {
						Member member = members.get(i);
						
						System.out.printf("%d  | %s  | %s  |  %s%n",member.id ,member.name, member.loginId, member.loginPw);	
					}
					
				}else if(command.startsWith("member delete") ){
					
					String[] commandBits = command.split(" ");
					int id = Integer.parseInt(commandBits[2]);
					
					
					
					int foundIndex = getMemberIndex(id);
					if(foundIndex == -1) {
						System.out.printf("%d번 회원은 존재하지 않습니다.%n",id);
					}
					members.remove(foundIndex);
					System.out.printf("%d번 회원이 삭제되었습니다.%n",id);
					//회원 삭제 
					
				} else if(command.startsWith("member modify")) {
					String[] commandBits = command.split(" ");
					int id = Integer.parseInt(commandBits[2]);
					
					Member foundMember = getMemberById(id);
					if(foundMember == null) {
						System.out.printf("%d번 회원은 존재하지 않습니다.%n", id);
					}
					
					while(true) {
						System.out.printf("변경할 비밀번호 : ");
						foundMember.loginPw = sc.nextLine();
						
						System.out.printf("변경할 비밀번호 확인 : ");
						String loginConfrimPw = sc.nextLine();
						
						if(foundMember.loginPw.equals(loginConfrimPw)) {
							System.out.println("회원정보가 수정되었습니다.");
						} else if(foundMember.loginPw.equals(loginConfrimPw) == false) {
							System.out.println("비밀번호가 일치하지 않습니다 다시 입력해주세요.");
							continue;
						}
						break;
					}
					
					
				} else if (command.equals("article write")) {

					int id = articles.size() + 1;
					
					System.out.printf("제목 : ");
					String title = sc.nextLine();

					System.out.printf("내용 : ");
					String body = sc.nextLine();
					String regDate = Util.getRegDate();
				
					
					Article article = new Article(id, title, body, regDate);
					articles.add(article);

					System.out.printf("%d번째 글이 생성되었습니다.%n", articles.size());
				} else if (command.startsWith("article list")) {
				
					
					String searchKeyword = command.substring("article list".length()).trim();
					
					System.out.println("검색어 : " + searchKeyword);
					
					List<Article> forListArticle = articles;
					
					if ( searchKeyword.length() > 0 ) {
						forListArticle = new ArrayList<>();
						
						for ( Article article : articles ) {
							if ( article.title.contains(searchKeyword) ) {
								forListArticle.add(article);
							}
						}
						
						if (articles.size() == 0) {
							System.out.println("검색결과가 존재하지 않습니다.");
							continue;
						}
					}
					System.out.println("  번호| 제목| 조회수");
					for (int i = 0; i < forListArticle.size(); i++) {
						Article article = forListArticle.get(i);
						System.out.printf("%4d %3s %4d%n", article.id, article.title, article.hit);
					}
					
					
				} else if (command.startsWith("article detail")) {
					String[] commandBits = command.split(" ");

					int id = Integer.parseInt(commandBits[2]);

					Article foundArticle = getArticleById(id);;

					
					
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
					
					
					int foundIndex = getArticleIndexById(id);
									
					if(foundIndex == -1) {
						System.out.printf("%d번 게시물은 존재하지 않습니다.%n",id);
						continue;
					}
					articles.remove(foundIndex);
					System.out.printf("%d번 게시물이 삭제되었습니다.%n",id);
					
					
				} else if(command.startsWith("article modify")) {
					String[] commandBits = command.split(" ");
					int id = Integer.parseInt(commandBits[2]);
					
					Article foundArticle = getArticleById(id);
					
					
					if( foundArticle == null) {
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
					System.out.println("존재하지 않는 명령어입니다");
				}
			}

		}

		private Member getMemberById(int id) {
		for(int i = 0; i < members.size() ; i++) {
			Member member = members.get(i);
			if(member.id == id) {
				return member;
			}
		}
		return null;
		
		
	}

		private int getMemberIndex(int id) {
		for(int i = 0; i < members.size(); i++) {
			Member member = members.get(i);
			if(member.id == id) {
				return i;
			}
		}
		return -1;
	}

		private boolean isJoindId(String loginId) {
			int index = getMemberIndexByLoginId(loginId);
			if(index == -1) {
				return true;
			}
		return false;
	}

		private int getMemberIndexByLoginId(String loginId) {
			int i = 0;
			for(Member member : members) {
				if(member.loginId.equals(loginId)) {
					return i;
				}
				i++;
			}
			return -1;
		}

		private static int getArticleIndexById(int id) {
			int i = 0;
			for(Article article : articles ) {
				if(article.id == id) {
					return i;
				}
				i++;
			}
			return -1;
		}

		private static Article getArticleById(int id) {
			for(int i = 0; i < articles.size(); i++) {
				Article article = articles.get(i);

				if (article.id == id) {
					
					return articles.get(i);
				}
			}
			return null;
			
		}
		// 테스트 계정을 3개 생성해주세요 
		
		private static void makeTestData() {
			System.out.println("테스트를 위한 데이터를 생성합니다");
			
			articles.add(new Article(1, "제목1", "내용1",Util.getRegDate()));
			articles.add(new Article(2, "제목1", "내용1",Util.getRegDate()));
			articles.add(new Article(3, "제목1", "내용1",Util.getRegDate()));
			
			
			members.add(new Member(1, "홍길동", "hong1111","12121212",Util.getRegDate()));
			members.add(new Member(2, "홍길순", "hong2222","12121212",Util.getRegDate()));
			members.add(new Member(3, "홍길영", "hong3333","12121212",Util.getRegDate()));
			
		}
	
	
}
