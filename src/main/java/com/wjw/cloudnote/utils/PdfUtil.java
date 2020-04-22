/**
 * @CopyRight : 华润河南医药有限公司
 * @Datetime : 2020/4/20 0020 下午 5:35
 * @Author : 温建武
 * @Version:1.0
 */
package com.wjw.cloudnote.utils;

import com.lowagie.text.pdf.BaseFont;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author : 温建武
 * @Datetime : 2020/4/20 0020 下午 5:35
 * @Desc : 
 *
 * @Modor :  Modifytime: 
 * @modDesc : 
 */
public class PdfUtil {

    public static String getUrlContent(String uri){
        StringBuffer data=new StringBuffer();
        try {
            //建立连接
            URL url = new URL(uri);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //获取输入流
            InputStream input = httpUrlConn.getInputStream();
            //将字节输入流转换为字符输入流
            InputStreamReader read = new InputStreamReader(input, "utf-8");
            //为字符输入流添加缓冲
            BufferedReader br = new BufferedReader(read);
            // 读取返回结果
            String s=null;
            while((s=br.readLine())!=null)  {
                System.out.println(s);
                data.append(s);
            }
            // 释放资源
            br.close();
            read.close();
            input.close();
            httpUrlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data.toString();
    }

    /**
     * 把URL转换为PDF
     *
     * @param outputFile
     *            ， 示例：/data/fs/inspector/BJ20150522001.pdf
     * @param
     *            ，示例：html 页面
     * @return
     * @throws Exception
     */
    public static boolean htmlToPdf(String outputFile, String url)throws Exception {
        File outFile = new File(outputFile);
        if (!outFile.exists()) {
            outFile.getParentFile().mkdirs();
        }
        OutputStream os = new FileOutputStream(outputFile);
        //url样例 这是通过ajax传递过来的 我这个项目没有配置ssh 所以就给了一个样例
        //	url = "<?xml version='1.0' encoding='UTF-8'?><html xmlns='http://www.w3.org/1999/xhtml'><head><title>页面打印</title><style media= 'print' > @page{size:297mm 210mm;}; .page { page-break-after: always; } </style>  <style type='text/css'> .tab td {  border-bottom: 1 solid #000000;  border-left: 1 solid #000000;  border-right: 0 solid #ffffff;  border-top: 0 solid #ffffff;  }  .tab {  border-color: #000000 #000000 #000000 #000000;  border-style: solid;  border-top-width: 2px;  border-right-width: 2px;   border-bottom-width: 1px;  border-left-width: 1px;  }    .hr {  font-family: '宋体';  font-size: 9pt;  }   </style>  </head>   <body bgcolor='white' style='font-family:SimSun; height:100%;' screen_capture_injected='true' ryt11773='1'>   <table cellspacing='0' cellpadding='0' width='100%' align='center' >  <tr>  <td align='center' colspan='3' style='font-size: 24px'>  <b id ='pdf_text'>报告印章申请表</b>  </td>  </tr>   <tr>  <td align='left' >子账户：600117332664</td> <td align='left'  >户名：王树</td> <td align='center'  >起始日期：20170607</td>  </tr>   <tr> <td align='left' colspan='2' >开户行：深圳农商行 </td> <td align='center' >终止日期：20170607 </td> </tr>   <tr>   <td colspan='3'> <table class='tab' cellSpacing='0' cellPadding='0' width='100%' border='1' >  <tr align='center' height='23'>  <td width='10%' height='23' colspan='3'>子账户：000117332670</td> <td width='10%' colspan='4' >币种：000117332670</td> <td width='10%' colspan='4' >产品类型：个人人民币</td>   </tr>   <tr align='center' height='23'>  <td width='5%' height='23'>交易日期</td>  <td width='5%'>摘要</td>  <td width='12%'>借方交易金额</td>  <td width='12%'>贷方交易金额</td>   <td width='12%'>账户余额</td> <td width='12%'>对方户名</td>  <td width='12%'>对方行号</td>  <td width='7%'>凭证类型</td>  <td width='8%'>凭证号码</td>  <td width='10%'>交易机构</td>  <td width='10%'>备注</td> </tr>  <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>      </table>   </td>  </tr>  </table>   </body></html>";
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        String content=getUrlContent(url);
        System.out.println("content===:"+content);
        Document doc = builder.parse(new ByteArrayInputStream(url.getBytes("utf-8")));

        //Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ITextRenderer renderer = new ITextRenderer();

        renderer.setDocument(doc, null);

        //renderer.setDocument(url);
        String fontPath = PdfUtil.class.getClassLoader().getResource("").toString().replaceAll("file:/", "")+ "simsun.ttc";
        System.out.println(fontPath);


        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont(fontPath, BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        renderer.layout();
        renderer.createPDF(os);
        os.flush();
        os.close();


        System.out.println("文件转换成功");
        return true;
    }
    public static   void  HtmlToPdf(String inputFile,String outputFile,HttpServletResponse response) throws Exception{
        String url = new File(inputFile).toURI().toURL().toString();
        System.out.println(url);
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition", "attachment; filename=download.pdf");
        OutputStream os = response.getOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("c:\\simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 解决图片的相对路径问题
        // renderer.getSharedContext().setBaseURL("file:/D:/z/temp/");
        renderer.layout();
        renderer.createPDF(os);
        os.flush();
        os.close();
    }
    /**
     * 在线 网页转pdf
     * @param
     * @param url
     * @return
     * @throws Exception
     */
    public static boolean htmlToPdf(HttpServletResponse response, String url)throws Exception {
        OutputStream os = response.getOutputStream();
        //url样例 这是通过ajax传递过来的 我这个项目没有配置ssh 所以就给了一个样例
        //	url = "<?xml version='1.0' encoding='UTF-8'?><html xmlns='http://www.w3.org/1999/xhtml'><head><title>页面打印</title><style media= 'print' > @page{size:297mm 210mm;}; .page { page-break-after: always; } </style>  <style type='text/css'> .tab td {  border-bottom: 1 solid #000000;  border-left: 1 solid #000000;  border-right: 0 solid #ffffff;  border-top: 0 solid #ffffff;  }  .tab {  border-color: #000000 #000000 #000000 #000000;  border-style: solid;  border-top-width: 2px;  border-right-width: 2px;   border-bottom-width: 1px;  border-left-width: 1px;  }    .hr {  font-family: '宋体';  font-size: 9pt;  }   </style>  </head>   <body bgcolor='white' style='font-family:SimSun; height:100%;' screen_capture_injected='true' ryt11773='1'>   <table cellspacing='0' cellpadding='0' width='100%' align='center' >  <tr>  <td align='center' colspan='3' style='font-size: 24px'>  <b id ='pdf_text'>报告印章申请表</b>  </td>  </tr>   <tr>  <td align='left' >子账户：600117332664</td> <td align='left'  >户名：王树</td> <td align='center'  >起始日期：20170607</td>  </tr>   <tr> <td align='left' colspan='2' >开户行：深圳农商行 </td> <td align='center' >终止日期：20170607 </td> </tr>   <tr>   <td colspan='3'> <table class='tab' cellSpacing='0' cellPadding='0' width='100%' border='1' >  <tr align='center' height='23'>  <td width='10%' height='23' colspan='3'>子账户：000117332670</td> <td width='10%' colspan='4' >币种：000117332670</td> <td width='10%' colspan='4' >产品类型：个人人民币</td>   </tr>   <tr align='center' height='23'>  <td width='5%' height='23'>交易日期</td>  <td width='5%'>摘要</td>  <td width='12%'>借方交易金额</td>  <td width='12%'>贷方交易金额</td>   <td width='12%'>账户余额</td> <td width='12%'>对方户名</td>  <td width='12%'>对方行号</td>  <td width='7%'>凭证类型</td>  <td width='8%'>凭证号码</td>  <td width='10%'>交易机构</td>  <td width='10%'>备注</td> </tr>  <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>   <tr style='font-size: 13px' align='center' height='23'>  <td align='center'>20160101</td>  <td align='center' style='font-family:SimHei '>自助汇款</td>  <td  align='right' id ='pdf_text2' > 22.00</td>  <td align='right' >213123123.00</td>   <td align='right'>490000000.00</td>   <td align='center'>足足</td>  <td align='center' >中国工商银行</td>  <td align='center'>存折</td>  <td align='center'></td>  <td align='center'>00003</td> <td align='center'>拿去花</td> </tr>      </table>   </td>  </tr>  </table>   </body></html>";
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        String content=getUrlContent(url);
        System.out.println("content===:"+content);
        Document doc = builder.parse(new ByteArrayInputStream(url.getBytes("utf-8")));

        //Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ITextRenderer renderer = new ITextRenderer();

        renderer.setDocument(doc, null);

        //renderer.setDocument(url);
        String fontPath = PdfUtil.class.getClassLoader().getResource("").toString().replaceAll("file:/", "")+ "simsun.ttc";
        System.out.println(fontPath);


        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont(fontPath, BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        renderer.layout();
        renderer.createPDF(os);
        os.flush();
        os.close();


        System.out.println("文件转换成功");
        return true;
    }
    /**
     * 把URL转换为PDF
     *
     * @param outputFile
     *            ， 示例：/data/fs/inspector/BJ20150522001.pdf
     * @param url
     *            ，示例：http :xxxx
     * @return
     * @throws Exception
     */
    public static boolean htmlToPdf2(String outputFile, String url)
            throws Exception {
        File outFile = new File(outputFile);
        if (!outFile.exists()) {
            outFile.getParentFile().mkdirs();
        }
        OutputStream os = new FileOutputStream(outputFile);
        ITextRenderer renderer = new ITextRenderer();

        renderer.setDocument(url);
        String fontPath = PdfUtil.class.getClassLoader().getResource("").toString().replaceAll("file:/", "")+ "simsun.ttc";
        System.out.println(fontPath);
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont(fontPath, BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        renderer.layout();
        renderer.createPDF(os);
        os.flush();
        os.close();
        System.out.println("文件转换成功");
        return true;
    }
}
