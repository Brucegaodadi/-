package com.gao.servlet;

import com.gao.bean.User;
import com.gao.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type","text/html;charset=utf-8");
        //h获取请求的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //获取前台录入验证码
        String yzm = request.getParameter("yzm");
        //从session中获取图片中验证码
        String sessionYzm = (String) request.getSession().getAttribute("sessionYzm");
        //清空session 保证点击登录的时候验证码是最新的
        request.getSession().removeAttribute("sessionYzm");
        //创建LoginService
        LoginService ls = new LoginService();
        try {
            //校验验证码
            if (yzm == "" || yzm.trim().length() == 0){
                //把错误信息放入request
                request.setAttribute("msg","请输入验证码");
                //请求转发
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }else {
                //如果两个验证码不一致  需要给出提示
                if(!yzm.equalsIgnoreCase(sessionYzm)){
                    //把错误信息放入request域中
                    request.setAttribute("msg","请输入正确的验证码");
                    //请求转发
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                }
            }
            //调用service中的方法
            User user = ls.getUserByUsernameAndPwd(username,password);
            //根据返回的对象，判断提示信息的内容
            if (user == null) {
                //response.getWriter().println("账号密码不存在");
                //把错误信息放入request域对象中
                request.setAttribute("msg","登录失败");
                //使用请求转发跳转到login.jsp
                request.getRequestDispatcher("/login.jsp").forward(request,response);
            }else {
                response.getWriter().println(user.getUsername()+":欢迎回来");;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
