package ch3_Functional_Programming;

import java.util.ArrayList;
import java.util.List;

public class new_SearchingTravel {
    public static final String VIETNAM = "vietnam";
    public static final String PHILLIPHINE = "philliphine";
    public static final String TAILAND = "tailand";
    private List<TravelInfo> travelInfoList=new ArrayList<>();

    public new_SearchingTravel() {
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
        new_SearchingTravel new_searchingTravel = new new_SearchingTravel();
        // 익명 클래스 사용.
        // 국가 정보 기반 찾기
        List<TravelInfo> searchTravel=new_searchingTravel.searchTravelInfo(new TravelInfoFilter() {
            @Override
            public boolean isMatched(TravelInfo travelInfo) {
                if(travelInfo.getCountry().equals(VIETNAM))
                    return true;
                return false;
            }
        });
        for (TravelInfo travelInfo : searchTravel) {
            System.out.println(travelInfo.toString());
        }
        searchTravel.clear();

        List<TravelInfo> searchTrave2l=new_searchingTravel.searchTravelInfo(new TravelInfoFilter() {
            @Override
            public boolean isMatched(TravelInfo travelInfo) {
                if(travelInfo.getCity().equals("hanoi"))
                    return true;
                return false;
            }
        });

        for (TravelInfo travelInfo : searchTrave2l) {
            System.out.println(travelInfo.toString());
        }
        searchTrave2l.clear();

        System.out.println("============lamda using============");

        //=================================== lamda
        List<TravelInfo> lamdaByCountry=new_searchingTravel.searchTravelInfo(travelInfo -> travelInfo.getCountry().equals(VIETNAM));
        lamdaByCountry.forEach(travelInfo -> System.out.println(travelInfo.toString()));
        System.out.println();
        List<TravelInfo> lamdaByCity=new_searchingTravel.searchTravelInfo(travelInfo -> travelInfo.getCity().equals("bankok"));
        lamdaByCity.forEach(travelInfo -> System.out.println(travelInfo.toString()));

    }
}
