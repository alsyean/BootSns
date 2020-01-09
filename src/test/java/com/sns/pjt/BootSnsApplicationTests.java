package com.sns.pjt;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BootSnsApplicationTests {

	@Autowired
	private DataSource ds;

	@Test
	void contextLoads() {
	}

	@Test
	public void testConnection() {
		try (Connection con = ds.getConnection()) {
			System.out.println("MySQL Connection Success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
