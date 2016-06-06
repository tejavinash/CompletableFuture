package com.assess.serviceImpl;

import java.util.concurrent.CompletableFuture;

public class ServiceAA {

	public static int aa = 15;

	public ServiceAA() {
	}

	public ServiceAA(int a) {
		ServiceAA.aa = a;
	}

	public static CompletableFuture<Integer> processAA() { // This invokes serviceA and process with ServiceAA then provide results 

		System.out.println("In Process of ServiceAA");
		CompletableFuture<Integer> futureA = ServiceA.processA();
		CompletableFuture<Integer> resultAA = futureA.thenCombine(doServiceA(),
				(cust, shop) -> combine(cust, shop));

		/*
		 * final CompletableFuture<String> futureA =
		 * CompletableFuture.supplyAsync(() -> { try { // Process Logic for
		 * ServiceAA here System.out.println("Processing ServiceAA");
		 * Thread.sleep(5000); } catch (InterruptedException e) {
		 * e.printStackTrace(); } return "*ServiceAA is Completed *"; });
		 */
		// return the thread name executing this callable task
		return resultAA;
	}

	public static CompletableFuture<Integer> doServiceA() {  // This invokes the serviceAA
		// only Spanish are allowed
		return CompletableFuture.supplyAsync(() -> {
			// big task with back end dependencies
				System.out.println(" *ServiceAA is completed*");
				return aa;
			});
	}

	private static Integer combine(Integer str1, Integer str2) {

		System.out
				.println("In process of combining both the services A n AA with values "
						+ str1 + " n " + str2);
		// return " * " + str1 + " & " + str2 + " * ";
		return str1 + str2;
	}

}
