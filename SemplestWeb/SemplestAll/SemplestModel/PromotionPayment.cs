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
    
    public partial class PromotionPayment
    {
        public int PromotionPaymentPK { get; set; }
        public Nullable<int> PromotionFK { get; set; }
        public decimal Amount { get; set; }
        public System.DateTime BudgetToAddDate { get; set; }
        public bool IsValid { get; set; }
        public System.DateTime CreatedDate { get; set; }
    
        public virtual Promotion Promotion { get; set; }
    }
}
