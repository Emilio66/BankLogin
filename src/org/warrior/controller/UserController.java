package org.warrior.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.warrior.bean.User;
import org.warrior.bean.qcode;
import org.warrior.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
/**
 * 负责用户密码校验、验证码更新及页面跳转
 * @author zhaozy
 *
 */
@Controller("userController")

public class UserController {
	@Autowired
	private UserService userService;
	public void setUserService(UserService service){
		userService=service;		
	}
	
	public UserController(){
		System.out.println("===========user controller created ========");
	}
	
	/**
	 * 转到登陆页面
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String showLogin(HttpServletResponse response){
		System.out.println("-------------We got login request");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		return "login";
	}
	
	/**
	 * 验证用户名、密码、验证码是否正确
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView loginValidate(@RequestParam Map<String, String>request,HttpServletResponse response,
			HttpSession session){
		System.out.println("=======we got to validate");
		ModelAndView modelAndView=new ModelAndView("login");
		
		String code=request.get("vcode");
		String vcode=session.getAttribute("vcode").toString();
		if(!code.equalsIgnoreCase(vcode)){//验证码不匹配，返回提示
			modelAndView.addObject("result", "code");
			return modelAndView;
		}
		
		String name=request.get("username");
		String password=request.get("password");
		String ip=request.get("ipAddr");	
		
		System.out.println("name: "+name+" password:"+password+" ip:"+ip+" code:"+code);
		User user= userService.login(name, password, ip);
		
		if(user!=null){
			System.out.println("===== validate pass -===");
			response.setContentType("json");
			response.setCharacterEncoding("utf-8");
			modelAndView.addObject("result", "pass");
			session.setAttribute("user", user);			
		}else{
			modelAndView.addObject("result", "name");
		}		
		return modelAndView;
	}	
	
	/**
	 *  创造验证码
	 * @param response
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value="/code",method=RequestMethod.GET)
	public void createCode(HttpServletResponse response,HttpSession session) throws IOException{
		response.setContentType("image/jpeg");//设置输出格式
		String codeString=new qcode(response).createRandImage();
		response.flushBuffer();
		session.setAttribute("vcode", codeString);//设置code 到session中便于验证
		System.out.println(".....vcode="+codeString);
	}
	
	@RequestMapping(value="/isExist",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView isExist(@RequestParam("name") String name){
		ModelAndView modelAndView=new ModelAndView();
		//调用 Service 判断用户名是否已经存在
		
		if(userService.isExist(name)){
			modelAndView.addObject("exist", true);
			System.out.println("===用户名"+name+"存在");
		}else{
			modelAndView.addObject("exist", false);
			System.out.println("===用户名"+name+"不存在");
		}		
		return modelAndView;
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView register(@RequestParam("name") String name,@RequestParam("password") String password){
		ModelAndView modelAndView=new ModelAndView("login");
		System.out.println("根据用户名 "+name+" 和密码"+password);
		userService.insertUser(name, password);
		return modelAndView;
	}
	
}
