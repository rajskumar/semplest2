﻿using System;
using System.Collections.Generic;
using System.Reflection;
using System.Web.Mvc;
using Semplest.Core.Models;
using SemplestWebApp.Services;
using Semplest.SharedResources.Helpers;
using Semplest.SharedResources.Services;
using SemplestModel;
using System.Linq;

namespace SemplestWebApp.Controllers
{
    [ExceptionHelper]
    [AuthorizeRole]
    [OutputCache(NoStore = true, Duration = 0, VaryByParam = "*")]
    public class HomeController : Controller
    {

        public ActionResult Index()
        {
            return RedirectToAction("Index2", "Home", new HomeModelChild());
            HomeModelParent hm = new HomeModelParent();
            SemplestModel.Semplest dbContext = new SemplestModel.Semplest();
            int userId = ((Credential)(Session[Semplest.SharedResources.SEMplestConstants.SESSION_USERID])).UsersFK;
            foreach (ProductGroup pg in dbContext.Users.Where(x => x.UserPK == userId).First().Customer.ProductGroups)
            {
                foreach (Promotion p in pg.Promotions)
                {
                    if (p.IsLaunched)
                        hm.LaunchedCampaigns++;
                    else
                        hm.StartedCampaignsNotLaunched++;
                }
            }
            hm.AdvertisingEngines = dbContext.AdvertisingEngines;
            return View(hm);
        }


        public ActionResult Index2()
        {
            SemplestModel.Semplest dbContext = new SemplestModel.Semplest();
            Credential cred = ((Credential)(Session[Semplest.SharedResources.SEMplestConstants.SESSION_USERID]));

            HomeModelChild child = new HomeModelChild();
            IQueryable<Credential> cCred = dbContext.Credentials.Where(x => x.UsersFK == cred.UsersFK);
            ViewBag.Title = cCred.First().User.FirstName + " " + cCred.First().User.LastName + " - " + cCred.First().User.Customer.Name;
            child.ProductGroups = cCred.First().User.Customer.ProductGroups;
            return View(child);
        }
        public ActionResult GetLiveProductGroups()
        {
            Credential cred = ((Credential)(Session[Semplest.SharedResources.SEMplestConstants.SESSION_USERID]));
            SemplestModel.Semplest dbContext = new SemplestModel.Semplest();
            return Json(dbContext.vwGetLivePromotionsForUsers.Where(t => t.UserPK == cred.UsersFK), JsonRequestBehavior.AllowGet);
        }

        public ActionResult About()
        {
            StaticModel vm = new StaticModel(StaticPages.About);
            return View("Static", vm);
        }

        public ActionResult FAQ()
        {
            StaticModel vm = new StaticModel(StaticPages.FAQ);
            return View("Static", vm);
        }

        public ActionResult Contact()
        {
            StaticModel vm = new StaticModel(StaticPages.Contact);
            return View("Static", vm);
        }

        public ActionResult PrivacyPolicy()
        {
            StaticModel vm = new StaticModel(StaticPages.PrivacyPolicy);
            return View("Static", vm);
        }

        public ActionResult TermsAndConditions()
        {
            StaticModel vm = new StaticModel(StaticPages.TermsAndConditions);
            return View("Static", vm);
        }

        public ActionResult SearchKeywords()
        {
            return View();
        }

