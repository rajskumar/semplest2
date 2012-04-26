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
    
    public partial class Schedule
    {
        public Schedule()
        {
            this.ScheduleJobs = new HashSet<ScheduleJob>();
            this.ScheduleTaskAssociations = new HashSet<ScheduleTaskAssociation>();
        }
    
        public int SchedulePK { get; set; }
        public string ScheduleName { get; set; }
        public System.DateTime StartTime { get; set; }
        public Nullable<System.DateTime> EndDate { get; set; }
        public int FrequencyFK { get; set; }
        public bool IsEnabled { get; set; }
        public bool IsInactive { get; set; }
        public int CreatedBy { get; set; }
        public System.DateTime CreatedDate { get; set; }
        public int EditedBy { get; set; }
        public Nullable<System.DateTime> EditedDate { get; set; }
        public Nullable<int> PromotionFK { get; set; }
        public Nullable<int> CustomerFK { get; set; }
        public Nullable<int> ProductGroupFK { get; set; }
        public Nullable<int> UsersFK { get; set; }
    
        public virtual Customer Customer { get; set; }
        public virtual Frequency Frequency { get; set; }
        public virtual ProductGroup ProductGroup { get; set; }
        public virtual Promotion Promotion { get; set; }
        public virtual ICollection<ScheduleJob> ScheduleJobs { get; set; }
        public virtual ICollection<ScheduleTaskAssociation> ScheduleTaskAssociations { get; set; }
        public virtual User User { get; set; }
    }
}
