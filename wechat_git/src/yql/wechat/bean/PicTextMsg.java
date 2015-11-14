package yql.wechat.bean;

import java.util.List;

public class PicTextMsg {

	private String ToUserName;
	private String FromUserName;
	private long CreateTime;
	private String MsgType;
	private String ArticleCount;
	private List<PTM_item> Articles;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

	public List<PTM_item> getArticles() {
		return Articles;
	}

	public void setArticles(List<PTM_item> articles) {
		Articles = articles;
	}
}
