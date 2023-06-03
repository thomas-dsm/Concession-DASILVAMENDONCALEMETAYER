package concession.service;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import concession.mongoclient.MongoClientEntretien;
import concession.mongoclient.MongoClientMarque;
import concession.mongoclient.MongoClientVoiture;
import data.Mock;
import org.bson.codecs.configuration.CodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServiceApplication {
    
    public static void main( String[] args ) {
            
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        // Replace the placeholder with your MongoDB deployment's connection string
        String uri = "mongodb://localhost:27017";
        
        MongoClientVoiture voitureClient = new MongoClientVoiture(uri);
        //MongoClientMarque marque = new MongoClientMarque(uri);
        //MongoClientEntretien entretien = new MongoClientEntretien(uri);
        
        Mock.MockVoiture(voitureClient);
    }
}
