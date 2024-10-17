import java.io.*;
import java.util.*;

class Kitap implements Serializable {
    private String ad;
    private String yazar;
    private boolean oduncAlindi;

    public Kitap(String ad, String yazar) {
        this.ad = ad;
        this.yazar = yazar;
        this.oduncAlindi = false;
    }

    public String getAd() {
        return ad;
    }

    public String getYazar() {
        return yazar;
    }

    public boolean isOduncAlindi() {
        return oduncAlindi;
    }

    public void setOduncAlindi(boolean oduncAlindi) {
        this.oduncAlindi = oduncAlindi;
    }

    @Override
    public String toString() {
        return "Kitap: " + ad + ", Yazar: " + yazar + ", Durum: " + (oduncAlindi ? "Ödünç alındı" : "Mevcut");
    }
}

public class KutuphaneYonetimSistemi {

    private static final String DOSYA_ADI = "kutuphane.dat";
    private static Map<String, Kitap> kitaplar = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        dosyadanOku();
        while (true) {
            System.out.println("\n1. Kitap Ekle\n2. Kitap Sil\n3. Kitap Ödünç Al\n4. Kitapları Listele\n5. Çıkış");
            System.out.print("Seçiminiz: ");
            int secim = scanner.nextInt();
            scanner.nextLine(); // Satır sonu karakterini temizle

            switch (secim) {
                case 1:
                    kitapEkle();
                    break;
                case 2:
                    kitapSil();
                    break;
                case 3:
                    kitapOduncAl();
                    break;
                case 4:
                    kitaplariListele();
                    break;
                case 5:
                    dosyayaKaydet();
                    System.out.println("Çıkış yapılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim.");
            }
        }
    }

    private static void kitapEkle() {
        System.out.print("Kitap adını girin: ");
        String ad = scanner.nextLine();
        System.out.print("Yazar adını girin: ");
        String yazar = scanner.nextLine();

        Kitap yeniKitap = new Kitap(ad, yazar);
        kitaplar.put(ad, yeniKitap);
        System.out.println("Kitap eklendi: " + yeniKitap);
    }

    private static void kitapSil() {
        System.out.print("Silmek istediğiniz kitabın adını girin: ");
        String ad = scanner.nextLine();

        if (kitaplar.containsKey(ad)) {
            kitaplar.remove(ad);
            System.out.println(ad + " adlı kitap silindi.");
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    private static void kitapOduncAl() {
        System.out.print("Ödünç almak istediğiniz kitabın adını girin: ");
        String ad = scanner.nextLine();

        Kitap kitap = kitaplar.get(ad);
        if (kitap != null) {
            if (!kitap.isOduncAlindi()) {
                kitap.setOduncAlindi(true);
                System.out.println(ad + " adlı kitap ödünç alındı.");
            } else {
                System.out.println("Bu kitap zaten ödünç alınmış.");
            }
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    private static void kitaplariListele() {
        if (kitaplar.isEmpty()) {
            System.out.println("Kütüphanede kitap yok.");
        } else {
            System.out.println("Kütüphanedeki kitaplar:");
            for (Kitap kitap : kitaplar.values()) {
                System.out.println(kitap);
            }
        }
    }

    // Kitap bilgilerini dosyaya kaydet
    private static void dosyayaKaydet() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DOSYA_ADI))) {
            oos.writeObject(kitaplar);
            System.out.println("Kitap bilgileri dosyaya kaydedildi.");
        } catch (IOException e) {
            System.out.println("Dosya kaydetme hatası: " + e.getMessage());
        }
    }

    // Kitap bilgilerini dosyadan oku
    @SuppressWarnings("unchecked")
    private static void dosyadanOku() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DOSYA_ADI))) {
            kitaplar = (Map<String, Kitap>) ois.readObject();
            System.out.println("Kitap bilgileri dosyadan okundu.");
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı, yeni dosya oluşturulacak.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
    }
}
