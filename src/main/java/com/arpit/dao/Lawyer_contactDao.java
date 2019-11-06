package com.arpit.dao;


import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import java.math.*;
import com.arpit.model.Lawyer_contact;
import com.arpit.NotFoundException;



@Repository
public class Lawyer_contactDao {



   
    public Lawyer_contact createValueObject() {
          return new Lawyer_contact();
    }


    public Lawyer_contact getObject(Connection conn, int lawyer_id, String contact) throws NotFoundException, SQLException {

          Lawyer_contact valueObject = createValueObject();
          valueObject.setLawyer_id(lawyer_id);
          valueObject.setContact(contact);
          load(conn, valueObject);
          return valueObject;
    }


   
    public void load(Connection conn, Lawyer_contact valueObject) throws NotFoundException, SQLException {

          if (valueObject.getContact() == null) {
               //System.out.println("Can not select without Primary-Key!");
               throw new NotFoundException("Can not select without Primary-Key!");
          }

          String sql = "SELECT * FROM lawyer_contact WHERE (lawyer_id = ? AND contact = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, valueObject.getLawyer_id()); 
               stmt.setString(2, valueObject.getContact()); 

               singleQuery(conn, stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


  
    public List loadAll(Connection conn) throws SQLException {

          String sql = "SELECT * FROM lawyer_contact ORDER BY contact ASC ";
          List searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }



    
    public synchronized void create(Connection conn, Lawyer_contact valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO lawyer_contact ( lawyer_id, contact) VALUES (?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getLawyer_id()); 
               stmt.setString(2, valueObject.getContact()); 

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


   
    public void save(Connection conn, Lawyer_contact valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE lawyer_contact SET  WHERE (lawyer_id = ? AND contact = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);

              stmt.setInt(1, valueObject.getLawyer_id()); 
              stmt.setString(2, valueObject.getContact()); 

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


    
    public void delete(Connection conn, Lawyer_contact valueObject) 
          throws NotFoundException, SQLException {

          if (valueObject.getContact() == null) {
               //System.out.println("Can not delete without Primary-Key!");
               throw new NotFoundException("Can not delete without Primary-Key!");
          }

          String sql = "DELETE FROM lawyer_contact WHERE (lawyer_id = ? AND contact = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getLawyer_id()); 
              stmt.setString(2, valueObject.getContact()); 

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

          String sql = "DELETE FROM lawyer_contact";
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

          String sql = "SELECT count(*) FROM lawyer_contact";
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


    public List searchMatching(Connection conn, Lawyer_contact valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM lawyer_contact WHERE 1=1 ");

          if (valueObject.getLawyer_id() != 0) {
              if (first) { first = false; }
              sql.append("AND lawyer_id = ").append(valueObject.getLawyer_id()).append(" ");
          }

          if (valueObject.getContact() != null) {
              if (first) { first = false; }
              sql.append("AND contact LIKE '").append(valueObject.getContact()).append("%' ");
          }


          sql.append("ORDER BY contact ASC ");

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



    protected void singleQuery(Connection conn, PreparedStatement stmt, Lawyer_contact valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setLawyer_id(result.getInt("lawyer_id")); 
                   valueObject.setContact(result.getString("contact")); 

              } else {
                    //System.out.println("Lawyer_contact Object Not Found!");
                    throw new NotFoundException("Lawyer_contact Object Not Found!");
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
                   Lawyer_contact temp = createValueObject();

                   temp.setLawyer_id(result.getInt("lawyer_id")); 
                   temp.setContact(result.getString("contact")); 

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

