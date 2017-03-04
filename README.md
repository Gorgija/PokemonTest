# PokemonTest

Project was build on Glassfish 4.1 or Payara 4.1 App Server - JavaEE 7 with Mysql Database. 
There is DockerBuild file for bulding Docker container with copy of PokemonProject WAR file placed in AUTODEPLOYMENT dir of 
Glassfish Server , installation of MySqL Database ( start service internaly when running docker container ) , getting MySQL JAR file from 
Internet and placing in GlassFish LIB directory and EXPOSING 8080 port for Application Server ( you might wanna EXPOSE 4848 port for 
Admistering server ).


  DDL Files are placed in META-INF directory, and there are 2 files, one is MySql generated for pokemon database and schema, and secon is 
  handwriten for creation of Database and placing some values for testing purposes.

Pokemon App path:
    - http://[host]:[port]/PokemonProject/
Pokemon Rest App Config Path:
    - /rest/  
Pokemon rest Pokemon CRUD operations:
     - /pokemons/
     
EXAMPLE:
  Create pokemon:
    /POST "Content-Type":"application/json" http://host:8080/PokemonProject/rest/pokemons/
        ( successful resturn Response with 200 , Unsuccessfull return 400 Bad_REQUEST )
        
  Edit Pokemon:
    /PUT "Content-Type":"application/json" http://host:8080/PokemonProject/rest/pokemons/[id]
        (success - 200 , unsuccessfull - 406 - not_acceptable )
        
  Delete Pokemon:
    /DELETE "Content-Type":"application/json" http://host:8080/PokemonProject/rest/pokemons/[id]
        - aaditional requerement for testing color type - if it's yellow then there will be no DELETE on Pokemon.- 405 return !
        (success - 200 , unsuccessfull - 405 - method_not_allowed )
        
  Get Pokemon:
    /GET "Content-Type":"application/json" http://host:8080/PokemonProject/rest/pokemons/[id]
      (success - POKEMON JSON ,   else - empty )
      
  List Pokemons:
    /GET "Content-Type":"application/json" http://host:8080/PokemonProject/rest/pokemons/list
          - return JSON Array for all pokemons
    /GET "Content-Type":"application/json" http://host:8080/PokemonProject/rest/pokemons/colors
          - return JSON Array for all RED pokemons but with DEFAULT QUERY VALUE for RED color
    /GET "Content-Type":"application/json" http://host:8080/PokemonProject/rest/pokemons/all/[color]
          - return JSON Array for all RED pokemons but with DEFAULT PATH PARAMETER for RED color
          
There is also config , ping and count REST path URI for returning config info ( maybe for this is better use of SWAGER ? ) , 
Pingig REST path if it is operational and count for number of all Pokemons in database.
