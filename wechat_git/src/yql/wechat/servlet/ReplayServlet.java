package yql.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import yql.wechat.bean.TextMessage;
import yql.wechat.util.CheckedUtil;
import yql.wechat.util.MsgUtil;
import yql.wechat.util.WechatUtil;


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
			TextMessage tm = new TextMessage();
			tm.setToUserName(fromUserName);
			tm.setFromUserName(toUserName);
			tm.setMsgType("text");
			tm.setCreateTime(new Date().getTime());
			tm.setContent("�����͵���Ϣ�ǣ�" + content);
			msg = MsgUtil.textMsgToXml(tm);
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
