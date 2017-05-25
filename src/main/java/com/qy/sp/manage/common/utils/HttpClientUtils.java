package com.qy.sp.manage.common.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {

	//private static Logger log = Logger.getLogger(HttpClientUtils.class);
	public final static String UTF8 = "UTF-8";
	public final static String GBK = "GBK";
	private final static int TIME_OUT = 10000;  //超时时间设置 默认10秒
	
	private static PoolingHttpClientConnectionManager cm;
	private static RequestConfig requestConfig;
	
	static{
		cm = new PoolingHttpClientConnectionManager();
		//Increase max total connection to 200  
		cm.setMaxTotal(200);//总共保持200个连接(对于多个通过该http访问的网站)
		//Increase default max connection per route to 20  
		cm.setDefaultMaxPerRoute(20); //每个网站的默认连接最多20个
		//Increase max connections for localhost:80 to 50
		//连接池里面可以保持长连接到"http://open.wo.com.cn/"地址的最大数是50个,如果你请求"http://open.wo.com.cn/"这个地址的量很大，
		//把50个HTTP连接都占完了，那新的请求过来就需要等到其他使用连接池里面到这个地址的HTTP连接释放了才行
		//HttpHost wo = new HttpHost("http://open.wo.com.cn/", 80);
		//cm.setMaxPerRoute(new HttpRoute(wo), 80);
		
		//设置超时时间
		requestConfig = RequestConfig.custom()
				.setSocketTimeout(TIME_OUT)
				.setConnectTimeout(TIME_OUT)
				.build();
	}
	
	/**
	 * 发送POST请求
	 * @param url 请求地址
	 * @param json 请求数据JSON格式
	 * @param platID
	 * @param platPwd
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, String json, String platID, String platPwd,String encode) throws Exception {
		CloseableHttpClient httpclient = bulidHttpClient();
		
		HttpPost httpPost = new HttpPost(url);
		
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json;charset="+encode+"");
		httpPost.setHeader("Authorization", "platformID=\""+platID+"\",password=\""+platPwd+"\"");

		StringEntity entity = new StringEntity(json, encode);
		httpPost.setEntity(entity);
		
		//IdleConnectionEvictor connEvictor = new IdleConnectionEvictor(cm);
        //connEvictor.start();
        
		CloseableHttpResponse response = httpclient.execute(httpPost);
		String content = null;
		try {
			HttpEntity entity2 = response.getEntity();
			// do something useful with the response body
			content = getContent(response,encode);
			// and ensure it is fully consumed
			EntityUtils.consume(entity2);
		} finally {
			response.close();
		}
        
		//Thread.sleep(20000);
        //connEvictor.shutdown();
        //connEvictor.join();
        
        return content;
	}

	/**
	 * 发送单个HTTP请求
	 * 
	 * @param url  请求地址
	 * @param json 请求数据JSON格式
	 * @throws Exception
	 */
	public static String doPost(String url, String json, String encode) throws Exception {
		CloseableHttpClient httpclient = bulidHttpClient();
		
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json;charset="+encode+"");

		StringEntity entity = new StringEntity(json,encode);
		httpPost.setEntity(entity);
		CloseableHttpResponse response = httpclient.execute(httpPost);
		String content = null;
		try {
			HttpEntity entity2 = response.getEntity();
			// do something useful with the response body
			content = getContent(response,encode);
			// and ensure it is fully consumed
			EntityUtils.consume(entity2);
		} finally {
			response.close();
		}
		return content;
	}
	 
	
	/**
	 * 发送单个HTTP请求
	 * 
	 * @param url
	 *            请求地址
	 * @param json
	 *            请求数据JSON格式
	 * @throws Exception
	 */
	public static String doPost(String url, Map<String, String> params,String encode) throws Exception {
		CloseableHttpClient httpclient = bulidHttpClient();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvp = setParams(params);
		httpPost.setEntity(new UrlEncodedFormEntity(nvp, encode));
		CloseableHttpResponse response = httpclient.execute(httpPost);
		String content = null;
		try {
			HttpEntity entity2 = response.getEntity();
			content = getContent(response,encode);
			EntityUtils.consume(entity2);
		} finally {
			response.close();
		}
		return content;
	}
	
	

	/**
	 * 发送Get请求
	 * 
	 * @param url  请求地址
	 * @param json 请求数据JSON格式
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String url,String encode) throws Exception {
		CloseableHttpClient httpclient = bulidHttpClient();
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json;charset="+encode+"");
		
		CloseableHttpResponse response = httpclient.execute(httpGet);
		String content = null;

		try {
			HttpEntity entity = response.getEntity();
			content = getContent(response,encode);
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}
		return content;
	}

	
	/**
	 * 发送Get请求
	 * 
	 * @param url  请求地址
	 * @param json 请求数据JSON格式
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String url,String platID, String platPwd,String encode) throws Exception {
		CloseableHttpClient httpclient = bulidHttpClient();
		
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json;charset="+encode+"");
		httpGet.setHeader("Authorization", "platformID=\""+platID+"\",password=\""+platPwd+"\"");
		
		CloseableHttpResponse response = httpclient.execute(httpGet);
		String content = null;

		try {
			HttpEntity entity = response.getEntity();
			content = getContent(response,encode);
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}
		return content;
	}

	/**
	 * 获取返回数据
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 */
	private static String getContent(HttpResponse response,String encode) {
		HttpEntity entity = response.getEntity();
		byte[] bytes;
		String content = null;
		try {
			bytes = EntityUtils.toByteArray(entity);
			content = new String(bytes, encode);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	private static List<NameValuePair> setParams(Map<String, String> map) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (null != map) {
			Iterator<String> iter = map.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				String value = map.get(key);
				nvps.add(new BasicNameValuePair(key, value));
			}
		}
		return nvps;
	}
	

	/**
	 * 初始化HTTP连接池
	 * @return
	 */
	/*private static PoolingHttpClientConnectionManager initManger(){
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		
		//Increase max total connection to 200  
		cm.setMaxTotal(200);//总共保持200个连接(对于多个通过该http访问的网站)
		
		//Increase default max connection per route to 20  
		cm.setDefaultMaxPerRoute(10); //每个网站的默认连接最多20个
		
		//Increase max connections for localhost:80 to 50
		//连接池里面可以保持长连接到"http://open.wo.com.cn/"地址的最大数是50个,如果你请求"http://open.wo.com.cn/"这个地址的量很大，
		//把50个HTTP连接都占完了，那新的请求过来就需要等到其他使用连接池里面到这个地址的HTTP连接释放了才行
		HttpHost wo = new HttpHost("http://open.wo.com.cn/", 80);
		cm.setMaxPerRoute(new HttpRoute(wo), 80);
		
		HttpHost qq = new HttpHost("http://www.qq.com/",80);
		cm.setMaxPerRoute(new HttpRoute(qq), 50);
		
		
		
		return cm;
	}*/
	
	/**
	 * 获取HttpClient对象
	 * @param cm
	 * @return
	 */
	private static CloseableHttpClient bulidHttpClient(){
		//清除过期连接
		//IdleConnectionEvictor ice = IdleConnectionEvictor.getInstance(cm);
		//ice.closed();
		cm.closeExpiredConnections(); //清除过期链接
		//cm.closeIdleConnections(5, TimeUnit.SECONDS); //一段时间内不活动的连接
		
		CloseableHttpClient httpClient = HttpClients.custom()  
		        .setConnectionManager(cm)
		        .setDefaultRequestConfig(requestConfig)
		        .build();
		
		return httpClient;
	}
	
	/**
	 * 连接回收策略
	 * @author Jvi
	 *
	 */
	/*public static class IdleConnectionEvictor extends Thread {
        private final HttpClientConnectionManager connMgr;
        private volatile boolean shutdown;

        public IdleConnectionEvictor(HttpClientConnectionManager connMgr) {
            super();
            this.connMgr = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(5000);
                        // Close expired connections
                        connMgr.closeExpiredConnections(); //过期链接
                        // Optionally, close connections
                        // that have been idle longer than 5 sec
                        connMgr.closeIdleConnections(5, TimeUnit.SECONDS); //一段时间内不活动的连接
                    }
                }
            } catch (Exception ex) {
                // terminate
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }

    }*/

	public static void main(final String[] args) throws Exception {
		String url = "http://open.wo.com.cn/openapi/getchannelpaymentsms/v1.0";
		String json = "{\"appKey\":\"api_test05\",\"appName\":\"压力测试appName\",\"callbackData\":\"1\",\"callbackUrl\":\"http://58.240.19.130:18888/feeapi4/payment.do\",\"outTradeNo\":\"10011419821193432298986930164574489\",\"subject\":\"压力测试Subject\",\"timeStamp\":\"20141229104632\",\"totalFee\":0.05,\"signType\":\"HMAC-SHA1\",\"signature\":\"kazArOz0Z1+zgDbzoggtB8PdTHU=\"}";
		String platID = "8f0dcfc5-6548-445d-962f-0e266d3d1f63"; 
		String platPwd = "";
		String s = HttpClientUtils.doPost(url, json, platID, platPwd,HttpClientUtils.UTF8);
		System.out.println(s);
	}
}
