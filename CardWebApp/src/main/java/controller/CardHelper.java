package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.Card;

public class CardHelper {

    static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("CardWebApp");

    public void insertCard(Card card) {
        EntityManager entityManager = emfactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(card);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void deleteCard(Card toDelete) {
        EntityManager entityManager = emfactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Card cardToDelete = entityManager.find(Card.class, toDelete.getId());
            if (cardToDelete != null) {
                entityManager.remove(cardToDelete);
                transaction.commit();
            } else {
                System.out.println("Card not found in the database.");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List<Card> searchForCardByCardNumber(String cardNumber) {
        EntityManager entityManager = emfactory.createEntityManager();
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.cardNumber = :cardNumber", Card.class);
        query.setParameter("cardNumber", cardNumber);
        List<Card> cardList = query.getResultList();
        entityManager.close();
        return cardList;
    }

    public List<Card> searchForCardByCardName(String cardName) {
        EntityManager entityManager = emfactory.createEntityManager();
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c WHERE c.cardName = :cardName", Card.class);
        query.setParameter("cardName", cardName);
        List<Card> cardList = query.getResultList();
        entityManager.close();
        return cardList;
    }

    public Card searchForCardById(int idToEdit) {
        EntityManager entityManager = emfactory.createEntityManager();
        Card card = entityManager.find(Card.class, idToEdit);
        entityManager.close();
        return card;
    }

    public void updateCard(Card toEdit) {
        EntityManager entityManager = emfactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(toEdit);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List<Card> getAllCards() {
        EntityManager entityManager = emfactory.createEntityManager();
        TypedQuery<Card> query = entityManager.createQuery("SELECT c FROM Card c", Card.class);
        List<Card> cardList = query.getResultList();
        entityManager.close();
        return cardList;
    }

    public void cleanUp() {
        emfactory.close();
    }
}