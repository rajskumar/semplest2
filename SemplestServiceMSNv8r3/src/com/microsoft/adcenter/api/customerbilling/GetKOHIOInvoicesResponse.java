/**
 * GetKOHIOInvoicesResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.adcenter.api.customerbilling;

public class GetKOHIOInvoicesResponse  implements java.io.Serializable {
    private com.microsoft.adcenter.api.customermanagement.Entities.Invoice[] invoices;

    public GetKOHIOInvoicesResponse() {
    }

    public GetKOHIOInvoicesResponse(
           com.microsoft.adcenter.api.customermanagement.Entities.Invoice[] invoices) {
           this.invoices = invoices;
    }


    /**
     * Gets the invoices value for this GetKOHIOInvoicesResponse.
     * 
     * @return invoices
     */
    public com.microsoft.adcenter.api.customermanagement.Entities.Invoice[] getInvoices() {
        return invoices;
    }


    /**
     * Sets the invoices value for this GetKOHIOInvoicesResponse.
     * 
     * @param invoices
     */
    public void setInvoices(com.microsoft.adcenter.api.customermanagement.Entities.Invoice[] invoices) {
        this.invoices = invoices;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetKOHIOInvoicesResponse)) return false;
        GetKOHIOInvoicesResponse other = (GetKOHIOInvoicesResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.invoices==null && other.getInvoices()==null) || 
             (this.invoices!=null &&
              java.util.Arrays.equals(this.invoices, other.getInvoices())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getInvoices() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInvoices());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInvoices(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetKOHIOInvoicesResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customerbilling", ">GetKOHIOInvoicesResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invoices");
        elemField.setXmlName(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customerbilling", "Invoices"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities", "Invoice"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities", "Invoice"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
