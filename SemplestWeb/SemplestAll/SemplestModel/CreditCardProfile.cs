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
    [KnownType(typeof(CreditCardTransaction))]
    public partial class CreditCardProfile
    {
        public CreditCardProfile()
        {
            this.CreditCardTransactions = new HashSet<CreditCardTransaction>();
        }
    
        [DataMember]
        public int CreditCardProfilePK { get; set; }
        [DataMember]
        public int CustomerFK { get; set; }
        [DataMember]
        public string CustomerRefNum { get; set; }
        [DataMember]
        public string AuthCode { get; set; }
        [DataMember]
        public string TxRefNum { get; set; }
    
        [DataMember]
        public virtual ICollection<CreditCardTransaction> CreditCardTransactions { get; set; }
    }
    
}
