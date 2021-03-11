package ch3_Functional_Programming;

public interface TravelInfoFilter  {
    // 인터페이스에 하나만 존재 함수형 인터페이스.
    public boolean isMatched(TravelInfo travelInfo);
}
