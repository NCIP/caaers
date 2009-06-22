package gov.nih.nci.cagrid.antinstaller.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DataBaseConnectionTester {
	
	public boolean testConnection(String databaseVendor,
			                     String userId,
			                     String password,
			                     String hostName,
			                     String port,
			                     String databaseName){
		boolean ok = true;
		Connection connection=null;
		String driver=null;
		String url=null;
		if(databaseVendor.equalsIgnoreCase("postgres")){
			driver = "org.postgresql.Driver";
			url= "jdbc:postgresql://"+hostName+":"+port+"/"+databaseName;
		}
		if(databaseVendor.equalsIgnoreCase("oracle")){
			driver = "oracle.jdbc.driver.OracleDriver";
			url = "jdbc:oracle:thin:@"+hostName+":"+port+":"+databaseName;
		
		}
		
		connection = this.getConnection(userId, password, driver, url);
		
		if(connection==null){
			ok=false;
		}
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return ok;
	}
	
	
	
	
	
	public boolean csmPopulated(String databaseVendor,
            String userId,
            String password,
            String hostName,
            String port,
            String databaseName) throws Exception{
		boolean isPopulated = true;
		Connection connection=null;
		String driver=null;
		String url=null;
		if(databaseVendor.equalsIgnoreCase("postgres")){
			driver = "org.postgresql.Driver";
			url= "jdbc:postgresql://"+hostName+":"+port+"/"+databaseName;
		}
		if(databaseVendor.equalsIgnoreCase("oracle")){
			driver = "oracle.jdbc.driver.OracleDriver";
			url = "jdbc:oracle:thin:@"+hostName+":"+port+":"+databaseName;
		
		}
		
		connection = this.getConnection(userId, password, driver, url);
		
		if(connection==null){
			throw new Exception("Unknown");
		}
		Statement stmt;
		try {
			stmt = connection.createStatement();
			ResultSet rs =  stmt.executeQuery("select count(*) from CSM_USER");
			int i=0;
			while(rs.next()){
				i = rs.getInt(1);
			}
			if(i==0){
				isPopulated=false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//throw new Exception("Unknown");
			isPopulated=false;
		}
		if(connection!=null){
			connection.close();
		}
		
		
		
		return isPopulated;
		
	}
	
	private Connection getConnection(String userId,
            String password,
            String driver,
            String url){
		
		Connection connection=null;
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			connection=null;
		}
		
		try {
			connection = DriverManager.getConnection(url, userId, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			connection=null;
		}
		
		return connection;
		
	}
	public static void main(String[] args){
		
		DataBaseConnectionTester dct = new DataBaseConnectionTester();
		//System.out.println(dct.testConnection("postgres", "postgres", "postgres", "localhost", "5432", "caaers"));
		try {
			System.out.println(dct.csmPopulated("postgres", "postgres", "postgres", "localhost", "5432", "caaers"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
