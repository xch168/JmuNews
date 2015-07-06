package cn.edu.jmu.news.parser.news;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.edu.jmu.news.entity.News;
import cn.edu.jmu.news.parser.BaseParser;

/**
 * ѧ�����Ž�����
 * 
 * @author Administrator
 *
 */
public class AcademicParser extends BaseParser {

	// http://www.jmu.edu.cn/s/1/t/99/p/12/i/1/list.htm ��һҳ
	// http://www.jmu.edu.cn/s/1/t/99/p/12/i/2/list.htm �ڶ�ҳ
	private final String BASE_URL = "http://www.jmu.edu.cn/s/1/t/99/p/12/i/";
	
	@Override
	protected void parse(int page) throws IOException {
		Document doc = Jsoup.connect(BASE_URL + page + "/list.htm").get();

		Elements elements = doc.getElementsByClass("columnStyle").tagName("td");
		
		for(int i = 0; i < elements.size(); i++) {
			News news = new News();
			news.setFrom("ѧ������");
			news.setTitle(elements.get(i).getElementsByAttributeValue("target", "_blank").text());
			news.setPublishTime(elements.get(i).getElementsByAttributeValue("width", "14%").text());
			news.setLink("http://www.jmu.edu.cn/" + elements.get(i).getElementsByAttribute("href").attr("href"));
			mNewsList.add(news);
		}
	}
	
}
