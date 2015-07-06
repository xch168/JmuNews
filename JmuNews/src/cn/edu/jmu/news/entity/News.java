package cn.edu.jmu.news.entity;

/**
 * 新闻实体类
 * 
 * @author Administrator
 *
 */
public class News {

	private String title;			// 新闻标题
	private String content;			// 新闻内容
	private String from;			// 新闻出处
	private String link;			// 新闻链接
	private String publisher;		// 发布者
	private String publishTime;		// 发布时间
	private String time;			// 校招时间
	private String place;			// 招聘会地点
	private int browseTimes;		// 浏览时间

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public int getBrowseTimes() {
		return browseTimes;
	}

	public void setBrowseTimes(int browseTimes) {
		this.browseTimes = browseTimes;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
	
	
}
