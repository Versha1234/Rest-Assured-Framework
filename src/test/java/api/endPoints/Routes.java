package api.endPoints;

public class Routes {
	
	public static String base_URI="https://petstore.swagger.io/v2/store";
	
	public static String post_url=base_URI+"/order";
	public static String put_url=base_URI+"/order/{orderId}";
	public static String delete_url=base_URI+"/order/{orderId}";
	public static String read_url=base_URI+"/order/{orderId}";

}
