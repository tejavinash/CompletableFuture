package com.assess.process;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.assess.serviceImpl.ServiceA;
import com.assess.serviceImpl.ServiceAA;
import com.assess.serviceImpl.ServiceB;
import com.assess.serviceImpl.ServiceC;

public class OrderLogic {

	public static void main(String[] args) {

		/*
		 * ExecutorService executor = Executors.newFixedThreadPool(5);
		 * 
		 * List<Future<String>> list = new ArrayList<Future<String>>();
		 * 
		 * Callable<String> callServiceA = new ServiceAA(); //Callable<String>
		 * callServiceAa = new ServiceAA(); Callable<String> callServiceB = new
		 * ServiceB(); Callable<String> callServiceC = new ServiceC();
		 * 
		 * Future<String> futureofA = executor.submit(callServiceA);
		 * list.add(futureofA);
		 * 
		 * 
		 * Future<String> futureofAA = executor.submit(callServiceAa);
		 * list.add(futureofAA);
		 * 
		 * Future<String> futureofB = executor.submit(callServiceB);
		 * list.add(futureofB);
		 * 
		 * Future<String> futureofC = executor.submit(callServiceC);
		 * list.add(futureofC);
		 * 
		 * for(Future<String> fut : list){ try { //print the return value of
		 * Future, notice the output delay in console // because Future.get()
		 * waits for task to get completed System.out.println(new Date()+
		 * "::"+fut.get()); } catch (InterruptedException | ExecutionException
		 * e) { e.printStackTrace(); } } //shut down the executor service now
		 * executor.shutdown();
		 */
		try {
		
		
		final CompletableFuture<Integer> futureA = ServiceA.processA(); // Invoking serviceA
		CompletableFuture<Integer> futureAA = futureA.thenCombine(ServiceAA.doServiceA(),
				(cust, shop) -> combine(cust, shop)); // Invoking ServiceAA with the result of serviceA
		
		final CompletableFuture<Integer> futureB = ServiceB.processB(); // Invoking ServiceB
		
		int resultAA = futureAA.get();
		int resultB = futureB.get();
		
		System.out.println("The result of serviceAA is"+resultAA+" and serviceB is "+resultB);
		
		CompletableFuture<Integer> futureBwithAA = futureAA.thenCombine(
				ServiceB.processB(), (cust, shop) -> combineB(cust, shop)); // Invoking serviceB with the output of serviceAA
			
		CompletableFuture<Integer> futureCwithAAB = futureBwithAA.thenCombine(ServiceC.doServiceC(),
				(cust, shop) -> combineC(cust, shop)); // Invoking serviceB with the output of serviceAA and ServiceB
		System.out.println("The result of serviceC with output from AA n B is "+futureCwithAAB.get());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		invokeServiceALL(); //  Invoking all the services
	}
	
	private static Integer combine(Integer str1, Integer str2) {

		System.out
				.println("In process of combining both the services A n AA with values "
						+ str1 + " n " + str2);
		// return " * " + str1 + " & " + str2 + " * ";
		return str1 + str2;
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
	
	private static void invokeServiceALL(){
		invokeServiceA();
		invokeServiceAA();
		invokeServiceB();
		invokeServiceC();
	}
	
	private static void invokeServiceA() {
		final CompletableFuture<Integer> futureA = ServiceA.processA();
		try {
			System.out.println("The reult of ServiceA is " + futureA.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void invokeServiceAA() {
		final CompletableFuture<Integer> futureAA = ServiceAA.processAA();
		try {
			System.out.println("The reult of ServiceA is " + futureAA.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void invokeServiceC() {
		final CompletableFuture<Integer> futureC = ServiceC.processC();
		try {
			System.out.println("The reult of ServiceA is " + futureC.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private static void invokeServiceB() {
		final CompletableFuture<Integer> futureB = ServiceB.processB();
		try {
			System.out.println("The reult of ServiceB is " + futureB.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
