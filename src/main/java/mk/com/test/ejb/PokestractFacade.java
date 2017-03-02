/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.com.test.ejb;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author georgy
 */
// For Future Use ....  Abstract class for All Session Facade EJBs CRUD methods.
public abstract class PokestractFacade<T> {

    private Class<T> entityClass;

    public PokestractFacade(Class<T> eClass) {
        this.entityClass = eClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void delete(T entity) {
        getEntityManager().remove(entity);
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

}
