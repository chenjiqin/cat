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
		String menuInfo = "{\"button\":[{\"type\":\"click\",\"name\":\"掌上市场\",\"sub_button\":[{\"type\":\"view\",\"name\":\"菜商\",\"url\":\"http://115.159.99.120/wechat/food_business.wx\"},{\"type\":\"view\",\"name\":\"排行榜\",\"url\":\"http://v.qq.com/\"}]},{\"type\":\"click\",\"name\":\"知识库\",\"sub_button\":[{\"type\":\"view\",\"name\":\"蔬菜常识\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"菜谱视频\",\"url\":\"http://v.qq.com/\"}]},{\"name\":\"我的\",\"sub_button\":[{\"type\":\"view\",\"name\":\"收支\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"收藏\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"关注\",\"key\":\"V1001_GOOD\"},{\"type\":\"click\",\"name\":\"积分\",\"key\":\"V1001_GOOD\"},{\"type\":\"click\",\"name\":\"卡券\",\"key\":\"V1001_GOOD\"}]}]}";
		try {
			JSONObject json = WechatUtil.wechatDoPost(url, menuInfo);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		createMenu();
	}
}
