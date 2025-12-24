package api.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endPoints.StorePage;
import api.payload.StorePayload;
import io.restassured.response.Response;

public class StoreTest {
	
	static StorePayload storePayload;
	private static final Logger log=Logger.getLogger(StoreTest.class);
	
	@BeforeClass
	public static void setdata() {
		storePayload=new StorePayload();
		storePayload.setId(1);
		storePayload.setpetId(56789);
		storePayload.setQuantity(1);
		storePayload.setStatus("placed");
	}
	
	@Test(priority=1)
	public void postData() {
		log.info("placing an order for pet");
		Response response=StorePage.placeOrder(storePayload);
		response.then().statusCode(200);
		response.then().log().all();
		log.info("placed an order for pet");
		
	}

	@Test(priority=2)
	public void readData() {
		log.info("finding the order");
		Response response=StorePage.findOrder(this.storePayload.getId());
		response.then().log().all();
	}
	

//	@Test
//	public void updateData() {
//		storePayload=new StorePayload();
//		storePayload.setId(1);
//		storePayload.setpetId(56789);
//		storePayload.setQuantity(2);
//		storePayload.setStatus("placed");
//		Response response=StorePage.updateOrder(this.storePayload.getId(),storePayload);
//		response.then().log().all();
//	}
	

	@Test(priority=3)
	public void deleteData() {
		log.info("deleting the order");
		Response response=StorePage.deleteOrder(this.storePayload.getId());
		Assert.assertEquals(response.getStatusCode(),200);
	}

}
