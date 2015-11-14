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
		String menuInfo = "{\"button\":[{\"type\":\"click\",\"name\":\"�����г�\",\"sub_button\":[{\"type\":\"view\",\"name\":\"����\",\"url\":\"http://115.159.99.120/wechat_git/food_business.wx\"},{\"type\":\"view\",\"name\":\"���а�\",\"url\":\"http://v.qq.com/\"}]},{\"type\":\"click\",\"name\":\"֪ʶ��\",\"sub_button\":[{\"type\":\"view\",\"name\":\"�߲˳�ʶ\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"������Ƶ\",\"url\":\"http://v.qq.com/\"}]},{\"name\":\"�ҵ�\",\"sub_button\":[{\"type\":\"view\",\"name\":\"��֧\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"�ղ�\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"��ע\",\"key\":\"V1001_GOOD\"},{\"type\":\"click\",\"name\":\"����\",\"key\":\"V1001_GOOD\"},{\"type\":\"click\",\"name\":\"��ȯ\",\"key\":\"V1001_GOOD\"}]}]}";
		try {
			JSONObject json = WechatUtil.wechatDoPost(url, menuInfo);
			System.out.println(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ���������ز�
	 */
	public static void addPermanentSource() {
		String url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=" + getAccessToken().getString("access_token");
		String content = "{\"articles\":[{\"title\":\"�ײ˵�Ӫ����ֵ\",\"thumb_media_id\":\"IN0sWjdy16_DMGQ_SDPK-A-xpb5s7uNrDuJ-HEsNP1Q\",\"author\":\"yql\",\"digest\":\"�ײ������������г������߲ˣ�Ҳ����ƽ�����߲ˣ��ر����ڱ�������������ײ˴򽻵���������԰ײ˵��˽��ж�����?����С��ͽ����°ײ˵�Ӫ����ֵ��������������\",\"show_cover_pic\":1,\"content\":\"�ײ������ǲ����ϱر��Ĳ��࣬�ײ��д�ײˡ�С�ײ˵�!��ײ��п�����ɫ��Ҷ�Ͱ�ɫ�˰���ز�Ҷ����������һ���γ�Բ���壬�������γ�һ����ʵ��ͷ��������������Ĳ�Ҷ���ڼ�����������ɫ�ϵ������ʵ���ɫ���ײ˺��зḻ�Ĵ���ά�����������󳦡��ٽ��Ŷ��������ִ̼���θ�䶯���ٽ������й����������(����ʳƷ)�Ĺ��ܡ���Ԥ���������������á��ﶬ���ڿ����ر���������˵�Ƥ���˺����󡣰ײ��к��зḻ��ά����C��ά����E����԰ײˣ������𵽺ܺõĻ���������Ч��������ŦԼ�����о����Ŀ�ѧ�ҷ��֣��й����ձ���Ů���ٰ�������֮���Ա�������Ů�͵ö࣬���������ǳ��԰ײ˵�Ե�ʡ��ײ�����һЩ΢��Ԫ��(΢��Ԫ��ʳƷ)�������ܰ����ֽ�ͬ���ٰ�����ϵ�ĴƼ��ء��ײ�΢����ζ�ʡ���ƽ���鳦��θ�����н��ȳ�����ͨ����θ����θ���򡢳�����ʡ�����ͨ�㡢���Ƚⶾ;�����ڷ��ȿ��ԡ����ء��������ᴯ���˳���Ϊ�߲�(�߲�ʳƷ)������ʳ��֮�⣬����ҩ�ü�ֵ�����ҽѧ��Ϊ���ײ���ζ��ƽ�������ȳ������������ͨ����θ�Ĺ�Ч�������԰ײ˿ɷ�ֹά����Cȱ��֢(��Ѫ��)����仹���ðײ��θ�ð���鷽���䷽�����ðײ˸ɸ��Ӻ��ǡ���Ƭ��ˮ��������ðײ˸���������и��߸�����ˮ�Ӻ��ǣ������������Ǳ���������ð��������ײ�ϴ�������Ũ����ÿ��˯ǰϴ������������ϴ���ռ��ɷ�Ч���ײ�����ɽ�ƣ����ھ������ߣ����ðײ�����ĩ��������ˮ��(����ˮ���иմ������ľ�ˮ)����֮��Ч����������θ����ˣ����˶�԰ײˣ����������ĭ�����Զ��ˣ�����������֮���ײ��ر��ʺϷ��ȿ��ԡ�����(����ʳƷ)���������߶�ʳ��ͬʱŮ��(Ů��ʳƷ)ҲӦ�ö��;��ײ���ƫ������θ����ʹ�������к�������߲��ɶ�ʳ����ײ��ʺ�������ʳ�á�������������ϰ���Ա��أ��˷��ð�����ȿ��ԣ��ʺ��ף����ͼ�����֮��ʳ�á��ô�ײ�Ҷ�����ɼ����Ҳ��ķ۴���������к�߾�������ʳ�ðײˡ����õĴ�ײ˲��ܳԣ�������ϸ���������£���ײ��е�������ת��Ϊ�ж����������Ρ��������ο�ʹѪҺ�еĵ�Ѫ��ϸ��������������(��ʳƷ)Ѫ�쵰�ף����»���ȥЯ��������ʹ��Ȩ�˷������ص�ȱ�������ж�������ͷ�Σ�ͷʹ�����ģ������ӿ죬���ԣ�����������Σ�ա����̴�������ѡ�ײˣ���ѡ���ĵĴ�ײ���ֱ���������Ľ��������ء��ײ�ͻ���������пڴ�Ĳ�Ϊ�á��ײ�Ӫ���ḻ���۸���ˣ��ײ˵�Ӫ����ֵ�ߣ���⿷��㣬�ǾӼұر��ĺ��߲˰�!\",\"content_source_url\":\"http://115.159.99.120/wechat_git/p_share.wx\"}]}";
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
		String content = "{\"articles\":[{\"title\":\"�ײ˵�Ӫ����ֵ\",\"thumb_media_id\":\"AoAE1ccYbNxUCA_b3isygLykJtZ26ZBrpVjkWWryPmc\",\"author\":\"yql\",\"digest\":\"�ײ������������г������߲ˣ�Ҳ����ƽ�����߲ˣ��ر����ڱ�������������ײ˴򽻵���������԰ײ˵��˽��ж�����?����С��ͽ����°ײ˵�Ӫ����ֵ��������������\",\"show_cover_pic\":1,\"content\":\"�ײ������ǲ����ϱر��Ĳ��࣬�ײ��д�ײˡ�С�ײ˵�!��ײ��п�����ɫ��Ҷ�Ͱ�ɫ�˰���ز�Ҷ����������һ���γ�Բ���壬�������γ�һ����ʵ��ͷ��������������Ĳ�Ҷ���ڼ�����������ɫ�ϵ������ʵ���ɫ���ײ˺��зḻ�Ĵ���ά�����������󳦡��ٽ��Ŷ��������ִ̼���θ�䶯���ٽ������й����������(����ʳƷ)�Ĺ��ܡ���Ԥ���������������á��ﶬ���ڿ����ر���������˵�Ƥ���˺����󡣰ײ��к��зḻ��ά����C��ά����E����԰ײˣ������𵽺ܺõĻ���������Ч��������ŦԼ�����о����Ŀ�ѧ�ҷ��֣��й����ձ���Ů���ٰ�������֮���Ա�������Ů�͵ö࣬���������ǳ��԰ײ˵�Ե�ʡ��ײ�����һЩ΢��Ԫ��(΢��Ԫ��ʳƷ)�������ܰ����ֽ�ͬ���ٰ�����ϵ�ĴƼ��ء��ײ�΢����ζ�ʡ���ƽ���鳦��θ�����н��ȳ�����ͨ����θ����θ���򡢳�����ʡ�����ͨ�㡢���Ƚⶾ;�����ڷ��ȿ��ԡ����ء��������ᴯ���˳���Ϊ�߲�(�߲�ʳƷ)������ʳ��֮�⣬����ҩ�ü�ֵ�����ҽѧ��Ϊ���ײ���ζ��ƽ�������ȳ������������ͨ����θ�Ĺ�Ч�������԰ײ˿ɷ�ֹά����Cȱ��֢(��Ѫ��)����仹���ðײ��θ�ð���鷽���䷽�����ðײ˸ɸ��Ӻ��ǡ���Ƭ��ˮ��������ðײ˸���������и��߸�����ˮ�Ӻ��ǣ������������Ǳ���������ð��������ײ�ϴ�������Ũ����ÿ��˯ǰϴ������������ϴ���ռ��ɷ�Ч���ײ�����ɽ�ƣ����ھ������ߣ����ðײ�����ĩ��������ˮ��(����ˮ���иմ������ľ�ˮ)����֮��Ч����������θ����ˣ����˶�԰ײˣ����������ĭ�����Զ��ˣ�����������֮���ײ��ر��ʺϷ��ȿ��ԡ�����(����ʳƷ)���������߶�ʳ��ͬʱŮ��(Ů��ʳƷ)ҲӦ�ö��;��ײ���ƫ������θ����ʹ�������к�������߲��ɶ�ʳ����ײ��ʺ�������ʳ�á�������������ϰ���Ա��أ��˷��ð�����ȿ��ԣ��ʺ��ף����ͼ�����֮��ʳ�á��ô�ײ�Ҷ�����ɼ����Ҳ��ķ۴���������к�߾�������ʳ�ðײˡ����õĴ�ײ˲��ܳԣ�������ϸ���������£���ײ��е�������ת��Ϊ�ж����������Ρ��������ο�ʹѪҺ�еĵ�Ѫ��ϸ��������������(��ʳƷ)Ѫ�쵰�ף����»���ȥЯ��������ʹ��Ȩ�˷������ص�ȱ�������ж�������ͷ�Σ�ͷʹ�����ģ������ӿ죬���ԣ�����������Σ�ա����̴�������ѡ�ײˣ���ѡ���ĵĴ�ײ���ֱ���������Ľ��������ء��ײ�ͻ���������пڴ�Ĳ�Ϊ�á��ײ�Ӫ���ḻ���۸���ˣ��ײ˵�Ӫ����ֵ�ߣ���⿷��㣬�ǾӼұر��ĺ��߲˰�!\",\"content_source_url\":\"http://115.159.99.120/wechat_git/p_share.wx\"}]}";
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
