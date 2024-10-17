import java.io.*;
import java.util.*;

public class YapilacaklarListesiApp {
    private static final String DOSYA_ADI = "gorevler.txt";
    private static List<String> gorevler = new ArrayList<>();

    public static void main(String[] args) {
        gorevleriDosyadanYukle();
        Scanner scanner = new Scanner(System.in);
        String komut;

        System.out.println("Yapılacaklar Listesi Uygulamasına Hoş Geldiniz!");

        while (true) {
            System.out.println("\nKomutlar: [ekle], [sil], [listele], [cikis]");
            System.out.print("Komut girin: ");
            komut = scanner.nextLine().trim().toLowerCase();

            switch (komut) {
                case "ekle":
                    gorevEkle(scanner);
                    break;
                case "sil":
                    gorevSil(scanner);
                    break;
                case "listele":
                    gorevleriListele();
                    break;
                case "cikis":
                    gorevleriDosyayaKaydet();
                    System.out.println("Uygulamadan çıkılıyor. Hoşça kalın!");
                    return;
                default:
                    System.out.println("Geçersiz komut. Tekrar deneyin.");
            }
        }
    }

    // Görev ekleme
    private static void gorevEkle(Scanner scanner) {
        System.out.print("Görev girin: ");
        String gorev = scanner.nextLine().trim();
        if (!gorev.isEmpty()) {
            gorevler.add(gorev);
            System.out.println("Görev eklendi: " + gorev);
        } else {
            System.out.println("Görev boş olamaz!");
        }
    }

    // Görev silme
    private static void gorevSil(Scanner scanner) {
        System.out.print("Silmek istediğiniz görev numarasını girin: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < gorevler.size()) {
                String silinenGorev = gorevler.remove(index);
                System.out.println("Görev silindi: " + silinenGorev);
            } else {
                System.out.println("Geçersiz görev numarası.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Lütfen geçerli bir numara girin.");
        }
    }

    // Görevleri listeleme
    private static void gorevleriListele() {
        if (gorevler.isEmpty()) {
            System.out.println("Görev bulunamadı.");
        } else {
            System.out.println("\nYapılacaklar Listesi:");
            for (int i = 0; i < gorevler.size(); i++) {
                System.out.println((i + 1) + ". " + gorevler.get(i));
            }
        }
    }

    // Görevleri dosyadan yükleme
    private static void gorevleriDosyadanYukle() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_ADI))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                gorevler.add(satir);
            }
            System.out.println("Görevler dosyadan yüklendi.");
        } catch (IOException e) {
            System.out.println("Önceki görevler bulunamadı.");
        }
    }

    // Görevleri dosyaya kaydetme
    private static void gorevleriDosyayaKaydet() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_ADI))) {
            for (String gorev : gorevler) {
                writer.write(gorev);
                writer.newLine();
            }
            System.out.println("Görevler dosyaya kaydedildi.");
        } catch (IOException e) {
            System.out.println("Görevler kaydedilirken bir hata oluştu.");
        }
    }
}
