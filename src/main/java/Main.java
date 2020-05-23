public class Main {
    public static void main(String[] args) {

        QRCode qrcode = new QRCode("label", "secret");
        qrcode.setAlgorihtm("SHA256");
        qrcode.setDigits(8);
        qrcode.setIssuer("Issuer");
        qrcode.setSize(300);
        qrcode.setPeriod(60);

        qrcode.show();

        qrcode.saveImage("./");
    }
}
