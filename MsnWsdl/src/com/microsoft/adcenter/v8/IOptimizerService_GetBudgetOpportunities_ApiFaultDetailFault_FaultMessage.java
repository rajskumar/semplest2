
/**
 * IOptimizerService_GetBudgetOpportunities_ApiFaultDetailFault_FaultMessage.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.microsoft.adcenter.v8;

public class IOptimizerService_GetBudgetOpportunities_ApiFaultDetailFault_FaultMessage extends java.lang.Exception{

    private static final long serialVersionUID = 1337717315671L;
    
    private com.microsoft.adcenter.v8.ApiFaultDetailE faultMessage;

    
        public IOptimizerService_GetBudgetOpportunities_ApiFaultDetailFault_FaultMessage() {
            super("IOptimizerService_GetBudgetOpportunities_ApiFaultDetailFault_FaultMessage");
        }

        public IOptimizerService_GetBudgetOpportunities_ApiFaultDetailFault_FaultMessage(java.lang.String s) {
           super(s);
        }

        public IOptimizerService_GetBudgetOpportunities_ApiFaultDetailFault_FaultMessage(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public IOptimizerService_GetBudgetOpportunities_ApiFaultDetailFault_FaultMessage(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.microsoft.adcenter.v8.ApiFaultDetailE msg){
       faultMessage = msg;
    }
    
    public com.microsoft.adcenter.v8.ApiFaultDetailE getFaultMessage(){
       return faultMessage;
    }
}
    