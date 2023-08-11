import java.util.Scanner;

public class Helper {
    static Scanner scan = new Scanner(System.in);

    private static String loggedInUsername;
    private static UserRole loggedInUserRole;

    private static void login() {   //TODO LOGIN FONKSIYONUNU YAZIN
        // login olunduğunda, "loggedInUserRole" ya ADMIN ya USER rolünü alacak, eğer null ise
        // login olunmamış demektir...

        //TODO loggedInUserRole null olup olmadığını kontrol ederek,
        //TODO kullanıcıdan, kullanıcı adı ve şifre isteyiniz...

        //TODO username:password > admin:Admin123 ya da user:User123 şartının sağlanıp sağlanmadığına bakın...
        //TODO Geçerli giriş sağlanırsa kullanıcının rolünü belirleyin,
        //TODO "Admin olarak giris yapildi!" ya da "Standart kullanici olarak giris yapildi!" uygun olan mesajı verin..
        //TODO Yoksa "Gecersiz kullanici adi veya sifre!" mesajını döndürün.
        if (loggedInUserRole == null) {
            // kullanıcıdan, kullanıcı adı ve şifre isteyiniz...
            System.out.print("Kullanıcı Adı: ");
            String username = scan.nextLine();
            System.out.print("Şifre: ");
            String password = scan.nextLine();

            // username:password > admin:Admin123 ya da user:User123 şartının sağlanıp sağlanmadığına bakın...
            if (username.equals("admin") && password.equals("Admin123")) {
                loggedInUserRole = UserRole.ADMIN;
                System.out.println("Admin olarak giriş yapıldı!");
            } else if (username.equals("user") && password.equals("User123")) {
                loggedInUserRole = UserRole.USER;
                System.out.println("Standart kullanıcı olarak giriş yapıldı!");
            } else {
                System.out.println("Geçersiz kullanıcı adı veya şifre!");
            }
        }

    }

    public static void anaMenu() throws InterruptedException {

        login(); // Kullanıcı girişi yapılıyor
        //TODO User Role'e göre kullanıcının "showAdminMenu()" ya da "showUserMenu()" ye
        //TODO Yönlendirin...

//        String tercih = "";
//         {//TODO Kullanıcıdan alacağınız tercihlere göre, gerekli menü metodlarına yönlendirme yapınız
//
////                    "\n=========== TECHNO STUDY CONFLUENCE =============\n" +
////                            "=================== ANA MENU ====================\n" +
////                            "\n" +
////                            "\t   1- Kutuphane Bilgileri Goruntule\n" +
////                            "\t   2- Uyeler Menu\n" +
////                            "\t   3- Kitaplar Menu\n" +
////                            "\t   Q- CIKIS\n");
////            System.out.print("Lutfen Menuden tercihinizi yapiniz:");
//
//            tercih = scan.nextLine().toLowerCase();
//
//             {
////                    kutuphaneBilgileriniYazdir();
////                    loginAndShowUserMenu(UserRole.ADMIN);
////                    loginAndShowUserMenu(UserRole.ADMIN);
////                    System.out.print("Lutfen gecerli bir tercih giriniz:");
//            }
//
//        }
//
//        projeDurdur();
        while (true) {
            System.out.println("\n=========== TECHNO STUDY CONFLUENCE =============\n" +
                    "=================== ANA MENU ====================\n" +
                    "\n" +
                    "\t   1- Kutuphane Bilgileri Goruntule\n" +
                    "\t   2- Uyeler Menu\n" +
                    "\t   3- Kitaplar Menu\n" +
                    "\t   Q- CIKIS\n");

            System.out.print("Lutfen Menuden tercihinizi yapiniz: ");
            String tercih = scan.nextLine().toLowerCase();

            switch (tercih) {
                case "1":
                    kutuphaneBilgileriniYazdir();
                    break;
                case "2":
                    loginAndShowUserMenu(UserRole.ADMIN);
                    break;
                case "3":
                    loginAndShowUserMenu(UserRole.USER);
                    break;
                case "q":
                    projeDurdur();
                    break;
                default:
                    System.out.println("Geçersiz bir tercih yaptınız.");
                    break;
            }
        }
    }

