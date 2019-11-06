package com.arpit.dao;


import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import java.math.*;
import com.arpit.model.Tenders;
import com.arpit.NotFoundException;



@Repository
public class TendersDao {



    public Tenders createValueObject() {
          return new Tenders();
    }

    public Tenders getObject(Connection conn, int tender_id) throws NotFoundException, SQLException {

          Tenders valueObject = createValueObject();
          valueObject.setTender_id(tender_id);
          load(conn, valueObject);
          return valueObject;
    }


    public void load(Connection conn, Tenders valueObject) throws NotFoundException, SQLException {

          String sql = "SELECT * FROM tenders WHERE (tender_id = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, valueObject.getTender_id()); 

               singleQuery(conn, stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    public List loadAll(Connection conn) throws SQLException {

          String sql = "SELECT * FROM tenders ORDER BY tender_id ASC ";
          List searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }



   
    public synchronized void create(Connection conn, Tenders valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO tenders ( tender_id, department, city, "
               + "last_date, cost, managed_by) VALUES (?, ?, ?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getTender_id()); 
               stmt.setString(2, valueObject.getDepartment()); 
               stmt.setString(3, valueObject.getCity()); 
               stmt.setDate(4, valueObject.getLast_date()); 
               stmt.setString(5, valueObject.getCost()); 
               stmt.setInt(6, valueObject.getManaged_by()); 

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


    public void save(Connection conn, Tenders valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE tenders SET department = ?, city = ?, last_date = ?, "
               + "cost = ?, managed_by = ? WHERE (tender_id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getDepartment()); 
              stmt.setString(2, valueObject.getCity()); 
              stmt.setDate(3, valueObject.getLast_date()); 
              stmt.setString(4, valueObject.getCost()); 
              stmt.setInt(5, valueObject.getManaged_by()); 

              stmt.setInt(6, valueObject.getTender_id()); 

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


    public void delete(Connection conn, Tenders valueObject) 
          throws NotFoundException, SQLException {

          String sql = "DELETE FROM tenders WHERE (tender_id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getTender_id()); 

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

          String sql = "DELETE FROM tenders";
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

          String sql = "SELECT count(*) FROM tenders";
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


   
    public List searchMatching(Connection conn, Tenders valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM tenders WHERE 1=1 ");

          if (valueObject.getTender_id() != 0) {
              if (first) { first = false; }
              sql.append("AND tender_id = ").append(valueObject.getTender_id()).append(" ");
          }

          if (valueObject.getDepartment() != null) {
              if (first) { first = false; }
              sql.append("AND department LIKE '").append(valueObject.getDepartment()).append("%' ");
          }

          if (valueObject.getCity() != null) {
              if (first) { first = false; }
              sql.append("AND city LIKE '").append(valueObject.getCity()).append("%' ");
          }

          if (valueObject.getLast_date() != null) {
              if (first) { first = false; }
              sql.append("AND last_date = '").append(valueObject.getLast_date()).append("' ");
          }

          if (valueObject.getCost() != null) {
              if (first) { first = false; }
              sql.append("AND cost LIKE '").append(valueObject.getCost()).append("%' ");
          }

          if (valueObject.getManaged_by() != 0) {
              if (first) { first = false; }
              sql.append("AND managed_by = ").append(valueObject.getManaged_by()).append(" ");
          }


          sql.append("ORDER BY tender_id ASC ");

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



    protected void singleQuery(Connection conn, PreparedStatement stmt, Tenders valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setTender_id(result.getInt("tender_id")); 
                   valueObject.setDepartment(result.getString("department")); 
                   valueObject.setCity(result.getString("city")); 
                   valueObject.setLast_date(result.getDate("last_date")); 
                   valueObject.setCost(result.getString("cost")); 
                   valueObject.setManaged_by(result.getInt("managed_by")); 

              } else {
                    //System.out.println("Tenders Object Not Found!");
                    throw new NotFoundException("Tenders Object Not Found!");
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
                   Tenders temp = createValueObject();

                   temp.setTender_id(result.getInt("tender_id")); 
                   temp.setDepartment(result.getString("department")); 
                   temp.setCity(result.getString("city")); 
                   temp.setLast_date(result.getDate("last_date")); 
                   temp.setCost(result.getString("cost")); 
                   temp.setManaged_by(result.getInt("managed_by")); 

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
