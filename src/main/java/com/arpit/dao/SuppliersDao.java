package com.arpit.dao;


import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import java.math.*;
import com.arpit.model.Suppliers;
import com.arpit.NotFoundException;



@Repository
public class SuppliersDao {



   
    public Suppliers createValueObject() {
          return new Suppliers();
    }

    public Suppliers getObject(Connection conn, int supplier_id) throws NotFoundException, SQLException {

          Suppliers valueObject = createValueObject();
          valueObject.setSupplier_id(supplier_id);
          load(conn, valueObject);
          return valueObject;
    }


    public void load(Connection conn, Suppliers valueObject) throws NotFoundException, SQLException {

          String sql = "SELECT * FROM suppliers WHERE (supplier_id = ? ) "; 
          PreparedStatement stmt = null;

          try {
               stmt = conn.prepareStatement(sql);
               stmt.setInt(1, valueObject.getSupplier_id()); 

               singleQuery(conn, stmt, valueObject);

          } finally {
              if (stmt != null)
                  stmt.close();
          }
    }


    public List loadAll(Connection conn) throws SQLException {

          String sql = "SELECT * FROM suppliers ORDER BY material ASC ";
          List searchResults = listQuery(conn, conn.prepareStatement(sql));

          return searchResults;
    }



    public synchronized void create(Connection conn, Suppliers valueObject) throws SQLException {

          String sql = "";
          PreparedStatement stmt = null;
          ResultSet result = null;

          try {
               sql = "INSERT INTO suppliers ( supplier_id, name, material, "
               + "site_id) VALUES (?, ?, ?, ?) ";
               stmt = conn.prepareStatement(sql);

               stmt.setInt(1, valueObject.getSupplier_id()); 
               stmt.setString(2, valueObject.getName()); 
               stmt.setString(3, valueObject.getMaterial()); 
               stmt.setInt(4, valueObject.getSite_id()); 

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


    public void save(Connection conn, Suppliers valueObject) 
          throws NotFoundException, SQLException {

          String sql = "UPDATE suppliers SET name = ?, material = ?, site_id = ? WHERE (supplier_id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setString(1, valueObject.getName()); 
              stmt.setString(2, valueObject.getMaterial()); 
              stmt.setInt(3, valueObject.getSite_id()); 

              stmt.setInt(4, valueObject.getSupplier_id()); 

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


    public void delete(Connection conn, Suppliers valueObject) 
          throws NotFoundException, SQLException {

          String sql = "DELETE FROM suppliers WHERE (supplier_id = ? ) ";
          PreparedStatement stmt = null;

          try {
              stmt = conn.prepareStatement(sql);
              stmt.setInt(1, valueObject.getSupplier_id()); 

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

          String sql = "DELETE FROM suppliers";
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

          String sql = "SELECT count(*) FROM suppliers";
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


    public List searchMatching(Connection conn, Suppliers valueObject) throws SQLException {

          List searchResults;

          boolean first = true;
          StringBuffer sql = new StringBuffer("SELECT * FROM suppliers WHERE 1=1 ");

          if (valueObject.getSupplier_id() != 0) {
              if (first) { first = false; }
              sql.append("AND supplier_id = ").append(valueObject.getSupplier_id()).append(" ");
          }

          if (valueObject.getName() != null) {
              if (first) { first = false; }
              sql.append("AND name LIKE '").append(valueObject.getName()).append("%' ");
          }

          if (valueObject.getMaterial() != null) {
              if (first) { first = false; }
              sql.append("AND material LIKE '").append(valueObject.getMaterial()).append("%' ");
          }

          if (valueObject.getSite_id() != 0) {
              if (first) { first = false; }
              sql.append("AND site_id = ").append(valueObject.getSite_id()).append(" ");
          }


          sql.append("ORDER BY material ASC ");

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



    protected void singleQuery(Connection conn, PreparedStatement stmt, Suppliers valueObject) 
          throws NotFoundException, SQLException {

          ResultSet result = null;

          try {
              result = stmt.executeQuery();

              if (result.next()) {

                   valueObject.setSupplier_id(result.getInt("supplier_id")); 
                   valueObject.setName(result.getString("name")); 
                   valueObject.setMaterial(result.getString("material")); 
                   valueObject.setSite_id(result.getInt("site_id")); 

              } else {
                    //System.out.println("Suppliers Object Not Found!");
                    throw new NotFoundException("Suppliers Object Not Found!");
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
                   Suppliers temp = createValueObject();

                   temp.setSupplier_id(result.getInt("supplier_id")); 
                   temp.setName(result.getString("name")); 
                   temp.setMaterial(result.getString("material")); 
                   temp.setSite_id(result.getInt("site_id")); 

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