/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.com.test.enums;

import mk.com.test.entity.Type;

/**
 *
 * @author georgy
 */
public class PokemonType {

    public enum Types {
        WARRIOR,
        BIG,
        SMALL,
        TEACHER,
        POET
    }

    public static String[] types = {
        "warrior", "teacher", "small", "big", "poet", "poison", "water", "normal", "bug", "ghost", "dragon"
    };

}
