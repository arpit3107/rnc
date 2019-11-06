package com.arpit.dao;


import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import java.math.*;
import com.arpit.model.Machinery;
import com.arpit.NotFoundException;

@Repository
public class MachineryDao {



    public Machinery createValueObject() {
          return new Machinery();
    }


    public Machinery getObject(Connection conn, int tag) throws NotFoundException, SQLException {

          Machinery valueObject = createValueObject();
          valueObject.setTag(tag);
          load(conn, valueObject);
          return valueObject;
    }


    public void load(Connection conn, Machinery valueObject) throws NotFoundException, SQLException {

          String sql = "SELECT * FROM machinery WHERE (tag = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, valueObject.getTag()); 

               singleQuery(conn, stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }

    public List loadAll(Connection conn) throws SQLException {

          String sql = "SELECT * FROM machinery ORDER BY name ASC ";
          List searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }


    public synchronized void create(Connection conn, Machinery valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO machinery ( tag, price, name, "
               + "site_id, driver_id) VALUES (?, ?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getTag()); 
               stmt.setString(2, valueObject.getPrice()); 
               stmt.setString(3, valueObject.getName()); 
               stmt.setInt(4, valueObject.getSite_id()); 
               stmt.setInt(5, valueObject.getDriver_id()); 

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
    public void save(Connection conn, Machinery valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE machinery SET price = ?, name = ?, site_id = ?, "
               + "driver_id = ? WHERE (tag = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getPrice()); 
              stmt.setString(2, valueObject.getName()); 
              stmt.setInt(3, valueObject.getSite_id()); 
              stmt.setInt(4, valueObject.getDriver_id()); 

              stmt.setInt(5, valueObject.getTag()); 

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


    public void delete(Connection conn, Machinery valueObject) 
          throws NotFoundException, SQLException {

          String sql = "DELETE FROM machinery WHERE (tag = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getTag()); 

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

          String sql = "DELETE FROM machinery";
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

          String sql = "SELECT count(*) FROM machinery";
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


    public List searchMatching(Connection conn, Machinery valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM machinery WHERE 1=1 ");

          if (valueObject.getTag() != 0) {
              if (first) { first = false; }
              sql.append("AND tag = ").append(valueObject.getTag()).append(" ");
          }

          if (valueObject.getPrice() != null) {
              if (first) { first = false; }
              sql.append("AND price LIKE '").append(valueObject.getPrice()).append("%' ");
          }

          if (valueObject.getName() != null) {
              if (first) { first = false; }
              sql.append("AND name LIKE '").append(valueObject.getName()).append("%' ");
          }

          if (valueObject.getSite_id() != 0) {
              if (first) { first = false; }
              sql.append("AND site_id = ").append(valueObject.getSite_id()).append(" ");
          }

          if (valueObject.getDriver_id() != 0) {
              if (first) { first = false; }
              sql.append("AND driver_id = ").append(valueObject.getDriver_id()).append(" ");
          }


          sql.append("ORDER BY tag ASC ");

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


    protected void singleQuery(Connection conn, PreparedStatement stmt, Machinery valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setTag(result.getInt("tag")); 
                   valueObject.setPrice(result.getString("price")); 
                   valueObject.setName(result.getString("name")); 
                   valueObject.setSite_id(result.getInt("site_id")); 
                   valueObject.setDriver_id(result.getInt("driver_id")); 

              } else {
                    //System.out.println("Machinery Object Not Found!");
                    throw new NotFoundException("Machinery Object Not Found!");
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
                   Machinery temp = createValueObject();

                   temp.setTag(result.getInt("tag")); 
                   temp.setPrice(result.getString("price")); 
                   temp.setName(result.getString("name")); 
                   temp.setSite_id(result.getInt("site_id")); 
                   temp.setDriver_id(result.getInt("driver_id")); 

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
