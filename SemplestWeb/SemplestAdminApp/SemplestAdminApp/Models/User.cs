//------------------------------------------------------------------------------
// <auto-generated>
//    This code was generated from a template.
//
//    Manual changes to this file may cause unexpected behavior in your application.
//    Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------



namespace SemplestAdminApp.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    
    public partial class User
    {
        public User()
        {
            this.Credentials = new HashSet<Credential>();
            this.Roles = new HashSet<Role>();
        }
        
        public int UserPK { get; set; }
        
        public Nullable<int> CustomerFK { get; set; }
        [Required]
        public string FirstName { get; set; }
        public string MiddleInitial { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public System.DateTime CreatedDate { get; set; }
        public Nullable<System.DateTime> EditedDate { get; set; }
        public bool IsActive { get; set; }
    
        public virtual ICollection<Credential> Credentials { get; set; }
        public virtual Customer Customer { get; set; }
        public virtual ICollection<Role> Roles { get; set; }
    }
}
