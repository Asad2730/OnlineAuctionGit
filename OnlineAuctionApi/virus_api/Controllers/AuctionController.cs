using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Http;
using WebApplication1.Models;

namespace WebApplication1.Controllers
{
    public class AuctionController : ApiController
    {
        public AuctionDbEntities5 db = new AuctionDbEntities5();        

     

        [HttpPost]
        public HttpResponseMessage signUp(User u)
        {
            try
            {
               
                db.Users.Add(u);
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, u);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        public HttpResponseMessage login(User u)
        {
            try
            {
                var q = db.Users.FirstOrDefault(i=>i.email== u.email && i.password == u.password);
                if (q == null)
                {
                    return Request.CreateResponse(HttpStatusCode.OK,q);
                }
                return Request.CreateResponse(HttpStatusCode.OK, q);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        [HttpPost]
        public HttpResponseMessage insertProduct(Product p)
        {
            try
            {   
                var q = db.Products.OrderByDescending(i=>i.Id).FirstOrDefault();
                q.uid = p.uid;
                q.price = p.price;
                q.location = p.location;
                q.type = p.type;
                q.name = p.name;
                q.description = p.description;
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, q);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        public HttpResponseMessage upload()
        {
            try
            {

                var request = HttpContext.Current.Request;
                
                var photo = request.Files["image"];

                FileInfo fileInfo = new FileInfo(photo.FileName);
                var ex = fileInfo.Extension;
                var fname = photo.FileName;


                photo.SaveAs(HttpContext.Current.Server.MapPath("~/Content/Uploads/" + fname));
                Product p = new Product();
                p.image = "/Content/Uploads/" + fname;
                db.Products.Add(p);
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, "Image uploaded");
            }
            catch(Exception e)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }


        [HttpGet]
        public HttpResponseMessage getProducts(int uid)
        {
            try
            {

                var q = db.Products.Where(i=>i.uid != uid).ToList();
                return Request.CreateResponse(HttpStatusCode.OK, q);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpPost]
        public HttpResponseMessage insertWallet(Wallet w)
        {
            try
            {
                var q = db.Wallets.FirstOrDefault(i => i.uid == w.uid);
                if (q == null)
                {   
                    db.Wallets.Add(w);
                    db.SaveChanges();
                    return Request.CreateResponse(HttpStatusCode.OK, w);
                }

                q.balance += w.balance;
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, q);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        [HttpGet]

        public HttpResponseMessage getPkr(int uid)
        {
            try
            {
                var q = db.Wallets.FirstOrDefault(i=>i.uid == uid);
                if (q == null)
                {   
                    
                    return Request.CreateResponse(HttpStatusCode.OK,q);
                }
                return Request.CreateResponse(HttpStatusCode.OK, q);
            }
            catch(Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        [HttpPost]

        public HttpResponseMessage makeOffer(Offer o)
        {
            try
            {
               db.Offers.Add(o);
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, o);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

    }
}
