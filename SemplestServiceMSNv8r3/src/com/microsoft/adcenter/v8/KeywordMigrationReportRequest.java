/**
 * KeywordMigrationReportRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.adcenter.v8;

public class KeywordMigrationReportRequest  extends com.microsoft.adcenter.v8.ReportRequest  implements java.io.Serializable {
    private com.microsoft.adcenter.v8.KeywordMigrationReportColumn[] columns;

    private com.microsoft.adcenter.v8.AccountThroughCampaignReportScope scope;

    public KeywordMigrationReportRequest() {
    }

    public KeywordMigrationReportRequest(
           com.microsoft.adcenter.v8.ReportFormat format,
           com.microsoft.adcenter.v8.ReportLanguage language,
           java.lang.String reportName,
           java.lang.Boolean returnOnlyCompleteData,
           com.microsoft.adcenter.v8.KeywordMigrationReportColumn[] columns,
           com.microsoft.adcenter.v8.AccountThroughCampaignReportScope scope) {
        super(
            format,
            language,
            reportName,
            returnOnlyCompleteData);
        this.columns = columns;
        this.scope = scope;
    }


    /**
     * Gets the columns value for this KeywordMigrationReportRequest.
     * 
     * @return columns
     */
    public com.microsoft.adcenter.v8.KeywordMigrationReportColumn[] getColumns() {
        return columns;
    }


    /**
     * Sets the columns value for this KeywordMigrationReportRequest.
     * 
     * @param columns
     */
    public void setColumns(com.microsoft.adcenter.v8.KeywordMigrationReportColumn[] columns) {
        this.columns = columns;
    }


    /**
     * Gets the scope value for this KeywordMigrationReportRequest.
     * 
     * @return scope
     */
    public com.microsoft.adcenter.v8.AccountThroughCampaignReportScope getScope() {
        return scope;
    }


    /**
     * Sets the scope value for this KeywordMigrationReportRequest.
     * 
     * @param scope
     */
    public void setScope(com.microsoft.adcenter.v8.AccountThroughCampaignReportScope scope) {
        this.scope = scope;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof KeywordMigrationReportRequest)) return false;
        KeywordMigrationReportRequest other = (KeywordMigrationReportRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.columns==null && other.getColumns()==null) || 
             (this.columns!=null &&
              java.util.Arrays.equals(this.columns, other.getColumns()))) &&
            ((this.scope==null && other.getScope()==null) || 
             (this.scope!=null &&
              this.scope.equals(other.getScope())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getColumns() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getColumns());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getColumns(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getScope() != null) {
            _hashCode += getScope().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(KeywordMigrationReportRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://adcenter.microsoft.com/v8", "KeywordMigrationReportRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("columns");
        elemField.setXmlName(new javax.xml.namespace.QName("https://adcenter.microsoft.com/v8", "Columns"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://adcenter.microsoft.com/v8", "KeywordMigrationReportColumn"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("https://adcenter.microsoft.com/v8", "KeywordMigrationReportColumn"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scope");
        elemField.setXmlName(new javax.xml.namespace.QName("https://adcenter.microsoft.com/v8", "Scope"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://adcenter.microsoft.com/v8", "AccountThroughCampaignReportScope"));
        elemField.setNillable(true);
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