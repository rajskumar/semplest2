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
    
    public partial class CustomerHierarchy
    {
        public int CustomerHierarchyPK { get; set; }
        public int CustomerFK { get; set; }
        public int CustomerParentFK { get; set; }
        public System.DateTime CreatedDate { get; set; }
    
        public virtual Customer Customer { get; set; }
        public virtual Customer Customer1 { get; set; }
    }
}
