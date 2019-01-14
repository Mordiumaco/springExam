package exam.post.model;

import org.springframework.format.annotation.DateTimeFormat;

public class PostVO {
	
	private Integer postCode;
	private String userId;
	private String postName;
	private String postCon;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String postDate;
	private String postInquiry;
	private String boardCode;
	private String postRefer;
	private Integer postAvailable;
	private String title;
	private Integer rnum;
	
	public Integer getRnum() {
		return rnum;
	}
	public void setRnum(Integer rnum) {
		this.rnum = rnum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getPostCode() {
		return postCode;
	}
	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getPostCon() {
		return postCon;
	}
	public void setPostCon(String postCon) {
		this.postCon = postCon;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getPostInquiry() {
		return postInquiry;
	}
	public void setPostInquiry(String postInquiry) {
		this.postInquiry = postInquiry;
	}
	public String getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(String boardCode) {
		this.boardCode = boardCode;
	}
	public String getPostRefer() {
		return postRefer;
	}
	public void setPostRefer(String postRefer) {
		this.postRefer = postRefer;
	}
	public Integer getPostAvailable() {
		return postAvailable;
	}
	public void setPostAvailable(Integer postAvailable) {
		this.postAvailable = postAvailable;
	}
	
}
