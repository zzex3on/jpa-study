package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // 애플리케이션 로딩 시점에 딱 하나만 만들어둬야 함,
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // DB Connection을 하고 close 하는 일련의 과정에서 EntityManager를 꼭 만들어줘야 함
        // DB당 하나 생성
        EntityManager em = emf.createEntityManager();
        // 기능 하나에 쓰고 버림 -> 스레드간에 공유하지 않음! 일회용성

        // JPA에서는 트랜잭션이라는 단위가 매우 중요함! 데이터를 변경하는 모든 작업은 반드시 트랜잭션 내에서 수행되어야 함
        // DB Connection 하나 받는다는 느낌
        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻음
        tx.begin(); // 트랜잭션 시작

        try {
            // 비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            // 영속
            System.out.println(":: before ::");
            em.persist(member);
            System.out.println(":: after ::");

            Member findMember = em.find(Member.class, 101L);

            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 중요!
            emf.close();
        }
    }
}
