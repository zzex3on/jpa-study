# JPA (Java Persistence API)
- 자바 진영의 ```ORM``` 기술 표준

## ORM (Object-Relational Mapping, 객체 관계 매핑)
- 객체와 관계형 데이터베이스를 매핑해준다는 의미
- 객체는 객체대로 설계, 관계형 데이터베이스는 그것대로 설계하고 ORM이 중간 다리 역할로 매핑을 함
- .NET 등 대중적인 언어에 존재함

## 동작 방식
JAVA app | __```JPA```__ | JDBC API | DB

### 중간에서 동작하는 JPA의 역할
- entity에 대한 SELECT SQL 생성
- JDBC API 사용
- ResultSet 매핑
- __패러다임 불일치 해결__

## JPA 2.1 표준 명세를 구현한 구현체
1. Hibernate (✨)
2. EclipseLink
3. DataNucleus

- - -
## JPA를 사용해야 하는 이유
- SQL 중심적인 개발에서 객체 중심으로 개발하는 방향
  - 자바 컬렉션에 저장하는 것처럼 던져주면 됨
- 생산성
  - 저장 : ```jpa.persist(member)```
  - 조회 : ```Member member = jpa.find(memberId)```
  - 수정 : ```member.setName("변경할 이름")```
  - 삭제 : ```jpa.remove(member)```
- 유지보수
  - 기존에는 컬럼 추가 시 관련된 모든 SQL을 확인 및 수정해야 했음
  - JPA가 SQL을 처리해주기 때문에 개발자의 영역을 덜어줌!
- 패러다임의 불일치 해결
  - JPA와 상속
    - 삽입, 조회 등에 대해 중간 과정을 모두 처리함
  - JPA와 연관관계, 객체 그래프 탐색
  - JPA와 비교하기
    - __동일한 트랜잭션__ 에서 조회한 entity는 같음을 보장
- 성능 최적화 기능
  - 1차 캐시와 동일성 보장
    - __동일한 트랜잭션__ 에서는 동일한 entity를 반환 -> 약간의 조회 성능이 향상됨!
  - 트랜잭션을 지원하는 쓰기 지연
    - 트랜잭션을 commit할 때까지 INSERT SQL을 모아둠
    - JDBC BATCH SQL 기능을 사용해서 한 번에 SQL 전송
    - 데이터 통신비용을 절감할 수 있음
    - Buffer Writing
  - 지연 로딩과 즉시 로딩
    - 보통 지연 로딩을 기본적으로 세팅하고, 성능 최적화를 시킬 때 즉시 로딩을 부분부분 적용시킴
