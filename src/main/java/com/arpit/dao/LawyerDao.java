package com.arpit.dao;


import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import java.math.*;
import com.arpit.model.Lawyer;
import com.arpit.NotFoundException;


@Repository
public class LawyerDao {



    public Lawyer createValueObject() {
          return new Lawyer();
    }


    public Lawyer getObject(Connection conn, int lawyer_id) throws NotFoundException, SQLException {

          Lawyer valueObject = createValueObject();
          valueObject.setLawyer_id(lawyer_id);
          load(conn, valueObject);
          return valueObject;
    }


    public void load(Connection conn, Lawyer valueObject) throws NotFoundException, SQLException {

          String sql = "SELECT * FROM lawyer WHERE (lawyer_id = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, valueObject.getLawyer_id()); 

               singleQuery(conn, stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    
    public List loadAll(Connection conn) throws SQLException {

          String sql = "SELECT * FROM lawyer ORDER BY name ASC ";
          List searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }




    public synchronized void create(Connection conn, Lawyer valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO lawyer ( lawyer_id, name, city, "
               + "office_name) VALUES (?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getLawyer_id()); 
               stmt.setString(2, valueObject.getName()); 
               stmt.setString(3, valueObject.getCity()); 
               stmt.setString(4, valueObject.getOffice_name()); 

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


    
    public void save(Connection conn, Lawyer valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE lawyer SET name = ?, city = ?, office_name = ? WHERE (lawyer_id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getName()); 
              stmt.setString(2, valueObject.getCity()); 
              stmt.setString(3, valueObject.getOffice_name()); 

              stmt.setInt(4, valueObject.getLawyer_id()); 

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


   
    public void delete(Connection conn, Lawyer valueObject) 
          throws NotFoundException, SQLException {

          String sql = "DELETE FROM lawyer WHERE (lawyer_id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getLawyer_id()); 

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

          String sql = "DELETE FROM lawyer";
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

          String sql = "SELECT count(*) FROM lawyer";
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


  
    public List searchMatching(Connection conn, Lawyer valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM lawyer WHERE 1=1 ");

          if (valueObject.getLawyer_id() != 0) {
              if (first) { first = false; }
              sql.append("AND lawyer_id = ").append(valueObject.getLawyer_id()).append(" ");
          }

          if (valueObject.getName() != null) {
              if (first) { first = false; }
              sql.append("AND name LIKE '").append(valueObject.getName()).append("%' ");
          }

          if (valueObject.getCity() != null) {
              if (first) { first = false; }
              sql.append("AND city LIKE '").append(valueObject.getCity()).append("%' ");
          }

          if (valueObject.getOffice_name() != null) {
              if (first) { first = false; }
              sql.append("AND office_name LIKE '").append(valueObject.getOffice_name()).append("%' ");
          }


          sql.append("ORDER BY name ASC ");

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



  
    protected void singleQuery(Connection conn, PreparedStatement stmt, Lawyer valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setLawyer_id(result.getInt("lawyer_id")); 
                   valueObject.setName(result.getString("name")); 
                   valueObject.setCity(result.getString("city")); 
                   valueObject.setOffice_name(result.getString("office_name")); 

              } else {
                    //System.out.println("Lawyer Object Not Found!");
                    throw new NotFoundException("Lawyer Object Not Found!");
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
                   Lawyer temp = createValueObject();

                   temp.setLawyer_id(result.getInt("lawyer_id")); 
                   temp.setName(result.getString("name")); 
                   temp.setCity(result.getString("city")); 
                   temp.setOffice_name(result.getString("office_name")); 

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