package api.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

public class DataProviderUtils {
	private static List<Map<String, String>> list = new ArrayList<>();

	@DataProvider
	public static Object[] getData(Method m) {
		String testname = m.getName();
		if (list.isEmpty()) {
			list = ExcelUtils.getTestDetails("API_TestData");
		}

		List<Map<String, String>> iterationList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).get("testname").equalsIgnoreCase(testname)) {
				if (list.get(i).get("execute").equalsIgnoreCase("yes")) {
					iterationList.add(list.get(i));
				}
			}

		}
		list.removeAll(iterationList);
		return iterationList.toArray();
		// TODO Auto-generated method stub

	}
}

