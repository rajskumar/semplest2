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
    
    public partial class WebContent
    {
        public int WebContentPK { get; set; }
        public Nullable<int> CustomerFK { get; set; }
        public string FAQ { get; set; }
        public string AboutUs { get; set; }
        public string ContactUs { get; set; }
    
        public virtual Customer Customer { get; set; }
    }
}