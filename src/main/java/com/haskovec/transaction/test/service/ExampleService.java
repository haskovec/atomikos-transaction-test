package com.haskovec.transaction.test.service;

import org.springframework.stereotype.Service;

/**
 * Created by jhaskovec on 4/5/16.
 */
@Service
public interface ExampleService {
	public void testCommit() throws Exception;

	public void testRollback();
}
