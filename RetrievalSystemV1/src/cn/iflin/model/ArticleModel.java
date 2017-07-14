package cn.iflin.model;

public class ArticleModel {
	private String articleText;
	private String articleTtile;
	private String articleTime;
	private String articleId;
	private String articleSource;
	public ArticleModel(String title,String time,String content,String articleId,String source){
		this.articleTtile=title;
		this.articleTime=time;
		this.articleText=content;
		this.articleId=articleId;
		this.articleSource=source;
	}
	public String getArticleText() {
		return articleText;
	}

	public void setArticleText(String articleText) {
		this.articleText = articleText;
	}

	public String getArticleTtile() {
		return articleTtile;
	}

	public void setArticleTtile(String articleTtile) {
		this.articleTtile = articleTtile;
	}

	public String getArticleTime() {
		return articleTime;
	}

	public void setArticleTime(String articleTime) {
		this.articleTime = articleTime;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	
	public String getArticleSource() {
		return articleSource;
	}

	public void setArticleSource(String articleSource) {
		this.articleSource = articleSource;
	}
}
