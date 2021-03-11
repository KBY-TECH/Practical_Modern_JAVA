# chapter3_Functional Programming
--- ---
## Learning Objectives
> - 함수형 프로그래밍이 필요한 이유
> - 인터페이스로 분리 후 람다 표현식을 사용하여 코드 중복 제거.
> - 함수를 구현할 코드를 전달하는 메서드 참조 기능에 대해 알아본다.`

###intro
자바 8에서 함수형 프로그래밍을 도입함으로써 새로운 패러다임으로의 변화를 시도한 것이 였다.
단순히 함수형 프로그래밍의 도입만이 아닌 많은 API 애플리케이션에 영향을 미쳤다.
이러한 점에서 자바 8이 가장 혁신적인이였다고 불리우는 이유중 하나이다.
<br>
<br>

### 요구사항
`여행 상품을 조회하는 애플리케이션으로 고객이 원하는 상품을 조회할 수 있도록 한다.
처음 여행사 개발 의뢰자들은 국가별로 여행 상품을 분류하고 조회하도록 요구하였다.`

[v] 판매중인 여행 상품 모두를 List에 저장하여 조회할 수 있도록 구현한다.

```javascript
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
```

<br>
<br>

### 요구사항의 변경. 
- `의뢰자는 또한 도시 기반으로도 조회 가능하도록 바꾸려고 한다.`
- `국가 그리고 도시에 대한 검색 조건 추가.`
```javascript
 // 국가 정보 기반 검색.
    public List<TravelInfo> searchTravelInfoByCountry(String country){
        ...
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
```
**-  조회 조건이 늘어날 때마다 메소드를 추가하게 하여 코드가 길어지고 반복적인 코드는 점점 많아진다.** </br>
**- 결국 비즈니스 변경에 따라 API가 자주 바뀌게 되어 이 클래스를 사용하는 다른 클래스에게 큰 영향을 주게 된다.**
<br>
<br>
### 검색방법의 번경
향후 유연하게 요청에 대응하도록 검색 메서드를 인터페이스로 노출하고, 실제 실행 결과는 별도로 분리하기로 결정.

    인터페이스로 노출시킨다는 의미 : 여행 상품을 관리하는 클래스와 상품을 조회하는  로직의 분리를 의미.
                                   즉, 다양한 요청 조회조건 처리 메서드를 인터페이스로 분리하여 외부에서 정의하겟다 라는 것.
<br>
<br>

**인터페이스 생성**
`` 자바 8에서는 인터페이스에 하나의 메서드만 정의한 것을 함수형 인터페이스라고 부른다.``
```javascript
public interface TravelInfoFilter  {
    // 인터페이스에 하나만 존재 함수형 인터페이스.
    public boolean isMatched(TravelInfo travelInfo);
}

```
<br>
<br>

#### 메소드 변경
```javascript
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
```
**코드 설명 :** 메서드 isMatched 를 호출하여 조회조건이 맞으면 리스트에 집어 넣고 아니면 집어넣지 않는 것이다. 이 메서드만 보고서 어떤 조건을 구현해 놓았는지는 알 수 없다.
<br>
- 위 조회 조건을 외부로 분리시켜 여향 상품 정보 관리에만 집중하도록 구현한 것이다.
- 이것이 인터페이스를 이용해서 분리한 대표적 패턴이다.
- 파라미터가 일반적인 클래스 또는 타입이 있는 데이터가 아닌 인터페이스를 파라미터로 받고 있다.<br>
  
  1. 익명 클래스 사용
```javascript
    List<TravelInfo> searchTravel=new_searchingTravel.searchTravelInfo(new TravelInfoFilter() {
            @Override
            public boolean isMatched(TravelInfo travelInfo) {
                if(travelInfo.getCountry().equals(VIETNAM))
                    return true;
                return false;
            }
        });
```
해당 로직이 외부 인터페이스 또는 클래스내에서 제약 조건을 안에서 구현하는 것이 아닌 외부에서 새롭게 제약조건을 추가하여 만들었다.
나라를 기반으로 한 조회이다.

<p style="color: indianred"> - 이렇게 인터페이스로 이용하여 분리를 해도 검색 조건에 따라 결국 반복적인 코드는 똑같아 진다.
<br>
 - 소스 코드의 가독성이 떨어진다.
<br>
- 또한 익명 클래스도 클래스 이므로 컴파일하면 클래스 파일이 별도로 생성된다. 이는 매우 번거로우며 추후 배포,업데이트 시 계속 따라다닌다.
</p>

<br>

### Lamda 사용.
<p style="color: cornflowerblue">이러한 불편함으로 인하여 람다를 사용하기를 권장한다. 최소한 코드의 중복도가 개선된 것을 확인할 수 있고 가독성 또한 매우 높아졌다.</p>

```javascript
List<TravelInfo> lamdaByCountry=new_searchingTravel.searchTravelInfo(travelInfo -> travelInfo.getCountry().equals(VIETNAM));
List<TravelInfo> lamdaByCity=new_searchingTravel.searchTravelInfo(travelInfo -> travelInfo.getCity().equals("bankok"));
```
<p style="color: indianred">이 부분에서 피할 수 없는 부분은 바로 코드의 재사용의 활용도가 떨어진다는 것이다.
이전 보다는 코드의 중복과 가독성이 완하 되기만 했던 것이다.</p>


                                
### 메소드 참조
```javascript
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
        ...
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
```
<p style="color: cornflowerblue">
이 메서드 참조의 람다식을 이용하여 코드의 재사용성 또한 좋은 경우이다.
</p>

`익명 클래스를 사용한 것 과 동일한 방법이다.   실제 구현할 부분만을 코드로 작성하고 이를 람다 표현식으로 선언 하거나 메서드를 추가 후 메서드 참조를 이용해서 할지 선택해서 사용하면 된다.`
<p style="color: indianred">지비 8이 나오기 전에서는 인터페이스 구현한 클래스를 여러 개를 만들어 사용한다면 클래스 개수가 많아지는 단점이 있엇지만,</p>
<p style="color: cornflowerblue">람다의 메서드 참조로 인해 클래스 개수에 구애받지 않고 경우에 맞춰 추가하기만 하면 되기 때문에 소스코드의 양이 줄어들고 이를 컴파일할 클래스 또한 줄어든다는 장점을 갖는다.</p>

## Learning Objectives
> - 함수형 프로그래밍이 필요한 이유
> > - 코드의 중복 가독성이 떨어지므로 인해서. 람다식을 이용하여 코드의 중복을 줄이고 가독성 또한 얻을 수 있다.
> > - 람다식 표현은 일반적인 방법과 메서드 참조 방식이 있는데 일반적인 람다식 표현은 재사용성이 좋지 못하는 단점을 가진다. 메서드 참조방식은 코드의 재사용성 면에서도 효율적이다.
> - 인터페이스로 분리 후 람다 표현식을 사용하여 코드 중복 제거.
> > 조회 조건을 외부에서 받았지만 여전히 코드의 중복 클래스들의 무수한 생성으로 이어질 수 있다. 람다식 표현으로 clean하게 작성할 수 있다.
> - 함수를 구현할 코드를 전달하는 메서드 참조 기능에 대해 알아본다.`
> > 일반적인 람다 표현식과는 다르게 코드의 재사용성 면에 이점이 존재한다. 클래스의 개수에 구애받지 않고 경우에 맞게 추가하기만 하면 
> > 코드의 양이 줄어들고 컴파일할 클래스들도 줄어든다


