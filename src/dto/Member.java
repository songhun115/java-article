package dto;

import article.util.Util;

public class Member {
	public int id;
	public String loginId;
	public String loginPw;
	public String regDate;
	public String name;
	
	public Member(int id, String loginId, String loginPw, String regDate) {
		this.id = id;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
	}
	
	
	public Member(int id, String name, String loginId, String loginPw  , String regDate) {
		this.id = id;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.regDate = regDate;
	}
	
}
