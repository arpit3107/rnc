package com.arpit.model;


import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

 /**
  * Lawyer Value Object.
  * This class is value object representing database table lawyer
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



public class Lawyer implements Cloneable, Serializable {


    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int lawyer_id;
    private String name;
    private String city;
    private String office_name;



    /** 
     * Constructors. DaoGen generates two constructors by default.
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public Lawyer () {

    }

    public Lawyer (int lawyer_idIn) {

          this.lawyer_id = lawyer_idIn;

    }


    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

    public int getLawyer_id() {
          return this.lawyer_id;
    }
    public void setLawyer_id(int lawyer_idIn) {
          this.lawyer_id = lawyer_idIn;
    }

    public String getName() {
          return this.name;
    }
    public void setName(String nameIn) {
          this.name = nameIn;
    }

    public String getCity() {
          return this.city;
    }
    public void setCity(String cityIn) {
          this.city = cityIn;
    }

    public String getOffice_name() {
          return this.office_name;
    }
    public void setOffice_name(String office_nameIn) {
          this.office_name = office_nameIn;
    }



    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variales, without going trough the 
     * individual set-methods.
     */

    public void setAll(int lawyer_idIn,
          String nameIn,
          String cityIn,
          String office_nameIn) {
          this.lawyer_id = lawyer_idIn;
          this.name = nameIn;
          this.city = cityIn;
          this.office_name = office_nameIn;
    }


    /** 
     * hasEqualMapping-method will compare two Lawyer instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     */
    public boolean hasEqualMapping(Lawyer valueObject) {

          if (valueObject.getLawyer_id() != this.lawyer_id) {
                    return(false);
          }
          if (this.name == null) {
                    if (valueObject.getName() != null)
                           return(false);
          } else if (!this.name.equals(valueObject.getName())) {
                    return(false);
          }
          if (this.city == null) {
                    if (valueObject.getCity() != null)
                           return(false);
          } else if (!this.city.equals(valueObject.getCity())) {
                    return(false);
          }
          if (this.office_name == null) {
                    if (valueObject.getOffice_name() != null)
                           return(false);
          } else if (!this.office_name.equals(valueObject.getOffice_name())) {
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
        out.append("\nclass Lawyer, mapping to table lawyer\n");
        out.append("Persistent attributes: \n"); 
        out.append("lawyer_id = " + this.lawyer_id + "\n"); 
        out.append("name = " + this.name + "\n"); 
        out.append("city = " + this.city + "\n"); 
        out.append("office_name = " + this.office_name + "\n"); 
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the retuned cloned object
     * will also have all its attributes cloned.
     */
    public Object clone() {
        Lawyer cloned = new Lawyer();

        cloned.setLawyer_id(this.lawyer_id); 
        if (this.name != null)
             cloned.setName(new String(this.name)); 
        if (this.city != null)
             cloned.setCity(new String(this.city)); 
        if (this.office_name != null)
             cloned.setOffice_name(new String(this.office_name)); 
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

