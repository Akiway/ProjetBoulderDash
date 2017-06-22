package model.dao;

import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BoulderDashBDDConnectorTest {
	private static String                  user     = "pi-ux-ce";
    private static String                  password = "Palyp557";
    private static String                  url      = "jdbc:mysql://mysql-pi-ux-ce.alwaysdata.net/pi-ux-ce_boulderdash?useSSL=false&serverTimezone=UTC";
    private String connection;
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void open() {
		connection = "DriverManager.getConnection(" + url + ", " + user + ", " + password + ")";
		assertEquals("DriverManager.getConnection(jdbc:mysql://mysql-pi-ux-ce.alwaysdata.net/pi-ux-ce_boulderdash?useSSL=false&serverTimezone=UTC, pi-ux-ce, Palyp557)", this.connection);
	}
}
