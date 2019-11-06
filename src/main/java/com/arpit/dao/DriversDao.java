package com.arpit.dao;


import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import java.math.*;
import com.arpit.model.Drivers;
import com.arpit.NotFoundException;



@Repository
public class DriversDao {



    public Drivers createValueObject() {
          return new Drivers();
    }

    public Drivers getObject(Connection conn, int driver_id) throws NotFoundException, SQLException {

          Drivers valueObject = createValueObject();
          valueObject.setDriver_id(driver_id);
          load(conn, valueObject);
          return valueObject;
    }


    public void load(Connection conn, Drivers valueObject) throws NotFoundException, SQLException {

          String sql = "SELECT * FROM drivers WHERE (driver_id = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, valueObject.getDriver_id()); 

               singleQuery(conn, stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    public List loadAll(Connection conn) throws SQLException {

          String sql = "SELECT * FROM drivers ORDER BY driver_id ASC ";
          List searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }



    public synchronized void create(Connection conn, Drivers valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO drivers ( driver_id, license_number, expertise) VALUES (?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getDriver_id()); 
               stmt.setString(2, valueObject.getLicense_number()); 
               stmt.setString(3, valueObject.getExpertise()); 

               int rowcount = databaseUpdate(conn, stmt);
               if (rowcount != 1) {
                    //System.out.println("PrimaryKey Error when updating DB!");
                    throw new SQLException("PrimaryKey Error when updating DB!");
               }

          } finally {
              if (stmt != null)
                  stmt.close();
          }


    }


    
    public void save(Connection conn, Drivers valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE drivers SET license_number = ?, expertise = ? WHERE (driver_id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getLicense_number()); 
              stmt.setString(2, valueObject.getExpertise()); 

              stmt.setInt(3, valueObject.getDriver_id()); 

              int rowcount = databaseUpdate(conn, stmt);
              if (rowcount == 0) {
                   //System.out.println("Object could not be saved! (PrimaryKey not found)");
                   throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
              }
              if (rowcount > 1) {
                   //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                   throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
              }
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    public void delete(Connection conn, Drivers valueObject) 
          throws NotFoundException, SQLException {

          String sql = "DELETE FROM drivers WHERE (driver_id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getDriver_id()); 

              int rowcount = databaseUpdate(conn, stmt);
              if (rowcount == 0) {
                   //System.out.println("Object could not be deleted (PrimaryKey not found)");
                   throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
              }
              if (rowcount > 1) {
                   //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                   throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
              }
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    
    public void deleteAll(Connection conn) throws SQLException {

          String sql = "DELETE FROM drivers";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              int rowcount = databaseUpdate(conn, stmt);
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }

    public int countAll(Connection conn) throws SQLException {

          String sql = "SELECT count(*) FROM drivers";
          PreparedStatement stmt = null;
          ResultSet result = null;
          int allRows = 0;

          try {
              stmt = conn.prepareStatement(sql);
              result = stmt.executeQuery();

              if (result.next())
                  allRows = result.getInt(1);
          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }
          return allRows;
    }


    public List searchMatching(Connection conn, Drivers valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM drivers WHERE 1=1 ");

          if (valueObject.getDriver_id() != 0) {
              if (first) { first = false; }
              sql.append("AND driver_id = ").append(valueObject.getDriver_id()).append(" ");
          }

          if (valueObject.getLicense_number() != null) {
              if (first) { first = false; }
              sql.append("AND license_number LIKE '").append(valueObject.getLicense_number()).append("%' ");
          }

          if (valueObject.getExpertise() != null) {
              if (first) { first = false; }
              sql.append("AND expertise LIKE '").append(valueObject.getExpertise()).append("%' ");
          }


          sql.append("ORDER BY driver_id ASC ");

          // Prevent accidential full table results.
          // Use loadAll if all rows must be returned.
          if (first)
               searchResults = new ArrayList();
          else
               searchResults = listQuery(conn, conn.prepareStatement(sql.toString()));

          return searchResults;
    }


  
    protected int databaseUpdate(Connection conn, PreparedStatement stmt) throws SQLException {

          int result = stmt.executeUpdate();

          return result;
    }



   
    protected void singleQuery(Connection conn, PreparedStatement stmt, Drivers valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setDriver_id(result.getInt("driver_id")); 
                   valueObject.setLicense_number(result.getString("license_number")); 
                   valueObject.setExpertise(result.getString("expertise")); 

              } else {
                    //System.out.println("Drivers Object Not Found!");
                    throw new NotFoundException("Drivers Object Not Found!");
              }
          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }
    }


    protected List listQuery(Connection conn, PreparedStatement stmt) throws SQLException {

          ArrayList searchResults = new ArrayList();
          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              while (result.next()) {
                   Drivers temp = createValueObject();

                   temp.setDriver_id(result.getInt("driver_id")); 
                   temp.setLicense_number(result.getString("license_number")); 
                   temp.setExpertise(result.getString("expertise")); 

                   searchResults.add(temp);
              }

          } finally {
              if (result != null)
                  result.close();
              if (stmt != null)
                  stmt.close();
          }

          return (List)searchResults;
    }


}
