//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace SemplestWebApp.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class PhoneType
    {
        public PhoneType()
        {
            this.Phones = new HashSet<Phone>();
        }
    
        public int PhoneTypePK { get; set; }
        public string PhoneType1 { get; set; }
    
        public virtual ICollection<Phone> Phones { get; set; }
    }
}
