package com.arpit.dao;


import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import java.math.*;
import com.arpit.model.Bank;
import com.arpit.NotFoundException;



@Repository
public class BankDao {



    public Bank createValueObject() {
          return new Bank();
    }


    
    public Bank getObject(Connection conn, String account_number) throws NotFoundException, SQLException {

          Bank valueObject = createValueObject();
          valueObject.setAccount_number(account_number);
          load(conn, valueObject);
          return valueObject;
    }
 void load(Connection conn, Bank valueObject) throws NotFoundException, SQLException {

          if (valueObject.getAccount_number() == null) {
               //System.out.println("Can not select without Primary-Key!");
               throw new NotFoundException("Can not select without Primary-Key!");
          }

          String sql = "SELECT * FROM bank WHERE (account_number = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setString(1, valueObject.getAccount_number()); 

               singleQuery(conn, stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    
    public List loadAll(Connection conn) throws SQLException {

          String sql = "SELECT * FROM bank ORDER BY account_number ASC ";
          List searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }



    public synchronized void create(Connection conn, Bank valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO bank ( account_number, name, branch, "
               + "balance, employee_id) VALUES (?, ?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setString(1, valueObject.getAccount_number()); 
               stmt.setString(2, valueObject.getName()); 
               stmt.setString(3, valueObject.getBranch()); 
               stmt.setString(4, valueObject.getBalance()); 
               stmt.setInt(5, valueObject.getEmployee_id()); 

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


    public void save(Connection conn, Bank valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE bank SET name = ?, branch = ?, balance = ?, "
               + "employee_id = ? WHERE (account_number = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getName()); 
              stmt.setString(2, valueObject.getBranch()); 
              stmt.setString(3, valueObject.getBalance()); 
              stmt.setInt(4, valueObject.getEmployee_id()); 

              stmt.setString(5, valueObject.getAccount_number()); 

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


   
    public void delete(Connection conn, Bank valueObject) 
          throws NotFoundException, SQLException {

          if (valueObject.getAccount_number() == null) {
               //System.out.println("Can not delete without Primary-Key!");
               throw new NotFoundException("Can not delete without Primary-Key!");
          }

          String sql = "DELETE FROM bank WHERE (account_number = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getAccount_number()); 

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

          String sql = "DELETE FROM bank";
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

          String sql = "SELECT count(*) FROM bank";
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


    public List searchMatching(Connection conn, Bank valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM bank WHERE 1=1 ");

          if (valueObject.getAccount_number() != null) {
              if (first) { first = false; }
              sql.append("AND account_number LIKE '").append(valueObject.getAccount_number()).append("%' ");
          }

          if (valueObject.getName() != null) {
              if (first) { first = false; }
              sql.append("AND name LIKE '").append(valueObject.getName()).append("%' ");
          }

          if (valueObject.getBranch() != null) {
              if (first) { first = false; }
              sql.append("AND branch LIKE '").append(valueObject.getBranch()).append("%' ");
          }

          if (valueObject.getBalance() != null) {
              if (first) { first = false; }
              sql.append("AND balance LIKE '").append(valueObject.getBalance()).append("%' ");
          }

          if (valueObject.getEmployee_id() != 0) {
              if (first) { first = false; }
              sql.append("AND employee_id = ").append(valueObject.getEmployee_id()).append(" ");
          }


          sql.append("ORDER BY account_number ASC ");

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



    protected void singleQuery(Connection conn, PreparedStatement stmt, Bank valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setAccount_number(result.getString("account_number")); 
                   valueObject.setName(result.getString("name")); 
                   valueObject.setBranch(result.getString("branch")); 
                   valueObject.setBalance(result.getString("balance")); 
                   valueObject.setEmployee_id(result.getInt("employee_id")); 

              } else {
                    //System.out.println("Bank Object Not Found!");
                    throw new NotFoundException("Bank Object Not Found!");
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
                   Bank temp = createValueObject();

                   temp.setAccount_number(result.getString("account_number")); 
                   temp.setName(result.getString("name")); 
                   temp.setBranch(result.getString("branch")); 
                   temp.setBalance(result.getString("balance")); 
                   temp.setEmployee_id(result.getInt("employee_id")); 

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

