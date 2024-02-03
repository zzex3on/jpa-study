package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction(); // 트랜잭션 얻음
        tx.begin(); // 트랜잭션 시작

        try {
            Member member = em.find(Member.class, 101L);
            em.clear();

            Member member2 = em.find(Member.class, 150L);
            member2.setId(150L);

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 중요!
            emf.close();
        }
    }
}
