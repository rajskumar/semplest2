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
    [KnownType(typeof(Right))]
    [KnownType(typeof(Role))]
    public partial class RolesRightsAssociation
    {
        [DataMember]
        public int RolesFK { get; set; }
        [DataMember]
        public int RightsFK { get; set; }
        [DataMember]
        public bool IsVisible { get; set; }
        [DataMember]
        public bool IsReadonly { get; set; }
    
        [DataMember]
        public virtual Right Right { get; set; }
        [DataMember]
        public virtual Role Role { get; set; }
    }
    
}
