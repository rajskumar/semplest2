//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Semplest.Core.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class Keyword
    {
        public Keyword()
        {
            this.PromotionKeywordAssociations = new HashSet<PromotionKeywordAssociation>();
        }
    
        public int KeywordPK { get; set; }
        public string Keyword1 { get; set; }
        public bool IsActive { get; set; }
        public bool ISNegative { get; set; }
        public System.DateTime CreatedDate { get; set; }
        public Nullable<System.DateTime> EditedDate { get; set; }
    
        public virtual ICollection<PromotionKeywordAssociation> PromotionKeywordAssociations { get; set; }
    }
}
