# Stream
## Learning Objectives
> - 스트림의 이해 : 자바 8의 스트림 개념, 특징<br>
> - 스트림 기본 사용 방법<br>
> - 스트림 연산 이해<br>
> - 리듀스 연산.<br>

--- ---

## 스트림 IFS 이해
    '작은 하천', '시냇물' 이라는 뜻의 단이어며 데이터의 흐름을 말한다.
    java.io에서의 스트림과는 다르게 컬렉션 프로임워크나 이와 유사 형태의 데이터를 처리할 때 
    도움을 줄 수 있는 자바 8에서 새롭게 제안한 API이다.

**스트림을 이용한 컬렉션 프레임 워크의 가장 큰 특징은 기존 컬렉션 프레임 워크처럼 개발자가 정의한 외부 코드로 for 루프를
실행하는 것이 아닌 스트림 내부에서 개발자가 정의한 코드가 반복적으로 실행 된다는 것을 의미한다.**


## 스트림 IFS 
    IFS로 주를 이루는 이유
    
    실질적인 구현체는 데이터의 원천에 해당하는 컬렉션 프레임워크 기반의 클래스에 위임 하고 있기 때문이다.

    기본IFS : BaseStream<T,S extends BaseTime<T,S>> 
    T : 처리 할 데이터 타입 의미
    S : BaseStream을 구현한 스트림 구현체 의미.

**BaseStream IFS는 스트림 API의 최상위에 있으며, 스트림 객체를 병렬 혹은 순차 방식으로 생성하고 최종적으로 종료하기 위한 명세를 제공.
가장 기본이 되는 IFS지만 실질적으로 사용하지는 않으며 상속한 Stream IFS를 주로 사용한다.**

