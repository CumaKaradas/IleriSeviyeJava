import java.util.Scanner;

public class SicaklikDonusturucu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sıcaklık Dönüştürücüye Hoş Geldiniz!");
        System.out.println("Lütfen sıcaklık değerini ve birimini girin (örneğin: 36.5 C, 97.7 F, 300 K):");
        String giris = scanner.nextLine().trim().toUpperCase();

        try {
            // Girilen değeri sıcaklık ve birim olarak ayırıyoruz
            double sicaklikDegeri = Double.parseDouble(giris.substring(0, giris.length() - 2).trim());
            char birim = giris.charAt(giris.length() - 1);

            // Seçilen birime göre dönüşüm yapıyoruz
            switch (birim) {
                case 'C':
                    celsiusDonustur(sicaklikDegeri);
                    break;
                case 'F':
                    fahrenheitDonustur(sicaklikDegeri);
                    break;
                case 'K':
                    kelvinDonustur(sicaklikDegeri);
                    break;
                default:
                    System.out.println("Geçersiz birim. Lütfen 'C', 'F' veya 'K' birimlerinden birini kullanın.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Geçersiz sıcaklık değeri. Lütfen doğru formatta girin (örneğin: 36.5 C).");
        }
    }

    // Celsius'tan Fahrenheit ve Kelvin'e dönüştürme
    private static void celsiusDonustur(double celsius) {
        double fahrenheit = (celsius * 9 / 5) + 32;
        double kelvin = celsius + 273.15;
        System.out.printf("Celsius: %.2f°C\nFahrenheit: %.2f°F\nKelvin: %.2fK\n", celsius, fahrenheit, kelvin);
    }

    // Fahrenheit'tan Celsius ve Kelvin'e dönüştürme
    private static void fahrenheitDonustur(double fahrenheit) {
        double celsius = (fahrenheit - 32) * 5 / 9;
        double kelvin = (fahrenheit - 32) * 5 / 9 + 273.15;
        System.out.printf("Fahrenheit: %.2f°F\nCelsius: %.2f°C\nKelvin: %.2fK\n", fahrenheit, celsius, kelvin);
    }

    // Kelvin'den Celsius ve Fahrenheit'a dönüştürme
    private static void kelvinDonustur(double kelvin) {
        double celsius = kelvin - 273.15;
        double fahrenheit = (kelvin - 273.15) * 9 / 5 + 32;
        System.out.printf("Kelvin: %.2fK\nCelsius: %.2f°C\nFahrenheit: %.2f°F\n", kelvin, celsius, fahrenheit);
    }
}
