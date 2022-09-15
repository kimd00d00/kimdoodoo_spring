package doo.doo.manager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import java.util.*;
import doo.doo.vo.*;

@Component
public class MusicManager {
	public List<MusicVO> musicTop5(){
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			Document doc = Jsoup.connect("https://www.genie.co.kr/chart/top200").get();
			Elements title=doc.select("table.list-wrap td.info a.title");
			Elements singer=doc.select("table.list-wrap td.info a.artist");
			for(int i=0; i<5; i++) {
				MusicVO vo = new MusicVO();
				vo.setTitle(title.get(i).text());
				vo.setSinger(singer.get(i).text());
				list.add(vo);
			}
		}catch(Exception ex) {}
		return list;
	}
}
