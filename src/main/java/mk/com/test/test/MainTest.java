/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.com.test.test;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import mk.com.test.ejb.PokemonFacade;
import mk.com.test.entity.Color;
import mk.com.test.entity.Pokemon;
import mk.com.test.entity.Type;

/**
 *
 * @author georgy
 */
@Startup
@Singleton
public class MainTest {

    @Inject
    PokemonFacade pokemonFacade;

    @PostConstruct
    public void init() {
//        Logger.getLogger(MainTest.class.getName()).log(Level.INFO, "Start pokemons creation ....");
//              ----  >   Pokemon with just a name with Random Color and Random Type ....
//        Pokemon pokemon = new Pokemon("lo");
//        pokemonFacade.create(pokemon);
//        Logger.getLogger(MainTest.class.getName()).log(Level.INFO, "Pokemons Created");

    }
}
