
/**
 * ICampaignManagementService_GetAdExtensionsByIds_ApiFaultDetailFault_FaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.microsoft.adcenter.v8;

public class ICampaignManagementService_GetAdExtensionsByIds_ApiFaultDetailFault_FaultMessage extends java.lang.Exception{

    private static final long serialVersionUID = 1337717191391L;
    
    private com.microsoft.adcenter.v8.ApiFaultDetailE faultMessage;

    
        public ICampaignManagementService_GetAdExtensionsByIds_ApiFaultDetailFault_FaultMessage() {
            super("ICampaignManagementService_GetAdExtensionsByIds_ApiFaultDetailFault_FaultMessage");
        }

        public ICampaignManagementService_GetAdExtensionsByIds_ApiFaultDetailFault_FaultMessage(java.lang.String s) {
           super(s);
        }

        public ICampaignManagementService_GetAdExtensionsByIds_ApiFaultDetailFault_FaultMessage(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public ICampaignManagementService_GetAdExtensionsByIds_ApiFaultDetailFault_FaultMessage(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.microsoft.adcenter.v8.ApiFaultDetailE msg){
       faultMessage = msg;
    }
    
    public com.microsoft.adcenter.v8.ApiFaultDetailE getFaultMessage(){
       return faultMessage;
    }
}
    