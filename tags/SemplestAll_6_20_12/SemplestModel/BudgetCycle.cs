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
    
    public partial class BudgetCycle
    {
        public BudgetCycle()
        {
            this.Promotions = new HashSet<Promotion>();
        }
    
        public int BudgetCyclePK { get; set; }
        public string BudgetCycle1 { get; set; }
    
        public virtual ICollection<Promotion> Promotions { get; set; }
    }
}
