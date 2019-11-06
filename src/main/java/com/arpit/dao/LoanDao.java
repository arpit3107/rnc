package com.arpit.dao;


import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import java.math.*;
import com.arpit.model.Loan;
import com.arpit.NotFoundException;

@Repository
public class LoanDao {


    public Loan createValueObject() {
          return new Loan();
    }


    public Loan getObject(Connection conn, int loan_id) throws NotFoundException, SQLException {

          Loan valueObject = createValueObject();
          valueObject.setLoan_id(loan_id);
          load(conn, valueObject);
          return valueObject;
    }


    public void load(Connection conn, Loan valueObject) throws NotFoundException, SQLException {

          String sql = "SELECT * FROM loan WHERE (loan_id = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, valueObject.getLoan_id()); 

               singleQuery(conn, stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    public List loadAll(Connection conn) throws SQLException {

          String sql = "SELECT * FROM loan ORDER BY loan_id ASC ";
          List searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }



    public synchronized void create(Connection conn, Loan valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO loan ( loan_id, source_id, amount, "
               + "given_date) VALUES (?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getLoan_id()); 
               stmt.setInt(2, valueObject.getSource_id()); 
               stmt.setString(3, valueObject.getAmount()); 
               stmt.setDate(4, valueObject.getGiven_date()); 

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


    public void save(Connection conn, Loan valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE loan SET source_id = ?, amount = ?, given_date = ? WHERE (loan_id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getSource_id()); 
              stmt.setString(2, valueObject.getAmount()); 
              stmt.setDate(3, valueObject.getGiven_date()); 

              stmt.setInt(4, valueObject.getLoan_id()); 

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


    public void delete(Connection conn, Loan valueObject) 
          throws NotFoundException, SQLException {

          String sql = "DELETE FROM loan WHERE (loan_id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getLoan_id()); 

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

          String sql = "DELETE FROM loan";
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

          String sql = "SELECT count(*) FROM loan";
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


    public List searchMatching(Connection conn, Loan valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM loan WHERE 1=1 ");

          if (valueObject.getLoan_id() != 0) {
              if (first) { first = false; }
              sql.append("AND loan_id = ").append(valueObject.getLoan_id()).append(" ");
          }

          if (valueObject.getSource_id() != 0) {
              if (first) { first = false; }
              sql.append("AND source_id = ").append(valueObject.getSource_id()).append(" ");
          }

          if (valueObject.getAmount() != null) {
              if (first) { first = false; }
              sql.append("AND amount LIKE '").append(valueObject.getAmount()).append("%' ");
          }

          if (valueObject.getGiven_date() != null) {
              if (first) { first = false; }
              sql.append("AND given_date = '").append(valueObject.getGiven_date()).append("' ");
          }


          sql.append("ORDER BY loan_id ASC ");

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



    protected void singleQuery(Connection conn, PreparedStatement stmt, Loan valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setLoan_id(result.getInt("loan_id")); 
                   valueObject.setSource_id(result.getInt("source_id")); 
                   valueObject.setAmount(result.getString("amount")); 
                   valueObject.setGiven_date(result.getDate("given_date")); 

              } else {
                    //System.out.println("Loan Object Not Found!");
                    throw new NotFoundException("Loan Object Not Found!");
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
                   Loan temp = createValueObject();

                   temp.setLoan_id(result.getInt("loan_id")); 
                   temp.setSource_id(result.getInt("source_id")); 
                   temp.setAmount(result.getString("amount")); 
                   temp.setGiven_date(result.getDate("given_date")); 

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
