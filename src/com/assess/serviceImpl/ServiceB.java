package com.assess.serviceImpl;

import java.util.concurrent.CompletableFuture;

public class ServiceB {

	public static int b = 20;

	public ServiceB() {
	}

	public ServiceB(int a) {
		ServiceB.b = a;
	}

	public static CompletableFuture<Integer> processB() {

		System.out.println("In process of ServiceB");

		final CompletableFuture<Integer> futureB = CompletableFuture
				.supplyAsync(() -> {
					try {
						// Process Logic for ServiceA here
						System.out.println("Processing ServiceB");
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("*ServiceB is Completed *");
					return b;
				});

		// return the thread name executing this callable task
		return futureB;
	}

}
