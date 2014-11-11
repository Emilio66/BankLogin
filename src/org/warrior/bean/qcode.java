package org.warrior.bean;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
/**
 * verification code generation
 * @author Administrator
 *
 */
public class qcode {
     private static final String CODE_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
     private HttpServletResponse response = null;
     private static final int HEIGHT = 20;
     private static final int FONT_NUM = 4;
     private int width = 0;
     private int iNum = 0;
     private String codeList = "";
     private boolean drawBgFlag = false;
    
     private int rBg = 0;
     private int gBg = 0;
     private int bBg = 0;
     public qcode(HttpServletResponse response) {//generate a code with 4 item.
         this.response = response;
         this.width = 13 * FONT_NUM + 12;
         this.iNum = FONT_NUM;
         this.codeList = CODE_LIST;
     }
    
     public qcode(HttpServletResponse response,int iNum,String codeList) {
         this.response = response;
         this.width = 13 * iNum + 12;
         this.iNum = iNum;
         this.codeList = codeList;        
     }
    
     public String createRandImage(){//create a image
         BufferedImage image = new BufferedImage(width, HEIGHT,
                 BufferedImage.TYPE_INT_RGB);
        
         Graphics g = image.getGraphics();
        
         Random random = new Random();
        
         if ( drawBgFlag ){
             g.setColor(new Color(rBg,gBg,bBg));
             g.fillRect(0, 0, width, HEIGHT);
         }else{
             g.setColor(getRandColor(200, 250));
             g.fillRect(0, 0, width, HEIGHT);
            
             for (int i = 0; i < 155; i++) {//draw some random line to confuse the attacker
                 g.setColor(getRandColor(140, 200));
                 int x = random.nextInt(width);
                 int y = random.nextInt(HEIGHT);
                 int xl = random.nextInt(12);
                 int yl = random.nextInt(12);
                 g.drawLine(x, y, x + xl, y + yl);
             }
         }
        
         g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        
         String sRand=new String();
         for (int i=0;i<iNum;i++){
             int rand=random.nextInt(codeList.length());
             String strRand=codeList.substring(rand,rand+1);
             sRand+=strRand;
             g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
             g.drawString(strRand,13*i+6,16); //draw these numbers or letters
         }
         g.dispose();
         try{
        	 OutputStream out = response.getOutputStream();
             ImageIO.write(image, "JPEG", out);
         }catch(IOException e){
         }
         
         return sRand;// return true string of this code
     }
    
     public void setBgColor(int r,int g,int b){
         drawBgFlag = true;
         this.rBg = r;
         this.gBg = g;
         this.bBg = b;
     }
     private Color getRandColor(int fc, int bc) {
         Random random = new Random();
         if (fc > 255)
             fc = 255;
         if (bc > 255)
             bc = 255;
         int r = fc + random.nextInt(bc - fc);
         int g = fc + random.nextInt(bc - fc);
         int b = fc + random.nextInt(bc - fc);
         return new Color(r, g, b);
     }
}