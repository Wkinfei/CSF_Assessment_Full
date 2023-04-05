package ibf2022.batch1.csf.assessment.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch1.csf.assessment.server.models.MovieComment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import ibf2022.batch1.csf.assessment.server.utils.Utils;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;;

@Service
public class MovieService {
	@Autowired
	MovieRepository movieRepository;
	@Value("${api.key}")
    	private String API_KEY;
	private final String URL = "https://api.nytimes.com/svc/movies/v2/reviews/search.json";

	// TODO: Task 4
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	public List<Review> searchReviews(String query) {
		
		String uri = UriComponentsBuilder
					.fromUriString(URL)
					.queryParam("query", query)
					.queryParam("api-key", API_KEY)
					.build()
					.toUriString();
		
		System.out.println(uri);

		RequestEntity<Void> req = RequestEntity
									.get(uri)
									.accept(MediaType.APPLICATION_JSON)
                                    .build();
		RestTemplate template = new RestTemplate();

		ResponseEntity<String> response = null;

		// try{
		response = template.exchange(req, String.class);
        System.out.println("\n\n\n\n\n"+ response.getBody());

		JsonObject json = Utils.toJson(response.getBody());
		JsonArray results = json.getJsonArray("results");
        List<Review> reviews = results.stream()
                                    .map(x -> (JsonObject) x)
                                    .map(x -> Utils.toReview(x))
                                    .toList();

		return reviews;
		// }catch(Exception e){
		// 	return results.isEmpty;
		// }
	}


}
