package GreenHouse_WebService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataDaoDB {
    
    public DataDaoDB ()  {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public List<Sensordata> getAllData(){
        List<Sensordata> allData = new ArrayList<>();
        
        try{
           // Get a connection to database
           Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenhouse", 
                   "root", "o45pm80ab5n3");
           // Create a statement
           Statement myStmt = myConn.createStatement();
           //Execute SQL query
           ResultSet myRs = myStmt.executeQuery("SELECT * FROM greenhouse.sensordata");
           // Process the result set
           while (myRs.next()){
              System.out.println(myRs.getInt("Temperatur") +  "°C "   + myRs.getInt("Belysning")+ "/kWh " + myRs.getInt("El") + "kr/kWh " 
                       + myRs.getInt("Luftfuktighet") + "% " + myRs.getInt("SektorId") + " " + myRs.getDate("Tid")+ " ");
           }
       }
      
        catch (Exception e){
            e.printStackTrace();
        }   
        
        return allData;
    }
    
    
    public boolean deleteData(int id){
       int rowChanged = 0;
        String query = "delete from Sensordata where id = ? ";
                
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenhouse",
                "root","o45pm80ab5n3");
             PreparedStatement stmt = con.prepareStatement(query)){

            stmt.setInt(1, id);
            rowChanged = stmt.executeUpdate();
            if (rowChanged == 1){  //en rad ändrades
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean addData(Sensordata b){
        String query = "insert into Sensordata (temperatur, belysning, el, luftfuktighet, sektorId) values (?, ?, ?, ?, ?)";
        int rowChanged = 0;
                
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenhouse",
                "root","o45pm80ab5n3");
             PreparedStatement stmt = con.prepareStatement(query)){
            
            stmt.setInt(1, b.getTemperatur());
            stmt.setInt(2, b.getBelysning());
            stmt.setInt(3, b.getEl());
            stmt.setInt(4, b.getLuftfuktighet());
            stmt.setInt(5, b.getId());
            rowChanged = stmt.executeUpdate();
            if (rowChanged == 1){  //en rad ändrades
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateData(Sensordata b){
       
        String queryUpdate = "UPDATE Data SET title = ?, author = ? where id = ? ;";
        String queryInsert = "insert into Sensordata (temperatur, belysning, el, luftfuktighet) values (?, ?)";
        int rowChanged = -1;
                
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/greenhouse",
                "root","o45pm80ab5n3");
             PreparedStatement stmtUpdate = con.prepareStatement(queryUpdate);
             PreparedStatement stmtInsert = con.prepareStatement(queryInsert)){

            stmtInsert.setInt(1, b.getTemperatur());
            stmtInsert.setInt(2, b.getBelysning());
            stmtInsert.setInt(3, b.getEl());
            stmtInsert.setInt(4, b.getLuftfuktighet());
            stmtUpdate.setInt(5, b.getId());
            rowChanged = stmtUpdate.executeUpdate();
            System.out.println("i updateData, rowChanged "+ rowChanged);
            
            //det fanns ingen rad att uppdatera, vi gör en insert
            if (rowChanged == 0){
                stmtInsert.setInt(1, b.getTemperatur());
                stmtInsert.setInt(2, b.getBelysning());
                stmtInsert.setInt(3, b.getEl());
                stmtInsert.setInt(4, b.getLuftfuktighet());
                rowChanged = stmtInsert.executeUpdate();
            }
            if (rowChanged == 1){  //en rad ändrades
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
