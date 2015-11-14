package yql.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yql.wechat.bean.PTM_item;
import yql.wechat.bean.PicTextMsg;
import yql.wechat.bean.TextMessage;
import yql.wechat.util.CheckedUtil;
import yql.wechat.util.MsgUtil;


public class ReplayServlet extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		PrintWriter pw = resp.getWriter();
		if(CheckedUtil.checkSignature(signature, timestamp, nonce)) {
			pw.print(echostr);
		}
		close(pw);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		Map<String, String> map = MsgUtil.xmlToMap(req);
		String fromUserName = map.get("FromUserName");
		String toUserName = map.get("ToUserName");
		String msgType = map.get("MsgType");
		String content = map.get("Content");
		String Event = map.get("Event");
		String msg = null;
		System.out.println("event:"+Event);
		System.out.println("msgType:"+msgType);
		if("text".equals(msgType)) {
			//��ͨ��Ϣ�ظ�
			if("1".equals(content)){
				System.out.println("three");
				PicTextMsg ptm = new PicTextMsg();
				ptm.setToUserName(fromUserName);
				ptm.setFromUserName(toUserName);
				ptm.setMsgType("news");
				ptm.setCreateTime(new Date().getTime());
				ptm.setArticleCount("1");
				List<PTM_item> list = new ArrayList<PTM_item>();
				PTM_item ptm_item = new PTM_item();
				ptm_item.setTitle("�����Ƽ�");
				ptm_item.setDescription("ͨ���˹���ѡ�����̳ǵĸ��Լ۱���Ʒ�ʹ������Ϣ,����������޶ȵĽ�ʡ��֧,���췢��ʲôֵ����!");
				ptm_item.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/WYaic9EYGtPxMURlTIDlPHicqYbq4df7nD5Un8yKjyqfJOtWwJywp0X4kibmNqNc5EC6HLibPibn98p1RaPQ351xMRA/0");
				ptm_item.setUrl("http://115.159.99.120/wechat_git/pages/wechat/food/p_share.html");
//				ptm_item.setUrl("http://blog.csdn.net/lyq8479");
				list.add(ptm_item);
				ptm.setArticles(list);;
				msg = MsgUtil.PicTextMsgToXml(ptm, ptm_item);
			}else if("2".equals(content)){
				System.out.println("three");
				PicTextMsg ptm = new PicTextMsg();
				ptm.setToUserName(fromUserName);
				ptm.setFromUserName(toUserName);
				ptm.setMsgType("news");
				ptm.setCreateTime(new Date().getTime());
				ptm.setArticleCount("1");
				List<PTM_item> list = new ArrayList<PTM_item>();
				PTM_item ptm_item = new PTM_item();
				ptm_item.setTitle("�Żݻ");
				ptm_item.setDescription("�����Żݻͣ������...");
				ptm_item.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/WYaic9EYGtPxMURlTIDlPHicqYbq4df7nDVfvE86ubhl2wYKHLOE0ZtGiaKHlIsQcr566KcyYDGbrxxjkwMc7BzZQ/0");
				ptm_item.setUrl("http://115.159.99.120/wechat_git/pages/wechat/food/p_share.html");
//				ptm_item.setUrl("http://blog.csdn.net/lyq8479");
				list.add(ptm_item);
				ptm.setArticles(list);;
				msg = MsgUtil.PicTextMsgToXml(ptm, ptm_item);
			}else if("3".equals(content)){
				System.out.println("three");
				PicTextMsg ptm = new PicTextMsg();
				ptm.setToUserName(fromUserName);
				ptm.setFromUserName(toUserName);
				ptm.setMsgType("news");
				ptm.setCreateTime(new Date().getTime());
				ptm.setArticleCount("1");
				List<PTM_item> list = new ArrayList<PTM_item>();
				PTM_item ptm_item = new PTM_item();
				ptm_item.setTitle("�ײ˵�Ӫ����ֵ");
				ptm_item.setDescription("�ײ������������г������߲ˣ�Ҳ����ƽ�����߲ˣ��ر����ڱ�������������ײ˴򽻵���������԰ײ˵��˽��ж�����?����С��ͽ����°ײ˵�Ӫ����ֵ��������������!");
				ptm_item.setPicUrl("https://mmbiz.qlogo.cn/mmbiz/WYaic9EYGtPxMURlTIDlPHicqYbq4df7nDH8WIWM78EObzXgq6DIVDibmFNFiaR3xU8RhXnniceYDzb5wnJJ2ic2Y5Xw/0");
				ptm_item.setUrl("http://115.159.99.120/wechat_git/pages/wechat/food/p_share.html");
//				ptm_item.setUrl("http://blog.csdn.net/lyq8479");
				list.add(ptm_item);
				ptm.setArticles(list);;
				msg = MsgUtil.PicTextMsgToXml(ptm, ptm_item);
			}else {
				TextMessage tm = new TextMessage();
				tm.setToUserName(fromUserName);
				tm.setFromUserName(toUserName);
				tm.setMsgType("text");
				tm.setCreateTime(new Date().getTime());
				tm.setContent("�����͵���Ϣ�ǣ�" + content);
				msg = MsgUtil.textMsgToXml(tm);
			}
		}else if("event".equals(msgType)){
    		if("subscribe".equals(Event)){
    			//��ע�¼�
    			System.out.println("subscribe");
    			TextMessage tm = new TextMessage();
    			tm.setToUserName(fromUserName);
    			tm.setFromUserName(toUserName);
    			tm.setMsgType("text");
    			tm.setCreateTime(new Date().getTime());
    			tm.setContent("��л���Ĺ�ע���ظ��������ݽ��ظ�������Ϣ��\n1:��ȡ�����Ƽ�\n2:��ȡ�Żݻ\n3:��ȡ���·���");
    			msg = MsgUtil.textMsgToXml(tm);
    		}else if("unsubscribe".equals(Event)){
    			//ȡ����ע�¼�
    		}
    	}
		PrintWriter pw = resp.getWriter();
		pw.print(msg);
		close(pw);
	}
	
	private void close(PrintWriter pw){
		if(pw != null) {
			pw.close();
		}
	}
}
