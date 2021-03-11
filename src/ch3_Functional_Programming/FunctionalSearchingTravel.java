package ch3_Functional_Programming;

import java.util.ArrayList;
import java.util.List;

public class FunctionalSearchingTravel {
    public static final String VIETNAM = "vietnam";
    public static final String PHILLIPHINE = "philliphine";
    public static final String TAILAND = "tailand";
    private List<TravelInfo> travelInfoList=new ArrayList<>();

    public FunctionalSearchingTravel() {
        init();
    }
    private void init()
    {
        TravelInfo cebu = new TravelInfo("Cebu_Travel", PHILLIPHINE, "cebu", 5, 3);
        travelInfoList.add(cebu);
        TravelInfo boracay = new TravelInfo("boracay_Travel", PHILLIPHINE, "boracay", 5, 3);
        travelInfoList.add(boracay);
        TravelInfo hanoi = new TravelInfo("hanoi_Travel", VIETNAM, "hanoi", 3 ,2);
        travelInfoList.add(hanoi);
        TravelInfo danang = new TravelInfo("danang_Travel", VIETNAM, "danang", 6, 4);
        travelInfoList.add(danang);
        TravelInfo bankok = new TravelInfo("bankok_Travel", TAILAND, "bankok", 5, 3);
        travelInfoList.add(bankok);
    }
    public static boolean isVeetnam(TravelInfo travelInfo)
    {
        return travelInfo.getCountry().equals("vietnam");
    }
    public List<TravelInfo> searchTravelInfo(TravelInfoFilter searchCondition)
    {
        List<TravelInfo> returnVal=new ArrayList<>();
        for (TravelInfo travelInfo : travelInfoList) {
            if(searchCondition.isMatched(travelInfo))
            {
                returnVal.add(travelInfo);
            }
        }
        return returnVal;
    }

    public static void main(String[] args) {
        FunctionalSearchingTravel travel=new FunctionalSearchingTravel();
        List<TravelInfo> searchTravel=travel.searchTravelInfo(FunctionalSearchingTravel::isVeetnam);
        for (TravelInfo travelInfo : searchTravel) {
            System.out.println(travelInfo);
        }
    }
}
