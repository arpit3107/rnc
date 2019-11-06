package com.arpit.model;


import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

 /**
  * Suppliers Value Object.
  * This class is value object representing database table suppliers
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



public class Suppliers implements Cloneable, Serializable {

    /** 
     * Persistent Instance variables. This data is directly 
     * mapped to the columns of database table.
     */
    private int supplier_id;
    private String name;
    private String material;
    private int site_id;



    /** 
     * Constructors. DaoGen generates two constructors by default.
     * The first one takes no arguments and provides the most simple
     * way to create object instance. The another one takes one
     * argument, which is the primary key of the corresponding table.
     */

    public Suppliers () {

    }

    public Suppliers (int supplier_idIn) {

          this.supplier_id = supplier_idIn;

    }


    /** 
     * Get- and Set-methods for persistent variables. The default
     * behaviour does not make any checks against malformed data,
     * so these might require some manual additions.
     */

    public int getSupplier_id() {
          return this.supplier_id;
    }
    public void setSupplier_id(int supplier_idIn) {
          this.supplier_id = supplier_idIn;
    }

    public String getName() {
          return this.name;
    }
    public void setName(String nameIn) {
          this.name = nameIn;
    }

    public String getMaterial() {
          return this.material;
    }
    public void setMaterial(String materialIn) {
          this.material = materialIn;
    }

    public int getSite_id() {
          return this.site_id;
    }
    public void setSite_id(int site_idIn) {
          this.site_id = site_idIn;
    }



    /** 
     * setAll allows to set all persistent variables in one method call.
     * This is useful, when all data is available and it is needed to 
     * set the initial state of this object. Note that this method will
     * directly modify instance variales, without going trough the 
     * individual set-methods.
     */

    public void setAll(int supplier_idIn,
          String nameIn,
          String materialIn,
          int site_idIn) {
          this.supplier_id = supplier_idIn;
          this.name = nameIn;
          this.material = materialIn;
          this.site_id = site_idIn;
    }


    /** 
     * hasEqualMapping-method will compare two Suppliers instances
     * and return true if they contain same values in all persistent instance 
     * variables. If hasEqualMapping returns true, it does not mean the objects
     * are the same instance. However it does mean that in that moment, they 
     * are mapped to the same row in database.
     */
    public boolean hasEqualMapping(Suppliers valueObject) {

          if (valueObject.getSupplier_id() != this.supplier_id) {
                    return(false);
          }
          if (this.name == null) {
                    if (valueObject.getName() != null)
                           return(false);
          } else if (!this.name.equals(valueObject.getName())) {
                    return(false);
          }
          if (this.material == null) {
                    if (valueObject.getMaterial() != null)
                           return(false);
          } else if (!this.material.equals(valueObject.getMaterial())) {
                    return(false);
          }
          if (valueObject.getSite_id() != this.site_id) {
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
        out.append("\nclass Suppliers, mapping to table suppliers\n");
        out.append("Persistent attributes: \n"); 
        out.append("supplier_id = " + this.supplier_id + "\n"); 
        out.append("name = " + this.name + "\n"); 
        out.append("material = " + this.material + "\n"); 
        out.append("site_id = " + this.site_id + "\n"); 
        return out.toString();
    }


    /**
     * Clone will return identical deep copy of this valueObject.
     * Note, that this method is different than the clone() which
     * is defined in java.lang.Object. Here, the retuned cloned object
     * will also have all its attributes cloned.
     */
    public Object clone() {
        Suppliers cloned = new Suppliers();

        cloned.setSupplier_id(this.supplier_id); 
        if (this.name != null)
             cloned.setName(new String(this.name)); 
        if (this.material != null)
             cloned.setMaterial(new String(this.material)); 
        cloned.setSite_id(this.site_id); 
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
