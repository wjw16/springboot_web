package com.wjw.cloudnote.controller;

import com.it.code.utils.MyEntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenjianwu
 * @date 2019/1/9 0009 上午 11:40
 */
@Controller
@RequestMapping(value="/portal")
public class ProtalController {
    @RequestMapping("/home")
    public String home() {
        return "index";
    }
    @RequestMapping("/drag")
    public String drag(){
        return "drag";
    }
    @RequestMapping("/change")
    public  String change(){
        return  "changediv";
    }
    @RequestMapping("/account")
    public  String account(){
        return  "account";
    }
    @RequestMapping("/simple")
    public  String simple(){
        return  "simple";
    }
    @RequestMapping("/a")
    public  String a(HttpServletRequest request,HttpServletResponse response){
       String path_Class=PdfController.class.getResource("/").toString();
        String path1=request.getContextPath();
        String path2= request.getSession().getServletContext().getRealPath("/");
        String path3= request.getSession().getServletContext().getRealPath("");
         String path=System.getProperty("catalina.home");
         System.out.println("path1:"+path1);
        System.out.println("path2:"+path2);
        System.out.println("path:"+path);

        String url = request.getScheme()+"://"+ request.getServerName()+request.getRequestURI()+"?"+request.getQueryString();
        System.out.println("获取全路径（协议类型：//域名/项目名/命名空间/action名称?其他参数）url="+url);
        String url2=request.getScheme()+"://"+ request.getServerName();//+request.getRequestURI();
        System.out.println("协议名：//域名="+url2);

        String urlProject=request.getScheme()+"://"+ request.getServerName()+":"+request.getServerPort();
        if(!request.getContextPath().equals(""))
            urlProject=urlProject+"/"+request.getContextPath();
        System.out.println("urlProject:"+urlProject);
        System.out.println(request.getScheme()+"://"+ request.getContextPath());
        System.out.println("获取项目名="+request.getContextPath());
        System.out.println("获取参数="+request.getQueryString());
        System.out.println("获取全路径="+request.getRequestURL());
        request.getSession().getServletContext().getRealPath("/");//获取web项目的路径
        return  "a";
    }
    @RequestMapping("/urlparam")
    public  void urlparam(HttpServletResponse response, HttpServletRequest request){
        Map jsonMap =  MyEntityUtils.getRequestParams(request);
        String fileName="学生信息表";
        String username=jsonMap.get("username").toString();
        String password=jsonMap.get("password").toString();
        List<Map<String,Object>>stuList=new ArrayList<Map<String,Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        stuList.add(map);
        Map<String,Object> stu=new HashMap<String,Object>();
        stu.put("name","a");
        stu.put("gender","男");
        stuList.add(stu);
        String columnNames[] = {"姓名","性别"};//列名
        String keys[] = {"name","gender"};//map中的key
       /* try {
            ExcelUtil.downloadWorkBook(stuList,keys,columnNames,fileName,response);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
