/*
 * package com.sns.pjt.mongo;
 * 
 * import java.util.Arrays;
 * 
 * import org.springframework.beans.factory.annotation.Value; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.data.mongodb.config.AbstractMongoConfiguration; import
 * org.springframework.data.mongodb.core.MongoTemplate;
 * 
 * import com.mongodb.Mongo; import com.mongodb.MongoClient; import
 * com.mongodb.MongoClientOptions; import com.mongodb.MongoClientURI; import
 * com.mongodb.MongoCredential; import com.mongodb.ServerAddress;
 * 
 * @Configuration public class MongoConfig extends AbstractMongoConfiguration {
 * 
 * @Value("${spring.data.mongodb.host}") private String mongoHost;
 * 
 * @Value("${spring.data.mongodb.port}") private int mongoPort;
 * 
 * @Value("${spring.data.mongodb.authentication-database}") private String
 * database;
 * 
 * @Value("${spring.data.mongodb.username}") private String username;
 * 
 * @Value("${spring.data.mongodb.password}") private String password;
 * 
 * @Override public MongoClient mongoClient() { // TODO Auto-generated method
 * stub MongoCredential credential = MongoCredential.createCredential(username,
 * database, password.toCharArray());
 * 
 * 
 * MongoClientURI mongoClientURI = new MongoClientURI(this.url); MongoCredential
 * mongoCredential = MongoCredential.createCredential(this.username,
 * mongoClientURI.getDatabase(), this.password.toCharArray()); ServerAddress
 * serverAddress = new ServerAddress(mongoClientURI.getHosts().get(0));
 * MongoClientOptions options = MongoClientOptions.builder().build(); return new
 * MongoClient(serverAddress, mongoCredential, options);
 * 
 * 
 * return new MongoClient(new ServerAddress(mongoHost, mongoPort),
 * Arrays.asList(credential)); }
 * 
 * @Override protected String getDatabaseName() { // TODO Auto-generated method
 * stub return database; }
 * 
 * @Bean public MongoTemplate mongoTemplate() throws Exception {
 * 
 * return new MongoTemplate(mongoClient(), database); }
 * 
 * }
 */