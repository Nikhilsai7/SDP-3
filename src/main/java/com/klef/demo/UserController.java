package com.klef.demo;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
        @Autowired
        UserRepo user;
        @Autowired
        ManagerRepo mg;
        @Autowired 
        ProductRepo pr;
        @Autowired 
        CartRepo cr;
        
    @RequestMapping(value="/")
    public String display()
    {
      return "index";
    }
    @RequestMapping(value="/register")
    public String register()
    {
      return "register";
    }
    @RequestMapping(value="/login")
    public String login()
    {
      return "login";
    }
    
    @RequestMapping(value="/adduser")
    public String adduser(User u)
    {
      System.out.println(u.username);
      user.save(u);
      return "login";
    }
    public User cu;
    public void setUser(User cu)
    {
    	this.cu=cu;
    }
    public User getUser()
    {
    	return this.cu;
    }
    
    @RequestMapping(value="/logincheck")
    public ModelAndView logincheck(User u,HttpServletRequest request)
    {
      HttpSession s=request.getSession();
      
      ModelAndView mv=new ModelAndView("managerindex");
     
      ModelAndView mv2=new ModelAndView("home");
      ModelAndView mv3=new ModelAndView("login");
      Manager m=new Manager();
      m.username=u.username;
      m.password=u.password;
      Manager m2=mg.findByUsername(m.username);
      
        if(m2!=null && m2.password.equals(m.password))
        {
            s.setAttribute("admin", m2);  
          mv.addObject("admin",m2);
          return mv;
        }
      
      User u2=user.findByUsername(u.username);
      if(u2!=null && u2.password.equals(u.password))
      {
        s.setAttribute("user", u2);
        mv2.addObject("user", u2);
        return mv2;
      }
      mv3.addObject("message", "Invalid Login");
      return mv3;
    }
    
    @RequestMapping(value="/profile")
    public ModelAndView profile(HttpServletRequest request)
    {
    	HttpSession s=request.getSession();
    	User u2=(User) s.getAttribute("user");
    	ModelAndView mv=new ModelAndView("my-account");
    	mv.addObject("user",getUser());
      return mv;
    }
    @RequestMapping(value="/addproduct")
    public ModelAndView addproduct(Product p,@RequestParam("image") MultipartFile multipartFile, HttpServletRequest request)  throws IOException
    {
    	HttpSession s=request.getSession();
    	User u2=(User) s.getAttribute("user");
      ModelAndView mv=new ModelAndView("managerindex");
      System.out.println(p.pname);
       String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            p.setPhotos(fileName);
             
            Product savedUser = pr.save(p);
     
            String uploadDir = "user-photos/" + savedUser.getPid();
     
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            mv.addObject("product",p);
      return mv;
    }
    
    @RequestMapping(value="/shop")
    public ModelAndView shop(HttpServletRequest request)
    {
    	HttpSession s=request.getSession();
    	User u2=(User) s.getAttribute("user");
    	ModelAndView mv=new ModelAndView("shop");
    	List<Product> ps=pr.findAll();
    	mv.addObject("ps", ps);
 
      return mv;
    }
    
    @RequestMapping(value="/cart")
    public ModelAndView cart(HttpServletRequest request)
    {
    	HttpSession s=request.getSession();
    	User u2=(User) s.getAttribute("user");
    	ModelAndView mv=new ModelAndView("cart");
    	List<Cart> ps=cr.findAll();
    	mv.addObject("cart", ps);
 
      return mv;
    }
    @RequestMapping(value="/addtocart/{id}")
    public String addtocart(@PathVariable("id") int id,HttpServletRequest request)
    {
      HttpSession s=request.getSession();
      User u2=(User)s.getAttribute("user");
      Product p=pr.findByPid(id);
      String referer = request.getHeader("Referer");
      Cart ca=cr.findByUserAndProduct(u2, p);
      if(ca==null)
      {
        Cart c=new Cart();
        c.product=p;
        c.quantity=1;
        c.user=u2;
      cr.save(c);
      }
      else {
        ca.quantity+=1;
        cr.save(ca);
      }
      
      return "redirect:"+ referer;
}
    
}
