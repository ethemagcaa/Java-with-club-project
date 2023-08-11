import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class KitapManager extends Veritabani {

    static Scanner scan = new Scanner(System.in);
    private static Map<String, String> iadeEdilenKitaplarMap = new HashMap<>();


    public static void kitapMenu() throws InterruptedException {
        String tercih = "";
        {// TODO Kullanıcı Çıkış Yapmadığı sürece menüde kalalım...
            System.out.println(
                    "\n============ TECHNO STUDY BOOTCAMP ============\n" +
                            "================== KITAP MENU =================\n" +
                            "\t   1- Kitap Listesi Yazdir\n" +
                            "\t   2- Yazardan Kitap Bulma\n" +
                            "\t   3- Kitap Turu veya Yayin Tarihi Ile Kitap Bulma\n" +
                            "\t   4- Bilgilerini Girerek Kitap Ekleme\n" +
                            "\t   5- Kitap Ismi Ile Kayit Silme \t\n" +
                            "\t   6- Kitap Odunc Al \t\n" +
                            "\t   7- Kitap Iade Et \t\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS");

            //TODO Kullanıcıdan alacağınız tercihe göre ilgili menü metodlarına yönlendirmeler yapın...

//            {
//                kitapListesiYazdir();
//                // Yazar Ismiyle Kitap Bulma
//                yazardanKitapBulma();
//                // Kitap Turu veya Yayin Tarihi Ile Kitap Bulma
//                turVeyaYayinTarihiIleKitapBulma();
//                // Bilgilerini Girerek Kitap Ekleme
//                kitapEkle();
//                isimIleKitapSilme();
//                kitapOduncAl();
//                kitapIadeEt();
//                Helper.anaMenu();
//                Helper.projeDurdur();
//                System.out.println("Lutfen gecerli bir tercih giriniz");
//            }
//        }
//        Helper.projeDurdur();
            System.out.print("Lutfen tercihinizi yapiniz: ");
            tercih = scan.nextLine();

            switch (tercih.toUpperCase()) {
                case "1":
                    kitapListesiYazdir();
                    break;
                case "2":
                    yazardanKitapBulma();
                    break;
                case "3":
                    turVeyaYayinTarihiIleKitapBulma();
                    break;
                case "4":
                    kitapEkle();
                    break;
                case "5":
                    isimIleKitapSilme();
                    break;
                case "6":
                    kitapOduncAl();
                    break;
                case "7":
                    kitapIadeEt();
                    break;
                case "A":
                    return; // Ana menüye dönmek için metoddan çıkış yapılır.
                case "Q":
                    Helper.projeDurdur();
                    break;
                default:
                    System.out.println("Gecersiz tercih! Lutfen tekrar deneyin.");
            }
        }

    }

    public static void kitapOduncAl() {
        System.out.println("Odunc almak istediginiz kitabin ismini giriniz: ");
        String kitapAdi = scan.nextLine();

        //TODO Kullanıcıdan alacağınız kitap ismiyle (Map te var olmalı)
        //TODO kitap ödünç alma metodunu tamamlayın...
        //NOT: Veritabanı'nda ödünç almayla alakalı ikinci bir map 'i tampon gibi kullanmalısınız...
        //Ödünç alındığında kitaplarMap 'ten düşüp bu map e eklenecek...
        if (kitaplarMap.containsKey(kitapAdi)) {
            // Eğer kitap map'te bulunuyorsa, kitapları geçici bir map'ten çıkarıp ana map'e geri ekleyelim.
            String kitapBilgisi = kitaplarMap.get(kitapAdi);
            kitaplarMap.remove(kitapAdi);
            iadeEdilenKitaplarMap.put(kitapAdi, kitapBilgisi);
            System.out.println(kitapAdi + " kitabı ödünç alındı.");
        } else {
            System.out.println("Böyle bir kitap bulunamadı.");
        }

    }


    public static void kitapIadeEt() {
        System.out.println("Iade etmek istediginiz kitabin ismini giriniz: ");
        String kitapAdi = scan.nextLine();
        //TODO Kullanıcıdan alacağınız kitap ismiyle (Map te var olmalı)
        //TODO kitap iade etme metodunu tamamlayın...
        //NOT: Veritabanı'nda iade etmeyle alakalı ikinci bir map 'i tampon gibi kullanmalısınız...
        //Kitap iade edildiğinde,  kitaplarMap 'e geri eklenmeli...

        if (kitaplarMap.containsKey(kitapAdi)) {
            // Eğer kitap map'te bulunuyorsa, kitapları geçici bir map'ten çıkarıp ana map'e geri ekleyelim.
            String kitapBilgisi = kitaplarMap.get(kitapAdi);
            kitaplarMap.remove(kitapAdi);
            iadeEdilenKitaplarMap.put(kitapAdi, kitapBilgisi);
            System.out.println("Kitap basariyla iade edildi.");
        } else {
            System.out.println("Boyle bir kitap bulunamadi.");
        }
    }


    private static void isimIleKitapSilme() throws InterruptedException
    {//İPUCU METODU... Bu metodu değiştirmenize gerek yok.
        System.out.println("Silinecek kitabin ismini giriniz");
        String silinecekKitap = scan.nextLine();

        String silinecekValue = kitaplarMap.get(silinecekKitap);
        String sonucValue = kitaplarMap.remove(silinecekKitap);

        System.out.print(silinecekKitap + " Siliniyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        //ARTIK UYGUN YERLERDE BEKLEMEDİĞİNİZ SONUÇLAR İÇİN TRY CATCH KULLANABİLİRSİNİZ...
        //////////////////////////////////////////////////////////////////////////////////////////////////
        try {
            boolean sonuc = sonucValue.equals(silinecekValue);
        } catch (Exception e) {
            System.out.println("Istediginiz kitap ismi bulunamadi");
        }/////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private static void kitapEkle() {
        //"A Tale of Two Cities", "Charles Dickens, Tarih, 1859" >> Kitap key,value su buna benzer şekilde...

        //TODO Kitap Adını, Yazar Adını, Kitap Türünü ve Yayınlanma Yılını Kullanıcıdan alarak,
        //TODO kitaplarMap'e ekleme yapınız...

        //TODO MÜMKÜNSE, kitap türünü, Enum olarak tanımlanan KitapTuru sınıfını kullanarak alınız...
        //KİTAP TÜRLERİ >>     TARIH, POLISIYE, KURGU, ROMAN, DESTAN, TANIMLANMAMIS_TUR
        //TODO Kullanıcı kitap türünü yanlış girdiği sürece , kullanıcıya
        //TODO "Hatali giris! Lutfen kitap turunu tekrar giriniz: " mesajı verin...
        //TODO while ve try-catch kullanılabilir... Giriş başarılı olursa try-catch bloğu kırılarak konsoldan
        //TODO yayınlama yılı alıp kitap ekleme işlemine devam edilebilir...
        //Kullanıcı tarafından girilen stringi, KitapTuru sınıfında tanımlanan türlerden birine çevirmeniz gerkecek...
        // kitapTuru değişkeni artık geçerli bir değere sahipse...
        // Diğer işlemlere devam edebilirsiniz.
        //System.out.println("Yayinlanma Tarihi: ");
        //String yayinTarihi = scan.nextLine();

        //TODO Ekleme işlemini tamamlayın...
        System.out.print("Kitap Adi: ");
        String kitapAdi = scan.nextLine();

        System.out.print("Yazar Adi: ");
        String yazarAdi = scan.nextLine();

        System.out.print("Kitap Turu (Tarih, Polisiye, Kurgu, Roman, Destan, Tanimlanmamis_Tur): ");
        String kitapTuruStr = scan.nextLine();

        // Kitap turunu kontrol edelim, uygun bir tur secilene kadar kullanicidan tekrar isteyelim.
        KitapTuru kitapTuru = null;
        while (kitapTuru == null) {
            try {
                kitapTuru = KitapTuru.valueOf(kitapTuruStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.print("Hatali giris! Lutfen kitap turunu tekrar giriniz: ");
                kitapTuruStr = scan.nextLine();
            }
        }

        System.out.print("Yayinlanma Yili: ");
        String yayinTarihi = scan.nextLine();

        String kitapBilgisi = yazarAdi + ", " + kitapTuru + ", " + yayinTarihi;
        kitaplarMap.put(kitapAdi, kitapBilgisi);

        System.out.println("Kitap basariyla eklendi.");


    }

    public static void turVeyaYayinTarihiIleKitapBulma() throws InterruptedException {

        //TODO kitaplar.Map'in Value larını almak için  Set<Map.Entry<String, String>> cinsinden myEntrySet tanımlayın...

        System.out.println("Istediginiz kitabin turunu yaziniz: ");
        System.out.println("(Tarih, Polisiye, Kurgu, Roman, Destan)");
        String arananTur = scan.nextLine();
        //TODO Metodu kullanıcıdan alacağınız girdileri kullanarak tamamlayın...
        Set<Map.Entry<String, String>> myEntrySet = kitaplarMap.entrySet();
        boolean kitapBulundu = false;

        System.out.println(
                "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
                        "================= KITAP LISTESI ===============\n" +
                        "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");
        for (Map.Entry<String, String> entry : myEntrySet) {
            String kitapIsmi = entry.getKey();
            String kitapBilgisi = entry.getValue();
            String[] kitapDetaylari = kitapBilgisi.split(", ");
            String kitapTuru = kitapDetaylari[1].toLowerCase(); // Küçük/kapital harf farkını önlemek için
            String yayinTarihi = kitapDetaylari[2];

            if (kitapTuru.equals(arananTur.toLowerCase())) {
                System.out.println(kitapIsmi + " : " + kitapBilgisi);
                kitapBulundu = true;
            }
        }

        if (!kitapBulundu) {
            System.out.println("Bu turde kitap bulunamadi.");
        }

    }


    public static void yazardanKitapBulma() throws InterruptedException {

        //TODO kitaplar.Map'in Value larını almak için  Set<Map.Entry<String, String>> cinsinden myEntrySet tanımlayın...
        System.out.println("Istediginiz yazar ismini yaziniz: ");
        String arananYazar = scan.nextLine();
        //TODO Metodu kullanıcıdan alacağınız girdileri kullanarak tamamlayın...
        Set<Map.Entry<String, String>> myEntrySet = kitaplarMap.entrySet();
        boolean kitapBulundu = false;

        System.out.println(
                "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
                        "================= KITAP LISTESI ===============\n" +
                        "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");
        // printf veya String.format metodları kullanılarak daha düzgün bi çıktı elde edilebilir.
        // Şart değil, isteğe bağlı.
        for (Map.Entry<String, String> entry : myEntrySet) {
            String kitapIsmi = entry.getKey();
            String kitapBilgisi = entry.getValue();
            String[] kitapDetaylari = kitapBilgisi.split(", ");
            String yazarIsmi = kitapDetaylari[0];

            if (yazarIsmi.equalsIgnoreCase(arananYazar)) {
                System.out.println(kitapIsmi + " : " + kitapBilgisi);
                kitapBulundu = true;
            }
        }

        if (!kitapBulundu) {
            System.out.println("Bu yazarin kitabi bulunamadi.");
        }

    }

    public static void kitapListesiYazdir() throws InterruptedException { //Üye Listesi Yazdır Metodundan Faydalanabilirsiniz...

        //TODO kitaplar.Map'in Value larını almak için  Set<Map.Entry<String, String>> cinsinden myEntrySet tanımlayın...

        System.out.println(
                "\n============ TECHNO STUDY CONFLUENCE ==========\n" +
                        "================= KITAP LISTESI ===============\n" +
                        "Kitap Ismi    :   Yazar Ismi , Kitap Turu , Yayin Yili");


        //TODO Kitapları listeleyecek metodu oluşturun...
        //Üye Listesi Yazdır Metodundan Faydalanabilirsiniz...
        Set<Map.Entry<String, String>> myEntrySet = kitaplarMap.entrySet();
        for (Map.Entry<String, String> entry : myEntrySet) {
            String kitapIsmi = entry.getKey();
            String kitapBilgisi = entry.getValue();
            System.out.println(kitapIsmi + " : " + kitapBilgisi);
        }
    }
}
