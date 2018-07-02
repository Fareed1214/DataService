package com.dataservice.util;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service
public class MongoDBUtil {

	static public MongoDatabase getDataBase(String pHost, Integer pPort, String pDBName) {
		MongoDatabase database = null;
		System.out.println(pHost);
		MongoClient mongoClient = new MongoClient(new ServerAddress(pHost,pPort));
		if (mongoClient != null)
			database = mongoClient.getDatabase(pDBName);
		return database;
	}

	static public MongoCollection<Document> getCollection(String pHost, Integer pPort, String pDBName, String collName) {
		MongoCollection<Document> coll = null;
		MongoDatabase database = getDataBase( pHost,  pPort, pDBName);
		if (database != null)
			coll = database.getCollection(collName);
		return coll;
	}
	
	static public MongoTemplate getMongoTemplate(String pHost, Integer pPort, String pDBName) {
		MongoTemplate  template = null;
		System.out.println(pHost);
		MongoClient mongoClient = new MongoClient(new ServerAddress(pHost,pPort));
		if (mongoClient != null)
			template = new MongoTemplate(mongoClient, pDBName);
		return template;
	}
}
