package cn.edu.jmu.news.entity;

/**
 * ����ʵ����
 * 
 * @author Administrator
 *
 */
public class News {

	private String title;			// ���ű���
	private String content;			// ��������
	private String from;			// ���ų���
	private String link;			// ��������
	private String publisher;		// ������
	private String publishTime;		// ����ʱ��
	private String time;			// У��ʱ��
	private String place;			// ��Ƹ��ص�
	private int browseTimes;		// ���ʱ��

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
