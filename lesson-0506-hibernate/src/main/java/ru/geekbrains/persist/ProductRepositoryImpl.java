package ru.geekbrains.persist;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    public void init() {
        entityManagerFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    @Override
    public List<Product> findAll() {
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    @Override
    public Product findById(long id) {
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Product.class, id);
    }

    public Product findByName(String name) {
        return entityManager.createQuery("select p from Product p where p.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList().get(0);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        entityManager = entityManagerFactory.createEntityManager();
        if (product.getId() == null) {
            entityManager.getTransaction().begin();
            entityManager.persist(new Product(null, product.getName(), product.getDescription(), product.getPrice()));
            entityManager.getTransaction().commit();
            return findByName(product.getName());
        } else {
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.getTransaction().commit();
            return findById(product.getId());
        }
    }

    @Override
    public void deleteById(long id) {
        Product product = findById(id);
        entityManager.getTransaction().begin();
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }
}
