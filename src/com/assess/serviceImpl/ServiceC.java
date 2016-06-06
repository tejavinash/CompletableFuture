package com.assess.serviceImpl;

import java.util.concurrent.CompletableFuture;

public class ServiceC {

	public static int c = 25;

	public ServiceC() {
	}

	public ServiceC(int a) {
		ServiceC.c = a;
	}

	public static CompletableFuture<Integer> processC() { // This will internally invoke ServiceAA and serviceB then starts its processing

		System.out.println("In Process of ServiceC");
		CompletableFuture<Integer> futureAA = ServiceAA.processAA();
		CompletableFuture<Integer> futureB = futureAA.thenCombine(
				ServiceB.processB(), (cust, shop) -> combineB(cust, shop));
		CompletableFuture<Integer> resultAA = futureB.thenCombine(doServiceC(),
				(cust, shop) -> combineC(cust, shop));

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

	public static CompletableFuture<Integer> doServiceC() {  // This is just an invocation of ServiceC with default 
		// only Spanish are allowed
		return CompletableFuture.supplyAsync(() -> {
			// big task with back end dependencies
				System.out.println(" *ServiceC is completed*");
				return c;
			});
	}
	
	
	public static CompletableFuture<Integer> doServiceC(int a, int b) {  // This is just an invocation of ServiceC with 2 inputs
		// only Spanish are allowed
		return CompletableFuture.supplyAsync(() -> {
			// big task with back end dependencies
				System.out.println(" *ServiceC is completed with a n b *");
				return a+b;
			});
	}

	private static Integer combineC(Integer str1, Integer str2) {

		System.out
				.println("In process of combining both the services (AA n B) n C with values "
						+ str1 + " " + str2);
		return str1 + str2;
	}

	private static Integer combineB(Integer str1, Integer str2) {

		System.out
				.println("In process of combining both the services AA n B with values "
						+ str1 + " " + str2);
		return str1 + str2;
	}

}
