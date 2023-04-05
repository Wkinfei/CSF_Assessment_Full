package ibf2022.batch1.csf.assessment.server.utils;

import java.io.StringReader;

import org.bson.Document;

import ibf2022.batch1.csf.assessment.server.models.MovieComment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

public class Utils {
    public static JsonObject toJson(String json) {
        JsonReader jsonReader = Json.createReader(new StringReader(json));
        JsonObject data = jsonReader.readObject();
        jsonReader.close();
        return data;
    }

    public static Review toReview(JsonObject json) {
        Review review = new Review();
        if(null == json.getString("display_title")){
            review.setTitle(null);
        }else{
            review.setTitle(json.getString("display_title"));
        }
        if(null == json.getString("mpaa_rating")){
            review.setRating(null);
        }else{
            review.setRating(json.getString("mpaa_rating"));
        }
       
        review.setByline(json.getString("byline"));
        review.setHeadline(json.getString("headline"));
        if(null == json.getString("summary_short")){
            review.setSummary(null);
        }else{
            review.setSummary(json.getString("summary_short"));
        }
        review.setReviewURL(json.getJsonObject("link").getString("url"));
        if(JsonValue.NULL == json.getJsonObject("multimedia")){
            review.setImage(null);
        }else{
            review.setImage(json.getJsonObject("multimedia").getString("src"));
        }
        
        
        return review;
    }

    public static JsonObject toJson(Review review) {
        return Json.createObjectBuilder()
                .add("title", review.getTitle())
                .add("rating", review.getRating())
                .add("byline", review.getByline())
                .add("headline", review.getHeadline())
                .add("summary", review.getSummary())
                .add("reviewURL", review.getReviewURL())
                .add("image", review.getImage())
                .add("commentCount",review.getCommentCount())
                .build();
    }

    public static Document toDocument(MovieComment comment) {
        return new Document()
                    .append("title", comment.getTitle())
                    .append("name", comment.getName())
                    .append("rating", comment.getRating())
                    .append("comment", comment.getComment());
    }
}
