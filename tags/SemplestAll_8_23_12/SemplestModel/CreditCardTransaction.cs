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
    
    public partial class CreditCardTransaction
    {
        public CreditCardTransaction()
        {
            this.PromotionPayments = new HashSet<PromotionPayment>();
        }
    
        public int CreditCardTransactionPK { get; set; }
        public Nullable<int> CreditCardProfileFK { get; set; }
        public string OrderID { get; set; }
        public decimal Amount { get; set; }
        public System.DateTime CreatedDate { get; set; }
        public decimal SEMplestFee { get; set; }
        public decimal MediaSpend { get; set; }
        public bool IsRefund { get; set; }
    
        public virtual CreditCardProfile CreditCardProfile { get; set; }
        public virtual ICollection<PromotionPayment> PromotionPayments { get; set; }
    }
}