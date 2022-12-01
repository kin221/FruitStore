package edu.ncucst.fruitweb.servlet;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import sun.awt.image.JPEGImageDecoder;
import sun.awt.image.codec.JPEGImageEncoderImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;

@WebServlet(name = "CheckServlet", urlPatterns = "/checkServlet")
public class CheckServlet extends HttpServlet {
    private static final int WIDTH=60;
    private static final int HEIGHT=20;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session =request.getSession();
        response.setContentType("image/jpg");
        response.setHeader("pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);
        BufferedImage image=new BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g=image.getGraphics();
        char[] rands=initCheckCode();
        drawBackgroud(g);
        drawRands(rands,g);
        g.dispose();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
        encoder.encode(image);
        session.setAttribute("savecode",new String(rands));
    }
    private char[] initCheckCode(){
        String chars="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] rands=new char[4];
        for (int i=0;i<rands.length;i++){
            int rand=(int)(Math.random()*chars.length());
            rands[i]=chars.charAt(rand);
        }
        return rands;
    }

    private void drawBackgroud(Graphics g){
        g.setColor(new Color(0xdcdcdc));
        g.fillRect(0,0,WIDTH,HEIGHT);
        //画120个随机干扰点
        for (int i=0;i<120;i++){
            int x=(int)(Math.random()*WIDTH);
            int y=(int)(Math.random()*HEIGHT);
            int width=(int)(Math.random()*2);
            int height=(int)(Math.random()*2);
            int red=(int)(Math.random()*255);
            int green=(int)(Math.random()*255);
            int blue=(int)(Math.random()*255);
            g.setColor(new Color(red,green,blue));
            g.drawOval(x,y,width,height);

        }
    }

    private void drawRands(char[] rands,Graphics g){
        g.setColor(new Color(0x1e1e1e));
        g.setFont(new Font(null,Font.ITALIC+Font.BOLD,18));
        g.drawString(""+rands[0],1,17);
        g.drawString(""+rands[1],16,15);
        g.drawString(""+rands[2],31,18);
        g.drawString(""+rands[3],46,16) ;
        System.out.println(rands);
    }
}
