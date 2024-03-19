import java.util.Scanner;

class BangunRuang { //class bangun ruang
    private String name;

    BangunRuang(String name){
        this.name = name;
    }

    //method
    public void inputNilai(){ 
        System.out.println("Input nilai");
    }

    public void luasPermukaan(){
        System.out.println("Menghitung luas permukaan bangun " + name);
    }

    public void volume(){
        System.out.println("Menghitung volume bangun " + name);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name; //harus sama dengan atribut
    }
}

class Kubus extends BangunRuang {
    Scanner scanner = new Scanner(System.in);
    private double sisi;

    Kubus(String nameBangun) { //constructor
        super(nameBangun); 
    }

    @Override
    public void inputNilai(){ //harus sesuai nama method
        super.inputNilai(); //akses method
        System.out.print("Input sisi: ");
        sisi = scanner.nextDouble();
    }

    @Override
    public void luasPermukaan(){
        double hasil = 6 * sisi * sisi;
        super.luasPermukaan();
        System.out.println("Hasil luas permukaan: " + hasil);
    }

    @Override
    public void volume(){
        double hasil = Math.pow(sisi, 3);
        super.volume();
        System.out.println("Hasil volume: " + hasil);
    }
}

class Tabung extends BangunRuang {
    Scanner scanner = new Scanner(System.in);
    private double tinggi;
    private double jari_jari;

    Tabung(String nameBangun) {
        super(nameBangun);
    }

    @Override
    public void inputNilai(){
        super.inputNilai();
        System.out.print("Input tinggi: ");
        tinggi = scanner.nextDouble();
        System.out.print("Input jari-jari: ");
        jari_jari = scanner.nextDouble();
    }

    @Override
    public void luasPermukaan(){
        double hasil = 2 * 3.14 * jari_jari * (jari_jari);
        super.luasPermukaan();
        System.out.println("Hasil luas permukaan: " + hasil);
    }

    @Override
    public void volume(){
        double hasil = 3.14 * Math.pow(jari_jari, 2) * tinggi;
        super.volume();
        System.out.println("Hasil volume: " + hasil);
    }
}

class Balok extends BangunRuang {
    Scanner scanner = new Scanner(System.in);
    private double panjang;
    private double lebar;
    private double tinggi;

    Balok(String nameBangun) {
        super(nameBangun);
    }

    @Override
    public void inputNilai(){
        super.inputNilai();
        System.out.print("Input panjang: ");
        panjang = scanner.nextDouble();
        System.out.print("Input lebar: ");
        lebar = scanner.nextDouble();
        System.out.print("Input tinggi: ");
        tinggi = scanner.nextDouble();
    }

    @Override
    public void luasPermukaan(){
        double hasil = 2 * ((panjang * lebar) + (panjang * tinggi) + (lebar * tinggi));
        super.luasPermukaan();
        System.out.println("Hasil luas permukaan: " + hasil);
    }

    @Override
    public void volume(){
        double hasil = panjang * lebar * tinggi;
        super.volume();
        System.out.println("Hasil volume: " + hasil);
    }
}

public class modul3 {
    public static void main(String[] args) throws Exception {
        Tabung tabung = new Tabung("tabung");
        Kubus kubus = new Kubus("kubus");
        Balok balok = new Balok("balok");

        balok.inputNilai();
        balok.luasPermukaan();
        balok.volume();

        kubus.inputNilai();
        kubus.luasPermukaan();
        kubus.volume();

        tabung.inputNilai();
        tabung.luasPermukaan();
        tabung.volume();
    }
}