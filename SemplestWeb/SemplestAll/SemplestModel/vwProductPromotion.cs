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
    public partial class vwProductPromotion
    {
        [DataMember]
        public int ProductGroupPK { get; set; }
        [DataMember]
        public int CustomerFK { get; set; }
        [DataMember]
        public string ProductGroupName { get; set; }
        [DataMember]
        public int PromotionPK { get; set; }
        [DataMember]
        public string PromotionName { get; set; }
        [DataMember]
        public int UserPK { get; set; }
    }
    
}
