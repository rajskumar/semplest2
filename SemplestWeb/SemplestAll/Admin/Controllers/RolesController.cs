﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Semplest.Admin.Models;
using System.Reflection;
using System.Data.Entity.Validation;
using SemplestModel;
using Semplest.SharedResources.Helpers;
using Semplest.SharedResources.Services;

namespace Semplest.Admin.Controllers
{
    [ExceptionHelper]
    [AuthorizeRole]
    [OutputCache(NoStore = true, Duration = 0, VaryByParam = "*")] 
    public class RolesController : Controller
    {
        SemplestEntities _dbContext = new SemplestEntities();
        //
        // GET: /Roles/

       public ActionResult Index()
        {
            IEnumerable<SemplestModel.Role> viewModel = _dbContext.Roles;
            return View(viewModel);
        }

        // GET: /Roles/Create
        public ActionResult Create()
        {
            ViewData["Roles"] = new SelectList(_dbContext.Roles, "RolePK", "RoleName");
            return View(_dbContext.sp_GetRigtsRolesInteraction(null));
        }

        //
        // POST: /Roles/Create
        [HttpPost]
        public ActionResult Create(IEnumerable<sp_GetRigtsRolesInteraction_Result> userRights, FormCollection f)
        {
            // TODO: Add insert logic here
            var ro = new Role {RoleName = f["roleName"].ToString()};
            _dbContext.Roles.Add(ro);
            _dbContext.SaveChanges();
            foreach (sp_GetRigtsRolesInteraction_Result s in userRights)
            {
                _dbContext.RolesRightsAssociations.Add(AddRoleRightAssociation(s, ro.RolePK));
            }
            _dbContext.SaveChanges();
            return RedirectToAction("Index");
        }

        //
        // GET: /Roles/Edit/5
        public ActionResult Edit(int id)
        {
            ViewBag.RoleName = _dbContext.Roles.Where(x => x.RolePK == id).First().RoleName;
            return View(_dbContext.sp_GetRigtsRolesInteraction(id));
        }
        //
        // POST: /Roles/Edit/5
        [HttpPost]
        public ActionResult Edit(IEnumerable<sp_GetRigtsRolesInteraction_Result> userRights, int id)
        {
                foreach (sp_GetRigtsRolesInteraction_Result s in userRights)
                {
                    var z = _dbContext.RolesRightsAssociations.Where(x => x.RolesFK == id  && x.RightsFK == s.RightsPK);
                    if (z.Any())
                    {
                        foreach (var l in z)
                        {
                            l.IsReadonly = s.IsReadonly == null ? false : bool.Parse(s.IsReadonly.ToString());
                            l.IsVisible = s.IsVisible == null ? false : bool.Parse(s.IsVisible.ToString());
                        }
                    }
                    else {  _dbContext.RolesRightsAssociations.Add(AddRoleRightAssociation(s, id)); }
                }
                _dbContext.SaveChanges();

                return RedirectToAction("Index");
        }

        public RolesRightsAssociation AddRoleRightAssociation(sp_GetRigtsRolesInteraction_Result s, int RolePK)
        {
            RolesRightsAssociation ra = new RolesRightsAssociation
            {
                IsReadonly = s.IsReadonly == null ? false : bool.Parse(s.IsReadonly.ToString()),
                IsVisible = s.IsVisible == null ? false : bool.Parse(s.IsVisible.ToString()),
                RightsFK = s.RightsPK,
                RolesFK = RolePK
            };
            return ra;
        }

        //
        // GET: /Roles/Delete/5



        //
        // POST: /Roles/Delete/5

        [HttpPost]
        public ActionResult Delete(int id)
        {
            try
            {
                // TODO: Add delete logic here
                SemplestEntities dbContext = new SemplestEntities();
                Role r = dbContext.Roles.Where(x => x.RolePK == id).First();
                r.RolesRightsAssociations.Clear();
               dbContext.Roles.Remove(r);
               dbContext.SaveChanges();
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }



        public ActionResult Models(string RoleId)
        {

            int role = int.Parse(RoleId);
            RolesRightsAssociation a = new RolesRightsAssociation();
            a.IsVisible = false;
            a.IsReadonly = false;
            //Right ri = new Right();
            //ri.Controller ="DefaultController";
            //ri.Label ="DefaultLabel";
            //a.Right=ri;
            //var viewModel =
            //    from ra in _dbContext.RolesRightsAssociations
            //    join r in _dbContext.Rights on ra.RightsFK equals r.RightsPK into tl_j
            //    where ra.RolesFK == role
            //    from j in tl_j.DefaultIfEmpty()
            //    select new UserRoleModel
            //    {
            //        Controller = j.Controller,
            //        Label = j.Label,
            //        IsVisible = ra.IsVisible == null ? false : ra.IsVisible,
            //        IsReadonly = ra.IsReadonly == null ? false : ra.IsReadonly
            //    };

            /*from r in _dbContext.Rights
            join ra in _dbContext.RolesRightsAssociations on r.RightsPK equals ra.RightsFK
            where ra.RolesFK == role
            select new UserRoleModel
            {
                Controller = r.Controller,
                Label = r.Controller,
                IsVisible = ra.IsVisible,
                IsReadonly = ra.IsReadonly
            };*/
            return PartialView("_RolesRightsAssociation", _dbContext.sp_GetRigtsRolesInteraction(role));
            //sp_GetRigtsRolesInteraction_Result model = _dbContext.sp_GetRigtsRolesInteraction(role);


            //return View();
            //var viewModel =
            //                from ro in _dbContext.Roles
            //                join ri in _dbContext.Rights on ro.RolePK equals ri.RolesFK
            //                where ro.RolePK == id
            //                select new RoleModel
            //                 {
            //                     RightPK = ri.RightsPK,
            //                     RightName = ri.RightName
            //                 }; 
            //return Json(viewModel.ToList());
        }

    }
}
