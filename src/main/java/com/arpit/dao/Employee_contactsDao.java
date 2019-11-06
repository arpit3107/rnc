package com.arpit.dao;


import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import java.math.*;
import com.arpit.model.Employee_contacts;
import com.arpit.NotFoundException;

 

@Repository
public class Employee_contactsDao {



    public Employee_contacts createValueObject() {
          return new Employee_contacts();
    }

    public Employee_contacts getObject(Connection conn, int employee_id, String contact) throws NotFoundException, SQLException {

          Employee_contacts valueObject = createValueObject();
          valueObject.setEmployee_id(employee_id);
          valueObject.setContact(contact);
          load(conn, valueObject);
          return valueObject;
    }


    public void load(Connection conn, Employee_contacts valueObject) throws NotFoundException, SQLException {

          if (valueObject.getContact() == null) {
               //System.out.println("Can not select without Primary-Key!");
               throw new NotFoundException("Can not select without Primary-Key!");
          }

          String sql = "SELECT * FROM employee_contacts WHERE (employee_id = ? AND contact = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, valueObject.getEmployee_id()); 
               stmt.setString(2, valueObject.getContact()); 

               singleQuery(conn, stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    public List loadAll(Connection conn) throws SQLException {

          String sql = "SELECT * FROM employee_contacts ORDER BY employee_id ASC ";
          List searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }

    public synchronized void create(Connection conn, Employee_contacts valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO employee_contacts ( employee_id, contact) VALUES (?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getEmployee_id()); 
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


    public void save(Connection conn, Employee_contacts valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE employee_contacts SET  WHERE (employee_id = ? AND contact = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);

              stmt.setInt(1, valueObject.getEmployee_id()); 
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

    public void delete(Connection conn, Employee_contacts valueObject) 
          throws NotFoundException, SQLException {

          if (valueObject.getContact() == null) {
               //System.out.println("Can not delete without Primary-Key!");
               throw new NotFoundException("Can not delete without Primary-Key!");
          }

          String sql = "DELETE FROM employee_contacts WHERE (employee_id = ? AND contact = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getEmployee_id()); 
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

          String sql = "DELETE FROM employee_contacts";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              databaseUpdate(conn, stmt);
          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }
    
    public void remove(Connection conn,Employee_contacts valueObject) throws SQLException {

        StringBuffer sql = new StringBuffer("DELETE FROM employee_contacts ");
        sql.append("WHERE employee_id =").append(valueObject.getEmployee_id());
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql.toString());
            databaseUpdate(conn, stmt);
        } finally {
            if (stmt != null)
                stmt.close();
        }
  }

    public int countAll(Connection conn) throws SQLException {

          String sql = "SELECT count(*) FROM employee_contacts ";
          
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


    public List searchMatching(Connection conn, Employee_contacts valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM employee_contacts WHERE 1=1 ");

          if (valueObject.getEmployee_id() != 0) {
              if (first) { first = false; }
              sql.append("AND employee_id = ").append(valueObject.getEmployee_id()).append(" ");
          }

          if (valueObject.getContact() != null) {
              if (first) { first = false; }
              sql.append("AND contact LIKE '").append(valueObject.getContact()).append("%' ");
          }


          sql.append("ORDER BY employee_id ASC ");

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



    protected void singleQuery(Connection conn, PreparedStatement stmt, Employee_contacts valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setEmployee_id(result.getInt("employee_id")); 
                   valueObject.setContact(result.getString("contact")); 

              } else {
                    //System.out.println("Employee_contacts Object Not Found!");
                    throw new NotFoundException("Employee_contacts Object Not Found!");
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
                   Employee_contacts temp = createValueObject();

                   temp.setEmployee_id(result.getInt("employee_id")); 
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
