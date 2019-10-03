package javaassn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.sql.PreparedStatement;
public class MySQL1 {

	
		  // JDBC driver name and database URL
		   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		   static final String DB_URL = "jdbc:mysql://localhost/user?autoReconnect=true&useSSL=false";

		   //  Database credentials
		   static final String USER = "root";
		   static final String PASS = "" ;
		   
		   public static void main(String[] args) {
		   Connection conn = null;
		   PreparedStatement statement = null;
		   try{
			   Class.forName("com.mysql.jdbc.Driver");

		        //STEP 3: Open a connection
		        System.out.println("Connecting to database...");
		        conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		        
		        System.out.println("Creating statement...");
		  
		        String sql = "INSERT  IGNORE INTO userdetails (name, id, phoneno, email,user_status) VALUES (?,?,?,?,?) ";
		         
		         statement = conn.prepareStatement(sql);
		         statement.setString(1, "rakshi");
		         statement.setInt(2, 4);
		         statement.setInt(3, 1245007015);
		         statement.setString(4, "raksh@gmail.com");
		         statement.setString(5, "active");
		         int rowsInserted = statement.executeUpdate();
		         /* try {
		         rowsInserted = statement.executeUpdate();
		          }
		          catch(SQLIntegrityConstraintViolationException si){
			    	  System.out.println("Duplicate entry!");
		          }*/
		         if (rowsInserted > 0) {
		             System.out.println("A new user was inserted successfully!");
		         }
		        
			     
			      sql = "SELECT * FROM userdetails";
		    	  ResultSet rs = statement.executeQuery(sql);

			      //STEP 5: Extract data from result set
			      while(rs.next()){
			         //Retrieve by column name
			         int phoneno  = rs.getInt("phoneno");
			         String name = rs.getString("name");
			         String email = rs.getString("email");
			         String userstatus = rs.getString("user_status");
			         
			         //Display values
			         System.out.print("Phoneno: " + phoneno + " ");
			         System.out.print("Name: " + name + " ");
			         System.out.print("Userstatus: " + userstatus + " ");
			         System.out.println("Email: " + email);
			      }
			         
			         
			             
			         sql = "UPDATE userdetails SET name=?, user_status=?, phoneno=?, email=? WHERE id=?";
			             
			         statement = conn.prepareStatement(sql);
			         statement.setString(1, "abc");
			         statement.setString(2, "inactive");
			         statement.setInt(3, 123554657);
			         statement.setString(4, "abc@gmail.com");
			         statement.setInt(5, 2);
			         
			         int rowsUpdate = statement.executeUpdate();
			         if (rowsUpdate > 0) {
			             System.out.println("An existing user was updated successfully!");
			         }
			              
			         sql = "DELETE FROM userdetails WHERE (id=? AND user_status=?)";
			         
			         statement = conn.prepareStatement(sql);
			         statement.setInt(1, 1);
			         statement.setString(2, "inactive");
			          
			         int rowsDeleted = statement.executeUpdate();
			         if (rowsDeleted > 0) {
			             System.out.println("A user was deleted successfully!");
			         }
			       
			      
			             rs.close();
					      statement.close();
					      conn.close();
					   }catch(SQLException se){
					      //Handle errors for JDBC
					      se.printStackTrace();
					   }catch(Exception e){
					      //Handle errors for Class.forName
					      e.printStackTrace();
					   }finally{
					      //finally block used to close resources
					      try{
					         if(statement!=null)
					            statement.close();
					      }catch(SQLException se2){
					      }// nothing we can do
					      try{
					         if(conn!=null)
					            conn.close();
					      }catch(SQLException se){
					         se.printStackTrace();
					      }//end finally try
					   }//end try
					   System.out.println("Goodbye!");
					}//end main
				}//end FirstExample