    public static void kutuphaneBilgileriniYazdir() throws InterruptedException
    {//BU METODDA BİR DEĞİŞİKLİK YAPMANIZA GEREK YOK...

        System.out.print("Kutuphane bilgileri yazdiriliyor...");
        for (int i = 0; i < 20; i++) {
            Thread.sleep(100);
            System.out.print(">");
        }

        System.out.println("\n" +
                "\n============= TECHNO STUDY CONFLUENCE =============\n" +
                "\t\t Kutuphane : " + Kutuphane.kutuphaneIsim +
                "\n\t\t Adres : " + Kutuphane.adres +
                "\n\t\t Telefon : " + Kutuphane.telefon);

    }

    private static void loginAndShowUserMenu(UserRole requiredRole) throws InterruptedException {
        System.out.print("Kullanici Adi: ");
        String username = scan.nextLine();
        System.out.print("Sifre: ");
        String password = scan.nextLine();

        //Kullanıcıdan aldığınız username ve password, authenticateUser(String username, String password)
        //metodunda doğrulamaya tabi tutuluyor...
        UserRole role = authenticateUser(username, password);

        //NOT : Doğrulamadan geçerse role ADMIN veya USER olur... Geçmezse "null" olur

        //TODO null ise "Gecersiz kullanici adi veya sifre." mesajı verin
        //TODO ADMIN ise showAdminMenu() metoduyla admin menüyü gösterin
        //TODO USER ise showUserMenu() metoduyla user menüyü gösterin...
        //TODO Aksi durumlarda "Bu sayfaya erisim izniniz yok." mesajını verin...
        //if-else bloklarıyla şartlar gerçeklenebilir...
        if (role == null) {
            System.out.println("Geçersiz kullanıcı adı veya şifre.");
        } else if (role == UserRole.ADMIN) {
            showAdminMenu(); // Admin rolü ise admin menüyü göster
        } else if (role == UserRole.USER) {
            showUserMenu(); // User rolü ise user menüyü göster
        } else {
            System.out.println("Bu sayfaya erişim izniniz yok.");
        }
    }

    private static UserRole authenticateUser(String username, String password) {

        //TODO Girilen Kullanıcı adı ve şifreyi kontrol edin,
        //TODO Geçerli kullanıcıadı ve şifreyse UserRole return edin... > ADMIN ya da USER
        if (username.equals("admin") && password.equals("Admin123")) {
            return UserRole.ADMIN;
        } else if (username.equals("user") && password.equals("User123")) {
            return UserRole.USER;
        } else {
            return null;
        }
    }

    private static void showAdminMenu() throws InterruptedException {

        System.out.println(
                "\n=========== TECHNO STUDY CONFLUENCE ==========\n" +
                        "================== ADMIN MENU ================\n" +
                        "\n" +
                        "\t   1- Uyeler Menu\n" +
                        "\t   2- Kitaplar Menu\n" +
                        "\t   A- ANAMENU\n" +
                        "\t   Q- CIKIS");

        //TODO Kullanıcıdan alacağınız tercihlere göre ilgili menüye (metoda) yönlendirme yapınız...
        {
//                UyeManager.uyeMenu();
//                KitapManager.kitapMenu();
//                anaMenu();
//                projeDurdur();
//                System.out.println("Lutfen gecerli bir tercih giriniz");
        }
        String tercih = "";
        tercih = scan.nextLine().toLowerCase();

        switch (tercih) {
            case "1":
                UyeManager.uyeMenu(); // Kullanıcı "1" seçerse üyeler menüsünü göster
                break;
            case "2":
                KitapManager.kitapMenu(); // Kullanıcı "2" seçerse kitaplar menüsünü göster
                break;
            case "a":
                anaMenu(); // Kullanıcı "A" seçerse ana menüye dön
                break;
            case "q":
                projeDurdur(); // Kullanıcı "Q" seçerse programı kapat
                break;
            default:
                System.out.println("Geçersiz bir tercih yaptınız.");
                showAdminMenu(); // Geçersiz tercih yapıldıysa tekrar admin menüsünü göster
                break;
        }
    }

