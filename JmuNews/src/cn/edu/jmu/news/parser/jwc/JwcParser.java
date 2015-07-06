package cn.edu.jmu.news.parser.jwc;

import java.io.IOException;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.edu.jmu.news.entity.News;
import cn.edu.jmu.news.parser.BaseParser;

/**
 * 教务处新闻解析器
 * 
 * @author Administrator
 *
 */
public class JwcParser extends BaseParser {

	private final String BASE_URL = "http://jwc.jmu.edu.cn/class_show.asp?fid=1&sid=0&od=&ob=&dpage=";
	
	@Override
	protected void parse(int page) throws IOException {
		Document doc = Jsoup.connect(BASE_URL + page).get();
		Elements froms = doc.getElementsByAttributeValueMatching("href", Pattern.compile("class_show\\.asp\\?fid=\\d&sid=\\d$"));
		Elements links = doc.getElementsByAttributeValueMatching("href", Pattern.compile("doc_show.asp\\?bh=\\d"));
		Elements times = doc.getElementsByAttributeValue("color", "#999999");
		for(int i = 0; i < froms.size(); i++) {
			News news = new News();
			news.setFrom(froms.get(i).text());
			news.setTitle(links.get(i).attr("title"));
			String time = times.get(i).text();
			news.setPublishTime(time.substring(1, time.length() - 1));
			news.setLink("http://jwc.jmu.edu.cn/" + links.get(i).attr("href"));
			mNewsList.add(news);
		}
	}
	
}
