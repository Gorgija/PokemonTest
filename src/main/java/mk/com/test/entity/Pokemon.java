/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.com.test.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import mk.com.test.enums.PokemonColor;
import mk.com.test.enums.PokemonType;

/**
 *
 * @author georgy
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pokemon.findAll", query = "SELECT p FROM Pokemon p")
    , @NamedQuery(name = "Pokemon.findByPokemonId", query = "SELECT p FROM Pokemon p WHERE p.pokemonId = :pokemonId")
    , @NamedQuery(name = "Pokemon.findByName", query = "SELECT p FROM Pokemon p WHERE p.name = :name")
    , @NamedQuery(name = "Pokemon.findRedPokemons", query = "SELECT p FROM Pokemon p WHERE p.color = :color")
})
public class Pokemon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "pokemon_generator", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pokemon_generator")
    @Column(name = "pokemon_id")
    private Integer pokemonId;
    @Basic(optional = false)
    @Size(min = 1, max = 45)
    private String name;

    @JoinColumn(name = "color", referencedColumnName = "color_id", updatable = false)
    @ManyToOne(optional = true, cascade = CascadeType.PERSIST)
    private Color color;

    @JoinColumn(name = "type", referencedColumnName = "type_id", updatable = false)
    @ManyToOne(optional = true, cascade = CascadeType.PERSIST)
    private Type type;

    public Pokemon() {
    }

    public Pokemon(Integer pokemonId) {
        this.pokemonId = pokemonId;
    }

    public Pokemon(Integer pokemonId, String name) {
        this.pokemonId = pokemonId;
        this.name = name;
    }

    public Pokemon(String name) {
        this.name = name;
    }

    public Pokemon(String name, Color color, Type type) {
        this.name = name;
        this.color = color;
        this.type = type;
    }

    public Integer getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(Integer pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
//        if (color == null) {
//            int ran = new Random().nextInt(PokemonColor.colors.length);
//            this.color = new Color(PokemonColor.colors[ran].toUpperCase());
//        } else {
            this.color = color;
//        }

    }

    public Type getType() {
        return type;
    }


    public void setType(Type type) {
//        if (type == null) {
//            int ran = new Random().nextInt(PokemonType.types.length);
//            this.type = new Type(PokemonType.types[ran].toUpperCase());
//        } else {
            this.type = type;
//        }

    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pokemonId != null ? pokemonId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pokemon)) {
            return false;
        }
        Pokemon other = (Pokemon) object;
        if ((this.pokemonId == null && other.pokemonId != null) || (this.pokemonId != null && !this.pokemonId.equals(other.pokemonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mk.com.test.entity.Pokemon[ pokemonId=" + pokemonId + " ]";
    }

}
