package com.yy.qrCode;

import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by luyuanyuan on 2017/9/19.
 */
public class QrCodeUtil {

    public static BufferedImage getQrcode(String content){
        Qrcode qrcode = new Qrcode();
        //设置二维码的配置信息 版本号，纠错率 模式
        qrcode.setQrcodeEncodeMode('B');
        //设置版本
        qrcode.setQrcodeVersion(15);
        //设置纠错率 l 7% m 15% q 25% h 30%
        qrcode.setQrcodeErrorCorrect('M');
        String str = content;
        //根据字节数组生成二维码的boolean二维数组
        //二维码实际就是0 1组成 0 false 1 true
        boolean[][] results = qrcode.calQrcode(str.getBytes());
        //遇到0 不画或者画背景色方块 遇到1 画一个黒块
        //在内存中创建一个图片对象
        BufferedImage image = new BufferedImage(235, 235,BufferedImage.TYPE_INT_RGB);
        //拿到画图工具
        Graphics2D grap = image.createGraphics();
        // 设置背景色
        grap.setBackground(Color.WHITE);
        //清空整个背景区域
        grap.clearRect(0, 0, 235, 235);
        //设置画笔颜色
        grap.setColor(Color.BLACK);
        //遍历数组，二维码数组取每个位置的值 根据值的true or false决定是否画制一个小方块
        for(int i = 0 ; i < results.length ;i++){
            for(int j = 0; j <results.length ;j++){
                if(results[j][i]){//如果是真的 我们就画小方块
                    //x坐标 y坐标 第一个方块 0-2 第二个方块 3-5  第三方块6-8
                    grap.fillRect(j*3+2, i*3+2, 3, 3);
                }
            }
        }
        //Toolkit.getDefaultToolkit().getImage();
        grap.drawImage(new ImageIcon("C:\\Users\\jame\\Pictures\\18.png").getImage(), 92, 92,50,50, null);
        grap.dispose();
        return image;
    }
    public static void main(String [] args) throws IOException {
        Qrcode qrcode = new Qrcode();
        //N代表数字,A代表字符a-Z,B代表其他字符
        qrcode.setQrcodeEncodeMode('B');
        //设置设置二维码版本，取值范围1-40，值越大尺寸越大，可存储的信息越大
        qrcode.setQrcodeVersion(15);
        ////设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
        qrcode.setQrcodeErrorCorrect('M');
        String str = "我爱你，老师真棒，你最帅，Jame老师最帅！";
        //根据字节数组生成二维码的boolean二维数组
        //二维码实际就是0 1组成 0 false 1 true
        boolean[][] results = qrcode.calQrcode(str.getBytes());
        //遇到0 不画或者画背景色方块 遇到1 画一个黒块
        //在内存中创建一个图片对象
        BufferedImage image = new BufferedImage(235, 235,BufferedImage.TYPE_INT_RGB);
        //拿到画图工具
        Graphics2D grap = image.createGraphics();
        // 设置背景色
        grap.setBackground(Color.WHITE);
        //清空整个背景区域
        grap.clearRect(0, 0, 235, 235);
        //设置画笔颜色
        grap.setColor(Color.BLACK);
        //遍历数组，二维码数组取每个位置的值 根据值的true or false决定是否画制一个小方块
        for(int i = 0 ; i < results.length ;i++){
            for(int j = 0; j <results.length ;j++){
                if(results[j][i]){//如果是真的 我们就画小方块
                    //x坐标 y坐标 第一个方块 0-2 第二个方块 3-5  第三方块6-8
                    grap.fillRect(j*3+2, i*3+2, 3, 3);
                }
            }
        }
        //Toolkit.getDefaultToolkit().getImage();
        grap.drawImage(new ImageIcon("C:\\Users\\jame\\Pictures\\18.png").getImage(), 92, 92,50,50, null);
        grap.dispose();
        ImageIO.write(image, "JPEG", new File("D:\\任壮壮.jpg"));
    }

    /**
     * 生成二维码(QRCode)图片
     * @param content 二维码图片的内容
     * @param imgPath 生成二维码图片完整的路径
     * @param ccbpath  二维码图片中间的logo路径
     */
    public static int createQRCode(String content, String imgPath,String ccbPath,int version) {
        try {
            Qrcode qrcodeHandler = new Qrcode();
            //设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小
            qrcodeHandler.setQrcodeErrorCorrect('M');
            //N代表数字,A代表字符a-Z,B代表其他字符
            qrcodeHandler.setQrcodeEncodeMode('B');
            // 设置设置二维码版本，取值范围1-40，值越大尺寸越大，可存储的信息越大
            qrcodeHandler.setQrcodeVersion(version);
            // 图片尺寸
            int imgSize =67 + 12 * (version - 1) ;

            byte[] contentBytes = content.getBytes("gb2312");
            BufferedImage image = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
            Graphics2D gs = image.createGraphics();

            gs.setBackground(Color.WHITE);
            gs.clearRect(0, 0, imgSize, imgSize);

            // 设定图像颜色 > BLACK
            gs.setColor(Color.BLUE);

            // 设置偏移量 不设置可能导致解析出错
            int pixoff = 2;
            // 输出内容 > 二维码
            if (contentBytes.length > 0 && contentBytes.length < 130) {
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
                for (int i = 0; i < codeOut.length; i++) {
                    for (int j = 0; j < codeOut.length; j++) {
                        if (codeOut[j][i]) {
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                        }
                    }
                }
            } else {
                System.err.println("QRCode content bytes length = "
                        + contentBytes.length + " not in [ 0,125]. ");
                return -1;
            }
            Image logo = ImageIO.read(new File(ccbPath));//实例化一个Image对象。
            int widthLogo = logo.getWidth(null)>image.getWidth()*2/10?(image.getWidth()*2/10):logo.getWidth(null),
                    heightLogo = logo.getHeight(null)>image.getHeight()*2/10?(image.getHeight()*2/10):logo.getWidth(null);

            /**
             * logo放在中心
             */
            int x = (image.getWidth() - widthLogo) / 2;
            int y = (image.getHeight() - heightLogo) / 2;
            gs.drawImage(logo, x, y, widthLogo, heightLogo, null);
            gs.dispose();
            image.flush();

            // 生成二维码QRCode图片
            File imgFile = new File(imgPath);
            ImageIO.write(image, "png", imgFile);

        } catch (Exception e)
        {
            e.printStackTrace();
            return -100;
        }

        return 0;
    }
}
