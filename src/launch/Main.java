package launch;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import game.BlockType;
import game.Logic;
import gametable.Table;
import java.sql.*;
public class Main {
	


	public static void main(String[] args) {
		try {
			String host= "jdbc:mysql://127.0.0.1:3306/tetris";
			String uName="root";
			String uPass="";
			Logic l= new Logic();
			Connection con = DriverManager.getConnection(host,uName,uPass);
			  Statement stmt = con.createStatement(); 
			  String q1 = "insert into score values('" +String.valueOf(l.getHighscore())+ "')";
			  String q2 = "select highscore from score"; 
			  ResultSet rs = stmt.executeQuery(q2); 
			  int x = stmt.executeUpdate(q1); 
if (x > 0)  {       
    System.out.println("Successfully Inserted");}             
else            {
    System.out.println("Insert Failed"); 
con.close(); 
	}
		}
		catch(SQLException err){
			System.out.println(err.getMessage( ) );
		}
		
	
		Logic.currentBlock = new BlockType();
		Logic.blocks.add(Logic.currentBlock);
		Logic.nextBlock = new BlockType();
		
		try {
			Table g = new Table();
			g.create();
		} catch (IOException e) {
			System.out.println("ERROR");
			e.printStackTrace(); 
		}
		
		startLoop();
		
	}
	
	public static void startLoop() {
		Movement loop = new Movement();
		loop.start();
	}

}
