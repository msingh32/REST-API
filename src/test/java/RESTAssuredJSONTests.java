
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.text.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RESTAssuredJSONTests {


    Properties prop = new Properties();

    @BeforeTest
    public void getData() throws IOException {

        FileInputStream fis = new FileInputStream("src/test/java/files/env.properties");
        prop.load(fis);

    }

    @Test(priority=1)
    public void test_Enroll() {

        List<String> status = new ArrayList<String>();
        status.add("EnrollmentCreated");
        status.add("EnrollmentUpdated");
        Long ut = System.currentTimeMillis() / 1000L;
        int oneDay = (1 * 24 * 60 * 60);
        Float MonitoringPeriodStart = ((Long) (ut + 5 * oneDay)).floatValue();
        Map<Object, Object> putRes = given().
                auth().
                preemptive().
                basic(prop.getProperty("USER"), prop.getProperty("PASSWORD")).
                contentType(ContentType.JSON)
                .accept(ContentType.JSON).
                        body("{	\"ActionCode\": \"ENROLL\",	\"EnrollmentRecord\": {		\"DriverID\": \"9785550165\",		\"PhoneNumber\": \"+19785550165\",		\"MonitoringPeriodStart\": " + MonitoringPeriodStart
                                + ",		\"MonitoringDuration\": -1,		\"Vehicles\": [{			\"Year\": 2013,			\"Make\": \"Ford\",			\"Model\": \"Focus\"		}]	}}").
                        when().
                        put(prop.getProperty("HOST") + "/enrollment").then().
                        contentType(ContentType.JSON).
                        statusCode(200).
                        body("StatusDetail", isIn(status)).// check that the content type return from the API is JSON
                extract().path("");

        JSONObject jsonObj = new JSONObject(putRes);
        String StatusDetail = (String) jsonObj.get("StatusDetail");
        Integer StatusCode = (Integer) jsonObj.get("StatusCode");
        System.out.println("StatusCode : " + StatusCode);
        System.out.println("StatusDetail : " + StatusDetail);

    }

    @Test(priority = 2)
    public void test_GetStatus() {

        List<Integer> status = new ArrayList<Integer>();
        status.add(0);
        status.add(-1);
        status.add(1);
        Map<Object, Object> res = given().
                auth().
                preemptive().
                basic(prop.getProperty("USER"), prop.getProperty("PASSWORD")).
                when().
                get(prop.getProperty("HOST") + "/drivers/driver_00001/status").
                then().
                statusCode(200).
                body("Data.DriverStatus.WiFiLocationServiceState", isIn(status), "Data.DriverStatus.BackgroundAppRefreshState", isIn(status), "Data.DriverStatus.GPSLocationServiceState", isIn(status)).
                contentType(ContentType.JSON).  // check that the content type return from the API is JSON
                extract().path("Data.DriverStatus");


        JSONObject jsonObj = new JSONObject(res);
        Float LastOnlineTime = (Float) jsonObj.get("LastOnlineTime");
        String d = LastOnlineTime.toString();
        BigDecimal bd1 = new BigDecimal(d);
        String output1 = bd1.setScale(0).toPlainString();

        long l = Long.valueOf(output1);
        java.util.Date time = new java.util.Date((long) l * 1000);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        String LastOnline = sdf.format(time);
        System.out.println("LastOnlineTime is :" + LastOnline);

        Float MonitoringPeriodStart = (Float) jsonObj.get("MonitoringPeriodStart");
        String d2 = MonitoringPeriodStart.toString();
        BigDecimal bd3 = new BigDecimal(d2);
        String output3 = bd3.setScale(0).toPlainString();
        long l2 = Long.valueOf(output3);
        java.util.Date time2 = new java.util.Date((long) l2 * 1000);
        SimpleDateFormat sdf2 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        String MonitoringPeriod = sdf2.format(time2);
        System.out.println("MonitoringPeriodStart is :" + MonitoringPeriod);


        Float LastTripTime = (Float) jsonObj.get("LastTripTime");
        String d1 = LastTripTime.toString();
        BigDecimal bd2 = new BigDecimal(d1);
        String output2 = bd2.setScale(0).toPlainString();

        long l1 = Long.valueOf(output2);
        java.util.Date time1 = new java.util.Date((long) l1 * 1000);
        SimpleDateFormat sdf1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        String LastTrip = sdf1.format(time1);
        System.out.println("LastTripTime is :" + LastTrip);
        //Repeat for one more


        Integer BackgroundAppRefreshState = (Integer) jsonObj.get("BackgroundAppRefreshState");
        checkStatus(BackgroundAppRefreshState, "BackgroundAppRefreshState");
        Integer WiFiLocationServiceState = (Integer) jsonObj.get("WiFiLocationServiceState");
        checkStatus(WiFiLocationServiceState, "WiFiLocationServiceState");
        Integer GPSLocationServiceState = (Integer) jsonObj.get("GPSLocationServiceState");
        checkStatus(GPSLocationServiceState, "GPSLocationServiceState");

    }

    public void checkStatus(Integer status, String name) {
        if (status == -1) {
            System.out.println(name + ": UNKNOWN");
        } else if (status == 0) {
            System.out.println(name + " OFF");
        } else if (status == 1) {
            System.out.println(name + " ON");
        } else {
            System.out.println(name + " ERROR");
            // check hoe to throw error
        }

    }

    @Test(priority = 3)
    public void test_UNEnroll() {

        Map<Object, Object> putRes = given().
                auth().
                preemptive().
                basic(prop.getProperty("USER"), prop.getProperty("PASSWORD")).
                contentType(ContentType.JSON)
                .accept(ContentType.JSON).
                        body("{	\"ActionCode\": \"UNENROLL\",	\"EnrollmentRecord\": {		\"DriverID\": \"9785550165\",		\"PhoneNumber\": \"+19785550165\"	}}").
                        when().
                        put(prop.getProperty("HOST") + "/enrollment").then().
                        statusCode(200).
                        body("StatusDetail", equalTo("UnenrollSuccessful")
                        ).
                        contentType(ContentType.JSON).  // check that the content type return from the API is JSON
                extract().path("");
        JSONObject jsonObj = new JSONObject(putRes);
        String StatusDetail = (String) jsonObj.get("StatusDetail");
        Integer StatusCode = (Integer) jsonObj.get("StatusCode");
        System.out.println("StatusCode : " + StatusCode);
        System.out.println("StatusDetail : " + StatusDetail);

    }

    @Test(priority = 4)
    public void test_GetStatus_Id_For_Wild_Card() {
        given().
                auth().
                preemptive().
                basic(prop.getProperty("USER"), prop.getProperty("PASSWORD")).when().get(prop.getProperty("HOST") + "/drivers/*/status").then().statusCode(404);
    }

}
