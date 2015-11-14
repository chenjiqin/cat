package yql.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import yql.wechat.bean.PTM_item;
import yql.wechat.bean.PicTextMsg;
import yql.wechat.bean.TextMessage;

public class MsgUtil {

	/**
	 * xml×ª»»map
	 * @param req
	 * @return
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest req){
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = null;
		InputStream is = null;
		try{
			reader = new SAXReader();
			is = req.getInputStream();
			Document doc = reader.read(is);
			Element root = doc.getRootElement();
			List<Element> list = root.elements();
			for(Element e : list){
				map.put(e.getName(), e.getText());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
	
	public static String textMsgToXml(TextMessage tm) {
		XStream xs = new XStream();
		xs.alias("xml", tm.getClass());
		return xs.toXML(tm);
	}
	
	public static String PicTextMsgToXml(PicTextMsg ptm, PTM_item ptm_item) {
		XStream xs = new XStream();
		xs.alias("xml", ptm.getClass());
		xs.alias("item", ptm_item.getClass());
		return xs.toXML(ptm);
	}
}
