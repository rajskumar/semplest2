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
    
    public partial class UserRolesAssociation
    {
        public int UserRolesAssociationPK { get; set; }
        public int RolesFK { get; set; }
        public int UsersFK { get; set; }
    
        public virtual Role Role { get; set; }
        public virtual User User { get; set; }
    }
}
