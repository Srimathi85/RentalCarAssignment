package api.rentalcar.utility;

import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ResponseUtility {
    Logger log=Logger.getLogger(getClass().getSimpleName());
    public void checkMakeAndColor(String sMake, String sColor, Response response){
        List<String> carsMakeList=response.jsonPath().getList("Car.make");
        System.out.println("Cars make list :"+carsMakeList);
        log.info("Cars make list :"+carsMakeList);
        System.out.println(sMake+" "+sColor+" car  :");
        for (int index = 0; index < carsMakeList.size(); index++) {
             if(carsMakeList.get(index).equalsIgnoreCase(sMake)){
                if(response.jsonPath().getString("Car["+index+"].metadata.Color").equalsIgnoreCase(sColor)){
                    System.out.println("-----------------Metadata notes------------- ");
                    System.out.println("Car :"+response.jsonPath().getString("Car["+index+"].metadata.Notes"));
                }
            }
        }
    }
    public int getLowestPerDayRentCar(Response response){
       List<Integer> perDayRentList=response.jsonPath().getList("Car.perdayrent.Price");
        System.out.println("List based on rent per day :"+perDayRentList);
        int indexOfLowestRentCar=0;
        for (int index = 1; index <perDayRentList.size(); index++) {
            if(perDayRentList.get(indexOfLowestRentCar)>=perDayRentList.get(index)){
                indexOfLowestRentCar=index;
            }
        }
        System.out.println("Lowest Rent per day is :\n"+perDayRentList.get(indexOfLowestRentCar));
        return indexOfLowestRentCar;
    }

    public int getLowestPerDayRentCarAfterDiscount(Response response){
        List<Integer> perDayRentList=response.jsonPath().getList("Car.perdayrent.Price");
        List<Integer> discountList=response.jsonPath().getList("Car.perdayrent.Discount");
        ArrayList<Float> perDayRentAfterDiscountAL=new ArrayList<>();
        for (int index = 0; index < perDayRentList.size(); index++) {
            perDayRentAfterDiscountAL.add(index, (float) (perDayRentList.get(index)-(perDayRentList.get(index)*discountList.get(index)/100)));
        }
        System.out.println("Per Day Rent List After discount :"+perDayRentAfterDiscountAL);
        log.info("Per Day Rent List After discount :"+perDayRentAfterDiscountAL);
        int iLowestRentIndex=0;
        for (int index = 1; index < perDayRentAfterDiscountAL.size(); index++) {
            if(perDayRentAfterDiscountAL.get(index)<=perDayRentAfterDiscountAL.get(iLowestRentIndex)){
                iLowestRentIndex=index;
            }
        }
        return iLowestRentIndex;
    }
    public int getHighRevenueCar(Response response){
        List<Float> yearMaintenanceCostList=response.jsonPath().getList("Car.metrics.yoymaintenancecost");
        List<Float> depreciationList=response.jsonPath().getList("Car.metrics.depreciation");
        ArrayList<Float> carExpenseList=new ArrayList<>();
        int iHighRevenueCarIndex=0;
        for (int index = 0; index <depreciationList.size() ; index++) {
            carExpenseList.add(yearMaintenanceCostList.get(index)+depreciationList.get(index));
        }
        System.out.println("Year Maintenance Cost List : "+carExpenseList);
        log.info("Year Maintenance Cost List : "+carExpenseList);
        for (int index = 1; index < carExpenseList.size(); index++) {
            if(carExpenseList.get(iHighRevenueCarIndex)>=carExpenseList.get(index))
                iHighRevenueCarIndex=index;
        }
        return iHighRevenueCarIndex;
    }
}
