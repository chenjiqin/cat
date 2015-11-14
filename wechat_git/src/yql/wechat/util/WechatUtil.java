package yql.wechat.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class WechatUtil {
	private static final String APPID="wx70b5a64e0dea5eeb";
	private static final String SECRET="d4624c36b6795d1d99dcf0547af5443d";
	
	
	public static JSONObject wechatDoGet(String url) throws ClientProtocolException, IOException{
//		DefaultHttpClient httpClient = new DefaultHttpClient();//该方法已经过时
		//新的创建httpClient的方法
        CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse resp = httpClient.execute(httpGet);
		HttpEntity entity = resp.getEntity();
		if(entity != null) {
			String result = EntityUtils.toString(entity, "utf-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}
	
	/**
	 * 处理post请求
	 * @param url
	 * @param outStr
	 * @return
	 * @throws Exception
	 */
	public static JSONObject wechatDoPost(String url, String outStr) throws Exception {
//		DefaultHttpClient httpClient = new DefaultHttpClient();该方法已经过时
		//新的创建httpClient的方法
        CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpPost.setEntity(new StringEntity(outStr, "utf-8"));
		HttpResponse resp = httpClient.execute(httpPost);
		String result = EntityUtils.toString(resp.getEntity(), "utf-8");
		jsonObject = JSONObject.fromObject(result);
		return jsonObject;
	}
	/**
	 * 获取access_token
	 * @return
	 */
	public static JSONObject getAccessToken() {
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+SECRET;
		JSONObject json = null;
		try {
			json = wechatDoGet(url);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * 创建菜单,手动用main方法执行下即可
	 * @throws Exception
	 */
	public static void createMenu(){
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+getAccessToken().getString("access_token");
//		String menuInfo = "{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]}";
		String menuInfo = "{\"button\":[{\"type\":\"click\",\"name\":\"掌上市场\",\"sub_button\":[{\"type\":\"view\",\"name\":\"菜商\",\"url\":\"http://115.159.99.120/wechat_git/food_business.wx\"},{\"type\":\"view\",\"name\":\"排行榜\",\"url\":\"http://v.qq.com/\"}]},{\"type\":\"click\",\"name\":\"知识库\",\"sub_button\":[{\"type\":\"view\",\"name\":\"蔬菜常识\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"菜谱视频\",\"url\":\"http://v.qq.com/\"}]},{\"name\":\"我的\",\"sub_button\":[{\"type\":\"view\",\"name\":\"收支\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"收藏\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"关注\",\"key\":\"V1001_GOOD\"},{\"type\":\"click\",\"name\":\"积分\",\"key\":\"V1001_GOOD\"},{\"type\":\"click\",\"name\":\"卡券\",\"key\":\"V1001_GOOD\"}]}]}";
		try {
			JSONObject json = WechatUtil.wechatDoPost(url, menuInfo);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增永久素材
	 */
	public static void addPermanentSource() {
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=" + getAccessToken().getString("access_token");
		String content = "{\"articles\":[{\"title\":\"白菜的营养价值\",\"thumb_media_id\":\"IN0sWjdy16_DMGQ_SDPK-A-xpb5s7uNrDuJ-HEsNP1Q\",\"author\":\"yql\",\"digest\":\"白菜是我们生活中常见的蔬菜，也是最平常的蔬菜，特别是在北方。虽总是与白菜打交道，但是你对白菜的了解有多深呢?今天小编就介绍下白菜的营养价值，快来来看看吧\",\"show_cover_pic\":1,\"content\":\"白菜是人们餐桌上必备的菜类，白菜有大白菜、小白菜等!大白菜有宽大的绿色菜叶和白色菜帮。多重菜叶紧紧包裹在一起形成圆柱体，多数会形成一个密实的头部。被包在里面的菜叶由于见不到阳光绿色较淡以至呈淡黄色。白菜含有丰富的粗纤维，不但能起到润肠、促进排毒的作用又刺激肠胃蠕动，促进大便排泄，帮助消化(消化食品)的功能。对预防肠癌有良好作用。秋冬季节空气特别干燥，寒风对人的皮肤伤害极大。白菜中含有丰富的维生素C、维生素E，多吃白菜，可以起到很好的护肤和养颜效果。美国纽约激素研究所的科学家发现，中国和日本妇女乳腺癌发病率之所以比西方妇女低得多，是由于她们常吃白菜的缘故。白菜中有一些微量元素(微量元素食品)，它们能帮助分解同乳腺癌相联系的雌激素。白菜微寒、味甘、性平，归肠、胃经。有解热除烦、通利肠胃、养胃生津、除烦解渴、利尿通便、清热解毒;可用于肺热咳嗽、便秘、丹毒、漆疮。菜除作为蔬菜(蔬菜食品)供人们食用之外，还有药用价值。祖国医学认为，白菜性味甘平，有清热除烦、解渴利尿、通利肠胃的功效，经常吃白菜可防止维生素C缺乏症(坏血病)。民间还有用白菜治感冒的验方，其方法是用白菜干根加红糖、姜片、水煎服，或用白菜根三个，大葱根七个，煎水加红糖，趁热饮服，盖被出汗，感冒即愈。大白菜洗净切碎煎浓汤，每晚睡前洗冻疮患处，连洗数日即可风效。白菜子则可解酒，对于酒醉不醒者，可用白菜子研末调“井华水”(即从水井中刚打上来的井水)，服之有效。对于气虚胃冷的人，则不宜多吃白菜，以免恶心吐沫。若吃多了，可用生姜解之。白菜特别适合肺热咳嗽、便秘(便秘食品)、肾病患者多食，同时女性(女性食品)也应该多吃;大白菜性偏寒凉，胃寒腹痛、大便溏泻及寒痢者不可多食。大白菜适合所有人食用。更适宜于慢性习惯性便秘，伤风感冒，肺热咳嗽，咽喉发炎，腹胀及发热之人食用。用大白菜叶贴脸可减少右部的粉刺生长。腹泻者尽量避免食用白菜。腐烂的大白菜不能吃，由于在细菌的作用下，大白菜中的硝酸盐转变为有毒的亚硝酸盐。亚硝酸盐可使血液中的低血红细胞氧化，变丰高铁(铁食品)血红蛋白，更新换代去携氧能力，使用权人发生严重的缺氧引起中毒，出现头晕，头痛，恶心，心跳加快，昏迷，甚至有生命危险。最后教大家如何挑选白菜，挑选包心的大白菜以直到顶部包心紧、分量重、底部突出、根的切口大的不为好。白菜营养丰富，价格便宜，白菜的营养价值高，烹饪方便，是居家必备的好蔬菜啊!\",\"content_source_url\":\"http://115.159.99.120/wechat_git/p_share.wx\"}]}";
		JSONObject json;
		try {
			json = WechatUtil.wechatDoPost(url, content);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void uploadPic(){
		String acct = "wxOaXlDqKCZC8sPhcWeXLs5zde2fT1VaRR037faOQoXfFHydI9EdSiWcuuVTTaRGSnBZN_spClGC-2l9-bl50NNjf1KLZBadoW8EY6pot7AGJBhAEAJME";
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=" + acct;
		String content = "{\"articles\":[{\"title\":\"白菜的营养价值\",\"thumb_media_id\":\"AoAE1ccYbNxUCA_b3isygLykJtZ26ZBrpVjkWWryPmc\",\"author\":\"yql\",\"digest\":\"白菜是我们生活中常见的蔬菜，也是最平常的蔬菜，特别是在北方。虽总是与白菜打交道，但是你对白菜的了解有多深呢?今天小编就介绍下白菜的营养价值，快来来看看吧\",\"show_cover_pic\":1,\"content\":\"白菜是人们餐桌上必备的菜类，白菜有大白菜、小白菜等!大白菜有宽大的绿色菜叶和白色菜帮。多重菜叶紧紧包裹在一起形成圆柱体，多数会形成一个密实的头部。被包在里面的菜叶由于见不到阳光绿色较淡以至呈淡黄色。白菜含有丰富的粗纤维，不但能起到润肠、促进排毒的作用又刺激肠胃蠕动，促进大便排泄，帮助消化(消化食品)的功能。对预防肠癌有良好作用。秋冬季节空气特别干燥，寒风对人的皮肤伤害极大。白菜中含有丰富的维生素C、维生素E，多吃白菜，可以起到很好的护肤和养颜效果。美国纽约激素研究所的科学家发现，中国和日本妇女乳腺癌发病率之所以比西方妇女低得多，是由于她们常吃白菜的缘故。白菜中有一些微量元素(微量元素食品)，它们能帮助分解同乳腺癌相联系的雌激素。白菜微寒、味甘、性平，归肠、胃经。有解热除烦、通利肠胃、养胃生津、除烦解渴、利尿通便、清热解毒;可用于肺热咳嗽、便秘、丹毒、漆疮。菜除作为蔬菜(蔬菜食品)供人们食用之外，还有药用价值。祖国医学认为，白菜性味甘平，有清热除烦、解渴利尿、通利肠胃的功效，经常吃白菜可防止维生素C缺乏症(坏血病)。民间还有用白菜治感冒的验方，其方法是用白菜干根加红糖、姜片、水煎服，或用白菜根三个，大葱根七个，煎水加红糖，趁热饮服，盖被出汗，感冒即愈。大白菜洗净切碎煎浓汤，每晚睡前洗冻疮患处，连洗数日即可风效。白菜子则可解酒，对于酒醉不醒者，可用白菜子研末调“井华水”(即从水井中刚打上来的井水)，服之有效。对于气虚胃冷的人，则不宜多吃白菜，以免恶心吐沫。若吃多了，可用生姜解之。白菜特别适合肺热咳嗽、便秘(便秘食品)、肾病患者多食，同时女性(女性食品)也应该多吃;大白菜性偏寒凉，胃寒腹痛、大便溏泻及寒痢者不可多食。大白菜适合所有人食用。更适宜于慢性习惯性便秘，伤风感冒，肺热咳嗽，咽喉发炎，腹胀及发热之人食用。用大白菜叶贴脸可减少右部的粉刺生长。腹泻者尽量避免食用白菜。腐烂的大白菜不能吃，由于在细菌的作用下，大白菜中的硝酸盐转变为有毒的亚硝酸盐。亚硝酸盐可使血液中的低血红细胞氧化，变丰高铁(铁食品)血红蛋白，更新换代去携氧能力，使用权人发生严重的缺氧引起中毒，出现头晕，头痛，恶心，心跳加快，昏迷，甚至有生命危险。最后教大家如何挑选白菜，挑选包心的大白菜以直到顶部包心紧、分量重、底部突出、根的切口大的不为好。白菜营养丰富，价格便宜，白菜的营养价值高，烹饪方便，是居家必备的好蔬菜啊!\",\"content_source_url\":\"http://115.159.99.120/wechat_git/p_share.wx\"}]}";
		JSONObject json;
		try {
			json = WechatUtil.wechatDoPost(url, content);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getAccessToken());
//		createMenu();
//		addPermanentSource();//IN0sWjdy16_DMGQ_SDPK-EFdJN3egcuyCQEZGN6H0pM
//		uploadPic();
	}

}
