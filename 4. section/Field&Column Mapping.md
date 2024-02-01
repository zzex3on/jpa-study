# 필드와 컬럼 매핑
- ```@Id``` : PK 지정
- ```@Column``` : DB에서 사용하는 컬럼명을 필드와 매핑 ✨
  - ```insertable```, ```updatable``` : 컬럼을 수정했을 경우 등록 및 변경 여부 (기본값 = ```TRUE```)
  - ```nullable``` : NOT NULL 제약 조건이 걸림 ✨
  - ```unique``` : UNIQUE 제약 조건이 걸림 (UNIQUE 이름이 이상하게 생성 돼서 잘 쓰진 않음)
    - ```@Talbe``` 어노테이션을 걸어줄 때 ```uniqueConstraints```로 이름을 지정해서 사용하는 것을 선호함
  - ```columnDefinition``` : 해당 컬럼의 정보를 직접 넣어줄 수 있음 (이어서 들어감)
  - ```percision```, ```scale``` : BigDecimal 타입에서 사용함, 아주 큰 숫자 or 소수점 쓸 때 사용함 
- ```@Enumerated``` : java에서의 enum 타입을 사용   
  ex. ```@Enumerated(EnumType.STRING)```
  - __주의사항__ : 기본 타입은 ```EnumType.Ordinal``` 이지만 __순서__ 를 저장함, 따라서 ```EnumType.String```을 사용하는 것이 맞음 
- ```@Temporal``` : java에서의 Date 타입을 사용    
  ex. ```@Enumerated(EnumType.STRING)```
  - ```TemporalType.DATE``` : 날짜
  - ```TemporalType.TIME``` : 시간
  - ```TemporalType.TIMESTAMP``` : 날짜 + 시간
  - __참고사항__ : ```LocalDate``` -> DATE, ```LocalDateTime``` -> TIMESTAMP 로 인식하여 생략 가능함! 
- ```@Lob``` : CLOB, BLOB 등처럼 DB에 VARCHAR를 넘어 크기가 큰 데이터를 넣고 싶을 경우 사용
  - ```CLOB``` : 매핑하는 필드 타입이 문자일 경우 ```(String, char[], java.sql.CLOB)```
  - ```BLOB``` : CLOB으로 매핑되는 경우의 나머지일 경우  ```(byte[], java.sql.BLOB)```
- ```@Transient``` : DB에 있는 필드와 매핑하고 싶지 않을 경우 사용 (메모리에 임시로 계산해 둔다거나, 캐시 데이터를 넣어두는 등)
```java
  @Id
  private Long id;

  @Column(name = "name")
  private String username;
  private Integer age;

  @Enumerated(EnumType.STRING)
  private RoleType roleType;

  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModifiedDate;

  @Lob
  private String description;
```
  ![image](https://github.com/zzex3on/jpa-study/assets/87990439/aeb9ee0d-834b-4656-83e5-7dd2053421a0)
