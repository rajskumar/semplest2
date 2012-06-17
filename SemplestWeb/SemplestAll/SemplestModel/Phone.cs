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
    [KnownType(typeof(CustomerPhoneAssociation))]
    [KnownType(typeof(EmployeePhoneAssociation))]
    [KnownType(typeof(PhoneType))]
    public partial class Phone
    {
        public Phone()
        {
            this.CustomerPhoneAssociations = new HashSet<CustomerPhoneAssociation>();
            this.EmployeePhoneAssociations = new HashSet<EmployeePhoneAssociation>();
        }
    
        [DataMember]
        public int PhonePK { get; set; }
        [DataMember]
        public string Phone1 { get; set; }
        [DataMember]
        public string Extension { get; set; }
        [DataMember]
        public int PhoneTypeFK { get; set; }
        [DataMember]
        public System.DateTime CreatedDate { get; set; }
        [DataMember]
        public Nullable<System.DateTime> EditedDate { get; set; }
    
        [DataMember]
        public virtual ICollection<CustomerPhoneAssociation> CustomerPhoneAssociations { get; set; }
        [DataMember]
        public virtual ICollection<EmployeePhoneAssociation> EmployeePhoneAssociations { get; set; }
        [DataMember]
        public virtual PhoneType PhoneType { get; set; }
    }
    
}
