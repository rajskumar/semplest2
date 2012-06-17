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
    [KnownType(typeof(KeywordBid))]
    public partial class TrafficEstimator
    {
        [DataMember]
        public int TrafficEstimatorPK { get; set; }
        [DataMember]
        public Nullable<int> KeywordBidFK { get; set; }
        [DataMember]
        public int MicroBid { get; set; }
        [DataMember]
        public double AveMicroCost { get; set; }
        [DataMember]
        public double AveNumberClicks { get; set; }
        [DataMember]
        public Nullable<double> AvePosition { get; set; }
        [DataMember]
        public Nullable<double> AveCPC { get; set; }
        [DataMember]
        public System.DateTime CreatedDate { get; set; }
    
        [DataMember]
        public virtual KeywordBid KeywordBid { get; set; }
    }
    
}
