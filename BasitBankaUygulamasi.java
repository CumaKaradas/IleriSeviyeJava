import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Kullanici {
    private String ad;
    private String hesapNumarasi;
    private String sifre;
    private double bakiye;

    public Kullanici(String ad, String hesapNumarasi, String sifre) {
        this.ad = ad;
        this.hesapNumarasi = hesapNumarasi;
        this.sifre = sifre;
        this.bakiye = 0.0;
    }

    public String getAd() {
        return ad;
    }

    public String getHesapNumarasi() {
        return hesapNumarasi;
    }

    public String getSifre() {
        return sifre;
    }

    public double getBakiye() {
        return bakiye;
    }

    public void paraYatir(double miktar) {
        if (miktar > 0) {
            bakiye += miktar;
            System.out.println(miktar + " TL yatırıldı. Güncel bakiye: " + bakiye + " TL");
        } else {
            System.out.println("Geçersiz miktar.");
        }
    }

    public void paraCek(double miktar) {
        if (miktar > 0 && miktar <= bakiye) {
            bakiye -= miktar;
            System.out.println(miktar + " TL çekildi. Güncel bakiye: " + bakiye + " TL");
        } else {
            System.out.println("Geçersiz miktar veya yetersiz bakiye.");
        }
    }

    public void bakiyeGoruntule() {
        System.out.println("Hesabınızdaki bakiye: " + bakiye + " TL");
    }
}

public class BasitBankaUygulamasi {
    private static Map<String, Kullanici> kullanicilar = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static Kullanici aktifKullanici;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Hesap Oluştur\n2. Giriş Yap\n3. Çıkış");
            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();
            scanner.nextLine(); // Yeni satır karakterini temizle

            switch (secim) {
                case 1:
                    hesapOlustur();
                    break;
                case 2:
                    girisYap();
                    break;
                case 3:
                    System.out.println("Çıkış yapılıyor. Güle güle!");
                    return;
                default:
                    System.out.println("Geçersiz seçim. Lütfen tekrar deneyin.");
            }
        }
    }

    private static void hesapOlustur() {
        System.out.print("Adınızı girin: ");
        String ad = scanner.nextLine();
        System.out.print("Hesap numaranızı girin: ");
        String hesapNumarasi = scanner.nextLine();
        System.out.print("Şifrenizi belirleyin: ");
        String sifre = scanner.nextLine();

        if (!kullanicilar.containsKey(hesapNumarasi)) {
            Kullanici yeniKullanici = new Kullanici(ad, hesapNumarasi, sifre);
            kullanicilar.put(hesapNumarasi, yeniKullanici);
            System.out.println("Hesap oluşturuldu.");
        } else {
            System.out.println("Bu hesap numarası zaten kullanılıyor.");
        }
    }

    private static void girisYap() {
        System.out.print("Hesap numaranızı girin: ");
        String hesapNumarasi = scanner.nextLine();
        System.out.print("Şifrenizi girin: ");
        String sifre = scanner.nextLine();

        Kullanici kullanici = kullanicilar.get(hesapNumarasi);

        if (kullanici != null && kullanici.getSifre().equals(sifre)) {
            aktifKullanici = kullanici;
            System.out.println("Giriş başarılı. Hoş geldiniz, " + kullanici.getAd());
            kullaniciIslemleri();
        } else {
            System.out.println("Geçersiz hesap numarası veya şifre.");
        }
    }

    private static void kullaniciIslemleri() {
        while (true) {
            System.out.println("\n1. Bakiye Görüntüle\n2. Para Yatır\n3. Para Çek\n4. Para Transferi\n5. Çıkış Yap");
            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();
            scanner.nextLine(); // Yeni satır karakterini temizle

            switch (secim) {
                case 1:
                    aktifKullanici.bakiyeGoruntule();
                    break;
                case 2:
                    paraYatir();
                    break;
                case 3:
                    paraCek();
                    break;
                case 4:
                    paraTransferi();
                    break;
                case 5:
                    System.out.println("Çıkış yapılıyor...");
                    aktifKullanici = null;
                    return;
                default:
                    System.out.println("Geçersiz seçim.");
            }
        }
    }

    private static void paraYatir() {
        System.out.print("Yatırmak istediğiniz miktarı girin: ");
        double miktar = scanner.nextDouble();
        aktifKullanici.paraYatir(miktar);
    }

    private static void paraCek() {
        System.out.print("Çekmek istediğiniz miktarı girin: ");
        double miktar = scanner.nextDouble();
        aktifKullanici.paraCek(miktar);
    }

    private static void paraTransferi() {
        System.out.print("Transfer yapmak istediğiniz hesap numarasını girin: ");
        String hedefHesap = scanner.nextLine();
        System.out.print("Transfer edilecek miktarı girin: ");
        double miktar = scanner.nextDouble();

        Kullanici hedefKullanici = kullanicilar.get(hedefHesap);
        if (hedefKullanici != null && miktar > 0 && miktar <= aktifKullanici.getBakiye()) {
            aktifKullanici.paraCek(miktar);
            hedefKullanici.paraYatir(miktar);
            System.out
                    .println("Transfer başarılı. " + miktar + " TL " + hedefKullanici.getAd() + " hesabına aktarıldı.");
        } else {
            System.out.println("Geçersiz hesap numarası veya yetersiz bakiye.");
        }
    }
}