        [HttpPost]
        [ActionName("SearchKeywords")]
        [AcceptSubmitType(Name = "Command", Type = "GetCategories")]
        public ActionResult GetCategories(SearchKeywordsModel model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    var scw = new ServiceClientWrapper();
                    var addcopies = new[] { model.AdCopy };
                    List<string> categories = scw.GetCategories(null, model.Product, model.Description, addcopies,
                                                                model.LandingPage);
                    if (categories != null && categories.Count > 0)
                    {
                        int i = 0;
                        foreach (string cate in categories)
                        {
                            var cm = new SearchKeywordsModel.CategoriesModel { Id = i, Name = cate };
                            i++;
                            model.AllCategories.Add(cm);
                        }
                    }
                    else
                    {
                        CreateDummyModel(model);
                    }

                    // save this some how while getting the keywords this is becoming null
                    Session.Add("AllCategories", model.AllCategories);
                }
                return View(model);
            }
            catch (Exception ex)
            {
                string err = ex.Message + "\\r\\n" + ex.StackTrace;
                //CreateDummyModel(model);
                //ViewBag.AllCategories = model.AllCategories;
                //Session.Add("AllCategories", model.AllCategories);
                return View(model);
            }
        }

        [HttpPost]
        public ActionResult AddProductGroupName(FormCollection fc)
        {
            try
            {
                string productgroupname = fc["newproductgroupname"].ToString().Trim();
                Credential c = ((Credential)(Session[Semplest.SharedResources.SEMplestConstants.SESSION_USERID]));
                if (productgroupname == null || productgroupname == "") throw new Exception("Blank");
                SemplestModel.Semplest dbContext = new SemplestModel.Semplest();
                var productgroupexists = from pg in dbContext.ProductGroups
                                         where pg.CustomerFK.Equals(c.User.CustomerFK.Value) && pg.ProductGroupName.Equals(productgroupname)
                                         select pg;

                if (productgroupexists.Count() > 0) throw new Exception("Duplicate");

                dbContext.ProductGroups.Add(new ProductGroup { CustomerFK = c.User.CustomerFK.Value, ProductGroupName = productgroupname, StartDate = DateTime.Now, IsActive = true });
                dbContext.SaveChanges();

            }
            catch (Exception ex)
            {

            }

            return RedirectToAction("Index2");
        }


        [HttpPost]
        [ActionName("SearchKeywords")]
        [AcceptSubmitType(Name = "Command", Type = "GetKeywords")]
        public ActionResult GetKeywords(SearchKeywordsModel model)
        {
            try
            {
                if (ModelState.IsValid)
                {
                    model.StatusMessage = "Please wait while getting the Keywords";
                    //SemplestWebApp.Helpers.ServiceHelper.CallSemplestTestGetMethod();
                    if (model.AllCategories.Count == 0)
                    {
                        model.AllCategories = (List<SearchKeywordsModel.CategoriesModel>)Session["AllCategories"];
                    }

                    if (model.AllCategories.Count <= 0)
                    {
                    }

                    var catList = new List<string>();

                    foreach (SearchKeywordsModel.CategoriesModel cat in model.AllCategories)
                    {
                        for (int i = 0; i < model.ItemIds.Length; i++)
                        {
                            if (cat.Id == model.ItemIds[i])
                            {
                                catList.Add(cat.Name);
                            }
                        }
                    }


                    var scw = new ServiceClientWrapper();
                    //var query = from c in model.AllCategories
                    //            let i = c.Id
                    //            where model.ItemIds.Contains(i)
                    //            select  c.Name ;
                    //List<string> catList = model.AllCategories.Select(m => m.Name).Where(
                    //List<string> keywords = scw.GetKeywords(catList, null, "coffee machine", null, null, "http://www.wholelattelove.com", null);
                    var addcopies = new[] { model.AdCopy };
                    List<string> keywords = scw.GetKeywords(catList, null, model.Product, model.Description, addcopies,
                                                            model.LandingPage, null);
                    if (keywords != null && keywords.Count > 0)
                    {
                        int i = 0;
                        foreach (string key in keywords)
                        {
                            var kwm = new SearchKeywordsModel.KeywordsModel();
                            kwm.Name = key;
                            model.AllKeywords.Add(kwm);
                        }
                    }
                    else
                    {
                        CreateDummyModel(model);
                    }
                }

                return View(model);
            }
            catch (Exception ex)
            {
                string err = ex.Message + "\\r\\n" + ex.StackTrace;
                //CreateDummyModel(model);
                return View(model);
            }
        }

        [HttpPost]
        public ActionResult RemovePromotion(int promotionId)
        {
            try
            {
                SemplestModel.Semplest dbContext = new SemplestModel.Semplest();
                dbContext.Promotions.Where(x => x.PromotionPK == promotionId).First().IsDeleted = true;
                dbContext.SaveChanges();
            }
            catch (Exception ex) { Semplest.SharedResources.Helpers.ExceptionHelper.LogException(ex.ToString()); }
            return RedirectToAction("Index2");
        }

        [HttpPost]
        public ActionResult PausePromotion(int promotionIdP)
        {
            try
            {
                ServiceClientWrapper sw = new ServiceClientWrapper();
                SemplestModel.Semplest dbContext = new SemplestModel.Semplest();
                List<string> adEngines = new List<string>();
                Promotion p = dbContext.Promotions.Where(x => x.PromotionPK == promotionIdP).First();
                int customerId = ((Credential)Session[Semplest.SharedResources.SEMplestConstants.SESSION_USERID]).User.CustomerFK.Value;
                foreach (PromotionAdEngineSelected pades in p.PromotionAdEngineSelecteds)
                    adEngines.Add(pades.AdvertisingEngine.AdvertisingEngine1);
                p.IsPaused = sw.schedulePromotion(customerId, promotionIdP, adEngines.ToArray(), false);
                dbContext.SaveChanges();
            }
            catch (Exception ex) { Semplest.SharedResources.Helpers.ExceptionHelper.LogException(ex.ToString()); }
            return RedirectToAction("Index2");
        }

        [HttpPost]
        public ActionResult ResumePromotion(int promotionIdR)
        {
            try
            {
                ServiceClientWrapper sw = new ServiceClientWrapper();
                SemplestModel.Semplest dbContext = new SemplestModel.Semplest();
                List<string> adEngines = new List<string>();
                Promotion p = dbContext.Promotions.Where(x => x.PromotionPK == promotionIdR).First();
                int customerId = ((Credential)Session[Semplest.SharedResources.SEMplestConstants.SESSION_USERID]).User.CustomerFK.Value;
                foreach (PromotionAdEngineSelected pades in p.PromotionAdEngineSelecteds)
                    adEngines.Add(pades.AdvertisingEngine.AdvertisingEngine1);
                p.IsPaused = !sw.schedulePromotion(customerId, promotionIdR, adEngines.ToArray(), false);
                dbContext.SaveChanges();
            }
            catch (Exception ex) { Semplest.SharedResources.Helpers.ExceptionHelper.LogException(ex.ToString()); }
            return RedirectToAction("Index2");
        }

        [HttpPost]
        public ActionResult EndPromotion(int promotionIdE)
        {
            try
            {
                ServiceClientWrapper sw = new ServiceClientWrapper();
                SemplestModel.Semplest dbContext = new SemplestModel.Semplest();
                List<string> adEngines = new List<string>();
                Promotion p = dbContext.Promotions.Where(x => x.PromotionPK == promotionIdE).First();
                int customerId = ((Credential)Session[Semplest.SharedResources.SEMplestConstants.SESSION_USERID]).User.CustomerFK.Value;
                foreach (PromotionAdEngineSelected pades in p.PromotionAdEngineSelecteds)
                    adEngines.Add(pades.AdvertisingEngine.AdvertisingEngine1);
                p.IsCompleted = sw.schedulePromotion(customerId, promotionIdE, adEngines.ToArray(), false);
                dbContext.SaveChanges();
            }
            catch (Exception ex) { Semplest.SharedResources.Helpers.ExceptionHelper.LogException(ex.ToString()); }
            return RedirectToAction("Index2");
        }


        private void CreateDummyModel(SearchKeywordsModel model)
        {
            // create a dummy model
            //model.AllCategories.Add(new SearchKeywordsModel.CategoriesModel { Name = "category 1" });
            //model.AllCategories.Add(new SearchKeywordsModel.CategoriesModel { Name = "category 2" });
            //model.AllCategories.Add(new SearchKeywordsModel.CategoriesModel { Name = "category 3" });
            //model.AllCategories.Add(new SearchKeywordsModel.CategoriesModel { Name = "category 4" });
            //model.AllCategories.Add(new SearchKeywordsModel.CategoriesModel { Name = "category 5" });
            model.AllCategories.Add(new SearchKeywordsModel.CategoriesModel { Id = 1, Name = "category 1" });
            model.AllCategories.Add(new SearchKeywordsModel.CategoriesModel { Id = 2, Name = "category 2" });
            model.AllCategories.Add(new SearchKeywordsModel.CategoriesModel { Id = 3, Name = "category 3" });
            model.AllCategories.Add(new SearchKeywordsModel.CategoriesModel { Id = 4, Name = "category 4" });
            model.AllCategories.Add(new SearchKeywordsModel.CategoriesModel { Id = 5, Name = "category 5" });
        }

        #region Nested type: AcceptSubmitTypeAttribute

        public class AcceptSubmitTypeAttribute : ActionMethodSelectorAttribute
        {
            public string Name { get; set; }
            public string Type { get; set; }

            public override bool IsValidForRequest(ControllerContext controllerContext, MethodInfo methodInfo)
            {
                return controllerContext.RequestContext.HttpContext
                           .Request.Form[Name] == Type;
            }
        }

        #endregion
    }
}