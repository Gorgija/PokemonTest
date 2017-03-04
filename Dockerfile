FROM glassfish
RUN apt-get update
RUN curl http://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.34/mysql-connector-java-5.1.34.jar -o glassfish/lib/mysql-connector-java-5.1.34.jar
COPY target/PokemonProject-1.0.war glassfish/domains/domain1/autodeploy/PokemonProject-1.0.war
EXPOSE 8080