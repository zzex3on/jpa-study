# SQL 중심적인 개발의 문제점

관계형 DB에 객체를 저장하는 기법 (현재 가장 많이 사용되는 방식)   
   
__단점__
- 반복되는 CRUD 작업
- 컬럼 추가 시 SQL 대량 수정 가능성 있음

## 객체와 RDB의 차이
1. 상속
  - 객체에서의 상속 관계 => RDB에서의 슈퍼타입 서브타입 관계(테이블을 부모, 자식처럼 따로 생성하여 상황에 따라 맞게 사용)
  - 각 테이블에 따른 조인 및 다양한 과정... 상당히 복잡해짐 
2. 연관관계
  - 객체는 __참조__, 테이블은 __외래키__
  - 자바 컬렉션에 저장한다면?
      - 정의된 DAO가 확실한지, __엔터티 신뢰 문제__ 가 발생
      - 상황에 따라 다른 데이터를 불러올 때 정리가 되지 않음 (.getAwithB와 같은 형태)
  ```java
  String memberId = "100";
  Member member1 = memberDAO.getMember(memberId);
  Member member2 = memberDAO.getMember(memberId);

  member1 == member2; // 결과 : false
  ```
  ```java
  // 자바 컬렉션에서 조회한다면? 인스턴스 자체가 같음!
  String memberId = "100";
  Member member1 = list.get(memberId);
  Member member2 = list.get(memberId);

  member1 == member2; // 결과 : true
  ```
3. 데이터 타입
4. 데이터 식별 방법

### 객체를 자바 컬렉션에 저장하듯 DB에 저장하는 방법?
__JPA!__
