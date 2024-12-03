import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplitter {

    // Fungsi untuk membaca file dan memotongnya menjadi beberapa bagian
    public static void splitFile(String filePath, int partSize) throws IOException {
        // Membaca file teks
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        // Queue untuk menyimpan bagian-bagian file
        Queue<String> queue = new LinkedList<>();

        StringBuilder currentPart = new StringBuilder();
        String line;

        // Membaca file baris per baris
        while ((line = reader.readLine()) != null) {
            if (currentPart.length() + line.length() > partSize) {
                // Jika panjang teks lebih dari ukuran part, simpan bagian sebelumnya ke queue
                queue.offer(currentPart.toString());
                currentPart.setLength(0);  // Reset currentPart
            }
            // Tambahkan baris ke currentPart
            currentPart.append(line).append("\n");
        }

        // Menambahkan sisa teks terakhir ke dalam queue
        if (currentPart.length() > 0) {
            queue.offer(currentPart.toString());
        }

        reader.close();

        // Menampilkan hasil pemotongan
        System.out.println("File telah dipotong menjadi beberapa bagian:");
        int partNumber = 1;
        while (!queue.isEmpty()) {
            System.out.println("Bagian " + partNumber + ":");
            System.out.println(queue.poll());  // Ambil dan tampilkan bagian pertama dari queue
            partNumber++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Minta input dari pengguna
        System.out.print("Masukkan path file yang akan dipotong: ");
        String filePath = scanner.nextLine();

        System.out.print("Masukkan ukuran potongan (dalam karakter): ");
        int partSize = scanner.nextInt();

        try {
            // Memanggil fungsi untuk memotong file dan menampilkannya
            splitFile(filePath, partSize);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }

        scanner.close();
    }
}