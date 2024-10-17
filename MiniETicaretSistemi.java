import java.util.*;

class Urun {
    private String isim;
    private double fiyat;

    public Urun(String isim, double fiyat) {
        this.isim = isim;
        this.fiyat = fiyat;
    }

    public String getIsim() {
        return isim;
    }

    public double getFiyat() {
        return fiyat;
    }

    @Override
    public String toString() {
        return isim + " - " + fiyat + " TL";
    }
}

public class MiniETicaretSistemi {
    private static List<Urun> urunler = new ArrayList<>();
    private static List<Urun> sepet = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String komut;

        System.out.println("Mini E-Ticaret Sistemine Hoş Geldiniz!");

        while (true) {
            System.out.println(
                    "\nKomutlar: [urun_ekle], [urun_listele], [sepet_ekle], [sepet_sil], [sepet_goruntule], [cikis]");
            System.out.print("Komut girin: ");
            komut = scanner.nextLine().trim().toLowerCase();

            switch (komut) {
                case "urun_ekle":
                    urunEkle(scanner);
                    break;
                case "urun_listele":
                    urunleriListele();
                    break;
                case "sepet_ekle":
                    sepeteUrunEkle(scanner);
                    break;
                case "sepet_sil":
                    sepettenUrunSil(scanner);
                    break;
                case "sepet_goruntule":
                    sepetiGoruntule();
                    break;
                case "cikis":
                    System.out.println("Sistemden çıkılıyor. Hoşça kalın!");
                    return;
                default:
                    System.out.println("Geçersiz komut. Lütfen tekrar deneyin.");
            }
        }
    }

    // Ürün ekleme
    private static void urunEkle(Scanner scanner) {
        System.out.print("Ürün ismi girin: ");
        String isim = scanner.nextLine().trim();
        System.out.print("Ürün fiyatı girin: ");
        double fiyat;
        try {
            fiyat = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz fiyat, lütfen sayısal bir değer girin.");
            return;
        }
        Urun urun = new Urun(isim, fiyat);
        urunler.add(urun);
        System.out.println("Ürün eklendi: " + urun);
    }

    // Ürünleri listeleme
    private static void urunleriListele() {
        if (urunler.isEmpty()) {
            System.out.println("Sistemde ürün bulunmuyor.");
        } else {
            System.out.println("Sistemdeki Ürünler:");
            for (int i = 0; i < urunler.size(); i++) {
                System.out.println((i + 1) + ". " + urunler.get(i));
            }
        }
    }

    // Sepete ürün ekleme
    private static void sepeteUrunEkle(Scanner scanner) {
        urunleriListele();
        if (urunler.isEmpty()) {
            return;
        }
        System.out.print("Sepete eklemek istediğiniz ürün numarasını girin: ");
        int urunNo;
        try {
            urunNo = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (urunNo >= 0 && urunNo < urunler.size()) {
                Urun urun = urunler.get(urunNo);
                sepet.add(urun);
                System.out.println("Ürün sepete eklendi: " + urun);
            } else {
                System.out.println("Geçersiz ürün numarası.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lütfen geçerli bir numara girin.");
        }
    }

    // Sepetten ürün silme
    private static void sepettenUrunSil(Scanner scanner) {
        sepetiGoruntule();
        if (sepet.isEmpty()) {
            return;
        }
        System.out.print("Sepetten silmek istediğiniz ürün numarasını girin: ");
        int urunNo;
        try {
            urunNo = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (urunNo >= 0 && urunNo < sepet.size()) {
                Urun urun = sepet.remove(urunNo);
                System.out.println("Ürün sepetten çıkarıldı: " + urun);
            } else {
                System.out.println("Geçersiz ürün numarası.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lütfen geçerli bir numara girin.");
        }
    }

    // Sepeti görüntüleme ve toplam fiyatı hesaplama
    private static void sepetiGoruntule() {
        if (sepet.isEmpty()) {
            System.out.println("Sepetinizde ürün bulunmuyor.");
        } else {
            double toplamTutar = 0;
            System.out.println("Sepetinizdeki Ürünler:");
            for (int i = 0; i < sepet.size(); i++) {
                Urun urun = sepet.get(i);
                System.out.println((i + 1) + ". " + urun);
                toplamTutar += urun.getFiyat();
            }
            System.out.println("Toplam Tutar: " + toplamTutar + " TL");
        }
    }
}
