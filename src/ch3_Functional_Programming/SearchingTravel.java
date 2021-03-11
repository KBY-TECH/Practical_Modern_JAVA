package ch3_Functional_Programming;

import java.util.ArrayList;
import java.util.List;

public class SearchingTravel {
    public static final String VIETNAM = "vietnam";
    public static final String PHILLIPHINE = "philliphine";
    public static final String TAILAND = "tailand";

    private List<TravelInfo> travelInfoList = new ArrayList<>();

    public SearchingTravel() {
        init();
    }

    private void init() {
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
    // 국가 정보 기반 검색.
    public List<TravelInfo> searchTravelInfoByCountry(String country)
    {
        List<TravelInfo> returnVal = new ArrayList<>();
        for (TravelInfo travelInfo : travelInfoList) {
            if (country.equals(travelInfo.getCountry())) {
                returnVal.add(travelInfo);
            }
        }
        return returnVal;
    }
    // 도시 정보 기반 검색.
    public List<TravelInfo> searchTravelInfoByCity(String city)
    {
        List<TravelInfo> returnVal = new ArrayList<>();
        for (TravelInfo travelInfo : travelInfoList) {
            if (city.equals(travelInfo.getCity())) {
                returnVal.add(travelInfo);
            }
        }
        return returnVal;
    }
    // 국가,도시 정보 기반 검색 AND
    public List<TravelInfo> searchTravelInfoByCityAndCountry(String country,String city)
    {
        List<TravelInfo> returnVal = new ArrayList<>();
        for (TravelInfo travelInfo : travelInfoList) {
            if (city.equals(travelInfo.getCountry()) && country.equals(travelInfo.getCity())) {
                returnVal.add(travelInfo);
            }
        }
        return returnVal;
    }

    public static void main(String[] args) {
        SearchingTravel searchingTravel = new SearchingTravel();
        List<TravelInfo> searchList = searchingTravel.searchTravelInfoByCountry(searchingTravel.PHILLIPHINE);
        for (TravelInfo searchTravel : searchList) {
            System.out.println(searchTravel.toString());
        }
    }

}
