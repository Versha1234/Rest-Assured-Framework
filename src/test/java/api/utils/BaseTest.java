package api.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeSuite;

import api.tests.StoreTest;
import api.tests.StoreTestUsingDataDriven;


public class BaseTest {
    static Logger log;
	

	@BeforeSuite
	public void beforeSuite() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		System.setProperty("log4jFileName", timeStamp);
		
		PropertyConfigurator.configure(System.getProperty("user.dir")+
				"/src/test/resources/log4j.properties");
		log = Logger.getLogger(StoreTestUsingDataDriven.class);
		String date = new SimpleDateFormat("dd-MM-yy").format(new Date());
		File warningLog = new File(System.getProperty("user.dir") + "\\WarningLogs\\" + date);
		warningLog.mkdir();

	}

}
