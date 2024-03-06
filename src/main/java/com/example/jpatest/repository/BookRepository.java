package com.example.jpatest.repository;

import com.example.jpatest.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myEntityObjectTest");
        EntityManager em = entityManagerFactory.createEntityManager();
        
        // 비영속 상태
        Book book = new Book();
        book.setName("1번책");
        book.setContent("1번책 내용");

        // 트랜잭션 시작
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            // 영속 상태
            em.persist(book);

            // 커밋
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            em.detach(book);
            book.setContent("사실 2번책임");
            em.merge(book);
            em.remove(book);
        }
    }
}
