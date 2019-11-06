package com.arpit.dao;


import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import java.math.*;
import com.arpit.model.Site;
import com.arpit.NotFoundException;



@Repository
public class SiteDao {



    public Site createValueObject() {
          return new Site();
    }

    public Site getObject(Connection conn, int site_id) throws NotFoundException, SQLException {

          Site valueObject = createValueObject();
          valueObject.setSite_id(site_id);
          load(conn, valueObject);
          return valueObject;
    }


    public void load(Connection conn, Site valueObject) throws NotFoundException, SQLException {

          String sql = "SELECT * FROM site WHERE (site_id = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, valueObject.getSite_id()); 

               singleQuery(conn, stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    public List loadAll(Connection conn) throws SQLException {

          String sql = "SELECT * FROM site ORDER BY site_id ASC ";
          List searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }



    public synchronized void create(Connection conn, Site valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO site ( site_id, name, city, "
               + "starting_date, ending_date, number_of_labours, "
               + "manager_id) VALUES (?, ?, ?, ?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getSite_id()); 
               stmt.setString(2, valueObject.getName()); 
               stmt.setString(3, valueObject.getCity()); 
               stmt.setDate(4, valueObject.getStarting_date()); 
               stmt.setDate(5, valueObject.getEnding_date()); 
               stmt.setInt(6, valueObject.getNumber_of_labours()); 
               stmt.setInt(7, valueObject.getManager_id()); 

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


    public void save(Connection conn, Site valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE site SET name = ?, city = ?, starting_date = ?, "
               + "ending_date = ?, number_of_labours = ?, manager_id = ? WHERE (site_id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getName()); 
              stmt.setString(2, valueObject.getCity()); 
              stmt.setDate(3, valueObject.getStarting_date()); 
              stmt.setDate(4, valueObject.getEnding_date()); 
              stmt.setInt(5, valueObject.getNumber_of_labours()); 
              stmt.setInt(6, valueObject.getManager_id()); 

              stmt.setInt(7, valueObject.getSite_id()); 

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


    public void delete(Connection conn, Site valueObject) 
          throws NotFoundException, SQLException {

          String sql = "DELETE FROM site WHERE (site_id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getSite_id()); 

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

          String sql = "DELETE FROM site";
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

          String sql = "SELECT count(*) FROM site";
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


    public List searchMatching(Connection conn, Site valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM site WHERE 1=1 ");

          if (valueObject.getSite_id() != 0) {
              if (first) { first = false; }
              sql.append("AND site_id = ").append(valueObject.getSite_id()).append(" ");
          }

          if (valueObject.getName() != null) {
              if (first) { first = false; }
              sql.append("AND name LIKE '").append(valueObject.getName()).append("%' ");
          }

          if (valueObject.getCity() != null) {
              if (first) { first = false; }
              sql.append("AND city LIKE '").append(valueObject.getCity()).append("%' ");
          }

          if (valueObject.getStarting_date() != null) {
              if (first) { first = false; }
              sql.append("AND starting_date = '").append(valueObject.getStarting_date()).append("' ");
          }

          if (valueObject.getEnding_date() != null) {
              if (first) { first = false; }
              sql.append("AND ending_date = '").append(valueObject.getEnding_date()).append("' ");
          }

          if (valueObject.getNumber_of_labours() != 0) {
              if (first) { first = false; }
              sql.append("AND number_of_labours = ").append(valueObject.getNumber_of_labours()).append(" ");
          }

          if (valueObject.getManager_id() != 0) {
              if (first) { first = false; }
              sql.append("AND manager_id = ").append(valueObject.getManager_id()).append(" ");
          }


          sql.append("ORDER BY site_id ASC ");

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



    
    protected void singleQuery(Connection conn, PreparedStatement stmt, Site valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setSite_id(result.getInt("site_id")); 
                   valueObject.setName(result.getString("name")); 
                   valueObject.setCity(result.getString("city")); 
                   valueObject.setStarting_date(result.getDate("starting_date")); 
                   valueObject.setEnding_date(result.getDate("ending_date")); 
                   valueObject.setNumber_of_labours(result.getInt("number_of_labours")); 
                   valueObject.setManager_id(result.getInt("manager_id")); 

              } else {
                    //System.out.println("Site Object Not Found!");
                    throw new NotFoundException("Site Object Not Found!");
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
                   Site temp = createValueObject();

                   temp.setSite_id(result.getInt("site_id")); 
                   temp.setName(result.getString("name")); 
                   temp.setCity(result.getString("city")); 
                   temp.setStarting_date(result.getDate("starting_date")); 
                   temp.setEnding_date(result.getDate("ending_date")); 
                   temp.setNumber_of_labours(result.getInt("number_of_labours")); 
                   temp.setManager_id(result.getInt("manager_id")); 

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

