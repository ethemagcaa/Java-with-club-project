import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class UyeManager extends Veritabani {
    static Scanner scan = new Scanner(System.in);

    public static void uyeMenu() throws InterruptedException {
        String tercih = "";

        { //TODO Kullanıcı Çıkış Yapmadığı Sürece, Menüyü Görmeye Devam Etsin...

            System.out.println(
                    "\n========== TECHNO STUDY CONFLUENCE =========\n" +
                            "================= UYE MENU =================\n" +
                            "\n" +
                            "\t   1- Uye Listesi Yazdir\t\n" +
                            "\t   2- Soyisimden Uye Bulma\n" +
                            "\t   3- Sehire Gore Uye Bulma\n" +
                            "\t   4- Bilgilerini Girerek Uye Ekleme\n" +
                            "\t   5- Kimlik No Ile Kayit Silme \t\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS\n");

            //TODO Kullanıcıdan alacağınız tercihe göre ilgili menü metodlarına yönlendirmeler yapın...
            {

                // Uye Listesi Yazdir
                uyeListesiYazdir();
                // Soyisimden Uye Bulma
                soyisimdenUyeBulma();
                // Sehre Gore Uye Bulma
                sehreGoreUyeBulma();
                // Bilgilerini Girerek Uye Ekleme
                uyeEkleme();
                // Kimlik No Ile Kayit Silme
                tcNoIleUyeSil();
                Helper.anaMenu();
                System.out.println("Lutfen gecerli tercih yapiniz: ");
            }

        }
        Helper.projeDurdur();
    }

    public static void tcNoIleUyeSil() throws InterruptedException {

        //TODO Kullanıcıdan alacağınız kimlik no ile ilgili üyeyi kayıtlardan siliniz...
        System.out.println("Silinecek uyeye ait kimlik no giriniz: ");
        String kimlikNo = scan.nextLine();
        String silinecekUye = uyelerMap.remove(kimlikNo);

        //TODO Gerekli atamaları yapınız. Aşağıdaki try-catch bloğu yardımcı olabilir...
        if (silinecekUye != null) {
            System.out.print("Üye siliniyor...");
            for (int i = 0; i < 20; i++) {
                Thread.sleep(100);
                System.out.print(">");
            }
            System.out.println("Üye başarıyla silindi.");
        } else {
            System.out.println("Girilen kimlik numarasına sahip üye bulunamadı.");
        }
//        System.out.print("silinecekUye" + " Siliniyor...");
//        for (int i = 0; i < 20; i++) {
//          Thread.sleep(100);
//          System.out.print(">");
//      }

        //try {
        //    boolean sonuc = sonucValue.equals(silinecekValue);
        //} catch (Exception e) {
        //    System.out.println("Istediginiz Tc numarasi ile uye bulunamadi.");
        //}
    }

    public static void uyeEkleme() {

        //TODO Kullanıcıdan Tc no , Isim, Soyisim, Sehir, Dogum Yili alınız...
        //TODO Aldığınız değeri UyelerMap mapine uygun şekilde ekleyiniz...
        System.out.print("Tc no: ");
        String tcNo = scan.nextLine();
        System.out.print("Isim: ");
        String isim = scan.nextLine();
        System.out.print("Soyisim: ");
        String soyisim = scan.nextLine();
        System.out.print("Sehir: ");
        String sehir = scan.nextLine();
        System.out.print("Dogum Yili: ");
        String dogumYili = scan.nextLine();

        uyelerMap.put(tcNo, isim + ", " + soyisim + ", " + sehir + ", " + dogumYili);
        System.out.println("Üye başarıyla eklendi.");

    }

    public static void sehreGoreUyeBulma() throws InterruptedException {

        //TODO Kullanıcıdan aldığınız girdiyle, UyelerMap'inde şehir araması yapın;
        //TODO Girilen şehire sahip tüm üyeleri map veya liste olarak döndürünüz...
        System.out.println("Aradiginiz Uyenin Sehrini Giriniz:");
        String arananSehir = scan.nextLine();

        System.out.println(
                "\n============= TECHNO STUDY CONFLUENCE =============\n" +
                        "=============== SEHIR ILE UYE ARAMA ===============\n" +
                        "TcNo : Isim , Soyisim , Sehir, D.Yili");
        // Daha düzgün bi görünüm için printf veya String.format kullanılabilir... Zorunlu değil...
        boolean bulunanUyeVarMi = false;
        for (Map.Entry<String, String> entry : uyelerMap.entrySet()) {
            String uyeTcNo = entry.getKey();
            String uyeBilgileri = entry.getValue();

            if (uyeBilgileri.contains(arananSehir)) {
                System.out.println(uyeTcNo + " : " + uyeBilgileri);
                bulunanUyeVarMi = true;
            }
        }

        if (!bulunanUyeVarMi) {
            System.out.println("Aradığınız şehre ait üye bulunamadı.");
        }

    }

    public static void soyisimdenUyeBulma() throws InterruptedException {
        //TODO Kullanıcıdan aldığınız girdiyle, UyelerMap'inde soyisim araması yapın;
        //TODO Girilen soyismine sahip tüm üyeleri map veya liste olarak döndürünüz...
        System.out.println("Aradiginiz uyenin soyisminin tamamini ya da birkismini giriniz: ");
        String arananSoyisim = scan.nextLine().toUpperCase();

        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        System.out.println(
                "\n========== TECHNO STUDY BOOTCAMP ===========\n" +
                        "=========== SOYISIM ILE UYE ARAMA ==========\n" +
                        "TcNo : Isim , Soyisim , Sehir , D.Yili");
        // printf veya String.format metodları kullanılarak daha düzgün bi çıktı elde edilebilir. Şart değil, isteğe bağlı.

        //TODO Syisminin bir kısmı girilse bile bulunan üyeler listelensin...
        boolean bulunanUyeVarMi = false;
        for (Map.Entry<String, String> entry : uyelerMap.entrySet()) {
            String uyeTcNo = entry.getKey();
            String uyeBilgileri = entry.getValue();

            if (uyeBilgileri.toUpperCase().contains(arananSoyisim)) {
                System.out.println(uyeTcNo + " : " + uyeBilgileri);
                bulunanUyeVarMi = true;
            }
        }

        if (!bulunanUyeVarMi) {
            System.out.println("Aradığınız soyisime ait üye bulunamadı.");
        }
    }

    public static void uyeListesiYazdir() throws InterruptedException {
        //İPUCU METODU: Bu metodu değiştirmenize gerek yok...

        Set<Map.Entry<String, String>> uyelerEntrySet = uyelerMap.entrySet();

        System.out.print("Uye Listesi yazdiriliyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        System.out.println(
                "\n========== TECHNO STUDY CONFLUENCE =========\n" +
                        "=============== UYE LISTESI ================\n" +
                        "TcNo : Isim , Soyisim , Sehir , D.Yili");

        // Daha düzgün bi görünüm için printf veya String.format kullanılabilir...
        for (Map.Entry<String, String> each : uyelerEntrySet) {
            String eachKey = each.getKey();
            String eachValue = each.getValue();

            System.out.println(eachKey + " : " + eachValue + " | ");
        }
    }
}
