/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.com.test.ejb;

import java.util.List;
import java.util.Random;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mk.com.test.entity.Color;
import mk.com.test.entity.Pokemon;
import mk.com.test.entity.Type;
import mk.com.test.enums.PokemonColor;
import mk.com.test.enums.PokemonType;

/**
 *
 * @author georgy
 */
@Stateless
public class PokemonFacade {

    @Inject
    ColorFacade colorFacade;

    // CDI = INJECT over EJB Anotation 
    @Inject
    TypeFacade typeFacade;

    // NOT very GOD use of Entity Manager instance !! = Maybe in Abstract class with Entity class reference ?
    @PersistenceContext(unitName = "mk.com.test_PokemonProject_war_1.0PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    
    public PokemonFacade() {
    }

    // Create New Pokemon in Database ...
    public void create(Pokemon entity) {
        // If there is no color on this pokemon - then maybe generate random color >>> ?
        if (entity.getColor() == null) {
            Color c = colorFacade.find(new Random().nextInt(PokemonColor.colors.length));
            entity.setColor(c);
        }
        // same for type - if there is no type then generate random type >>> ?
        if (entity.getType() == null) {
            Type t = typeFacade.find(new Random().nextInt(PokemonType.types.length));
            entity.setType(t);
        }
        getEntityManager().persist(entity);
    }

    // Edit existing Pokemon
    public void edit(Pokemon entity) {
        getEntityManager().merge(entity);
    }

    // Delete pokemon
    public void delete(Pokemon entity) {
        getEntityManager().remove(entity);
    }

    // Find / Fetch Pokemon from DB based on his Id
    public Pokemon find(Object id) {
        return getEntityManager().find(Pokemon.class, id);
    }

    // List All Pokemons stored in DB , if there is need for client side reduction based on some parametar...
    public List<Pokemon> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Pokemon.class));
        return getEntityManager().createQuery(cq).getResultList();
    }

    // Return List of All Red Pokemons from DB based on named query, but maybe better is to fetch them based on CriteriaQuery >>> ?
    public List<Pokemon> findAllReds() {
        return getEntityManager().createNamedQuery("Pokemon.findRedPokemons")
            .setParameter("color", colorFacade.find(1))
            .getResultList();
    }
    
}
