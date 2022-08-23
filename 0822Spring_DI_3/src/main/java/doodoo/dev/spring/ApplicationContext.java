package doodoo.dev.spring;
import java.util.*;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

import java.io.*;
public class ApplicationContext {
	private Map map = new HashMap();
	String path="C:\\springDev\\springStudy\\0822Spring_DI_3\\src\\main\\java\\doodoo\\dev\\spring\\";
	public ApplicationContext(String filename) {
		try {
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLParser xml = new XMLParser();
			sp.parse(new File(path+filename),xml);
			map = xml.map;
		}catch(Exception ex) {}
	}
	
	public Object getBean(String id) {
		return map.get(id);
	}
}
