//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace SemplestModel
{
    using System;
    using System.Collections.Generic;
    
    public partial class Right
    {
        public Right()
        {
            this.RolesRightsAssociations = new HashSet<RolesRightsAssociation>();
        }
    
        public int RightsPK { get; set; }
        public string Controller { get; set; }
        public string Label { get; set; }
    
        public virtual ICollection<RolesRightsAssociation> RolesRightsAssociations { get; set; }
    }
}
