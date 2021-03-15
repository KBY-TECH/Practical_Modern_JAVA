# Stream
## Learning Objectives
> - 스트림의 이해 : 자바 8의 스트림 개념, 특징<br>
> - 스트림 기본 사용 방법<br>
> - 스트림 연산 이해<br>
> - 리듀스 연산.<br>



### 스트림 IFS 이해
    '작은 하천', '시냇물' 이라는 뜻의 단이어며 데이터의 흐름을 말한다.
    java.io에서의 스트림과는 다르게 컬렉션 프로임워크나 이와 유사 형태의 데이터를 처리할 때 
    도움을 줄 수 있는 자바 8에서 새롭게 제안한 API이다.

**스트림을 이용한 컬렉션 프레임 워크의 가장 큰 특징은 기존 컬렉션 프레임 워크처럼 개발자가 정의한 외부 코드로 for 루프를
실행하는 것이 아닌 스트림 내부에서 개발자가 정의한 코드가 반복적으로 실행 된다는 것을 의미한다.**


### 스트림 IFS 
    IFS로 주를 이루는 이유
    
    실질적인 구현체는 데이터의 원천에 해당하는 컬렉션 프레임워크 기반의 클래스에 위임 하고 있기 때문이다.

    기본IFS : BaseStream<T,S extends BaseTime<T,S>> 
    T : 처리 할 데이터 타입 의미
    S : BaseStream을 구현한 스트림 구현체 의미.

**BaseStream IFS는 스트림 API의 최상위에 있으며, 스트림 객체를 병렬 혹은 순차 방식으로 생성하고 최종적으로 종료하기 위한 명세를 제공.
가장 기본이 되는 IFS지만 실질적으로 사용하지는 않으며 상속한 Stream IFS를 주로 사용한다.**

#### StreamIFS 주요 메서드
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
    
### 스트림 객체 생성
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

#### 스트림 빌더
  이전 까지를 데이터의 흐름이라는 측면에서 올라블 처리 방법이라고 생각할 수 있다.**<p style="color:cornflowerblue">하지만 스트림에서는 생성된 데이터 처리에서 
  끝나지 않고 데이터를 직접 생성하기 위한 기능을 제공하는데 이것을 스트림 빌더라고 한다.**

|    return Type    	|    Method   	| description 	|
|:-----------------:	|:-----------:	|:-----------:	|
|        void       	| accept(T t) 	| 스트림 빌더 데이터 추가.|
| Stream.Builder<T> 	|   add(T t)  	| 기존에 추가한 데이터와 현재 추가한 데이터가 포함된 Stream.Builder 객체 리턴.|
|      Stram<T>     	|   build()   	| Stream.Builder객체에 데이터를 추가하는 작업 종료.             	|


  #### 실습
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

### 스트림 연산 이해
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


#### 스트림 객체의 생명주기

1. 스트림 객체 생성
2. 중간 연산
   - 필터링 정렬,변환 단계를 거친다.
3. 최종 연산
    - 데이터를 전부 소모하고 스트림을 종료.
  
#### 스트림 연산 상세
- 데이터 필터링
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

- 데이터 정렬
    - sort를 통해 익명클래스
    - sort를 통해 Comparable IFS 상속 받고 정렬 기준 정하기
<br>
- 데이터 매핑
<br>
- 데이터 반복 처리(소모)
<br>
- 컬렉션 변환
<br>