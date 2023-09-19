package com.test.exam02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExam01 {
    public static void main(String[] args) {
        /*
         ------stream API 정의 및 등장 배경-------
         - 자바에서는 많은 양의 데이터를 저장하기 위해 배열이나 컬렉션을 사용
         - 이렇게 저장된 데이터에 접근하기 위해서는 반복문이나 반복자(itorator)을 사용하여
         - 매번 새로운 코드를 작성해야함.
         - 이렇게 작성된 코드는 길이도 길고 가독성이 떨어짐. 즉 코드의 재사용성이 안됨.
         - 데이터베이스의 쿼리와 같이 정형화된 처리 패턴을 가지지 못했기에 데이터마다 다른 항목으로 접근해야만 함.
         - 이런 문제를 극복하기 위해 Java SE8부터 함수형 프로그램의 특성을 가진 Stream API를 도입
         - Stream API는 데이터를 추상화하여 다루므로, 다양한 방식으로 저장된 데이터를 읽고 쓰기위한 공통된방법
         - 따라서 Stream AIP를 이용하면 배열이나 컬렉션 뿐만 아니라 파일에 저장된 데이터도 모두 같은 방법으로 처리가 가능함.
        -------Stream API 특성----------
        1. Stream은 외부 반복을 통해 작업하는 컬렉션과는 달리 내부반복(Internal iteration)을 통해 작업을 수행.
        2. Stream은 재사용이 가능한 컬렉션과는 달리 단 한번만 사용.
        3. Strem은 원본데이터를 변경하지 않음.
        4. Stream의 연산은 필터-맵(filter-map)기반의 API를 사용하여 "지연(lazy)연산"을 통해 성능을 최적화 
        5. Stream은 paralleStream() 메소드를 통한 손쉬운 "병렬 처리"를 지원.

        ----------Stream API 동작 흐름------------
        데이터 소스 -> (스트림 타입으로 변환) -> 스트림을 생성 -> 중개연산(필터) -> 중개연산(맵) -> 최종연산(출력)
         */

        // **Stream API 예제
        // 컬렉션은 stream메소드를 내장하고있음
        System.out.println("<--------1부터 10까지의 정수를 갖는 List에서 6보다 작고, 짝수인 요소를 찾아 10배 시키고, 이를 리스트로 출력 예제-------->");
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);//초기화 방법 // map값변경, collectors tolist 리스트형태로 변환
        list.stream().filter(i->i<6).filter(i->i%2==0).map(i->i*10)
        .collect(Collectors.toList()).forEach(result->System.out.println(result+" ")); //스트림생성,필터,맵,출력

        // 스트림 생성
        /*
         자바에서 제공하는 모든 컬렉션의 최고 상위 객체인 Collect 인터페이스에는 stream()메소드가 정의 되어있음.
         따라서 Collection 인터페이스를 구현한 모든 List와 Set 컬렉션 객체에서도 stream() 메소드로 스트림을 생성할 수 있음.
         또한 기본타입인  int, long, double형을 저장할 수 있는 배열에 관한 스트림이 별도로 정의되어 있음.
         이러한 스트림은 java.util.stream 패키지내에 IntStream, LongStream, DoubleStream 인터페이스로 각각 제공됨. 
         */
        ArrayList<Integer> ArrayList = new ArrayList<>();
        ArrayList.add(4);
        ArrayList.add(2);
        ArrayList.add(3);
        System.out.println("<-------컬렉션 객체로부터 스트림 생성 예제-------->");
        Stream<Integer> stream = ArrayList.stream(); //List 컬렉션에서 스트림을 생성
        stream.forEach(System.out::println); //**forEach()메소드를 이용한 스트림 요소에 대한 순차적 접근 및 출력

        System.out.println("<-------배열로부터 스트림 생성 예제-------->");
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        IntStream stream1 = Arrays.stream(arr);
        stream1.forEach(System.out::println);

        System.out.println("<-------가변 매게 변수로부터 스트림 생성 예제-------->");
        Stream<Double> stream2 = Stream.of(4.2, 2.5, 3.1, 1.0);
        stream2.forEach(System.out::println);

        // 스트림 중개 연산
        /*
         - Stream API의 의해 생성된 초기 스트림은 중개연산을 통해 또 다른 스트림으로 변환
         - 이러한 중개 연산은 스트림을 전달 받아 스트림을 반환하므로, 중개 연산은 연속으로 연결해서 사용 가능하다. 
         - 스트림의 중개 연산은 **필터-맵(filter-map)기반의 API를 사용함으로서 지연(lazy)연산을 통해 성능을 최적화함.

         1. 스프팀 필터링: filter(), distinct()
         2. 스트림 변환: map(), flatMap()-배열데이터 출력에 사용
         3. 스트림 제한: limit(), skip()
         4. 스트림 정렬: sorted()
         5. 스트림 연산결과를 확인: peek()
         */
        System.out.println("<-------스트림 필터링: 스트림에서 반복된 요소를 제거하고 홀수만을 골라내어 출력-------->");
         IntStream stream3 = IntStream.of(7,5,5,2,1,2,3,5,4,6); //데이터 소스+스트림 생성
         stream3.distinct().filter(i->i%2!=0).forEach(System.out::println);

        System.out.println("<-------스트림 변환: 문자열로 이루어진 스트림을 map 메소드를 이용하여 각 문자열의 길이로 이루어진 스트림으로 반환-------->");
        Stream<String> stream4 = Stream.of("HTML","CSS","JAVA","JavaScript"); //데이터소스+스트림 생성
        stream4.map(s->s.length()).forEach(System.out::println);

        System.out.println("<-------스트림 변환: 여러 문자열이 저장된 배열을 각 문자열에 포함된 단어로 이루어진 스트림으로 변환 -------->");
        String[] arr1 = {"I study hard", "You study JAVA", "I an a boy"};
        Stream<String> stream5 = Arrays.stream(arr1); // 스트림생성
        stream5.flatMap(s->Stream.of(s.split(" "))).forEach(System.out::println);

        
        System.out.println("<-------스트림 제한 -------->");
        //limit() 메소드는 해당 스트림의 첫 번째 요소로부터 전달된 개수만큼의 요소만으로 이루어진 새로운 스트림을 반환
        //skip() 메소드는 해당 스크림의 첫 번째 요소로부터 전달된 개수만큼의 요소를 제외한 나머지 요소만으로 이루어진 새로운 스트림을 반환
        IntStream stream6 = IntStream.range(0, 10);
        IntStream stream7 = IntStream.range(0, 10);
        IntStream stream8 = IntStream.range(0, 10);

        stream6.skip(4).forEach(n-> System.out.print(n+" "));
        System.out.println();

        stream7.limit(5).forEach(n-> System.out.print(n+" "));
        System.out.println();

        stream8.skip(3).forEach(n-> System.out.print(n+" "));
        System.out.println();

        System.out.println("<-------스트림 정렬 -------->");
        //오름차순
        Stream<String> stream9 = Stream.of("Park","Kim","Lee","Choi");
        stream9.sorted().forEach(s->System.out.print(s+" "));
        System.out.println();
        //내림차순
        Stream<String> stream10 = Stream.of("Park","Kim","Lee","Choi");
        stream10.sorted(Comparator.reverseOrder()).forEach(s->System.out.print(s+" "));
        System.out.println();

        //스트림 최종 연산
        /*
         1. 요소의 출력: forEach()
         2. 요소의 소모: reduce() --> reduce()메소드는 첫번째와 두번째 요소를 가지고 연산을 수행한 뒤, 그결과와 세번째 요소를 가지고 또 다시 연산을 수행, 
                                     이런식의 해당 스트림의 모든 요소를 소모하여 연산을 수행하고 그 결과를 반환 
         3. 요소의 검색: findFirst(), FindAny()
         4. 요소의 검사: anyMatch(), allMatch(), noneMatch()
         5. 요소의 통계: count(), min(), max()
         6. 요소의 연산: sum(), average()
         7. 요소의 수집: collect() --> stream의 요소들을 컬렉션 자료형으로 변환시키고, 각 요소들의 값을 필요시 joining, sorting하여 리턴 
         */

        System.out.println("<-------reduce 메소드를 이용하여 1부터 100까지 합 구하기 -------->");
        Stream<Integer> numbers1 = Stream.of(1,2,3,4,5,6,7,8,9,10);
        Stream<Integer> numbers2 = Stream.of(1,2,3,4,5,6,7,8,9,10);
        // 초기값이 없는 형태. numbers1은 reduce메소드를 통해 작동이 완료되면 소명된다. so,재사용 불가
        Optional<Integer> sum1 = numbers1.reduce((x,y)->x+y); //초기값이 없는 형태
        Optional<Integer> sum2 = Optional.ofNullable(numbers2.reduce(10, (x,y)->x+y)); //초기값이 있는 형태
        sum1.ifPresent(s->System.out.println("sum :"+s));
        sum2.ifPresent(s->System.out.println("sum :"+s));         

        System.out.println("<-------collect메소드를 이용하여 stream요소들을 합친 후 분리자 , 으로 구분하기  -------->");
        //collect collectors와 세트처럼 사용됨. map 메소드는 변화있을떄 사용
        Stream<String> fruits = Stream.of("banana", "apple","mango","kiwi","peach","cherry","lemon");
        String result = fruits.map(Object::toString).collect(Collectors.joining(","));
        System.out.println(result);
    }
}
