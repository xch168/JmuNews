package cn.edu.jmu.news.parser.news;

import java.io.IOException;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.edu.jmu.news.entity.News;
import cn.edu.jmu.news.parser.BaseParser;

/**
 * 校事新闻解析器
 * 
 * @author Administrator
 *
 */
public class SchoolEventParser extends BaseParser {

	// http://www.jmu.edu.cn/s/1/t/99/p/23/i/1/list.htm 第一页
	// http://www.jmu.edu.cn/s/1/t/99/p/23/i/2/list.htm 第二页
	private final String BASE_URL = "http://www.jmu.edu.cn/s/1/t/99/p/23/i/";
	
	@Override
	protected void parse(int page) throws IOException {
		Document doc = Jsoup.connect(BASE_URL + page + "/list.htm").get();

		// /s/1/t/99/a/115788/info.jspy
		// /s/1/t/99/a/115236/info.jspy
		Elements links = doc.getElementsByAttributeValueMatching("href", 
				Pattern.compile("/s/1/t/99/[a,b]"));
		
		Elements times = doc.getElementsByClass("postTime");
		for(int i = 0; i < links.size(); i++) {
			News news = new News();
			news.setFrom("校事瞭望台");
			news.setTitle(links.get(i).text());
			news.setPublishTime(times.get(i).text());
			news.setLink("http://www.jmu.edu.cn/" + links.get(i).attr("href"));
			mNewsList.add(news);
		}
	}


}
