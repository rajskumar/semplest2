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
    
    public partial class AdvertisingEngineBidData
    {
        public string AdvertisingEngineBidDataPK { get; set; }
        public Nullable<int> KeywordBidFK { get; set; }
        public int MicroBidAmount { get; set; }
        public int NumberImpressions { get; set; }
        public int NumberClick { get; set; }
        public int AveragePosition { get; set; }
        public decimal AverageCPC { get; set; }
        public System.DateTime CreatedDate { get; set; }
        public System.DateTime TransactionDate { get; set; }
    
        public virtual KeywordBid KeywordBid { get; set; }
    }
}
