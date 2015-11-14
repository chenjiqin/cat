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
			//普通消息回复
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
				ptm_item.setTitle("今日推荐");
				ptm_item.setDescription("通过人工甄选出各商城的高性价比商品和促销活动信息,让网友最大限度的节省开支,天天发现什么值得买!");
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
				ptm_item.setTitle("优惠活动");
				ptm_item.setDescription("各种优惠活动停不下来...");
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
				ptm_item.setTitle("白菜的营养价值");
				ptm_item.setDescription("白菜是我们生活中常见的蔬菜，也是最平常的蔬菜，特别是在北方。虽总是与白菜打交道，但是你对白菜的了解有多深呢?今天小编就介绍下白菜的营养价值，快来来看看吧!");
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
				tm.setContent("您发送的消息是：" + content);
				msg = MsgUtil.textMsgToXml(tm);
			}
		}else if("event".equals(msgType)){
    		if("subscribe".equals(Event)){
    			//关注事件
    			System.out.println("subscribe");
    			TextMessage tm = new TextMessage();
    			tm.setToUserName(fromUserName);
    			tm.setFromUserName(toUserName);
    			tm.setMsgType("text");
    			tm.setCreateTime(new Date().getTime());
    			tm.setContent("感谢您的关注，回复以下内容将回复更多信息：\n1:获取今日推荐\n2:获取优惠活动\n3:获取最新分享");
    			msg = MsgUtil.textMsgToXml(tm);
    		}else if("unsubscribe".equals(Event)){
    			//取消关注事件
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
