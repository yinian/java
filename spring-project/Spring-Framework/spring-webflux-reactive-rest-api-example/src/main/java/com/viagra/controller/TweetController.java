package com.viagra.controller;

import com.mongodb.DuplicateKeyException;
import com.viagra.exception.TweetNotFoundException;
import com.viagra.model.Tweet;
import com.viagra.payload.ErrorResponse;
import com.viagra.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 11:43 2019/5/13
 * @Modified By:
 */
@RestController
public class TweetController {

	@Autowired
	private TweetRepository tweetRepository;

	@GetMapping("/tweets")
	public Flux<Tweet> getAllTweets(){
		return tweetRepository.findAll();
	}

	@PostMapping("/tweets")
	public Mono<Tweet> createTweets(@Valid @RequestBody Tweet tweet){
		return tweetRepository.save(tweet);
	}

	@GetMapping("/tweets/{id}")
	public Mono<ResponseEntity<Tweet>> getTweetById(@PathVariable(value = "id") String tweetId){
		return tweetRepository.findById(tweetId)
				.map(savedTweet -> ResponseEntity.ok(savedTweet))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	@PutMapping("/tweets/{id}")
	public Mono<ResponseEntity<Tweet>> updateTweet(@PathVariable(value = "id") String tweetId,
	                                               @Valid @RequestBody Tweet tweet){
		return tweetRepository.findById(tweetId)
				.flatMap(existingTweet ->{
					existingTweet.setText(tweet.getText());
					return tweetRepository.save(existingTweet);
				})
				.map(updateTweet -> new ResponseEntity<>(updateTweet, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@DeleteMapping("/tweets/{id}")
	public Mono<ResponseEntity<Void>> deleteTweet(@PathVariable(value = "id") String tweetId){
		return tweetRepository.findById(tweetId)
				.flatMap(existingTweet ->
						tweetRepository.delete(existingTweet)
							.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
				)
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	// Tweets are Sent to the client as Server Sent Events
	@GetMapping(value = "/stream/tweets",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tweet> streamAllTweets(){
		return tweetRepository.findAll();
	}

	/*
	Exception Handling Examples (These can be put into a @ControllerAdvice to handle exceptions globally)
	 */
	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity handleDuplicateKeyException(DuplicateKeyException ex){
		return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse("A Tweet with the same text already exists"));
	}

	@ExceptionHandler(TweetNotFoundException.class)
	public ResponseEntity handleTweetNotFoundException(TweetNotFoundException ex){
		return ResponseEntity.notFound().build();
	}


}


