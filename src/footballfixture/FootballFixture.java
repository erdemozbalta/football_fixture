package footballfixture;
import java.util.Scanner;
import java.util.Random;

public class FootballFixture
{
    public static void main(String[] args) 
    {
       Scanner oku = new Scanner(System.in);
       Random rnd = new Random();
       
       String[] takimlar = new String[4];
       
       int[][] maclar = 
       {
           {0,1},{2,3},
           {0,2},{1,3},
           {0,3},{1,2},
           {1,0},{3,2},
           {2,0},{3,1},
           {3,0},{2,1} 
       };
       
       int[] ev_gol = new int [12];
       int[] dep_gol = new int [12];
       int[] oynanan_mac = new int[4];
       int[] galibiyet = new int[4];
       int[] beraberlik = new int[4];
       int[] maglubiyet = new int[4];
       int[] atilan_gol = new int[4];
       int[] yenilen_gol = new int[4];
       int[] puan = new int[4];
       
       boolean takimlar_girildi = false; 
       boolean fikstur_olustu = false;
       boolean skor_atandi = false;
       
       int secim;
       
       do{
       menu();
       secim= oku.nextInt();
       
       switch(secim){
           case 1:
               takim_gir(oku, takimlar);
               takimlar_girildi = true;
               break;
               
           case 2:
               if (!takimlar_girildi) 
               {
               System.out.println("Once takim isimlerini giriniz!");
               } 
               else
               {
               fikstur_yazdir(takimlar, maclar);
               fikstur_olustu = true;
               } break;
               
           case 3:
               if (!takimlar_girildi || !fikstur_olustu)
               {
               System.out.println("Once takim ve fikstur olusturmalisiniz!");
               } 
               else if (skor_atandi){
               System.out.println("Skorlar zaten atanmis.");
               } 
               else
               {
               skor_ata(rnd, maclar, ev_gol, dep_gol,
               oynanan_mac, galibiyet, beraberlik, maglubiyet,
               atilan_gol, yenilen_gol, puan);
               skor_atandi = true;
               } break;
               
           case 4:
               if (!takimlar_girildi)
               {
               System.out.println("Once takim giriniz!");
               } 
               else
               {
               puanDurumu(takimlar, oynanan_mac, galibiyet, beraberlik,
               maglubiyet, atilan_gol, yenilen_gol, puan);
               } 
               break;
               
           case 5:
               System.out.println("Cikis yapildi.");
               break;
               
               default:
               System.out.println("Lutfen gecerli bir sayi giriniz!");
               break;
       }
       }while(secim != 5);
       }
    
    static void menu()
    {
        System.out.println("\n1- Takim isimlerini belirle");
        System.out.println("2- Fikstur olustur");
        System.out.println("3- Skorlari rastgele ata");
        System.out.println("4- Puan durumu");
        System.out.println("5- Cikis");
        System.out.print("Secim: ");
    }
    
    static void takim_gir(Scanner oku2, String[] takimlar) 
    { 
        oku2.nextLine();
        
        for (int i = 0; i < 4; i++) 
        {
            System.out.print((i + 1) + ". Takim: ");
            takimlar[i] = oku2.nextLine(); 
        }
        System.out.println("Takimlar kaydedildi.");
    }
    
    static void fikstur_yazdir(String[] takimlar, int[][] maclar) 
    {
        System.out.println("\nFIKSTUR");
        int hafta = 1;

        for (int i = 0; i < 12; i += 2)
        {
            System.out.println("Hafta " + hafta++); 
            System.out.println("  " + takimlar[maclar[i][0]] + " - " + takimlar[maclar[i][1]]);
            System.out.println("  " + takimlar[maclar[i + 1][0]] + " - " + takimlar[maclar[i + 1][1]]);
        }
    }
    
    static void skor_ata(Random rnd2, int[][] maclar, int[] ev_gol, int[] dep_gol, int[] oynanan, 
    int[] galibiyet,int[] beraberlik, int[] maglubiyet,int[] atilan_gol, int[] yenilen_gol, int[] puan) 
    {
        for (int i = 0; i < 12; i++) 
        {
            ev_gol[i] = rnd2.nextInt(5);
            dep_gol[i] = rnd2.nextInt(5); 

            int ev = maclar[i][0]; 
            int dep = maclar[i][1]; 

            oynanan[ev]++; 
            oynanan[dep]++;

            atilan_gol[ev] += ev_gol[i];
            yenilen_gol[ev] += dep_gol[i]; 
            atilan_gol[dep] += dep_gol[i]; 
            yenilen_gol[dep] += ev_gol[i]; 

            if (ev_gol[i] > dep_gol[i]) 
            {
                galibiyet[ev]++;
                maglubiyet[dep]++;
                puan[ev] += 3;
            }
            else if (ev_gol[i] < dep_gol[i]) 
            {
                galibiyet[dep]++;
                maglubiyet[ev]++;       
                puan[dep] += 3;
            }
            else 
            {
                beraberlik[ev]++;
                beraberlik[dep]++;
                puan[ev]++;
                puan[dep]++;
            }
        }
        System.out.println("Skorlar rastgele atandi.");
    }
    
    static void puanDurumu(String[] takimlar, int[] oynanan, int[] galibiyet, int[] beraberlik, 
    int[] maglubiyet, int[] atilan_gol, int[] yenilen_gol, int[] puan) 
    {
        System.out.println("\nPUAN DURUMU");
        System.out.println(sabitle("Takim", 12) + "O  G  B  M  AG  YG  AV  P");

        for (int i = 0; i < 4; i++) 
        {
            int averaj = atilan_gol[i] - yenilen_gol[i];

            System.out.println(sabitle(takimlar[i], 12) + oynanan[i] + "  " + galibiyet[i] + "  " +beraberlik[i] + "  " + 
            maglubiyet[i] + "  " + atilan_gol[i] + "   " +yenilen_gol[i] + "   " +averaj + "   " +puan[i]); 
        }
    }
        
    static String sabitle(String metin, int uzunluk) 
    {
        while (metin.length() < uzunluk) 
        {
            metin += " ";  
        }
        return metin;
    }
}
