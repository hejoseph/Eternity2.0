package fr.esiea.glpoo.model.domain;

public class Score {

	private int score_id;
	
	private String nickname;
	
	private int time;
	
	private String level;

	public Score(int score_id, String nickname, String level , int time) {
		super();
		this.score_id = score_id;
		this.nickname = nickname;
		this.time = time;
		this.level = level;
	}

	public int getScore_id() {
		return score_id;
	}

	public void setScore_id(int score_id) {
		this.score_id = score_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
	
}
