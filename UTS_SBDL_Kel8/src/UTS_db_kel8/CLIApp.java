package UTS_db_kel8;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import java.util.Scanner;

public class CLIApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "Kelompok8_Catalog.db4o");

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=====================================");
            System.out.println("   DATA CENTER MANAGEMENT SYSTEM");
            System.out.println("=====================================");
            System.out.println("1. Tambah E-Business Center Baru");
            System.out.println("2. Tampilkan Semua Pusat Bisnis");
            System.out.println("3. Hitung Analisis Kelayakan Proposal Vendor");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Nama Organisasi: ");
                    String name = scanner.nextLine();
                    System.out.print("Biaya Downtime per Jam (USD): ");
                    float cost = scanner.nextFloat();
                    
                    EBusinessCenter newCenter = new EBusinessCenter(name, "Jakarta", "Catalog Sales", cost);
                    db.store(newCenter);
                    System.out.println("\n[INFO] Data E-Business Center berhasil disimpan!");
                    break;

                case 2:
                    ObjectSet<EBusinessCenter> centers = db.queryByExample(EBusinessCenter.class);
                    System.out.println("\n--- Daftar Pusat Bisnis ---");
                    if(centers.isEmpty()){
                        System.out.println("Data masih kosong.");
                    } else {
                        for (EBusinessCenter c : centers) {
                            System.out.println("- " + c.getName() + " | Tipe: " + c.getBusinessType());
                            System.out.println("  Biaya Kerugian Tahunan (99.9%): $" + c.getAnnualLoss());
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- Analisis Kelayakan Proposal (99.99%) ---");
                    UpgradeProposal proposal = new UpgradeProposal("High-Availability Suite v4.0", "2TB SSD Cluster", 25000f, 0.9999f);
                    
                    float annualInvest = proposal.getMonthlyInvestment() * 12;
                    System.out.println("Investasi Tambahan Tahunan: $" + annualInvest);
                    System.out.println("Potensi Penghematan Bruto: $" + proposal.calculateAnnualImpact());
                    System.out.println("-------------------------------------");
                    System.out.println("Keuntungan Bersih (ROI) per Tahun: $" + proposal.getROI());
                    System.out.println("Kesimpulan: " + (proposal.getROI() > 0 ? "LAYAK / JUSTIFIED" : "TIDAK LAYAK"));
                    break;

                case 4:
                    isRunning = false;
                    System.out.println("Keluar dari aplikasi... Sampai jumpa!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid, silakan coba lagi.");
            }
        }
        
        db.close();
        scanner.close();
    }
}
