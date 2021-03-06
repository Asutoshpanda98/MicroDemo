
//This is not microservice this is just spring boot apllication
package com.cg.iter.resource;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.cg.iter.resource.model.CatalogItem;
import com.cg.iter.resource.model.Movie;
import com.cg.iter.resource.model.Rating;
import com.cg.iter.resource.model.UserRating;

@RestController//with this annotation it becomes a rest controller
//whenever a request is made spring boot will look it and ask for any mapping for it so this will handle the request
@RequestMapping("/catalog")
public class MovieCatalogResource 
{
	
   @Autowired
   private  RestTemplate restTemplate;
   

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
	//@pathvariable is used to denote that this is a variable passed and it need to pass is at arguments 
	//Steps to connect it with movie info service
		 UserRating ratings =restTemplate.getForObject("http://localhost:9094/ratingdata/users/"+userId,UserRating.class);
			return ratings.getUserRating().stream().map(rating->{

	//for each movie id,call movie info service and get  data
	//now we have to make api call
 
		Movie movie =restTemplate.getForObject("http://localhost:9092/movies/"+ rating.getMovieId(), Movie.class);
		//put them all together
		return new CatalogItem(movie.getName() , "Test" ,rating.getRating());
		})
			.collect(Collectors.toList());
	
	}
	
	
	
	}


//@Autowired
//private WebClient.Builder webClientBuilder;
//WebClient.Builder builder=WebClient.builder();

//Movie movie=webClientBuilder.build() 
//    .get()
//    .uri("http://localhost:9092/movies"+ rating.getMovieId(), Movie.class)
//    .retrieve()
//    .bodyToMono(Movie.class)
//    .block();
//List<Rating> ratings = Arrays.asList(
//new Rating("1234",4),//haard coded the movies that has been watched
//new Rating("5467",3)
//); 