    private static void showUserMenu() throws InterruptedException {
        String tercih = "";
        //TODO Kullanıcı Çıkış Yapmadığı Sürece User Menüde Kalsın...
//        {
//            System.out.println(
//                          "\n========== TECHNO STUDY CONFLUENCE ===========\n" +
//                            "================== USER MENU =================\n" +
//                            "\n" +
//                            "\t   1- Uyeleri Listele\n" +
//                            "\t   2- Soyisimden Uye Bulma\n" +
//                            "\t   3- Sehire Gore Uye Bulma\n" +
//                            "\t   4- Kitaplari Listele\n" +
//                            "\t   5- Yazardan Kitap Bulma\n" +
//                            "\t   6- Kitap Turu veya Yayin Tarihi Ile Kitap Bulma\n" +
//                            "\t   7- Kitap Odunc Al\n" +
//                            "\t   8- Kitap Iade Et\n" +
//                            "\t   A- ANAMENU\n" +
//                            "\t   Q- CIKIS");
//
//            //TODO Kullanıcıdan alacağınız tercihlere göre ilgili menüye (metoda) yönlendirme yapınız...
//
//            {
////                    UyeManager.uyeListesiYazdir();
////                    UyeManager.soyisimdenUyeBulma();
////                    UyeManager.sehreGoreUyeBulma();
////                    KitapManager.kitapListesiYazdir();
////                    KitapManager.yazardanKitapBulma();
////                    KitapManager.turVeyaYayinTarihiIleKitapBulma();
////                    KitapManager.kitapOduncAl();
////                    KitapManager.kitapIadeEt();
////                    anaMenu();
////                    projeDurdur();
////                    System.out.println("Lutfen gecerli bir tercih giriniz");
//            }
//        }
        while (true) { // Kullanıcı çıkış yapana kadar döngüde kal
            System.out.println(
                    "\n========== TECHNO STUDY CONFLUENCE ===========\n" +
                            "================== USER MENU =================\n" +
                            "\n" +
                            "\t   1- Uyeleri Listele\n" +
                            "\t   2- Soyisimden Uye Bulma\n" +
                            "\t   3- Sehire Gore Uye Bulma\n" +
                            "\t   4- Kitaplari Listele\n" +
                            "\t   5- Yazardan Kitap Bulma\n" +
                            "\t   6- Kitap Turu veya Yayin Tarihi Ile Kitap Bulma\n" +
                            "\t   7- Kitap Odunc Al\n" +
                            "\t   8- Kitap Iade Et\n" +
                            "\t   A- ANAMENU\n" +
                            "\t   Q- CIKIS");

            tercih = scan.nextLine().toLowerCase();

            switch (tercih) {
                case "1":
                    UyeManager.uyeListesiYazdir();
                    break;
                case "2":
                    UyeManager.soyisimdenUyeBulma();
                    break;
                case "3":
                    UyeManager.sehreGoreUyeBulma();
                    break;
                case "4":
                    KitapManager.kitapListesiYazdir();
                    break;
                case "5":
                    KitapManager.yazardanKitapBulma();
                    break;
                case "6":
                    KitapManager.turVeyaYayinTarihiIleKitapBulma();
                    break;
                case "7":
                    KitapManager.kitapOduncAl();
                    break;
                case "8":
                    KitapManager.kitapIadeEt();
                    break;
                case "a":
                    anaMenu();
                    break;
                case "q":
                    projeDurdur();
                    break;
                default:
                    System.out.println("Geçersiz bir tercih yaptınız.");
                    break;
            }
        }
    }

    public static void projeDurdur() {
        System.out.println("Kutuphane projesinden ciktiniz");
        System.exit(0);
    }
}

