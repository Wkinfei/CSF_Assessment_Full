package ibf2022.batch1.csf.assessment.server.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	private final String COLLECTION_COMMENTS ="comments";
	private final String FIELD_MOVIE_TITLE = "title";

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	 // db.comments.insertOne(document); 

	public void insertComment(Document doc) { 
       
        Document inserted = mongoTemplate.insert(doc, COLLECTION_COMMENTS); 

    }
	public int countComments(String title) {
		Criteria criteria = Criteria.where(FIELD_MOVIE_TITLE).is(title); 
        Query query = Query.query(criteria); 
        long count = mongoTemplate.count(query, COLLECTION_COMMENTS);
		return (int)count;  
	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	// db.comments.find({title: "ant-man"}).count();
}
