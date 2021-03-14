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
    
### 실습
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
>   한번 사용하고나면 재사용 할 수 없다.<br>
>   void 리턴 하는 메서드를 호출하면 전체 스트림 데이터를 처리하기 때문에 데이터를 모두 소모하고 종료된다.
> 

스트림은 데이터 원천을 참조하는 형태이기 때문에 총 건수를 계산 위해 전부 읽어들인 후에 결과를 리턴한다.
결국 count 메서드는 스트림이 끝까지 도달하고 자동으로 종료되기 때문이다.(이미 데이터가 흘러 지나갔기 때문)
최종연산자는 모든 데이터를 소모하게 만드는 것이다.


