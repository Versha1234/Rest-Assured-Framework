package api.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static List<Map<String, String>> getTestDetails(String sheetName) {
		List<Map<String, String>> list = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(System.getProperty("user.dir") + "/TestData/Driver_Data.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int lastRowNum = sheet.getLastRowNum();
			int lastCellNum = sheet.getRow(0).getLastCellNum();
			Map<String, String> map = null;
			list = new ArrayList<>();
			for (int i = 0; i <= lastRowNum; i++) {
				map = new HashMap<>();
				for (int j = 0; j < lastCellNum; j++) {
					String key = sheet.getRow(0).getCell(j).getStringCellValue();
					String value = sheet.getRow(i).getCell(j).getStringCellValue();
					map.put(key, value);

				}
				list.add(map);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (Objects.nonNull(fs)) {
					fs.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}

		return list;

	}

}

