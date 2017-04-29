package cn.com.musicplayerserver.entity;

public class Comment {
	private Integer commentId;
	private Integer userId;
	private Integer musicId;
	private String commnetContent;
	private double score;
	/**
	 * @return the commentId
	 */
	public Integer getCommentId() {
		return commentId;
	}
	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the musicId
	 */
	public Integer getMusicId() {
		return musicId;
	}
	/**
	 * @param musicId the musicId to set
	 */
	public void setMusicId(Integer musicId) {
		this.musicId = musicId;
	}
	/**
	 * @return the commnetContent
	 */
	public String getCommnetContent() {
		return commnetContent;
	}
	/**
	 * @param commnetContent the commnetContent to set
	 */
	public void setCommnetContent(String commnetContent) {
		this.commnetContent = commnetContent;
	}
	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}
}
