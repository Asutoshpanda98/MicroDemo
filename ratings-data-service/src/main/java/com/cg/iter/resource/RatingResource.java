package com.cg.iter.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.iter.model.Rating;
import com.cg.iter.model.UserRating;

@RestController
@RequestMapping("/ratingdata")
public class RatingResource 
{
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId")String movieId) {
		return new Rating(movieId,4);
     }
	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId")String userId) {
		List<Rating> ratings = Arrays.asList(
				new Rating("1234",4),//haard coded the movies that has been watched
				new Rating("5467",3)
				);
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		return userRating;
	
	}
}
