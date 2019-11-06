package com.arpit.model;


import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

 /**
  * Tenders Value Object.
  * This class is value object representing database table tenders
  * This class is intented to be used together with associated Dao object.
  */

 /**
  * This sourcecode has been generated by FREE DaoGen generator version 2.4.1.
  * The usage of generated code is restricted to OpenSource software projects
  * only. DaoGen is available in http://titaniclinux.net/daogen/
  * It has been programmed by Tuomo Lukka, Tuomo.Lukka@iki.fi
  *
  * DaoGen license: The following DaoGen generated source code is licensed
  * under the terms of GNU GPL license. The full text for license is available
  * in GNU project's pages: http://www.gnu.org/copyleft/gpl.html
  *
  * If you wish to use the DaoGen generator to produce code for closed-source
  * commercial applications, you must pay the lisence fee. The price is
  * 5 USD or 5 Eur for each database table, you are generating code for.
  * (That includes unlimited amount of iterations with all supported languages
  * for each database table you are paying for.) Send mail to
  * "Tuomo.Lukka@iki.fi" for more information. Thank you!
  */



public class Tenders implements Cloneable, Serializable {

    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int tender_id;
    private String department;
    private String city;
    private java.sql.Date last_date;
    private String cost;
    private int managed_by;



    /** 
     * Constructors. DaoGen generates two constructors by default.
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public Tenders () {

    }

    public Tenders (int tender_idIn) {

          this.tender_id = tender_idIn;

    }


    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

    public int getTender_id() {
          return this.tender_id;
    }
    public void setTender_id(int tender_idIn) {
          this.tender_id = tender_idIn;
    }

    public String getDepartment() {
          return this.department;
    }
    public void setDepartment(String departmentIn) {
          this.department = departmentIn;
    }

    public String getCity() {
          return this.city;
    }
    public void setCity(String cityIn) {
          this.city = cityIn;
    }

    public java.sql.Date getLast_date() {
          return this.last_date;
    }
    public void setLast_date(java.sql.Date last_dateIn) {
          this.last_date = last_dateIn;
    }

    public String getCost() {
          return this.cost;
    }
    public void setCost(String costIn) {
          this.cost = costIn;
    }

    public int getManaged_by() {
          return this.managed_by;
    }
    public void setManaged_by(int managed_byIn) {
          this.managed_by = managed_byIn;
    }



    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variales, without going trough the 
     * individual set-methods.
     */

    public void setAll(int tender_idIn,
          String departmentIn,
          String cityIn,
          java.sql.Date last_dateIn,
          String costIn,
          int managed_byIn) {
          this.tender_id = tender_idIn;
          this.department = departmentIn;
          this.city = cityIn;
          this.last_date = last_dateIn;
          this.cost = costIn;
          this.managed_by = managed_byIn;
    }


    /** 
     * hasEqualMapping-method will compare two Tenders instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     */
    public boolean hasEqualMapping(Tenders valueObject) {

          if (valueObject.getTender_id() != this.tender_id) {
                    return(false);
          }
          if (this.department == null) {
                    if (valueObject.getDepartment() != null)
                           return(false);
          } else if (!this.department.equals(valueObject.getDepartment())) {
                    return(false);
          }
          if (this.city == null) {
                    if (valueObject.getCity() != null)
                           return(false);
          } else if (!this.city.equals(valueObject.getCity())) {
                    return(false);
          }
          if (this.last_date == null) {
                    if (valueObject.getLast_date() != null)
                           return(false);
          } else if (!this.last_date.equals(valueObject.getLast_date())) {
                    return(false);
          }
          if (this.cost == null) {
                    if (valueObject.getCost() != null)
                           return(false);
          } else if (!this.cost.equals(valueObject.getCost())) {
                    return(false);
          }
          if (valueObject.getManaged_by() != this.managed_by) {
                    return(false);
          }

          return true;
    }



    /**
     * toString will return String object representing the state of this 
     * valueObject. This is useful during application development, and 
     * possibly when application is writing object states in textlog.
     */
    public String toString() {
        StringBuffer out = new StringBuffer(this.getDaogenVersion());
        out.append("\nclass Tenders, mapping to table tenders\n");
        out.append("Persistent attributes: \n"); 
        out.append("tender_id = " + this.tender_id + "\n"); 
        out.append("department = " + this.department + "\n"); 
        out.append("city = " + this.city + "\n"); 
        out.append("last_date = " + this.last_date + "\n"); 
        out.append("cost = " + this.cost + "\n"); 
        out.append("managed_by = " + this.managed_by + "\n"); 
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the retuned cloned object
     * will also have all its attributes cloned.
     */
    public Object clone() {
        Tenders cloned = new Tenders();

        cloned.setTender_id(this.tender_id); 
        if (this.department != null)
             cloned.setDepartment(new String(this.department)); 
        if (this.city != null)
             cloned.setCity(new String(this.city)); 
        if (this.last_date != null)
             cloned.setLast_date((java.sql.Date)this.last_date.clone()); 
        if (this.cost != null)
             cloned.setCost(new String(this.cost)); 
        cloned.setManaged_by(this.managed_by); 
        return cloned;
    }



    /** 
     * getDaogenVersion will return information about
     * generator which created these sources.
     */
    public String getDaogenVersion() {
        return "DaoGen version 2.4.1";
    }

}