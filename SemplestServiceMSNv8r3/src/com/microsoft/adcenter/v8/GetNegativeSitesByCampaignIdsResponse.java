/**
 * GetNegativeSitesByCampaignIdsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.microsoft.adcenter.v8;

public class GetNegativeSitesByCampaignIdsResponse  implements java.io.Serializable {
    private com.microsoft.adcenter.v8.CampaignNegativeSites[] campaignNegativeSites;

    public GetNegativeSitesByCampaignIdsResponse() {
    }

    public GetNegativeSitesByCampaignIdsResponse(
           com.microsoft.adcenter.v8.CampaignNegativeSites[] campaignNegativeSites) {
           this.campaignNegativeSites = campaignNegativeSites;
    }


    /**
     * Gets the campaignNegativeSites value for this GetNegativeSitesByCampaignIdsResponse.
     * 
     * @return campaignNegativeSites
     */
    public com.microsoft.adcenter.v8.CampaignNegativeSites[] getCampaignNegativeSites() {
        return campaignNegativeSites;
    }


    /**
     * Sets the campaignNegativeSites value for this GetNegativeSitesByCampaignIdsResponse.
     * 
     * @param campaignNegativeSites
     */
    public void setCampaignNegativeSites(com.microsoft.adcenter.v8.CampaignNegativeSites[] campaignNegativeSites) {
        this.campaignNegativeSites = campaignNegativeSites;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetNegativeSitesByCampaignIdsResponse)) return false;
        GetNegativeSitesByCampaignIdsResponse other = (GetNegativeSitesByCampaignIdsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.campaignNegativeSites==null && other.getCampaignNegativeSites()==null) || 
             (this.campaignNegativeSites!=null &&
              java.util.Arrays.equals(this.campaignNegativeSites, other.getCampaignNegativeSites())));
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
        if (getCampaignNegativeSites() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCampaignNegativeSites());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCampaignNegativeSites(), i);
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
        new org.apache.axis.description.TypeDesc(GetNegativeSitesByCampaignIdsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("https://adcenter.microsoft.com/v8", ">GetNegativeSitesByCampaignIdsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("campaignNegativeSites");
        elemField.setXmlName(new javax.xml.namespace.QName("https://adcenter.microsoft.com/v8", "CampaignNegativeSites"));
        elemField.setXmlType(new javax.xml.namespace.QName("https://adcenter.microsoft.com/v8", "CampaignNegativeSites"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("https://adcenter.microsoft.com/v8", "CampaignNegativeSites"));
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
