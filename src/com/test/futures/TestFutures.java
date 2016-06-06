package com.test.futures;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.assess.serviceImpl.ServiceA;
import com.assess.serviceImpl.ServiceB;
import com.assess.serviceImpl.ServiceAA;
import com.assess.serviceImpl.ServiceC;


public class TestFutures {
	
	@Before
	public void setUp(){
	// Nothing to initialize here	
			
	}
	
	

	@Test
	public void testServiceA(){
		
		int a = 0;
		try {
			a = ServiceA.processA().get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(10, a);
		
	}
	
	
	@Test
	public void testDoServiceAA(){
		
		int a = 0;
		try {
			a = ServiceAA.doServiceA().get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(15, a);
		
	}
	
	@Test
	public void testDoServiceC(){
		
		int a = 0;
		try {
			a = ServiceC.doServiceC().get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(25, a);
		
	}
	
	@Test
	public void testServiceB(){
		
		int a = 0;
		try {
			a = ServiceB.processB().get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(20, a);
		
	}
	
	@Test
	public void testServiceAA(){
		
		int a = 0;
		try {
			a = ServiceAA.processAA().get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(25, a);
		
	}
	
	@Test
	public void testServiceC(){
		
		int a = 0;
		try {
			a = ServiceC.processC().get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(70, a);
		
	}
	
	@After
	public void cleanUp(){
		// nothing to clean up here
	}
	
	
}
