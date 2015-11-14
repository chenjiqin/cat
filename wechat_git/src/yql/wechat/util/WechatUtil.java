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
//		DefaultHttpClient httpClient = new DefaultHttpClient();//�÷����Ѿ���ʱ
		//�µĴ���httpClient�ķ���
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
	 * ����post����
	 * @param url
	 * @param outStr
	 * @return
	 * @throws Exception
	 */
	public static JSONObject wechatDoPost(String url, String outStr) throws Exception {
//		DefaultHttpClient httpClient = new DefaultHttpClient();�÷����Ѿ���ʱ
		//�µĴ���httpClient�ķ���
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
	 * ��ȡaccess_token
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
	 * �����˵�,�ֶ���main����ִ���¼���
	 * @throws Exception
	 */
	public static void createMenu(){
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+getAccessToken().getString("access_token");
//		String menuInfo = "{\"button\":[{\"type\":\"click\",\"name\":\"���ո���\",\"key\":\"V1001_TODAY_MUSIC\"},{\"name\":\"�˵�\",\"sub_button\":[{\"type\":\"view\",\"name\":\"����\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"��Ƶ\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"��һ������\",\"key\":\"V1001_GOOD\"}]}]}";
		String menuInfo = "{\"button\":[{\"type\":\"click\",\"name\":\"�����г�\",\"sub_button\":[{\"type\":\"view\",\"name\":\"����\",\"url\":\"http://115.159.99.120/wechat/food_business.wx\"},{\"type\":\"view\",\"name\":\"���а�\",\"url\":\"http://v.qq.com/\"}]},{\"type\":\"click\",\"name\":\"֪ʶ��\",\"sub_button\":[{\"type\":\"view\",\"name\":\"�߲˳�ʶ\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"������Ƶ\",\"url\":\"http://v.qq.com/\"}]},{\"name\":\"�ҵ�\",\"sub_button\":[{\"type\":\"view\",\"name\":\"��֧\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"�ղ�\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"��ע\",\"key\":\"V1001_GOOD\"},{\"type\":\"click\",\"name\":\"����\",\"key\":\"V1001_GOOD\"},{\"type\":\"click\",\"name\":\"��ȯ\",\"key\":\"V1001_GOOD\"}]}]}";
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
