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
    [KnownType(typeof(Role))]
    [KnownType(typeof(User))]
    public partial class UserRolesAssociation
    {
        [DataMember]
        public int UserRolesAssociationPK { get; set; }
        [DataMember]
        public int UsersFK { get; set; }
        [DataMember]
        public int RolesFK { get; set; }
    
        [DataMember]
        public virtual Role Role { get; set; }
        [DataMember]
        public virtual User User { get; set; }
    }
    
}
