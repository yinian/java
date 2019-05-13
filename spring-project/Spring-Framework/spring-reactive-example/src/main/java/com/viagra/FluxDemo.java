package com.viagra;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


/**
 * @Author: HASEE
 * @Description:
 * @Date: Created in 17:52 2019/5/11
 * @Modified By:
 */
public class FluxDemo {

	@Test
	public void flux01() {
		List<Integer> elements = new ArrayList<>();

		Flux.just(1,2,3,4)
				.log()
				.subscribe(elements::add);

		assertThat(elements).containsExactly(1,2,3,4);
	}

	@Test
	public void flux02() {
		List<Integer> elements = new ArrayList<>();

		Flux.just(1,2,3,4)
				.log()
				.subscribe(new Subscriber<Integer>() {
					@Override
					public void onSubscribe(Subscription subscription) {
						subscription.request(Long.MAX_VALUE);
					}

					@Override
					public void onNext(Integer integer) {
						elements.add(integer);
					}

					@Override
					public void onError(Throwable throwable) {

					}

					@Override
					public void onComplete() {

					}
				});

	}

	@Test
	public void fluxWithBackpressure() {
		List<Integer> elements = new ArrayList<>();
		Flux.just(1,2,3,4)
				.log()
				.subscribe(new Subscriber<Integer>() {

					private Subscription s;
					int onNextAmount;
					@Override
					public void onSubscribe(Subscription subscription) {

						this.s = subscription;
						s.request(2);
					}

					@Override
					public void onNext(Integer integer) {
						elements.add(integer);
						onNextAmount++;
						if(onNextAmount%2==0){
							s.request(2);
						}
					}

					@Override
					public void onError(Throwable throwable) {

					}

					@Override
					public void onComplete() {

					}
				});

	}
	@Test
	public void fluxWithMap(){
		List<Integer> elements = new ArrayList<>();
		Flux.just(1, 2, 3, 4)
				.log()
				.map(i -> i * 2)
				.subscribe(elements::add);

		elements.forEach(System.out::println);

	}
	@Test
	public void fluxWithCombination(){
		List<Integer> elements = new ArrayList<>();
		Flux.just(1, 2, 3, 4)
				.log()
				.map(i -> i * 2)
				.zipWith(Flux.range(0, Integer.MAX_VALUE),
						(one, two) -> String.format("First Flux: %d, Second Flux: %d", one, two))
				.subscribe(System.out::println);


	}

	@Test
	public void fluxWithHotStream(){
		ConnectableFlux<Object> publish = Flux.create(fluxSink ->{
			while (true){
				fluxSink.next(System.currentTimeMillis());
			}
		}).publish();
		publish.subscribe(System.out::println);
		publish.subscribe(System.out::println);
	}

	@Test
	public void fluxWithConcurrency(){

		List<Integer> elements = new ArrayList<>();
		Flux.just(1,2,3,4)
				.log()
				.map(i->i*2)
				.subscribeOn(Schedulers.parallel())
				.subscribe(elements::add);
		elements.forEach(System.out::println);
	}

}
