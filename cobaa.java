import java.util.ArrayList;
import java.util.Scanner;

class Mahasiswa {
    private String nim;
    private String nama;
    private String jurusan;
    private String universitas;
    

    public Mahasiswa(String nim, String nama, String jurusan, String universitas) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
        this.universitas = universitas;
    }

    public Mahasiswa(Scanner scanner) {
        
    }

    public static void tampilUniversitas() {
        System.out.println("Universitas Muhammadiyah Malang");
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public String getUniversitas() {
        return universitas;
    }

    public void menuMahasiswa() {
       
    }

    public void setNama(String string) {

    }

    public void setFakultas(String string) {
      
    }

    public void setProgramStudi(String string) {
      

    }

    public String getProgramStudi() {
      
    }

    public String getFakultas() {
        
    
}

public class  cobaa {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Mahasiswa> daftarMahasiswa = new ArrayList<>();

        int pilihan;
        do {
            System.out.println("Menu:");
            System.out.println("1. Tambah Data Mahasiswa");
            System.out.println("2. Tampilkan Data Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Pilihan: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama: ");
                    String nama = input.next();
                    input.nextLine(); 
                    System.out.print("Masukkan NIM: ");
                    String nim = input.next();
                if(nim.length() != 15){
                    System.out.println("NIM harus 15 digit");
                }else{
                    input.nextLine(); 
                    System.out.print("Masukkan jurusan: ");
                    String jurusan = input.nextLine();
                    
                    Mahasiswa.tampilUniversitas(); // Static method call
                    String universitas = "Universitas Muhammadiyah Malang"; // Universitas sementara
                    Mahasiswa mahasiswa = new Mahasiswa(nim, nama, jurusan, universitas);
                    daftarMahasiswa.add(mahasiswa);
                }
                    break;
                case 2:
                    System.out.println("Data Mahasiswa:");
                    for (Mahasiswa mhs : daftarMahasiswa) {
                        System.out.println("Nama: " + mhs.getNama());
                        System.out.println("NIM: " + mhs.getNim());
                        System.out.println("Jurusan: " + mhs.getJurusan());
                        System.out.println("Universitas: " + mhs.getUniversitas());
                    }
                    break;
                case 3:
                    System.out.println("Adios");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 3);
    }

}