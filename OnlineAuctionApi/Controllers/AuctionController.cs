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
        public AuctionDbEntities6 db = new AuctionDbEntities6();        

     

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
                q.cid = p.cid;
                q.p_Date = p.p_Date;
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

        [HttpPost]
        public HttpResponseMessage addCategory(Category c)
        {
            try
            {
                db.Categories.Add(c);
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, c);
            }
            catch (Exception e)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        [HttpPost]
        public HttpResponseMessage insertFavourite(int uid, int pid)
        {
            try
            {   
                var q = db.Favourites.FirstOrDefault(i=>i.uid == uid);
                if(q == null)
                {   
                    Favourite f = new Favourite();
                    f.uid = uid;
                    f.pid = pid;
                    
                    db.Favourites.Add(f);
                    db.SaveChanges();
                    return Request.CreateResponse(HttpStatusCode.OK, "Product added to favourites!");
                }
                 db.Favourites.Remove(q);
                 db.SaveChanges();   
                return Request.CreateResponse(HttpStatusCode.OK, "Product removed from favourites!");
            }
            catch (Exception e)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        [HttpGet]
        public HttpResponseMessage getFavourite(int uid)
        {
            try
            {
                var q = db.Products.Join(db.Favourites,p=>p.Id,f=>f.pid,
                    (p,f)=>new
                    {
                        p,f
                    }).Where(w=>w.f.uid == uid).Select(s=>new
                    {   
                        id = s.f.id,
                        uid = s.p.uid,
                        name = s.p.name,
                        price = s.p.price,
                        description = s.p.description,
                        type = s.p.type,
                        image = s.p.image,
                        location = s.p.location,          

                    });
                return Request.CreateResponse(HttpStatusCode.OK, q.ToList());
            }
            catch (Exception e)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        [HttpGet]
        public HttpResponseMessage sellProduct(string date)
        {
            try
            {   
                var q = db.Products.Where(i => i.p_Date == date).ToList();
               
                foreach (var i in q)
                {
                    var o = db.Offers.Where(w=>w.pid == i.Id).ToList();
                    Offer of = o.FirstOrDefault(f=>f.pid == i.Id);
                    double price = (double)i.price;
                    foreach (var y in o)
                    {
                        
                       if(y.price > price)
                        {
                            of = y;
                        }                       
                        
                    }

                    if(of != null)
                    {
                        var pr = db.Products.FirstOrDefault(f=>f.Id == of.pid);
                        db.Offers.Remove(of);
                       // db.Products.Remove(pr);
                        Sold s = new Sold();
                        s.bid = of.bid;
                        s.sid = of.sid;
                        s.pid = of.pid;
                        db.Solds.Add(s);
                        db.SaveChanges();
                    }
                }
                return Request.CreateResponse(HttpStatusCode.OK, "OK");
            }
            catch (Exception e)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        [HttpGet]
        public HttpResponseMessage getCategory()
        {
            try
            {
                var q = db.Categories.Select(i => i).ToList();
                return Request.CreateResponse(HttpStatusCode.OK, q);
            }
            catch (Exception e)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }
        }

        [HttpGet]
        public HttpResponseMessage getProducts(int uid,int cid)
        {
            try
            {

                var q = db.Products.Where(i=>i.uid != uid && i.cid == cid && i.cid != null).ToList();
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
