package api.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endPoints.StorePage;
import api.payload.StorePayload;
import api.utils.DataProviderUtils;
import io.restassured.response.Response;

public class StoreTestUsingDataDriven {
	
	StorePayload storePayload=new StorePayload();
	
	private static final Logger log=Logger.getLogger(StoreTestUsingDataDriven.class);
	
	@Test(priority=1,dataProvider="getData", dataProviderClass=DataProviderUtils.class)
	public void postData(Map<String, String> map) {
		
		storePayload.setId(Integer.parseInt(map.get("Id")));
		storePayload.setpetId(Integer.parseInt(map.get("petId")));
		storePayload.setQuantity(Integer.parseInt(map.get("Quantity")));
		storePayload.setStatus(map.get("Status"));
		
		log.info("placing an order for pet");
		Response response=StorePage.placeOrder(storePayload);
		response.then().statusCode(200);
		response.then().log().all();
		log.info("placed an order for pet");
		
	}
	
	@Test(priority=2,dataProvider="getData", dataProviderClass=DataProviderUtils.class)
	public void readData(Map<String, String> map) {

		log.info("finding the order");
		Response response=StorePage.findOrder(this.storePayload.getId());
		response.then().log().all();
	}
	
	@Test(priority=3,dataProvider="getData", dataProviderClass=DataProviderUtils.class)
	public void deleteData(Map<String, String> map) {
		
		log.info("deleting the order");
		Response response=StorePage.deleteOrder(this.storePayload.getId());
		Assert.assertEquals(response.getStatusCode(),200);
	}

}
