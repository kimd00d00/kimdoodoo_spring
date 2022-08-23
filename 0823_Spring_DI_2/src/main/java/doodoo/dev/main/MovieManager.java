package doodoo.dev.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;
import java.util.*;
import java.io.*;
import java.net.*;

/*
 * searchMainDailyBoxOffice.do ���Ϲڽ����ǽ�
 * searchMainRealTicket.do �ǽð� ������
 * searchMainDailySeatTicket.do �¼�������
 * searchMainOnlineDailyBoxOffice.do �¶��λ󿵰�
 * 
 * https://www.kobis.or.kr/kobis/business/main/main.do
 */
@Component
public class MovieManager {
	public static void main(String[] args) {
		MovieManager m = new MovieManager();
		m.movieListData(1);
	}
	public List<MovieVO> movieListData(int no){
		List<MovieVO> list = new ArrayList<MovieVO>();
		try {
			String strUrl="";
			if(no==1)
				strUrl ="https://www.kobis.or.kr/kobis/business/main/searchMainDailyBoxOffice.do";
			else if(no==2)
				strUrl ="https://www.kobis.or.kr/kobis/business/main/searchMainRealTicket.do";
			else if(no==3)
				strUrl ="https://www.kobis.or.kr/kobis/business/main/searchMainDailySeatTicket.do";
			else if(no==4)
				strUrl ="https://www.kobis.or.kr/kobis/business/main/searchMainOnlineDailyBoxOffice.do";
			
			URL url = new URL(strUrl); //������Ʈ ������ �� ����ϴ� URL ��ü
			HttpURLConnection conn = (HttpURLConnection)url.openConnection(); //������Ʈ ����
			StringBuffer sb = new StringBuffer();
			
			if(conn!=null) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
				while(true) {
					String data = in.readLine();
					if(data==null)
						break;
//					System.out.println(data);
					sb.append(data);
				}
				in.close();
			}
//			System.out.println(sb.toString());
			/*
			 * {"movieNm":"��Ʈ",
			 * "director":"������",
			 * "genre":"�׼�,���",
			 * "watchGradeNm":"15���̻������"}
			 */
			String json = sb.toString();
			JSONParser jp = new JSONParser();
			JSONArray arr = (JSONArray)jp.parse(json);
			for(int i=0;i<arr.size();i++) {
				JSONObject obj = (JSONObject)arr.get(i);
//				System.out.println("����:"+obj.get("movieNm"));
//				System.out.println("�帣:"+obj.get("genre"));
//				System.out.println("���:"+obj.get("watchGradeNm"));
//				System.out.println("����:"+obj.get("director"));
//				System.out.println("================================");
				MovieVO vo = new MovieVO();
				vo.setTitle((String)obj.get("movieNm"));
				vo.setGenre((String)obj.get("genre"));
				vo.setGrade((String)obj.get("watchGradeNm"));
				vo.setDirector((String)obj.get("director"));
				list.add(vo);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
}