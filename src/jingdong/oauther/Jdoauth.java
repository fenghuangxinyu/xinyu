package jingdong.oauther;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAnyGetter;

import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.OrderSearchInfo;
import com.jd.open.api.sdk.internal.JSON.JSON;
import com.jd.open.api.sdk.request.order.PopOrderGetRequest;
import com.jd.open.api.sdk.request.order.PopOrderSearchRequest;
import com.jd.open.api.sdk.response.order.PopOrderGetResponse;
import com.jd.open.api.sdk.response.order.PopOrderSearchResponse;

public class Jdoauth {

	public static void main(String[] args) {
		//https://oauth.jd.com/oauth/authorize?response_type=code&client_id=877240B385746C749FDD234F66247A63&redirect_uri=http://www.weichaoqi.cn&scope=read
		String url = "https://gw.api.360buy.com/routerjson";

		String access_token="b88b698a-26ff-40c9-8cd4-10cfaee53c88";
		String app_key="877240B385746C749FDD234F66247A63";
		String app_secret="3148abfe43f542c09191463262139a27";
		JdClient client = new DefaultJdClient(url,access_token,app_key,app_secret);
/*		SellerVenderInfoGetRequest request = new SellerVenderInfoGetRequest();
		//request.setExtJsonParam("json");
		try {
			SellerVenderInfoGetResponse response = client.execute(request);
			System.out.println("nihao");
			System.out.println(response.getVenderInfoResult().getShopName());
		} catch (JdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("chuxianyichang");
		}
		*/

		PopOrderSearchRequest ordersearchrequest = new PopOrderSearchRequest();
		PopOrderGetRequest ordergetrequest = new PopOrderGetRequest();
		ordersearchrequest.setStartDate("2017-08-01 00:00:00");
		ordersearchrequest.setEndDate("2017-08-17 23:59:59");
		ordersearchrequest.setOrderState("FINISHED_L");
		ordersearchrequest.setOptionalFields("orderId,orderTotalPrice,invoiceInfo");
		ordersearchrequest.setPage("3");
		ordersearchrequest.setPageSize("100");
		try {
			PopOrderSearchResponse ordersearchresponse = client.execute(ordersearchrequest);
			PopOrderGetResponse ordergetresponse= client.execute(ordergetrequest);
			String json = ordersearchresponse.getMsg();
			System.out.println(json);
			List<com.jd.open.api.sdk.domain.order.OrderQueryJsfService.OrderSearchInfo> orderinfo = ordersearchresponse.getSearchorderinfoResult().getOrderInfoList();
			for(OrderSearchInfo orderinfo1:orderinfo) {
				System.out.println("订单号:"+orderinfo1.getOrderId()+" 订单总金额:"+orderinfo1.getOrderTotalPrice()+" 订单开票信息:"+orderinfo1.getInvoiceInfo());
			}
		} catch (JdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
