import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

    public class InputDataDiri {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in); //Parameter (System.in)

            System.out.print("Nama: ");
            String name = scanner.nextLine(); //nextline inputan

            System.out.print("Jenis Kelamin (L/P): ");
            char gender = scanner.nextLine().charAt(0);

            //Konversi Jenis Kelamin Menjadi Teks
            String jenisKelamin = (gender == 'P') ? "Perempuan" : "Laki-laki";

            System.out.print("Tanggal Lahir (yyyy-mm-dd): ");
            String tanggallahir = scanner.next();
            LocalDate tanggalLahir = LocalDate.parse(tanggallahir);

            LocalDate now = LocalDate.now();
            Period umur = Period.between(tanggalLahir, now);

            System.out.println("Nama         : " + name);
            System.out.println("Jenis Kelamin: " + jenisKelamin );
            System.out.println("Umur Anda    : " + umur.getYears() + " tahun " + umur.getMonths() + " bulan " );
        }
    }

