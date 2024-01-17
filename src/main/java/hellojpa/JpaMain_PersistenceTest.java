package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain_PersistenceTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻음
        tx.begin(); // 트랜잭션 시작

        try {
            // === 영속성 컨텍스트의 이점과 관련된 테스트를 진행한 소스임 ===
            /*
            // 1. 1차 캐시 테스트 (엔터티 조회)
            Member findMember1 = em.find(Member.class, 101L); // 1차 캐시에 없으니 DB에서 조회, 조회 후 1차 캐시에 저장
            Member findMember2 = em.find(Member.class, 101L); // 1차 캐시에 조회

            tx.commit(); // 로그에 쿼리가 한 번만 찍힘
            */

            /*
            // 2. 동일성 보장 테스트
            Member findMember1 = em.find(Member.class, 101L); // 1차 캐시에 없으니 DB에서 조회, 조회 후 1차 캐시에 저장
            Member findMember2 = em.find(Member.class, 101L); // 1차 캐시에 조회

            System.out.println("result = " + (findMember1 == findMember2)); // result = true
            tx.commit();
            */

            /*
            // 3. 트랜잭션을 지원하는 쓰기 지연 테스트 (엔티티 등록)
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            // 영속성 컨텍스트에 엔티티, 쿼리가 차곡차곡 쌓임
            em.persist(member1);
            em.persist(member2);

            // 쓰기 지연 저장소에 있던 쿼리가 날라가며 DB에 저장!
            tx.commit();
            */

            /*
            // 4. 변경 감지 (엔티티 수정)
            Member member = em.find(Member.class, 150L);
            member.setName("AAAA");

            // em.persist(member); // 이 과정을 거치지않음!!
            // 왜? jpa에서는 자바 컬렉션처럼 동작한다고 봐야하는데, 수정이 될 때마다 persist할 수 없음. 아니 필요없는 동작인거임
            // 중간에 또 수정되면 setName함수만 호출해서 값만 변경해주면 됨
            System.out.println("===========");
            tx.commit(); // setName으로 값이 변경된 것을 감지하고 jpa가 알아서 update 쿼리를 날림... 개똑똑 미쳤네
            */

            // 5. 엔티티 삭제
            // 삭제 대상 엔티티 조회
            Member memberA = em.find(Member.class, "memberA");
            em.remove(memberA); // 엔티티 삭제
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 중요!
            emf.close();
        }
    }
}
