
/**
 * Account.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:34:40 IST)
 */

            
                package com.microsoft.adcenter.api.customermanagement.entities;
            

            /**
            *  Account bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class Account
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = Account
                Namespace URI = https://adcenter.microsoft.com/api/customermanagement/Entities
                Namespace Prefix = ns6
                */
            

                        /**
                        * field for AccountType
                        */

                        
                                    protected com.microsoft.adcenter.api.customermanagement.entities.AccountType localAccountType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountTypeTracker = false ;

                           public boolean isAccountTypeSpecified(){
                               return localAccountTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return com.microsoft.adcenter.api.customermanagement.entities.AccountType
                           */
                           public  com.microsoft.adcenter.api.customermanagement.entities.AccountType getAccountType(){
                               return localAccountType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountType
                               */
                               public void setAccountType(com.microsoft.adcenter.api.customermanagement.entities.AccountType param){
                            localAccountTypeTracker = param != null;
                                   
                                            this.localAccountType=param;
                                    

                               }
                            

                        /**
                        * field for BillToCustomerId
                        */

                        
                                    protected long localBillToCustomerId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localBillToCustomerIdTracker = false ;

                           public boolean isBillToCustomerIdSpecified(){
                               return localBillToCustomerIdTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return long
                           */
                           public  long getBillToCustomerId(){
                               return localBillToCustomerId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param BillToCustomerId
                               */
                               public void setBillToCustomerId(long param){
                            localBillToCustomerIdTracker = true;
                                   
                                            this.localBillToCustomerId=param;
                                    

                               }
                            

                        /**
                        * field for CountryCode
                        */

                        
                                    protected java.lang.String localCountryCode ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCountryCodeTracker = false ;

                           public boolean isCountryCodeSpecified(){
                               return localCountryCodeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCountryCode(){
                               return localCountryCode;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CountryCode
                               */
                               public void setCountryCode(java.lang.String param){
                            localCountryCodeTracker = true;
                                   
                                            this.localCountryCode=param;
                                    

                               }
                            

                        /**
                        * field for CurrencyType
                        */

                        
                                    protected com.microsoft.adcenter.api.customermanagement.entities.CurrencyType localCurrencyType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCurrencyTypeTracker = false ;

                           public boolean isCurrencyTypeSpecified(){
                               return localCurrencyTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return com.microsoft.adcenter.api.customermanagement.entities.CurrencyType
                           */
                           public  com.microsoft.adcenter.api.customermanagement.entities.CurrencyType getCurrencyType(){
                               return localCurrencyType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param CurrencyType
                               */
                               public void setCurrencyType(com.microsoft.adcenter.api.customermanagement.entities.CurrencyType param){
                            localCurrencyTypeTracker = true;
                                   
                                            this.localCurrencyType=param;
                                    

                               }
                            

                        /**
                        * field for AccountFinancialStatus
                        */

                        
                                    protected com.microsoft.adcenter.api.customermanagement.entities.AccountFinancialStatus localAccountFinancialStatus ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountFinancialStatusTracker = false ;

                           public boolean isAccountFinancialStatusSpecified(){
                               return localAccountFinancialStatusTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return com.microsoft.adcenter.api.customermanagement.entities.AccountFinancialStatus
                           */
                           public  com.microsoft.adcenter.api.customermanagement.entities.AccountFinancialStatus getAccountFinancialStatus(){
                               return localAccountFinancialStatus;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountFinancialStatus
                               */
                               public void setAccountFinancialStatus(com.microsoft.adcenter.api.customermanagement.entities.AccountFinancialStatus param){
                            localAccountFinancialStatusTracker = true;
                                   
                                            this.localAccountFinancialStatus=param;
                                    

                               }
                            

                        /**
                        * field for Id
                        */

                        
                                    protected long localId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIdTracker = false ;

                           public boolean isIdSpecified(){
                               return localIdTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return long
                           */
                           public  long getId(){
                               return localId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Id
                               */
                               public void setId(long param){
                            localIdTracker = true;
                                   
                                            this.localId=param;
                                    

                               }
                            

                        /**
                        * field for Language
                        */

                        
                                    protected com.microsoft.adcenter.api.customermanagement.entities.LanguageType localLanguage ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLanguageTracker = false ;

                           public boolean isLanguageSpecified(){
                               return localLanguageTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return com.microsoft.adcenter.api.customermanagement.entities.LanguageType
                           */
                           public  com.microsoft.adcenter.api.customermanagement.entities.LanguageType getLanguage(){
                               return localLanguage;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Language
                               */
                               public void setLanguage(com.microsoft.adcenter.api.customermanagement.entities.LanguageType param){
                            localLanguageTracker = true;
                                   
                                            this.localLanguage=param;
                                    

                               }
                            

                        /**
                        * field for LastModifiedByUserId
                        */

                        
                                    protected long localLastModifiedByUserId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLastModifiedByUserIdTracker = false ;

                           public boolean isLastModifiedByUserIdSpecified(){
                               return localLastModifiedByUserIdTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return long
                           */
                           public  long getLastModifiedByUserId(){
                               return localLastModifiedByUserId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param LastModifiedByUserId
                               */
                               public void setLastModifiedByUserId(long param){
                            localLastModifiedByUserIdTracker = true;
                                   
                                            this.localLastModifiedByUserId=param;
                                    

                               }
                            

                        /**
                        * field for LastModifiedTime
                        */

                        
                                    protected java.util.Calendar localLastModifiedTime ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localLastModifiedTimeTracker = false ;

                           public boolean isLastModifiedTimeSpecified(){
                               return localLastModifiedTimeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.util.Calendar
                           */
                           public  java.util.Calendar getLastModifiedTime(){
                               return localLastModifiedTime;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param LastModifiedTime
                               */
                               public void setLastModifiedTime(java.util.Calendar param){
                            localLastModifiedTimeTracker = true;
                                   
                                            this.localLastModifiedTime=param;
                                    

                               }
                            

                        /**
                        * field for Name
                        */

                        
                                    protected java.lang.String localName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNameTracker = false ;

                           public boolean isNameSpecified(){
                               return localNameTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getName(){
                               return localName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Name
                               */
                               public void setName(java.lang.String param){
                            localNameTracker = true;
                                   
                                            this.localName=param;
                                    

                               }
                            

                        /**
                        * field for Number
                        */

                        
                                    protected java.lang.String localNumber ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localNumberTracker = false ;

                           public boolean isNumberSpecified(){
                               return localNumberTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getNumber(){
                               return localNumber;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Number
                               */
                               public void setNumber(java.lang.String param){
                            localNumberTracker = true;
                                   
                                            this.localNumber=param;
                                    

                               }
                            

                        /**
                        * field for ParentCustomerId
                        */

                        
                                    protected long localParentCustomerId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localParentCustomerIdTracker = false ;

                           public boolean isParentCustomerIdSpecified(){
                               return localParentCustomerIdTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return long
                           */
                           public  long getParentCustomerId(){
                               return localParentCustomerId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ParentCustomerId
                               */
                               public void setParentCustomerId(long param){
                            
                                       // setting primitive attribute tracker to true
                                       localParentCustomerIdTracker =
                                       param != java.lang.Long.MIN_VALUE;
                                   
                                            this.localParentCustomerId=param;
                                    

                               }
                            

                        /**
                        * field for PaymentMethodId
                        */

                        
                                    protected long localPaymentMethodId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPaymentMethodIdTracker = false ;

                           public boolean isPaymentMethodIdSpecified(){
                               return localPaymentMethodIdTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return long
                           */
                           public  long getPaymentMethodId(){
                               return localPaymentMethodId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PaymentMethodId
                               */
                               public void setPaymentMethodId(long param){
                            localPaymentMethodIdTracker = true;
                                   
                                            this.localPaymentMethodId=param;
                                    

                               }
                            

                        /**
                        * field for PaymentMethodType
                        */

                        
                                    protected com.microsoft.adcenter.api.customermanagement.entities.PaymentMethodType localPaymentMethodType ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPaymentMethodTypeTracker = false ;

                           public boolean isPaymentMethodTypeSpecified(){
                               return localPaymentMethodTypeTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return com.microsoft.adcenter.api.customermanagement.entities.PaymentMethodType
                           */
                           public  com.microsoft.adcenter.api.customermanagement.entities.PaymentMethodType getPaymentMethodType(){
                               return localPaymentMethodType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PaymentMethodType
                               */
                               public void setPaymentMethodType(com.microsoft.adcenter.api.customermanagement.entities.PaymentMethodType param){
                            localPaymentMethodTypeTracker = true;
                                   
                                            this.localPaymentMethodType=param;
                                    

                               }
                            

                        /**
                        * field for PrimaryUserId
                        */

                        
                                    protected long localPrimaryUserId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPrimaryUserIdTracker = false ;

                           public boolean isPrimaryUserIdSpecified(){
                               return localPrimaryUserIdTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return long
                           */
                           public  long getPrimaryUserId(){
                               return localPrimaryUserId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PrimaryUserId
                               */
                               public void setPrimaryUserId(long param){
                            localPrimaryUserIdTracker = true;
                                   
                                            this.localPrimaryUserId=param;
                                    

                               }
                            

                        /**
                        * field for AccountLifeCycleStatus
                        */

                        
                                    protected com.microsoft.adcenter.api.customermanagement.entities.AccountLifeCycleStatus localAccountLifeCycleStatus ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAccountLifeCycleStatusTracker = false ;

                           public boolean isAccountLifeCycleStatusSpecified(){
                               return localAccountLifeCycleStatusTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return com.microsoft.adcenter.api.customermanagement.entities.AccountLifeCycleStatus
                           */
                           public  com.microsoft.adcenter.api.customermanagement.entities.AccountLifeCycleStatus getAccountLifeCycleStatus(){
                               return localAccountLifeCycleStatus;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AccountLifeCycleStatus
                               */
                               public void setAccountLifeCycleStatus(com.microsoft.adcenter.api.customermanagement.entities.AccountLifeCycleStatus param){
                            localAccountLifeCycleStatusTracker = true;
                                   
                                            this.localAccountLifeCycleStatus=param;
                                    

                               }
                            

                        /**
                        * field for TimeStamp
                        */

                        
                                    protected javax.activation.DataHandler localTimeStamp ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTimeStampTracker = false ;

                           public boolean isTimeStampSpecified(){
                               return localTimeStampTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return javax.activation.DataHandler
                           */
                           public  javax.activation.DataHandler getTimeStamp(){
                               return localTimeStamp;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TimeStamp
                               */
                               public void setTimeStamp(javax.activation.DataHandler param){
                            localTimeStampTracker = true;
                                   
                                            this.localTimeStamp=param;
                                    

                               }
                            

                        /**
                        * field for TimeZone
                        */

                        
                                    protected com.microsoft.adcenter.api.customermanagement.entities.TimeZoneType localTimeZone ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localTimeZoneTracker = false ;

                           public boolean isTimeZoneSpecified(){
                               return localTimeZoneTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return com.microsoft.adcenter.api.customermanagement.entities.TimeZoneType
                           */
                           public  com.microsoft.adcenter.api.customermanagement.entities.TimeZoneType getTimeZone(){
                               return localTimeZone;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param TimeZone
                               */
                               public void setTimeZone(com.microsoft.adcenter.api.customermanagement.entities.TimeZoneType param){
                            localTimeZoneTracker = true;
                                   
                                            this.localTimeZone=param;
                                    

                               }
                            

                        /**
                        * field for PauseReason
                        */

                        
                                    protected org.apache.axis2.databinding.types.UnsignedByte localPauseReason ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localPauseReasonTracker = false ;

                           public boolean isPauseReasonSpecified(){
                               return localPauseReasonTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return org.apache.axis2.databinding.types.UnsignedByte
                           */
                           public  org.apache.axis2.databinding.types.UnsignedByte getPauseReason(){
                               return localPauseReason;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param PauseReason
                               */
                               public void setPauseReason(org.apache.axis2.databinding.types.UnsignedByte param){
                            localPauseReasonTracker = true;
                                   
                                            this.localPauseReason=param;
                                    

                               }
                            

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName);
               return factory.createOMElement(dataSource,parentQName);
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"https://adcenter.microsoft.com/api/customermanagement/Entities");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":Account",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Account",
                           xmlWriter);
                   }

               
                   }
                if (localAccountTypeTracker){
                                            if (localAccountType==null){
                                                 throw new org.apache.axis2.databinding.ADBException("AccountType cannot be null!!");
                                            }
                                           localAccountType.serialize(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","AccountType"),
                                               xmlWriter);
                                        } if (localBillToCustomerIdTracker){
                                    namespace = "https://adcenter.microsoft.com/api/customermanagement/Entities";
                                    writeStartElement(null, namespace, "BillToCustomerId", xmlWriter);
                             
                                               if (localBillToCustomerId==java.lang.Long.MIN_VALUE) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillToCustomerId));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCountryCodeTracker){
                                    namespace = "https://adcenter.microsoft.com/api/customermanagement/Entities";
                                    writeStartElement(null, namespace, "CountryCode", xmlWriter);
                             

                                          if (localCountryCode==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCountryCode);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localCurrencyTypeTracker){
                                    if (localCurrencyType==null){

                                        writeStartElement(null, "https://adcenter.microsoft.com/api/customermanagement/Entities", "CurrencyType", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localCurrencyType.serialize(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","CurrencyType"),
                                        xmlWriter);
                                    }
                                } if (localAccountFinancialStatusTracker){
                                    if (localAccountFinancialStatus==null){

                                        writeStartElement(null, "https://adcenter.microsoft.com/api/customermanagement/Entities", "AccountFinancialStatus", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localAccountFinancialStatus.serialize(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","AccountFinancialStatus"),
                                        xmlWriter);
                                    }
                                } if (localIdTracker){
                                    namespace = "https://adcenter.microsoft.com/api/customermanagement/Entities";
                                    writeStartElement(null, namespace, "Id", xmlWriter);
                             
                                               if (localId==java.lang.Long.MIN_VALUE) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localId));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localLanguageTracker){
                                    if (localLanguage==null){

                                        writeStartElement(null, "https://adcenter.microsoft.com/api/customermanagement/Entities", "Language", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localLanguage.serialize(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","Language"),
                                        xmlWriter);
                                    }
                                } if (localLastModifiedByUserIdTracker){
                                    namespace = "https://adcenter.microsoft.com/api/customermanagement/Entities";
                                    writeStartElement(null, namespace, "LastModifiedByUserId", xmlWriter);
                             
                                               if (localLastModifiedByUserId==java.lang.Long.MIN_VALUE) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLastModifiedByUserId));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localLastModifiedTimeTracker){
                                    namespace = "https://adcenter.microsoft.com/api/customermanagement/Entities";
                                    writeStartElement(null, namespace, "LastModifiedTime", xmlWriter);
                             

                                          if (localLastModifiedTime==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLastModifiedTime));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNameTracker){
                                    namespace = "https://adcenter.microsoft.com/api/customermanagement/Entities";
                                    writeStartElement(null, namespace, "Name", xmlWriter);
                             

                                          if (localName==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localNumberTracker){
                                    namespace = "https://adcenter.microsoft.com/api/customermanagement/Entities";
                                    writeStartElement(null, namespace, "Number", xmlWriter);
                             

                                          if (localNumber==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localNumber);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localParentCustomerIdTracker){
                                    namespace = "https://adcenter.microsoft.com/api/customermanagement/Entities";
                                    writeStartElement(null, namespace, "ParentCustomerId", xmlWriter);
                             
                                               if (localParentCustomerId==java.lang.Long.MIN_VALUE) {
                                           
                                                         throw new org.apache.axis2.databinding.ADBException("ParentCustomerId cannot be null!!");
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localParentCustomerId));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPaymentMethodIdTracker){
                                    namespace = "https://adcenter.microsoft.com/api/customermanagement/Entities";
                                    writeStartElement(null, namespace, "PaymentMethodId", xmlWriter);
                             
                                               if (localPaymentMethodId==java.lang.Long.MIN_VALUE) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPaymentMethodId));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localPaymentMethodTypeTracker){
                                    if (localPaymentMethodType==null){

                                        writeStartElement(null, "https://adcenter.microsoft.com/api/customermanagement/Entities", "PaymentMethodType", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localPaymentMethodType.serialize(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","PaymentMethodType"),
                                        xmlWriter);
                                    }
                                } if (localPrimaryUserIdTracker){
                                    namespace = "https://adcenter.microsoft.com/api/customermanagement/Entities";
                                    writeStartElement(null, namespace, "PrimaryUserId", xmlWriter);
                             
                                               if (localPrimaryUserId==java.lang.Long.MIN_VALUE) {
                                           
                                                         writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                      
                                               } else {
                                                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPrimaryUserId));
                                               }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAccountLifeCycleStatusTracker){
                                    if (localAccountLifeCycleStatus==null){

                                        writeStartElement(null, "https://adcenter.microsoft.com/api/customermanagement/Entities", "AccountLifeCycleStatus", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localAccountLifeCycleStatus.serialize(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","AccountLifeCycleStatus"),
                                        xmlWriter);
                                    }
                                } if (localTimeStampTracker){
                                    namespace = "https://adcenter.microsoft.com/api/customermanagement/Entities";
                                    writeStartElement(null, namespace, "TimeStamp", xmlWriter);
                             
                                        
                                    if (localTimeStamp!=null)  {
                                       try {
                                           org.apache.axiom.util.stax.XMLStreamWriterUtils.writeDataHandler(xmlWriter, localTimeStamp, null, true);
                                       } catch (java.io.IOException ex) {
                                           throw new javax.xml.stream.XMLStreamException("Unable to read data handler for TimeStamp", ex);
                                       }
                                    } else {
                                         
                                             writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                         
                                    }
                                 
                                   xmlWriter.writeEndElement();
                             } if (localTimeZoneTracker){
                                    if (localTimeZone==null){

                                        writeStartElement(null, "https://adcenter.microsoft.com/api/customermanagement/Entities", "TimeZone", xmlWriter);

                                       // write the nil attribute
                                      writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                      xmlWriter.writeEndElement();
                                    }else{
                                     localTimeZone.serialize(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","TimeZone"),
                                        xmlWriter);
                                    }
                                } if (localPauseReasonTracker){
                                    namespace = "https://adcenter.microsoft.com/api/customermanagement/Entities";
                                    writeStartElement(null, namespace, "PauseReason", xmlWriter);
                             

                                          if (localPauseReason==null){
                                              // write the nil attribute
                                              
                                                     writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","nil","1",xmlWriter);
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPauseReason));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("https://adcenter.microsoft.com/api/customermanagement/Entities")){
                return "ns6";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(namespace, localPart);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (xmlWriter.getPrefix(namespace) == null) {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            xmlWriter.writeAttribute(namespace,attName,attValue);
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localAccountTypeTracker){
                            elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "AccountType"));
                            
                            
                                    if (localAccountType==null){
                                         throw new org.apache.axis2.databinding.ADBException("AccountType cannot be null!!");
                                    }
                                    elementList.add(localAccountType);
                                } if (localBillToCustomerIdTracker){
                                      elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "BillToCustomerId"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localBillToCustomerId));
                            } if (localCountryCodeTracker){
                                      elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "CountryCode"));
                                 
                                         elementList.add(localCountryCode==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCountryCode));
                                    } if (localCurrencyTypeTracker){
                            elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "CurrencyType"));
                            
                            
                                    elementList.add(localCurrencyType==null?null:
                                    localCurrencyType);
                                } if (localAccountFinancialStatusTracker){
                            elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "AccountFinancialStatus"));
                            
                            
                                    elementList.add(localAccountFinancialStatus==null?null:
                                    localAccountFinancialStatus);
                                } if (localIdTracker){
                                      elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "Id"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localId));
                            } if (localLanguageTracker){
                            elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "Language"));
                            
                            
                                    elementList.add(localLanguage==null?null:
                                    localLanguage);
                                } if (localLastModifiedByUserIdTracker){
                                      elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "LastModifiedByUserId"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLastModifiedByUserId));
                            } if (localLastModifiedTimeTracker){
                                      elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "LastModifiedTime"));
                                 
                                         elementList.add(localLastModifiedTime==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localLastModifiedTime));
                                    } if (localNameTracker){
                                      elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "Name"));
                                 
                                         elementList.add(localName==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localName));
                                    } if (localNumberTracker){
                                      elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "Number"));
                                 
                                         elementList.add(localNumber==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localNumber));
                                    } if (localParentCustomerIdTracker){
                                      elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "ParentCustomerId"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localParentCustomerId));
                            } if (localPaymentMethodIdTracker){
                                      elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "PaymentMethodId"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPaymentMethodId));
                            } if (localPaymentMethodTypeTracker){
                            elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "PaymentMethodType"));
                            
                            
                                    elementList.add(localPaymentMethodType==null?null:
                                    localPaymentMethodType);
                                } if (localPrimaryUserIdTracker){
                                      elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "PrimaryUserId"));
                                 
                                elementList.add(
                                   org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPrimaryUserId));
                            } if (localAccountLifeCycleStatusTracker){
                            elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "AccountLifeCycleStatus"));
                            
                            
                                    elementList.add(localAccountLifeCycleStatus==null?null:
                                    localAccountLifeCycleStatus);
                                } if (localTimeStampTracker){
                                      elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                        "TimeStamp"));
                                
                            elementList.add(localTimeStamp);
                        } if (localTimeZoneTracker){
                            elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "TimeZone"));
                            
                            
                                    elementList.add(localTimeZone==null?null:
                                    localTimeZone);
                                } if (localPauseReasonTracker){
                                      elementList.add(new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities",
                                                                      "PauseReason"));
                                 
                                         elementList.add(localPauseReason==null?null:
                                         org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localPauseReason));
                                    }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Account parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Account object =
                new Account();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"Account".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Account)com.microsoft.adcenter.api.customermanagement.imports.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","AccountType").equals(reader.getName())){
                                
                                                object.setAccountType(com.microsoft.adcenter.api.customermanagement.entities.AccountType.Factory.parse(reader));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","BillToCustomerId").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setBillToCustomerId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setBillToCustomerId(java.lang.Long.MIN_VALUE);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setBillToCustomerId(java.lang.Long.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","CountryCode").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCountryCode(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","CurrencyType").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setCurrencyType(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setCurrencyType(com.microsoft.adcenter.api.customermanagement.entities.CurrencyType.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","AccountFinancialStatus").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setAccountFinancialStatus(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setAccountFinancialStatus(com.microsoft.adcenter.api.customermanagement.entities.AccountFinancialStatus.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","Id").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setId(java.lang.Long.MIN_VALUE);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setId(java.lang.Long.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","Language").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setLanguage(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setLanguage(com.microsoft.adcenter.api.customermanagement.entities.LanguageType.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","LastModifiedByUserId").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLastModifiedByUserId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setLastModifiedByUserId(java.lang.Long.MIN_VALUE);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setLastModifiedByUserId(java.lang.Long.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","LastModifiedTime").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setLastModifiedTime(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","Name").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","Number").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setNumber(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","ParentCustomerId").equals(reader.getName())){
                                
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                    if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                        throw new org.apache.axis2.databinding.ADBException("The element: "+"ParentCustomerId" +"  cannot be null");
                                    }
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setParentCustomerId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setParentCustomerId(java.lang.Long.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","PaymentMethodId").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPaymentMethodId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setPaymentMethodId(java.lang.Long.MIN_VALUE);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setPaymentMethodId(java.lang.Long.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","PaymentMethodType").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setPaymentMethodType(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setPaymentMethodType(com.microsoft.adcenter.api.customermanagement.entities.PaymentMethodType.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","PrimaryUserId").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPrimaryUserId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToLong(content));
                                            
                                       } else {
                                           
                                           
                                                   object.setPrimaryUserId(java.lang.Long.MIN_VALUE);
                                               
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                               object.setPrimaryUserId(java.lang.Long.MIN_VALUE);
                                           
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","AccountLifeCycleStatus").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setAccountLifeCycleStatus(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setAccountLifeCycleStatus(com.microsoft.adcenter.api.customermanagement.entities.AccountLifeCycleStatus.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","TimeStamp").equals(reader.getName())){
                                
                                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                        if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                             object.setTimeStamp(null);
                                             reader.next();
                                        } else {
                                    
                                            object.setTimeStamp(org.apache.axiom.util.stax.XMLStreamReaderUtils.getDataHandlerFromElement(reader));
                                    
                                        }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","TimeZone").equals(reader.getName())){
                                
                                      nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                      if ("true".equals(nillableValue) || "1".equals(nillableValue)){
                                          object.setTimeZone(null);
                                          reader.next();
                                            
                                            reader.next();
                                          
                                      }else{
                                    
                                                object.setTimeZone(com.microsoft.adcenter.api.customermanagement.entities.TimeZoneType.Factory.parse(reader));
                                              
                                        reader.next();
                                    }
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("https://adcenter.microsoft.com/api/customermanagement/Entities","PauseReason").equals(reader.getName())){
                                
                                       nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","nil");
                                       if (!"true".equals(nillableValue) && !"1".equals(nillableValue)){
                                    

                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setPauseReason(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToUnsignedByte(content));
                                            
                                       } else {
                                           
                                           
                                           reader.getElementText(); // throw away text nodes if any.
                                       }
                                      
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    