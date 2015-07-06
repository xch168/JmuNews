package cn.edu.jmu.news.parser.news;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.edu.jmu.news.entity.News;
import cn.edu.jmu.news.parser.BaseParser;

/**
 * 微新闻解析器
 * 
 * @author Administrator
 *
 */
public class WeiNewsParser extends BaseParser {
	
	// http://www.jmu.edu.cn/s/1/t/99/p/22/i/628/list.htm 第一页
	// http://www.jmu.edu.cn/s/1/t/99/p/22/i/627/list.htm 第二页
	private final String BASE_URL = "http://www.jmu.edu.cn/s/1/t/99/p/22/i/";
	
	@Override
	protected void parse(int page) throws IOException {
		Document doc = Jsoup.connect(BASE_URL + (628 - page + 1) + "/list.htm").get();

		// /s/1/t/99/c5/43/info116035.htm
		// /s/1/t/99/c5/42/info116034.htm
		// /s/1/t/99/c4/40/info115776.htm
		// /s/1/t/99/c3/f1/info115697.htm
		// /s/1/t/99/82/61/info98913.htm
		Elements elements = doc.getElementsByClass("columnStyle").tagName("td");

		for(int i = 0; i < elements.size(); i++) {
			News news = new News();
			news.setFrom("学院新闻");
			news.setTitle(elements.get(i).getElementsByAttributeValue("target", "_blank").text());
			news.setPublishTime(elements.get(i).getElementsByAttributeValue("width", "14%").text());
			news.setLink("http://www.jmu.edu.cn/" + elements.get(i).getElementsByAttribute("href").attr("href"));
			mNewsList.add(news);
		}
	}

}
