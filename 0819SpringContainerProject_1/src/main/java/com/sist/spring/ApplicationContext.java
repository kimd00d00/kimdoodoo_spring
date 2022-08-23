package com.sist.spring;
import java.io.File;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
public class ApplicationContext {
	private Map map = new HashMap();
	public ApplicationContext() {
		try {
			String path = "C:\\springDev\\springStudy\\0819SpringContainerProject_1\\src\\main\\java\\com\\sist\\spring\\app.xml";
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File(path));
			Element root = doc.getDocumentElement();
			NodeList list = root.getElementsByTagName("bean");
			for(int i=0;i<list.getLength();i++) {
				Element bean = (Element)list.item(i);
				String id = bean.getAttribute("id");
				String cls = bean.getAttribute("class");
				Class clsName = Class.forName(cls);
				Object obj = clsName.getDeclaredConstructor().newInstance();
				map.put(id, obj);
			}
		}catch(Exception ex) {
			
		}
	}
	public Object getBean(String id) {
		return map.get(id);
	}
}
