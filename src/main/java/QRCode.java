import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static com.google.zxing.BarcodeFormat.QR_CODE;


public class QRCode {
    private String code;
    private BitMatrix bitMatrix;

    public QRCode(String label, String secret, String issuer) {
        this.code = "otpauth://totp/" + label + "?secret=" + secret + "&issuer=" + issuer;
        this.bitMatrix = generateQR(200, 200);
    }

    private BitMatrix generateQR(int width, int height) {
        BitMatrix bitMatrix = null;
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            bitMatrix = qrCodeWriter.encode(this.code, QR_CODE, width, height);
        } catch (Exception e) {
            System.err.println("Error creating qrcode");
        }
        return bitMatrix;
    }

    public BufferedImage show() {
        try {
            BufferedImage image = MatrixToImageWriter.toBufferedImage(this.bitMatrix);

            JFrame frame = new JFrame();
            frame.getContentPane().setLayout(new FlowLayout());
            frame.getContentPane().add(new JLabel(new ImageIcon(image)));
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
