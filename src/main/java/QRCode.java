import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static com.google.zxing.BarcodeFormat.QR_CODE;


public class QRCode {

    private String secret; //Secret key [REQUIRED]
    private String label; // Label [REQUIRED]
    private String issuer; //Name of issuer [OPTIONAL]
    private String algorihtm; //SHA1, SHA256, SHA512  [OPTIONAL]
    private int digits; //6 or 8 [OPTIONAL]
    private int period; //Seconds for a valid token [OPTIONAL]

    private int size; //Pixels of QRCode

    public QRCode(String secret, String label) {
        this.secret = secret;
        this.label = label;
        this.issuer = getLabel();
        this.algorihtm = "SHA1";
        this.digits = 3;
        this.period = 30;
        this.size = 200;
    }

    private BitMatrix generateQR() {
        BitMatrix bitMatrix = null;
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            bitMatrix = qrCodeWriter.encode(this.toString(), QR_CODE, this.getSize(), this.getSize());
        } catch (Exception e) {
            System.err.println("Error creating qrcode");
        }
        return bitMatrix;
    }

    public void saveImage(String path) {
        try {
            BufferedImage image = MatrixToImageWriter.toBufferedImage(generateQR());
            File outputfile = new File(path + "qrcode.png");
            ImageIO.write(image, "png", outputfile);
        } catch (Exception e) {
            System.err.println("Cannot save file");
        }
    }

    public void show() {
        try {
            BufferedImage image = MatrixToImageWriter.toBufferedImage(generateQR());

            JFrame frame = new JFrame();
            frame.getContentPane().setLayout(new GridLayout(1, 1));
            frame.getContentPane().add(new JLabel(new ImageIcon(image)));
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String code = "otpauth://totp/" + this.getLabel() +
                "?secret=" + this.getSecret() +
                "&issuer=" + this.getIssuer() +
                "&algorithm=" + this.getAlgorihtm() +
                "&digits=" + this.getDigits() +
                "&period=" + this.getPeriod();
        return code;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setAlgorihtm(String algorihtm) {
        this.algorihtm = algorihtm;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSecret() {
        return secret;
    }

    public String getAlgorihtm() {
        return algorihtm;
    }

    public int getDigits() {
        return digits;
    }

    public int getPeriod() {
        return period;
    }

    public String getIssuer() {
        return issuer;
    }

    public int getSize() {
        return size;
    }

    public String getLabel() {
        return label;
    }
}
