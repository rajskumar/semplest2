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
    
    public partial class StateCode
    {
        public StateCode()
        {
            this.Addresses = new HashSet<Address>();
        }
    
        public int StateAbbrPK { get; set; }
        public string StateAbbr { get; set; }
    
        public virtual ICollection<Address> Addresses { get; set; }
    }
}
