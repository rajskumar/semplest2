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
    
    public partial class AdvertisingEngineAccount
    {
        public AdvertisingEngineAccount()
        {
            this.AdvertisingEngineCampaigns = new HashSet<AdvertisingEngineCampaign>();
        }
    
        public string AdvertisingEngineAccountPK { get; set; }
        public int AdvertisingEngineFK { get; set; }
        public int CustomerFK { get; set; }
    
        public virtual AdvertisingEngine AdvertisingEngine { get; set; }
        public virtual ICollection<AdvertisingEngineCampaign> AdvertisingEngineCampaigns { get; set; }
        public virtual Customer Customer { get; set; }
    }
}
