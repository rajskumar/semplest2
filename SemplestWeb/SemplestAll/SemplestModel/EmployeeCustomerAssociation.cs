//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

using System;
using System.Collections.Generic;
using System.Runtime.Serialization;


namespace SemplestModel
{
    [DataContract(IsReference = true)]
    [KnownType(typeof(Customer))]
    [KnownType(typeof(Employee))]
    public partial class EmployeeCustomerAssociation
    {
        [DataMember]
        public int EmployeeCustomerAssociationPK { get; set; }
        [DataMember]
        public int CustomerFK { get; set; }
        [DataMember]
        public int EmployeeFK { get; set; }
    
        [DataMember]
        public virtual Customer Customer { get; set; }
        [DataMember]
        public virtual Employee Employee { get; set; }
    }
    
}
