import java.io.*;
import java.util.*;

class Kisi {
    private String isim;
    private String telefonNumarasi;

    public Kisi(String isim, String telefonNumarasi) {
        this.isim = isim;
        this.telefonNumarasi = telefonNumarasi;
    }

    public String getIsim() {
        return isim;
    }

    public String getTelefonNumarasi() {
        return telefonNumarasi;
    }

    @Override
    public String toString() {
        return "İsim: " + isim + ", Telefon: " + telefonNumarasi;
    }
}

public class TelefonRehberi {
    private static final String DOSYA_ADI = "rehber.txt";
    private static List<Kisi> rehber = new ArrayList<>();

    public static void main(String[] args) {
        rehberiDosyadanYukle();
        Scanner scanner = new Scanner(System.in);
        String komut;

        System.out.println("Telefon Rehberine Hoş Geldiniz!");

        while (true) {
            System.out.println("\nKomutlar: [ekle], [sil], [listele], [cikis]");
            System.out.print("Komut girin: ");
            komut = scanner.nextLine().trim().toLowerCase();

            switch (komut) {
                case "ekle":
                    kisiEkle(scanner);
                    break;
                case "sil":
                    kisiSil(scanner);
                    break;
                case "listele":
                    rehberiListele();
                    break;
                case "cikis":
                    rehberiDosyayaKaydet();
                    System.out.println("Uygulamadan çıkılıyor. Hoşça kalın!");
                    return;
                default:
                    System.out.println("Geçersiz komut. Lütfen tekrar deneyin.");
            }
        }
    }

    // Kişi ekleme
    private static void kisiEkle(Scanner scanner) {
        System.out.print("İsim girin: ");
        String isim = scanner.nextLine().trim();
        System.out.print("Telefon numarası girin: ");
        String telefonNumarasi = scanner.nextLine().trim();

        if (!isim.isEmpty() && !telefonNumarasi.isEmpty()) {
            Kisi kisi = new Kisi(isim, telefonNumarasi);
            rehber.add(kisi);
            System.out.println("Kişi eklendi: " + kisi);
        } else {
            System.out.println("İsim veya telefon numarası boş olamaz.");
        }
    }

    // Kişi silme
    private static void kisiSil(Scanner scanner) {
        rehberiListele();
        if (rehber.isEmpty()) {
            return;
        }
        System.out.print("Silmek istediğiniz kişinin numarasını girin: ");
        int kisiNo;
        try {
            kisiNo = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (kisiNo >= 0 && kisiNo < rehber.size()) {
                Kisi silinenKisi = rehber.remove(kisiNo);
                System.out.println("Kişi silindi: " + silinenKisi);
            } else {
                System.out.println("Geçersiz kişi numarası.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lütfen geçerli bir numara girin.");
        }
    }

    // Rehberi listeleme
    private static void rehberiListele() {
        if (rehber.isEmpty()) {
            System.out.println("Rehberde kişi bulunmuyor.");
        } else {
            System.out.println("Rehberdeki Kişiler:");
            for (int i = 0; i < rehber.size(); i++) {
                System.out.println((i + 1) + ". " + rehber.get(i));
            }
        }
    }

    // Rehberi dosyadan yükleme
    private static void rehberiDosyadanYukle() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_ADI))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                String[] veri = satir.split(",");
                if (veri.length == 2) {
                    Kisi kisi = new Kisi(veri[0], veri[1]);
                    rehber.add(kisi);
                }
            }
            System.out.println("Rehber dosyadan yüklendi.");
        } catch (IOException e) {
            System.out.println("Önceki rehber bulunamadı.");
        }
    }

    // Rehberi dosyaya kaydetme
    private static void rehberiDosyayaKaydet() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_ADI))) {
            for (Kisi kisi : rehber) {
                writer.write(kisi.getIsim() + "," + kisi.getTelefonNumarasi());
                writer.newLine();
            }
            System.out.println("Rehber dosyaya kaydedildi.");
        } catch (IOException e) {
            System.out.println("Rehber kaydedilirken bir hata oluştu.");
        }
    }
}
