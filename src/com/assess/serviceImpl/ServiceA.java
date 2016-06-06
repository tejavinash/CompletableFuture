package com.assess.serviceImpl;

import java.util.concurrent.CompletableFuture;

public class ServiceA {

	public static int a = 10;

	public ServiceA() {
	}

	public ServiceA(int a) {
		ServiceA.a = a;
	}

	/*
	 * @Override public String call() throws Exception {
	 * 
	 * final CompletableFuture<String> futureA =
	 * CompletableFuture.supplyAsync(() -> { try { // Process Logic for
	 * ServiceAA here System.out.println("Processing ServiceAA");
	 * Thread.sleep(5000); } catch (InterruptedException e) {
	 * e.printStackTrace(); } return "*ServiceAA is Completed *"; });
	 * 
	 * //return the thread name executing this callable task return
	 * Thread.currentThread().getName(); }
	 */

	public static CompletableFuture<Integer> processA() {

		System.out.println("In process of ServiceA");

		final CompletableFuture<Integer> futureA = CompletableFuture
				.supplyAsync(() -> {
					try {
						// Process Logic for ServiceA here
						System.out.println("Processing ServiceA");
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("*ServiceA is Completed *");
					return a;
				});

		// return the thread name executing this callable task
		return futureA;
	}

}