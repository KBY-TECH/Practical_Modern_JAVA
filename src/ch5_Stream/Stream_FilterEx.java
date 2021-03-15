package ch5_Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Stream_FilterEx {
    public static final String VIETNAM = "vietnam";
    public static final String PHILLIPHINE = "philliphine";
    public static final String TAILAND = "tailand";
    public static List<TravelInfo> travelInfos;

    public static void main(String[] args) {
        travelInfos = new ArrayList<>();
        init();
        // 스트림 객체 생성
        Stream<TravelInfo> stream = travelInfos.stream();

        //필터 조건 정의
        stream.filter(new Predicate<TravelInfo>() {
            @Override
            public boolean test(TravelInfo travelInfo) {
                return travelInfo.getCountry().equals(PHILLIPHINE);
            }
        }).forEach(System.out::println);

        Stream<TravelInfo> stream2 = travelInfos.stream(); // 이미 최종연산 까지 들어간 forEach 진행했으므로 다 소모 되어서 재사용이 불가하다. 그래서 스트림 객체 하나 더 생성.
        // Lambda
        stream2.filter((TravelInfo t) ->
                t.getCountry().equals(VIETNAM)
        ).forEach(System.out::println);
    }


    public static void init() {
        TravelInfo cebu = new TravelInfo("Cebu_Travel", PHILLIPHINE, "cebu", 5, 3);
        travelInfos.add(cebu);
        TravelInfo boracay = new TravelInfo("boracay_Travel", PHILLIPHINE, "boracay", 5, 3);
        travelInfos.add(boracay);
        TravelInfo hanoi = new TravelInfo("hanoi_Travel", VIETNAM, "hanoi", 3, 2);
        travelInfos.add(hanoi);
        TravelInfo danang = new TravelInfo("danang_Travel", VIETNAM, "danang", 6, 4);
        travelInfos.add(danang);
        TravelInfo bankok = new TravelInfo("bankok_Travel", TAILAND, "bankok", 5, 3);
        travelInfos.add(bankok);
    }
}
