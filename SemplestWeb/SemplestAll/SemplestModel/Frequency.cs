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
    [KnownType(typeof(Schedule))]
    public partial class Frequency
    {
        public Frequency()
        {
            this.Schedules = new HashSet<Schedule>();
        }
    
        [DataMember]
        public int FrequencyPK { get; set; }
        [DataMember]
        public string Frequency1 { get; set; }
    
        [DataMember]
        public virtual ICollection<Schedule> Schedules { get; set; }
    }
    
}
