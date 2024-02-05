# 연관관계 매핑 기초
객체의 참조와 테이블의 외래 키를 어떻게 매핑하는지 ✨

1. 방향 - 단방향/양방향
2. 다중성 - N:1, 1:N, 1:1, N:M
3. ✨ __연관관계의 주인__ - JPA계의 포인터와 같은 존재, 객체 양방향 연관관계

## 연관관계가 필요한 이유
### 테이블과 객체의 차이점
* 테이블은 __외래 키로 조인__ 을 사용하여 연관된 테이블을 찾음
* 객체는 __참조를 사용__ 하여 연관된 객체를 찾음   
=> 객체를 테이블에 맞추어 데이터 중심의 모델링을 하게 되면 협력 관계를 만들 수 없음! (외래키 ≠ 참조, 패러다임이 완전히 다름)

## 단방향 연관관계
```java
// @Column(name = "TEAM_ID")
// private Long teamId;

// Team이라는 객체 자체를 참조하게끔 변경!! 객체지향적 설계
@ManyToOne // 멤버(Many)와 팀(One)을 다대일 관계로 매핑함 
@JoinColumn(name = "TEAM_ID") // 조인하는 컬럼은 TEAM_ID 라고 지정 
private Team team;
```
- 무슨 관계인지(```@ManyToOne```), 해당 관계로 조인하는 컬럼(```@JoinColumn(name = "TEAM_ID")```)은 무엇인지 설정

```java
[JpaMain.java]
Team team = new Team();
team.setName("TeamA");
em.persist(team);

Member member = new Member();
member.setUsername("member1");
member.setTeam(team); // JPA가 알아서 team 객체에서 PK값을 꺼낸 후 FK값에 insert할 때 FK값으로 사용함
em.persist(member);

em.flush(); // select 쿼리 확인용 flush 처리
em.clear();

Member findMember = em.find(Member.class, member.getId());
Team findTeam = findMember.getTeam();

tx.commit();
```
위와 같은 소스를 실행하면 로그에 ```flush``` 후 조회된 쿼리를 볼 수 있는데, ```left outer join``` 처리가 된 것을 알 수 있다.   

![image](https://github.com/zzex3on/jpa-study/assets/87990439/6877bb0f-502d-48d2-8f1c-ca0e074737c1)
