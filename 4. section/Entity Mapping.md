# 객체와 테이블 매핑
## 엔티티 매핑
- 객체와 테이블 매핑
  - ```@Entity```
    - JPA가 관리하는 엔티티 (@가 붙지 않으면 JPA에서 관리하지 않는, 마음대로 쓰는 클래스라고 보면 됨)
    - __기본 생성자 필수__ (```public``` 또는 ```protected```)
    - ```final```, ```enum```, ```interface```, ```inner``` 클래스에 매핑 불가
    - DB에 저장하고 싶은 필드에는 ```final``` 사용 금지
  - ```@Table``` : 실제 쿼리에 들어갈 테이블명을 기입(name 속성으로 구분)
  ```java
  @Entity
  @Table(name="MBR")
  public class Member {
    ...
  ```
- 필드와 컬럼 매핑 : ```@Column```
- 기본 키 매핑 : ```@Id```
- 연관관계 매핑 : ```@ManyToOne```, ```@JoinColumn```

## 데이터베이스 스키마 자동 생성
- 애플리케이션 실행 시점에 데이터베이스에 맞는 적절한 DDL 자동 생성
- 생성된 DDL은 개발 서버에서만 사용!
- 운영에서는 사용하지 않거나 적절히 다듬은 후에 사용함 
  ```xml
  [persistence.xml]
  <persistence-unit name="hello">
    <properties>
      ...
      <property name="hibernate.hbm2ddl.auto" value="create" />
    </properties>
  </persistence-unit>
  ```
  - ```hibernate.hbm2ddl.auto``` 속성
    - ```create``` : 기존 테이블 삭제 후 다시 생성 (DROP + CREATE)
    - ```create-drop``` : create와 같으나 애플리케이션 종료 시점에 다시 drop함 (DROP + CREATE + DROP) => 테스트 케이스처럼
    - ```update``` : 추가/변경된 컬럼이 있을 때 alter 명령어로 변경 처리 (단, 삭제는 불가능)
    - ```validate``` : 엔티티와 테이블이 정상 매핑되었는지 확인할 때 사용함 (테이블에 존재하지 않는 엔티티가 발견될 시 오류 발생)
    - ```none``` : 속성을 사용하지 않음 (주석 처리한 것과 동일)
