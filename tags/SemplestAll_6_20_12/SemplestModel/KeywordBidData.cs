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
    
    public partial class KeywordBidData
    {
        public int KeywordBidDataPK { get; set; }
        public Nullable<int> QualityScore { get; set; }
        public string ApprovalStatus { get; set; }
        public Nullable<int> FirstPageMicroCPC { get; set; }
        public bool IsEligibleForShowing { get; set; }
        public System.DateTime CreatedDate { get; set; }
        public Nullable<int> KeywordBidFK { get; set; }
    
        public virtual KeywordBid KeywordBid { get; set; }
    }
}
