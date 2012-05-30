/**
 * GetAdEditorialReasonsByIdsRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.adcenter.v8;

public class GetAdEditorialReasonsByIdsRequest  implements java.io.Serializable {
    private long[] adIds;

    private java.lang.Long accountId;

    public GetAdEditorialReasonsByIdsRequest() {
    }

    public GetAdEditorialReasonsByIdsRequest(
           long[] adIds,
           java.lang.Long accountId) {
           this.adIds = adIds;
           this.accountId = accountId;
    }


    /**
     * Gets the adIds value for this GetAdEditorialReasonsByIdsRequest.
     * 
     * @return adIds
     */
    public long[] getAdIds() {
        return adIds;
    }


    /**
     * Sets the adIds value for this GetAdEditorialReasonsByIdsRequest.
     * 
     * @param adIds
     */
    public void setAdIds(long[] adIds) {
        this.adIds = adIds;
    }


    /**
     * Gets the accountId value for this GetAdEditorialReasonsByIdsRequest.
     * 
     * @return accountId
     */
    public java.lang.Long getAccountId() {
        return accountId;
    }


    /**
     * Sets the accountId value for this GetAdEditorialReasonsByIdsRequest.
     * 
     * @param accountId
     */
    public void setAccountId(java.lang.Long accountId) {
        this.accountId = accountId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetAdEditorialReasonsByIdsRequest)) return false;
        GetAdEditorialReasonsByIdsRequest other = (GetAdEditorialReasonsByIdsRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.adIds==null && other.getAdIds()==null) || 
             (this.adIds!=null &&
              java.util.Arrays.equals(this.adIds, other.getAdIds()))) &&
            ((this.accountId==null && other.getAccountId()==null) || 
             (this.accountId!=null &&
              this.accountId.equals(other.getAccountId())));
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
        if (getAdIds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdIds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdIds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAccountId() != null) {
            _hashCode += getAccountId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetAdEditorialReasonsByIdsRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://adcenter.microsoft.com/v8", ">GetAdEditorialReasonsByIdsRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adIds");
        elemField.setXmlName(new javax.xml.namespace.QName("https://adcenter.microsoft.com/v8", "AdIds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://schemas.microsoft.com/2003/10/Serialization/Arrays", "long"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountId");
        elemField.setXmlName(new javax.xml.namespace.QName("https://adcenter.microsoft.com/v8", "AccountId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
