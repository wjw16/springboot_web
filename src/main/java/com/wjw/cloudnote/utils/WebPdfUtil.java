/**
 * @CopyRight : 华润河南医药有限公司
 * @Datetime : 2020/4/21 0021 下午 5:28
 * @Author : 温建武
 * @Version:1.0
 */
package com.wjw.cloudnote.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

/**
 * @Author : 温建武
 * @Datetime : 2020/4/21 0021 下午 5:28
 * @Desc : 
 *
 * @Modor :  Modifytime: 
 * @modDesc : 
 */
public class WebPdfUtil {
    /**
     * 此forward方法执行完毕之后不会输出内容到浏览器，而是把输出到字节流，最后以字符串的形式返回
     * @param request
     * @param response
     * @param src
     * @return
     */
    public static String forward(HttpServletRequest request, HttpServletResponse response, String src) {
        try{

            /*  ↓↓↓↓↓重新构造response，修改response中的输出流对象，使其输出到字节数组↓↓↓↓↓  */
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final ServletOutputStream servletOuputStream = new ServletOutputStream() {
                @Override
                public void write(int b) throws IOException {
                    byteArrayOutputStream.write(b);
                }
                @Override
                public boolean isReady() {
                    return false;
                }
                @Override
                public void setWriteListener(WriteListener writeListener) {
                }
            };

            final PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, "UTF-8"));

            response = new HttpServletResponseWrapper(response) {
                public ServletOutputStream getOutputStream() {
                    return servletOuputStream;
                }
                public PrintWriter getWriter() {
                    return printWriter;
                }
            };
            /*  ↑↑↑↑↑↑重新构造response，修改response中的输出流对象，使其输出到字节数组↑↑↑↑↑↑ */

            //执行forward操作
            request.getRequestDispatcher(src).forward(request,response);

            //把字节流中的内容太转为字符串
            return new String(byteArrayOutputStream.toByteArray(),"utf-8");
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    /**
            * 把html转换成pdf，以字节数组的形式返回pdf文件
     * @param html
     * @return pdf字节数组
     * @throws IOException
     * @throws DocumentException
     * @throws CssResolverException
     */
    public static byte[] html2pdf(String html) throws IOException, DocumentException,CssResolverException {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document,os);
        document.open();
        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(){
            @Override
            public Font getFont(String fontname, String encoding, float size, int style) {
                return super.getFont(fontname == null ? "宋体" : fontname, encoding, size, style);
            }
        };
        fontProvider.addFontSubstitute("lowagie", "garamond");
        fontProvider.setUseUnicode(true);
        //使用我们的字体提供器，并将其设置为unicode字体样式
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
        Pipeline<?> pipeline = new CssResolverPipeline(cssResolver,new HtmlPipeline(htmlContext, new PdfWriterPipeline(document,writer)));
        XMLWorker worker = new XMLWorker(pipeline, true);
        XMLParser p = new XMLParser(worker);
        p.parse(new InputStreamReader(new ByteArrayInputStream(html.getBytes("gbk"))));
        document.close();
        return os.toByteArray();
    }
}
