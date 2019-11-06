package com.arpit.dao;


import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import java.math.*;
import com.arpit.NotFoundException;
import com.arpit.model.Partners;


@Repository
public class PartnersDao {



    public Partners createValueObject() {
          return new Partners();
    }


    public Partners getObject(Connection conn, String name) throws NotFoundException, SQLException {

          Partners valueObject = createValueObject();
          valueObject.setName(name);
          load(conn, valueObject);
          return valueObject;
    }


    public void load(Connection conn, Partners valueObject) throws NotFoundException, SQLException {

          if (valueObject.getName() == null) {
               //System.out.println("Can not select without Primary-Key!");
               throw new NotFoundException("Can not select without Primary-Key!");
          }

          String sql = "SELECT * FROM partners WHERE (name = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setString(1, valueObject.getName()); 

               singleQuery(conn, stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


   
    public List loadAll(Connection conn) throws SQLException {

          String sql = "SELECT * FROM partners ORDER BY name ASC ";
          List searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }



   
    public synchronized void create(Connection conn, Partners valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO partners ( name, contact, total_share, "
               + "representative) VALUES (?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setString(1, valueObject.getName()); 
               stmt.setString(2, valueObject.getContact()); 
               stmt.setDouble(3, valueObject.getTotal_share()); 
               stmt.setString(4, valueObject.getRepresentative()); 

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


   
    public void save(Connection conn, Partners valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE partners SET contact = ?, total_share = ?, representative = ? WHERE (name = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getContact()); 
              stmt.setDouble(2, valueObject.getTotal_share()); 
              stmt.setString(3, valueObject.getRepresentative()); 

              stmt.setString(4, valueObject.getName()); 

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

    public void delete(Connection conn, Partners valueObject) 
          throws NotFoundException, SQLException {

          if (valueObject.getName() == null) {
               //System.out.println("Can not delete without Primary-Key!");
               throw new NotFoundException("Can not delete without Primary-Key!");
          }

          String sql = "DELETE FROM partners WHERE (name = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getName()); 

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

          String sql = "DELETE FROM partners";
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

          String sql = "SELECT count(*) FROM partners";
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


    public List searchMatching(Connection conn, Partners valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM partners WHERE 1=1 ");

          if (valueObject.getName() != null) {
              if (first) { first = false; }
              sql.append("AND name LIKE '").append(valueObject.getName()).append("%' ");
          }

          if (valueObject.getContact() != null) {
              if (first) { first = false; }
              sql.append("AND contact LIKE '").append(valueObject.getContact()).append("%' ");
          }

          if (valueObject.getTotal_share() != 0) {
              if (first) { first = false; }
              sql.append("AND total_share = ").append(valueObject.getTotal_share()).append(" ");
          }

          if (valueObject.getRepresentative() != null) {
              if (first) { first = false; }
              sql.append("AND representative LIKE '").append(valueObject.getRepresentative()).append("%' ");
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



    protected void singleQuery(Connection conn, PreparedStatement stmt, Partners valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setName(result.getString("name")); 
                   valueObject.setContact(result.getString("contact")); 
                   valueObject.setTotal_share(result.getDouble("total_share")); 
                   valueObject.setRepresentative(result.getString("representative")); 

              } else {
                    //System.out.println("Partners Object Not Found!");
                    throw new NotFoundException("Partners Object Not Found!");
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
                   Partners temp = createValueObject();

                   temp.setName(result.getString("name")); 
                   temp.setContact(result.getString("contact")); 
                   temp.setTotal_share(result.getDouble("total_share")); 
                   temp.setRepresentative(result.getString("representative")); 

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