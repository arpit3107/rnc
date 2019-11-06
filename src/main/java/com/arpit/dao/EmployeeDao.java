package com.arpit.dao;


import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import java.math.*;
import com.arpit.model.Employee;
import com.arpit.NotFoundException;



@Repository
public class EmployeeDao {



    public Employee createValueObject() {
          return new Employee();
    }

    public Employee getObject(Connection conn, int id) throws NotFoundException, SQLException {

          Employee valueObject = createValueObject();
          valueObject.setId(id);
          load(conn, valueObject);
          return valueObject;
    }


  
    public void load(Connection conn, Employee valueObject) throws NotFoundException, SQLException {

          String sql = "SELECT * FROM employee WHERE (id = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, valueObject.getId()); 

               singleQuery(conn, stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


 
    public List loadAll(Connection conn) throws SQLException {

          String sql = "SELECT * FROM employee ORDER BY id ASC ";
          List searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }



    
    public synchronized void create(Connection conn, Employee valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO employee ( id, name, address, "
               + "email_id) VALUES (?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getId()); 
               stmt.setString(2, valueObject.getName()); 
               stmt.setString(3, valueObject.getAddress()); 
               stmt.setString(4, valueObject.getEmail_id()); 

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


    public void save(Connection conn, Employee valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE employee SET name = ?, address = ?, email_id = ? WHERE (id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getName()); 
              stmt.setString(2, valueObject.getAddress()); 
              stmt.setString(3, valueObject.getEmail_id()); 

              stmt.setInt(4, valueObject.getId()); 

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


  
    public void delete(Connection conn, Employee valueObject) 
          throws NotFoundException, SQLException {

          String sql = "DELETE FROM employee WHERE (id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getId()); 

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

          String sql = "DELETE FROM employee";
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

          String sql = "SELECT count(*) FROM employee";
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


   
    public List searchMatching(Connection conn, Employee valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM employee WHERE 1=1 ");

          if (valueObject.getId() != 0) {
              if (first) { first = false; }
              sql.append("AND id = ").append(valueObject.getId()).append(" ");
          }

          if (valueObject.getName() != null) {
              if (first) { first = false; }
              sql.append("AND name LIKE '").append(valueObject.getName()).append("%' ");
          }

          if (valueObject.getAddress() != null) {
              if (first) { first = false; }
              sql.append("AND address LIKE '").append(valueObject.getAddress()).append("%' ");
          }

          if (valueObject.getEmail_id() != null) {
              if (first) { first = false; }
              sql.append("AND email_id LIKE '").append(valueObject.getEmail_id()).append("%' ");
          }


          sql.append("ORDER BY id ASC ");

          // Prevent accidential full table results.
          // Use loadAll if all rows must be returned.
          if (first)
               searchResults = new ArrayList();
          else
               searchResults = listQuery(conn, conn.prepareStatement(sql.toString()));

          return searchResults;
    }


    public String getDaogenVersion() {
        return "DaoGen version 2.4.1";
    }


    protected int databaseUpdate(Connection conn, PreparedStatement stmt) throws SQLException {

          int result = stmt.executeUpdate();

          return result;
    }



    protected void singleQuery(Connection conn, PreparedStatement stmt, Employee valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setId(result.getInt("id")); 
                   valueObject.setName(result.getString("name")); 
                   valueObject.setAddress(result.getString("address")); 
                   valueObject.setEmail_id(result.getString("email_id")); 

              } else {
                    //System.out.println("Employee Object Not Found!");
                    throw new NotFoundException("Employee Object Not Found!");
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
                   Employee temp = createValueObject();

                   temp.setId(result.getInt("id")); 
                   temp.setName(result.getString("name")); 
                   temp.setAddress(result.getString("address")); 
                   temp.setEmail_id(result.getString("email_id")); 

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