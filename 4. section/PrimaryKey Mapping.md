# 기본 키 매핑
## 기본 키 매핑 어노테이션
- ```@Id``` : 직접 할당하는 방법
- ```@GeneratedValue``` : 자동 생성
  - ```strategy```
    - ```AUTO```(기본값) : 설정된 데이터베이스 방언에 맞게 자동으로 생성 (Oracle이면 시퀀스가 생성되는 등)
    - ```IDENTITY``` : 기본 키 생성을 데이터베이스에 위임함.. (MySQL이면 Auto Increment로 숫자가 들어감)
      - ```AUTO_INCREMENT```는 데이터베이스에 INSERT SQL을 실행한 이후에 ID 값을 알 수 있으나, IDENTITY 전략은 ```em.persist()``` 시점에 즉시 INSERT SQL을 실행하고 DB에서 식별자를 조회함
    - ```SEQUENCE``` : sequence object를 만들어내고 값을 generate
      주의사항) ```String, int, Integer``` X, ```Long``` O => squence object가 10억 이상을 넘어가게 되면 문제가 발생할 수 있음..(?) 처음부터 Long으로 잡는다고 성능상 문제는 거의 없기 때문에 Long으로 지정 (중간에 Long으로 변경하는 것이 더 힘듦)
      - sequence를 따로 관리하고 싶을 때는 ```@SequenceGenrator```를 사용하여 매핑 처리함 (class 상단에 작성)
      - SEQUENCE 전략일 경우, ```em.persist()``` 시점에서 ```call next value for (seq name)```이 실행되며 select 쿼리로 seq를 가져오는 것이 아니라, 부른 next value를 갖고 있음
        실제 commit 시점에 INSERT 쿼리가 날아감
      - ```allocationSize``` : 한 번에 call할 seq의 사이즈를 지정함, 기본값은 50임
        ```persist()```할 때마다 next value를 call하다 보면 네트워크 통신이 잦아지며 성능 상 문제가 생길 수 있으므로 DB상에 미리 size를 저장해두고 실제 메모리는 해당 size가 초과될 때마다 call하는 방식
        __여러 웹 서버가 있어도 동시성 이슈 없이 문제를 해결함__
      ```java
      @Entity
      @SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 1)
      public class Member {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE,
              generator = "MEMBER_SEQ_GENERATOR")
        private Long id;
      ```
    - ```TABLE``` : 키 생성 전용 테이블을 생성, 데이터베이스 sequence를 흉내냄
      - 모든 DB에 적용 가능하나, 최적화가 되어 있지 않기 때문에 성능적으로 떨어짐
      ```java
      @Entity
      @TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
      public class Member {
        @Id
        @GeneratedValue(strategy = GenerationType.TABLE,
              generator = "MEMBER_SEQ_GENERATOR")
        private Long id;
      ```
      ![image](https://github.com/zzex3on/jpa-study/assets/87990439/ea022b67-1a60-4bdf-b7bc-c956a6ec6f85)

## 권장하는 식별자 전략
- __기본 키 제약 조건__ : not null + 유일성 + 변하지 않아야 함 (이 조건을 만족하는 자연키를 찾기 어려움 -> 대체키를 찾자)
- 권장 : __Long형 + 대체키 + 키 생성전략 사용__ (비즈니스를 키로 끌고 오지 않을 것)
