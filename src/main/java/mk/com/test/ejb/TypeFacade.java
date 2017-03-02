/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.com.test.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mk.com.test.entity.Type;

/**
 *
 * @author georgy
 */
@Stateless
public class TypeFacade {

    @PersistenceContext(unitName = "mk.com.test_PokemonProject_war_1.0PU")
    EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Type find(Object id) {
        return getEntityManager().find(Type.class, id);
    }

}
