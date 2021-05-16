package api.rentalcar.testcases;

import api.rentalcar.stubmappings.StubMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class RentalCarTests  extends StubMapper {
    Logger log=Logger.getLogger(getClass().getSimpleName());
    String sHostName;
    @BeforeMethod
    public  void setHostName(){
       sHostName="http://localhost:8088";
        String URI="/getData";
        String URL=sHostName+URI;
        RestAssured.baseURI=URL;


    }
    @Test()
    public void getListOfCars(){

        Response response=RestAssured.given().contentType("application/json").get();
        System.out.println("Response is :"+response.asString());
        log.info("Response is :"+response.asString());
        System.out.println("Status code :"+response.statusCode());
        log.info("Status code :"+response.statusCode());
        System.out.println("Output Data :"+response.getBody().asString());

    }
    @Test(priority = 1)
    public void getBlueTeslaCars(){
        String sMake="Tesla";
        String sColor="Blue";
        Response response=RestAssured.given().contentType("application/json").get();
        System.out.println("Status code :"+response.statusCode());
        log.info("Status code :"+response.statusCode());
        oResponseUtility.checkMakeAndColor(sMake,sColor,response);
    }
    @Test(priority = 2)
    public void printLowestPerDayRentalCar(){
        Response response=RestAssured.given().contentType("application/json").get();
        System.out.println("Status code :"+response.statusCode());
        log.info("Status code :"+response.statusCode());
        int iLowestRentIndex=oResponseUtility.getLowestPerDayRentCar(response);
        System.out.println("The Car detail of Lowest rent per day is : "+response.jsonPath().get("Car["+iLowestRentIndex+"]").toString());
        log.info("The Car detail of Lowest rent per day is : "+response.jsonPath().get("Car["+iLowestRentIndex+"]").toString());
        int iLowestRentAfterDiscountIndex=oResponseUtility.getLowestPerDayRentCarAfterDiscount(response);
        System.out.println("The Car detail of Lowest rent per day after the discount is :\n "+response.jsonPath().get("Car["+iLowestRentAfterDiscountIndex+"]").toString());
        log.info("The Car detail of Lowest rent per day after the discount is : "+response.jsonPath().get("Car["+iLowestRentAfterDiscountIndex+"]").toString());
    }
    @Test(priority = 3)
    public void getHighRevenueCar(){
        Response response=RestAssured.given().contentType("application/json").get();
        System.out.println("Status code :"+response.statusCode());
        log.info("Status code :"+response.statusCode());
        int iHighRevenueCarIndex=oResponseUtility.getHighRevenueCar(response);
        System.out.println("High Revenue Car Detail is : \n"+response.jsonPath().get("Car["+iHighRevenueCarIndex+"]").toString());
        log.info("High Revenue Car Detail is : "+response.jsonPath().get("Car["+iHighRevenueCarIndex+"]").toString());

    }
}
