//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Semplest.Admin.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class AdvertisingEngineCampaign
    {
        public string AdvertisingEngineCampaignPK { get; set; }
        public Nullable<int> PromotionFK { get; set; }
        public string AdvertisingEngineAccountFK { get; set; }
    
        public virtual AdvertisingEngineAccount AdvertisingEngineAccount { get; set; }
        public virtual Promotion Promotion { get; set; }
    }
}
