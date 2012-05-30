/**
 * AdStatusReportFilterNull.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.adcenter.v8;

public class AdStatusReportFilterNull implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected AdStatusReportFilterNull(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _Submitted = "Submitted";
    public static final java.lang.String _Rejected = "Rejected";
    public static final java.lang.String _Deleted = "Deleted";
    public static final java.lang.String _Pending = "Pending";
    public static final java.lang.String _Active = "Active";
    public static final AdStatusReportFilterNull Submitted = new AdStatusReportFilterNull(_Submitted);
    public static final AdStatusReportFilterNull Rejected = new AdStatusReportFilterNull(_Rejected);
    public static final AdStatusReportFilterNull Deleted = new AdStatusReportFilterNull(_Deleted);
    public static final AdStatusReportFilterNull Pending = new AdStatusReportFilterNull(_Pending);
    public static final AdStatusReportFilterNull Active = new AdStatusReportFilterNull(_Active);
    public java.lang.String getValue() { return _value_;}
    public static AdStatusReportFilterNull fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        AdStatusReportFilterNull enumeration = (AdStatusReportFilterNull)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static AdStatusReportFilterNull fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AdStatusReportFilterNull.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://adcenter.microsoft.com/v8", "AdStatusReportFilter>null"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
