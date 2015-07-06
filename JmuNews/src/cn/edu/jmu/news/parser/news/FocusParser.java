package cn.edu.jmu.news.parser.news;

import java.io.IOException;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.edu.jmu.news.entity.News;
import cn.edu.jmu.news.parser.BaseParser;

/**
 * 焦点新闻解析器
 * 
 * @author Administrator
 *
 */
public class FocusParser extends BaseParser {

	// http://www.jmu.edu.cn/s/1/t/99/p/21/i/430/list.htm 第一页
	// http://www.jmu.edu.cn/s/1/t/99/p/21/i/429/list.htm 第二页
	private final String BASE_URL = "http://www.jmu.edu.cn/s/1/t/99/p/21/i/";
	
	@Override
	protected void parse(int page) throws IOException {
		Document doc = Jsoup.connect(BASE_URL + (430 - page + 1) + "/list.htm").get();

		Elements links = doc.getElementsByAttributeValueMatching("href", 
				Pattern.compile("/s/1/t/99/[a-z]([a-z]|[0-9])/[a-z, 0-9]|[0-9, 0,9]/info\\d+\\d+\\.htm"));
		
		Elements times = doc.getElementsByClass("postTime");
		for(int i = 0; i < links.size(); i++) {
			News news = new News();
			news.setFrom("集大要闻");
			news.setTitle(links.get(i).text());
			news.setPublishTime(times.get(i).text());
			news.setLink("http://www.jmu.edu.cn/" + links.get(i).attr("href"));
			mNewsList.add(news);
		}
	}
	
}
