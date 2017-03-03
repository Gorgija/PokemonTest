/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mk.com.test.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mk.com.test.ejb.PokemonFacade;
import mk.com.test.entity.Color;
import mk.com.test.entity.Pokemon;
import mk.com.test.entity.Type;

/**
 *
 * @author Georgy Georgievski - georgievski.one@gmail.com
 */
// Maybe @Produces(MediaType.APPLICATION_JSON) on class >>> ?
@Stateless
@Path("pokemons")
public class PokemonREST {

    @Inject
    PokemonFacade pokemonFacade;

//---> /GET http://[host]:[port]/[Appname]/[REST_App_Config_Class]/[This_CLass_Path_name]/ =>  Test PING message
    @GET
    public String ping() {
        Logger.getLogger(PokemonREST.class.getName()).log(Level.INFO, "Ping message recived on " + LocalDateTime.now());
        return "recived ping message on " + LocalDateTime.now();
    }

//---> /GET "Content-Type":"application/json" http://[host]:[port]/[Appname]/[REST_App_Config_Class]/[This_CLass_Path_name]/ => READ  
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pokemon getPokemon(@PathParam("id") Integer pid) {
        return pokemonFacade.find(pid);
    }

//---> /GET "Content-Type":"application/json" http://[host]:[port]/[Appname]/[REST_App_Config_Class]/[This_CLass_Path_name]/ => CREATE
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Pokemon pokemon) {
        Logger.getLogger(PokemonREST.class.getName()).log(Level.INFO, "POST Request recived on " + LocalDateTime.now() + " Pokemon data: " + pokemon);
        if (pokemon == null) {
            System.out.println("There is no json Pokemon object in json request !!!!  ");
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            Pokemon poke = new Pokemon(pokemon.getName());
            pokemonFacade.create(poke);
            return Response.ok().build();
        }

    }
//---> /PUT http://[host]:[port]/[Appname]/[REST_App_Config_Class]/[This_CLass_Path_name]/[PokemonID] => UPDATE
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response edit(@PathParam("id") Integer id, Pokemon pokemon) {
        Logger.getLogger(PokemonREST.class.getName()).log(Level.INFO, "PUT Request recived on " + LocalDateTime.now() + " Pokemon obj: " + pokemon);
        Pokemon poke = pokemonFacade.find(id);
        try {
            pokemonFacade.edit(poke);
            return Response.status(Response.Status.OK).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

    }
//---> /DELETE http://[host]:[ port ]/[Appname]/[REST_App_Config_Class]/[This_CLass_Path_name]/[PokemonID] => DELETE
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response remove(@PathParam("id") Integer id) {
        // if it is yellow collor = don't remove it !!!
        Logger.getLogger(PokemonREST.class.getName()).log(Level.INFO, "DELETE Request recived on " + LocalDateTime.now() + " For Pokemon: " + id);
        Pokemon poke = pokemonFacade.find(id);
        if (poke.getColor().getColor().equalsIgnoreCase("yellow")) {
            return Response.status(Response.Status.METHOD_NOT_ALLOWED).build();
        } else {
            pokemonFacade.delete(poke);
            return Response.ok().build();
        }

    }

//---> /GET http://[host]:[port]/[Appname]/[REST_App_Config_Class]/[This_CLass_Path_name]/list => returns JSON Array of all pokemons
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pokemon> getAllPokemons() {
        // return list all pokemons
        Logger.getLogger(PokemonREST.class.getName()).log(Level.INFO, "GET /list Request recived on " + LocalDateTime.now());
        return pokemonFacade.findAll();
    }

//---> /GET http://[host]:[port]/[Appname]/[REST_App_Config_Class]/[This_CLass_Path_name]/colors => return JSON Array of RED pokemons
    // But if is provided color value in QUERY parameters then that color will be result list of pokemons.
    @GET
    @Path("colors")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pokemon> getAllColoredPokemons(@DefaultValue("red") @QueryParam("color") String color) {
        Logger.getLogger(PokemonREST.class.getName()).log(Level.INFO, "GET /list Request recived on " + LocalDateTime.now() + " For Color: " + color);
        return pokemonFacade.findAllByColor(color);
    }

//---> /GET http://[host]:[port]/[Appname]/[REST_App_Config_Class]/[This_CLass_Path_name]/all/[colors] => return JSON Array of {DEFAULT} RED pokemons
    // But if is provided color value in PATH parameters then that color will be result list of pokemons.
    @GET
    @Path("all/{color}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pokemon> getPokemonsByColor(@DefaultValue("red") @PathParam("color") String color) {
        Logger.getLogger(PokemonREST.class.getName()).log(Level.INFO, "GET /list Request recived on " + LocalDateTime.now() + " For Color: " + color);
        return pokemonFacade.findAllByColor(color);
    }

    // Improvisaton ...  For config it will be better to use SWAGER or some other REST API tool...
    @GET
    @Path("config")
    @Produces(MediaType.TEXT_PLAIN)
    public String getConfig() {
        StringBuilder config = new StringBuilder();
        config.append("/PokemonProject/rest/pokemons/count:GET|"); //count
        config.append("/PokemonProject/rest/pokemons:GET|"); // ping
        config.append("/PokemonProject/rest/pokemons/{pokemonId}:GET|");// getPokemon
        config.append("/PokemonProject/rest/pokemons/{pokemonId}:PUT|"); // edit
        config.append("/PokemonProject/rest/pokemons/{pokemonId}:DELETE|"); // remove
        config.append("/PokemonProject/rest/pokemons:POST|"); // create
        config.append("/PokemonProject/rest/pokemons/list|"); // getAllPokemons
        config.append("/PokemonProject/rest/pokemons/colors|"); // getAllColoredPokemons
        config.append("/PokemonProject/rest/pokemons/all/{color}"); // getPokemonsByColor
        return config.toString();
    }

//---> /GET http://[host]:[port]/[Appname]/[REST_App_Config_Class]/[This_CLass_Path_name]/count => returns text number of pokemon records in DB
    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String count() {
        return String.valueOf(pokemonFacade.findAll().size());
    }

}
