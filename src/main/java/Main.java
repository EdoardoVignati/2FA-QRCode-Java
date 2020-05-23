public class Main {
    public static void main(String[] args) {
        QRCode qrcode = new QRCode("label", "secret", "issuer");
        qrcode.show();
    }
}
