package com.MySQL.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLconnection {
	static Connection conn=null;
	public static  Connection baglan() {
	try {
	//"jdbc:mysql://serveripadress/db_ismi","kuulaniciadi","sifre"
		conn=DriverManager.getConnection("jdbc:mysql://localhost/projedb", "root" ,"mysql");
		return conn;		
		
	} catch(Exception e) {
		System.out.println(e.getMessage().toString());
		return null;
		
	}
}
	
}
