package cn.edu.jmu.news.parser.employment;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.jmu.news.entity.News;
import cn.edu.jmu.news.parser.BaseParser;

/**
 * �˲Ž����������
 * 
 * @author Administrator
 *
 */
public class TalentExchangeParser extends BaseParser {

	@Override
	protected void parse(int page) throws IOException {
		Document doc = Jsoup.parse(mHtmlData);
		Element table = doc.getElementsByAttributeValue("style", "BORDER-COLLAPSE: collapse").get(0);
		Elements elements = table.getElementsByTag("tr");
		elements.remove(0);	// ɾ����һ��û�õ����ݣ���ͷ��
		
		for (int i = 0; i < elements.size(); i++) {
			Element element = elements.get(i);
			Elements tds = element.getElementsByTag("td");
			Element link = tds.get(0).getElementsByTag("a").get(0);
			
			News news = new News();
			String title = link.text();
			news.setTitle(title.substring(2, title.length()));
			news.setPublishTime(tds.get(1).text());
			news.setFrom("��ҵ��");
			String linkStr = link.attr("onClick");
			linkStr = linkStr.substring(linkStr.indexOf("'") + 1, linkStr.lastIndexOf("'"));
			news.setLink("http://jyzd.jmu.edu.cn" + linkStr);
			mNewsList.add(news);
		}
	}

}