### StreamIFS 주요 메서드
![캡처](https://user-images.githubusercontent.com/67587446/111072565-7f870400-851e-11eb-9faf-537f3fcfe748.PNG)

**※BaseStream IFS 와 Stream IFS 정의되어 있는 상당수의 메서드들의 리턴 타입이 Stream 과 void 형이다.**

    retrunType : Stream
        데이터를 중간에 변경 혹은 필터링 한 후 다시 Stream을 만들어 결과를 리턴한다.(중간 연산 메서드)
    retrunType : Void
        데이터를 최종적으로 소비(최종 연산 메서드)
    ※중요 개념 : 파이프 라인 개념,중간연산,최종연산

- **스트림은 불변성이 특징을 갖는다.**
    - 데이터를 수정한 것이 아닌 완전히 새롭게 생성한 데이터 이다.;
- **스트림을 불변성이로 만든 이유**
    - => 중간 연산 작업과 병렬 처리가 가능하기 때문에 데이터의 정합성을 확보하기 위함이며, 스트림 뿐만 아니라 자바 8이후의 API들의 공통적인 특성이다.


하위 IFS :  DoubleStream,IntStream, LongStream
    <br>

- 차이점 : 객체를 데이터로 사용하지 않고 기본형 데이터를 사용.
- 사용이유 : 데이터가 자동으로 박싱/언박싱이 되지 않으므로 성능에 악영향을 끼칠 우려가 없고 처리 속도가 빠르다.
  <br>
  <br>
- 기본형 스트림 IFS 데이터 처리 메서드.
  - sum
  - max
  - min
    
## 스트림 객체 생성
```javascript
     Stream<Integer> stream=list.stream();
            // 최종 연산
            System.out.println(stream.count());
            // 중간 연산.
            Stream<Integer> stream2=stream.limit(5);
            stream2.forEach(System.out::println);
```
**<p style =color:red> Error: stream has already been operated upon or closed</p>**
 - Error 를 통한 스트림의 특징
>   - 한번 사용하고나면 재사용 할 수 없다.<br>
>   - void 리턴 하는 메서드를 호출하면 전체 스트림 데이터를 처리하기 때문에 데이터를 모두 소모하고 종료된다.
> 

스트림은 데이터 원천을 참조하는 형태이기 때문에 총 건수를 계산 위해 전부 읽어들인 후에 결과를 리턴한다.
결국 count 메서드는 스트림이 끝까지 도달하고 자동으로 종료되기 때문이다.(이미 데이터가 흘러 지나갔기 때문)
최종연산자는 모든 데이터를 소모하게 만드는 것이다.

### 스트림 빌더
  이전 까지를 데이터의 흐름이라는 측면에서 올라블 처리 방법이라고 생각할 수 있다.**<p style="color:cornflowerblue">하지만 스트림에서는 생성된 데이터 처리에서 
  끝나지 않고 데이터를 직접 생성하기 위한 기능을 제공하는데 이것을 스트림 빌더라고 한다.**

|    return Type    	|    Method   	| description 	|
|:-----------------:	|:-----------:	|:-----------:	|
|        void       	| accept(T t) 	| 스트림 빌더 데이터 추가.|
| Stream.Builder<T> 	|   add(T t)  	| 기존에 추가한 데이터와 현재 추가한 데이터가 포함된 Stream.Builder 객체 리턴.|
|      Stram<T>     	|   build()   	| Stream.Builder객체에 데이터를 추가하는 작업 종료.             	|


```javascript
public static void main(String[] args) {
        // accept
        Stream.Builder<String> builder=Stream.builder();
        for (int i = 0; i < 10; i++) {
            String s=Integer.toString(i+1);
            builder.accept(s);
        }
        builder.build().forEach(System.out::print);
        System.out.println();
        // accept
        Stream.Builder<Integer> builder2=Stream.builder();
        int cnt=1;
            builder2.add(cnt).add(++cnt).add(++cnt);
        builder2.build().forEach(System.out::print);
    }
```
- accept : 일반 리스트 형식의 add() 랑 비슷한 형태
- add : 체인형식.( 새롭게 생성 되는 객체가 아닌 자기 자신.)

**accept 나 add를 사용하는것은 성능이나 메모리 처리 등에 큰 차이가 없다.**

<p style="color: indianred"> 여기에서 주의 할 적음 스트림 빌더 역시 스트림 객체와 동일하게 한 번 
사용하고 나면 재사용이 불가하다. 그러므로 여러번 재활용하거나 데이터를 유지해야하는 경우 사용해서는 안되며
컬렉션 프레임 워크를 사용해야 한다.</p>

## 스트림 연산 이해
    파이프라인이란
    유닉스/리눅스의 명령어를 서로 연결해 주는 역할을 한다.
    파이프라인을 이용해 명령어들의 조합을 통해 강력한 결과를 확인 할 수 있다.
    이렇게 셸 스크립트에서 명령어와 파이프라인의 조합으로 많은 기능을 하는 것을 만들 수 있다.

  **스트림 API를 사용하는 이유는 파이프라인과 유사한 개념으로 기능을 조합할 수 있기 때문이다.**

<br><br>
  ![캡처](https://user-images.githubusercontent.com/67587446/111190525-8125fa00-85fa-11eb-9c07-4d11eb885cf8.PNG)

- 중간연산
  - filter : 데이터 추출
  - soreted : 정렬
  - map : 데이터 변환

- 최종연산  
  - collect : list 객체 리턴.

**단 한줄로 강력하게 추출 정렬 변환 list 객체 리턴을 한번에 할 수 있는 장점이 있다.**

<p style="color: cornflowerblue">각 연산들 위에 IFS 들은 함수형 IFS이다. 스트림의 중간,최종 연산자는 함수형 IFS를 기반으로 하고 있어 개발자가 람다 표현식으로 동작을 정의 할 수 있는 장점이 있다.</p>


## 스트림 객체의 생명주기

1. 스트림 객체 생성
2. 중간 연산
   - 필터링 정렬,변환 단계를 거친다.
3. 최종 연산
    - 데이터를 전부 소모하고 스트림을 종료.
  
## 스트림 연산 상세
- ###데이터 필터링
  - 성능을 높이고 하드웨어의 자원을 효율적으로 사용하기 위해 가장 먼저 해야하는 원천 데이터 중 불 필요한 데이터를 없애고 원하는 데이터로 정제하는 것.
  - 실습(Predicate IFS : boolean 값을 처리한다.)
      - distinct
        - 중복제거
        - 입력 파라미터 없이 메서드만 호출하여 중복값을 제거하고 스트림 객체를 리턴.
        - ※ 성능저하 우려
          - 데이터 중복을 제거하기 위해 여러 스레드에 분산해 놓은 데이터를 동기화해서 비교하기 때문이다.
          - 중복 제거를 위해 distinct 메서드를 쓰고 싶다면 병렬 스트림보다는 순차 스트림을 이용하는 것이 더 빠르다.
          - 전체 데이터를 비교해야 하는 메서드는 내부적으로 버퍼를 많이 사용하기 때문에 메모리 효윻이나 CPU 사용률에 영향을 줄 수 있다.
        - ※ 중복제거가 안 될 수 있다.
          - 스트림 항목의 중복 여부를 확인하기 위해 equals 메서드가 내부적으로 호출된다는 것을 기억해야 한다.
          - Object 의 equals 메서드만으로도 충분히 값을 비교하지만 오버라이드하는 것도 고려해야 한다.
          - 데이터를 충분히 검증 후 사용하는 것이 중요.
      - limit,skip
        - 일정 데이터만 보여주기.
    
<br>

- ###데이터 정렬
    - sort를 통해 Comparator 익명클래스(compare(args 2개)정렬 기준을 외부에서 상황에 따라 변경이 가능하다.)
    - sort를 통해 Comparable IFS 상속 받고(compareTo(args 1개)) 정렬 기준 정하기
    - <p style ="color:red">※ Comparable ,Comparator 차이</p>
        - Comparator,Comparator 은 정렬을 하는 기준을 정하는 기능은 같다. 하지만 Comparable은 정렬 기준이 클래스 내부에서 결정되어 있지만, Compartor는 정렬 기준을 여러가지로 설정할 수 있으며 외부에서 익명클래스 다른 정렬 기준을 그때 그때 바로 바꿀 수 있는 차이가 있다.
 ```javascript
    List<Person> list=Stream_Filter_Distinct_Ex.init();
        System.out.println("람다 외부 조건 Comparator.comparing ");  // A.getName().compareTo(B.getName()) 풀어서 쓰면.
           list.stream().sorted(Comparator.comparing(person -> person.getName())).forEach(System.out::println);

        System.out.println(" 역순 Comparable 기준");
           list.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
```
**람다 표현식이나 메서드 참조를 이용해서 최소화 된 코드로 구현할 수 있다. 이것이 스트림 API를 이용한 데이터 정렬의 장점**
<br>

- ###데이터 매핑 
  - map은 스트림에 포함되어 있는 데이터 항목을 다른 값으로 변환하는 것을 의미한다.
  - map 과 관련한 메서드들은 Function IFS 를 받으며 apply메서드를 제공하여 전달 받은 객체를 다른 타입의 객체로 리턴한다. 

```javascript

public class StreamMap
{
    public static void main(String[] args) {
        // Person 객체를 String 으로 반환
        List<Person> list=Stream_Filter_Distinct_Ex.init();
        Stream<String> stream = list.stream().map((Person p) -> p.toString());
        stream.forEach((String s) -> {
            String str[]=s.split("");
            for (String s1 : str) {
                System.out.print(s1+" ");
            }
        });
        System.out.println("");

        IntStream integerStream = list.stream().mapToInt((Person p) -> p.getAge());
        integerStream.forEach((int i)-> System.out.println(i));
    }
}

```
___ 스트림 중간 절차 마지막___
<br></br>
- ###데이터 반복 처리(소모)
    - 스트림도 결국 컬렉션과 마찬가지로 데이터의 집합이기 때문에 데이터를 반복해서 처리한다.
    - forEach 메서드
    - Consumer IFS를 사용. (리턴 타입 void)
    - Map IFS안에도 forEach 가있다.(스트림 API에 포함되어 있지 않지만 그 특징은 그대로 물려받았다. 그러므로
       람다 표현식을 이용해서 원하는 결과를 처리할 수 있게 된다.)</br></br>
- ###컬렉션 변환
    - 스트림은 한번 사용하면 재사용이 불가하므로 다른 곳에다가 저장을 해서 나중에 꺼내서 써야한다. 그래서 컬렉션 변수를 선언 후 그 변수에 담아 처리를 하는 것이 대부분이다.
    - 컬렉션으로 변환하기 위한 Suplier , Consumer IFS에서 2가지 메서드를 제공한다.
    - collect(Collector<? super T, A, R> IFS(최종 연산의 타입 T,A,R)
        - T : 리듀스 연산의 입력 항목으로 사용하는 데이터 타입.
        - A : 리듀스 연산의 변경 가능한 누적값으로 사용하는 데이터 타입.
        - R : 리듀스 연산의 최종 결과 데이터 타입.
    - 후속 작업을 할 때 유용하다.
    - Collectors.toList()를 살펴보면 ArrayList를 이용해서 생성된다. 
        - 만약 List IFS구현 클래스를 선택하려면 Collectors.toCollection(LinkedList::new)를 이용하면 된다.
        - Map으로 변환이 가능하고 Set으로도 가능하다.
    - 추가적으로 joining,groupingBy(첫번째는 기준데이터,누적 사용 데이터),summingInt,partitioningBy ... 등이 있다.
    - + Arrays ,Collection 둘다 stream API를 지원한다.
        - Arrays 주요깊은 메서드들
            - stream
            - parallelSort : 스트림 기능 이용 구현.
            - parallelPrefix : 데이터에 특정 값 더한다. 내부 기능은 스트림 이용하여 구현했다.
            -  spliteratior : 병렬처리가 가능한 Iterator 객체 반환.
            - 래거시한 코드들로부터 스트림 기능을 이용하여 데이터를 처리할 수 있다.
            - 자바 8 이후 스트림 객체 생성을 직접 of 라는 메서드를 통해 생성할 수 있다.
<br>
              
## 추가 스트림 연산들
- ###데이터 평면화 
    - 상당히 많은 데이터나 또는 그 안에 맵형식의 데이터에서 값이 배열이거나 등등 다차원의 데이터가 있는 환경에서
        필요한 기능.
    - 평면화가 필요하는 경우
        - 다중 배열 형태의 데이터 필터링
        - 검색하거나 특정 조건의 작업을 수행해서 데이터를 처리해야 할 경우,
- ###검색
    - 필터링 연산과 비슷하지만 전혀 다르다.
    - 일반적인 필터링 연산은 고정된 유형으로 데이터의 참과 거짓을 판별해서 원하는 데이터 집합을 생성.
    - 데이터를 검색한다는 의미는 특정한 패턴에 맞는 데이터를 조회하는 것이다.
    - 하나의 패턴이 아닌 여러 개의 패턴의 조합을 통해 자기가 원하는 데이터를 정확히 검색할 수 있다.
    - Mathch 계열의 메소드들이 있는데, 이 기능들은 특정한 조건에 맞는 것을 찾을 때 바로 종료 한다. 물론 이러한 조건에 해당하지 않을 경우 전체 데이터를 비교한다.
      - allMatch() 모든 요소들이 매개값(Predicate)으로 주어진 조건을 만족하는지 조사
      - anyMatch() 최소한 한 개의 요소가 주어진 조건에 만족하는지 조사
      - noneMatch() 모든 요소들이 주어진 조건을 만족하지 않는지 조사
    - 자바의 비교 연산 && , ||에도 그대로 적용할 수 있는 규칙으로 쇼트 서킷이라고 부르며, 불필요한 연산을 죄소화해서 비교 작업을 빠르게 수행할 수 있다.
      <br><br>
    - find 계열
        - Optional 을 사용해서 값이 없을 경우 NullPointerException 을 사전에 방지할 수 있다.
        - 병렬처리시 findAny 메서드를 이용해서 값을 가져오기 때문에 실행할 때마다 값이 달라질 가능성도 있다.
        - 특히 스트림에 포함되어 있는 데이터ㅏ 많으면 변경될 가능성이 있고 스트림에서는 순서가 보장되지 않을 수 있다.
        - 그러므로 원천 데이터가 순서에 따라 정렬되어 있다는 확신이 있을 때 사용한다.
```javascript
public class StreamSearch {
    public static void main(String[] args) {
        List<Integer> list= Arrays.asList(1_000_000_000,3,5,7,11);

        // allMatch
        boolean ans1=list.stream().allMatch(integer -> (integer<10));
        System.out.println("모든 원소가 10보다 작은 가요?"+ans1);
        // anyMatch
        boolean ans2=list.stream().anyMatch(integer -> integer%3==0);
        System.out.println("3의 배수가 하나라도 존재하나요?"+ans2);

        // noneMatch
        boolean ans3=list.stream().noneMatch(integer -> integer==1);
        System.out.println("모든 요소들이 1인가요?"+ans3);

        Optional<Integer> res=list.stream().parallel().filter(integer -> integer>4).findAny();
        Optional<Integer> res2=list.stream().parallel().findFirst();
        System.out.println("4보다 큰 값"+res.get());
        System.out.println("첫번째 값 "+res2.get());
    }
}
```

## 리듀스 연산
    스트림에서 연산은 중간연산, 최종으로 구분을 한다.
    forEach가 대표적인 최종연산이다.
    데이터를 모두 소모한 후에 그 결과를 알수 있는 연산은 count,max,min,sum 이 있다.

<p style="color: cornflowerblue">
데이터를 모두 확인해서 결과값을 도출하는 최종연산을 자바의 스트림 API에서는 리듀스 연산이라고 한다.
</p>

 > 결국 스트림 API를 활용해서 하는 이유는 ?

__데이터가 많아질떄 속도가 느려지며, 이를 개선하기 위해 병렬처리까지 하려면 굉장한 노력을 들여 테스트 작업을 수행해야 한다.
스트림 API를 이용하면 for문 작성 뿐 아니라 병렬처리에도 안전하다.__

리듀스 연산을 배우기 전에 IntStream, collect 연산을 이용해 구현 할 수 있다.

```javascript
public class Stream_ReduceOperation {
    public static void main(String[] args) {
        
        //=================리듀스 연산을 배우기 전=============================
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            list.add(i+1);
        }
        // 트릭 :  어떻게 배열은 컴파일이 허용될까?
        // 한번 배열이 생성되고 나면 배열의 크기를 변경할 수 없기 때문에 언어적으로는 final이므로 참조가 가능하다. sum자체는 final이고 sum[0]은 final이 아니기 때문에 정상적인 컴파일과 실행이 된다.

        int sum[]={0};
        // 람다 로컬 변수는 final이거나 final과 유사한 형태여야 한다.
        list.stream().forEach(integer -> sum[0]+=integer);
        System.out.println(sum[0]);

        // intStream
        System.out.println(list.stream().mapToInt(value -> value).sum());
        // collect 연산
        System.out.println(list.stream().collect(Collectors.summingInt(value -> value)));
    }
}

```
    
#### Reduce 메서드의 명세서
     reduce(T identity,BinaryOperatior<T> accumlator)
- identity는 초깃값을 의미하고
- BinaryOperator로 두 개의 인수를 받아서 하나의 값으로 리턴하는 함수형 인터페이스이다. 함수형 IFS 이므로 람다표현식 참조가 가능핟.

__reduce 메서드를 이용하면 스트림의 병렬 처리 기법을 활용 할 수 있고 특히 처리해야 하는 데이터가 많을 때 병렬 스트림으로 리듀스 연산을 하면 성능을 크게 높일 수 있다.__

> reduce 원소의 합을 구하는 연산에서의 흐름<br>
> {1,2,3,4,5}<br>
> 1+2=3<br>
3+3=6<br>
6+4=10<br>
10+5=15<br>

<p style="color:cornflowerblue;">BinaryOperator 가 중요한 부분이다.</p> 
<p style="color:cornflowerblue;">두번째 파라미터 값을 리턴받아 다음 값이랑 비교 또는 덧샘을 시도한다.</p> 

```javascript

public class Stream_ReduceOperation {
    public static void main(String[] args) {
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            list.add(i+1);
        }

        int sum=list.stream().reduce(0, Integer::sum);
        //Integer::sum
        int sum1 = Integer.sum(10, 100);
        System.out.println(sum1);
        System.out.println(sum);


        int parallelSum=list.parallelStream().reduce(0,(integer, integer2) -> integer+integer2);
        System.out.println(parallelSum);
    }
}
```        


