package ibf2022.batch1.csf.assessment.server.controllers;


import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch1.csf.assessment.server.models.MovieComment;
import ibf2022.batch1.csf.assessment.server.models.Review;
import ibf2022.batch1.csf.assessment.server.repositories.MovieRepository;
import ibf2022.batch1.csf.assessment.server.services.MovieService;
import ibf2022.batch1.csf.assessment.server.utils.Utils;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;



@RestController
@RequestMapping(path="/api")
public class MovieController {
	@Autowired
	MovieService movieService;
	@Autowired
	MovieRepository movieRepository;

	@GetMapping(path="/search", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getMoive(@RequestParam String query){
		List<Review> reviews = movieService.searchReviews(query);
		
		
		reviews.stream().forEach(x ->{
			if(!x.hasImage()) x.setImage("");
			Integer count = movieRepository.countComments(x.getTitle());
			x.setCommentCount(count);
		});
		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (Review r : reviews) {
            arrBuilder.add(Utils.toJson(r));
        }
		  
		return ResponseEntity.ok(Json.createObjectBuilder()
								.add("reviews", arrBuilder)
								.build().toString());
	}

	@PostMapping(path="/comment", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE
    ,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postComment(@RequestBody MultiValueMap<String, String> payload){
		 MovieComment comment = new MovieComment();

		 String x = Json.createObjectBuilder()
		 				.add("title",payload.getFirst("title"))
						.add("name",payload.getFirst("name"))
						.add("rating",payload.getFirst("rating"))
						.add("comment",payload.getFirst("comment"))
						.build().toString();

		Document doc = Document.parse(x);
		 movieRepository.insertComment(doc);
	
		 return ResponseEntity.ok().build();

	}

	

	// @GetMapping("/test")
    // public ResponseEntity<String> test() {
	// 	movieService.searchReviews("Iron Man");
	// 	return null;
	// }
}
