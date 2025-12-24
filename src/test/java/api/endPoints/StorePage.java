package api.endPoints;


import api.payload.StorePayload;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;



public class StorePage {
	
	
	public static Response placeOrder(StorePayload storePayload) {
		
		Response response=given()
			.contentType("application/json")
			.body(storePayload)
			
		.when()
			.post(Routes.post_url);
		
		return response;
		
	}
	
    public static Response findOrder(int orderId) {
		Response response=given()
				.contentType("application/json")
				.pathParam("orderId", orderId)
				
		.when()
				.get(Routes.read_url);
					
		return response;
	}
	

    public static Response updateOrder(int orderId, StorePayload storePayload) {
    	Response response=given()
				.contentType("application/json")
				.body(storePayload)
				.pathParam("orderId", orderId)
				
		.when()
				.put(Routes.put_url);
				
		return response;
		
	}
	
    public static Response deleteOrder(int orderId) {
    	Response response=given()
				.contentType("application/json")
				.pathParam("orderId", orderId)
				
		.when()
				.delete(Routes.delete_url);	
				
		return response;
		
	}

}
