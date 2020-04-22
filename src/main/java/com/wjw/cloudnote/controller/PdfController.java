/**
 * @CopyRight : 华润河南医药有限公司
 * @Datetime : 2020/4/20 0020 下午 3:28
 * @Author : 温建武
 * @Version:1.0
 */
package com.wjw.cloudnote.controller;

import com.wjw.cloudnote.utils.HtmlUtil;
import com.wjw.cloudnote.utils.PdfUtil;
import com.wjw.cloudnote.utils.WebPdfUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : 温建武
 * @Datetime : 2020/4/20 0020 下午 3:28
 * @Desc : 
 *
 * @Modor :  Modifytime: 
 * @modDesc : 
 */
@RestController
@RequestMapping("pdf")
public class PdfController {
    @RequestMapping(value ="/htmlToPdf")
    public  void htmlToPdf( HttpServletRequest request, HttpServletResponse response){
        try{
            String inputFile = request.getSession().getServletContext().getRealPath("/")+"a.html";
        String outputFile = "D://Test//123.pdf";
        PdfUtil.HtmlToPdf(inputFile, outputFile,response);

        /*  PdfUtil.htmlToPdf(response,"http://localhost:8080/a.html");*/
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @RequestMapping("/webToPdf")
    public  void webToPdf( HttpServletRequest request, HttpServletResponse response){
      /*  String html = PdfUtil.getUrlContent("http://localhost:8080/a.html");    //转发请求到jsp，返回解析之后的内容而不是输出到浏览器
       try{
        downloadPage("http://localhost:8080/a.html");
       }
       catch (Exception ex){
           ex.printStackTrace();
       }*/
        //新方式
        String html=HtmlUtil.getHtml("http://localhost:8080/a.html");
        try{
        byte[] pdf = WebPdfUtil.html2pdf(html);
        response.setContentType("application/pdf");
        response.setHeader("Content-Length",String.valueOf(pdf.length));
        response.setHeader("Content-disposition", "attachment; filename=download.pdf");
        response.setHeader("Connection","keep-alive");
        response.setHeader("Accept-Ranges","none");
        response.setHeader("X-Frame-Options","DENY");
        OutputStream out = response.getOutputStream();
        out.write(pdf);
        out.flush();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
    @RequestMapping(value = "/ajaxTest")
    public  Object ajaxTest( HttpServletRequest request, HttpServletResponse response){
        Map result=new HashMap();
        result.put("success",true);
        return  result;
    }
    private static void downloadPage(String path) throws HttpException, IOException {
          HttpClient httpClient = new HttpClient();
        System.out.println("123123");
        InputStream input = null;
        OutputStream output = null;
//得到post方法
        PostMethod postMethod = new PostMethod(path);
//设置post方法的参数
        NameValuePair[] postData = new NameValuePair[2];
        postData[0] = new NameValuePair("name","lietu");
        postData[1] = new NameValuePair("password","*****");
//把参数添加到请求路径上去
        postMethod.addParameters(postData);
//执行，返回状态码
        int statusCode = httpClient.executeMethod(postMethod);
        System.out.println(statusCode);
        if (statusCode == HttpStatus.SC_OK) {
            input = postMethod.getResponseBodyAsStream();
//得到文件的名字
            String fileName = path.substring(path.lastIndexOf('/')+1);
//获得文件的输出流
            System.out.println(fileName);
            output = new FileOutputStream(fileName);

//输出到文件中
            int tempByte = -1;
            while ((tempByte = input.read()) > 0) {
                output.write(tempByte);
            }
//关闭资源
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }
    }
}
