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
    [KnownType(typeof(RolesRightsAssociation))]
    [KnownType(typeof(UserRolesAssociation))]
    public partial class Role
    {
        public Role()
        {
            this.RolesRightsAssociations = new HashSet<RolesRightsAssociation>();
            this.UserRolesAssociations = new HashSet<UserRolesAssociation>();
        }
    
        [DataMember]
        public int RolePK { get; set; }
        [DataMember]
        public string RoleName { get; set; }
    
        [DataMember]
        public virtual ICollection<RolesRightsAssociation> RolesRightsAssociations { get; set; }
        [DataMember]
        public virtual ICollection<UserRolesAssociation> UserRolesAssociations { get; set; }
    }
    
}
