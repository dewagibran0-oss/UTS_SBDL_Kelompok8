package UTS_db_kel8;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.db4o.query.Query;

public class DB4OManager {
    static final String DB_FILE = "Kelompok8_Catalog.db4o";

    public static void main(String[] args) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB_FILE);
        try {
            // -- 1. CREATE INSTANCES & STORE TO DB --
            EBusinessCenter center = new EBusinessCenter("Global Catalog", "Jakarta", "Catalog Sales", 90000f);
            Infrastructure infra = new Infrastructure("Clustered Servers", "Mirrored Disk Drives", 0.999f);
            UpgradeProposal proposal = new UpgradeProposal("HA Suite v4.0", "2TB SSD", 25000f, 0.9999f);
            
            db.store(center);
            db.store(infra);
            db.store(proposal);
            System.out.println("Data awal berhasil disimpan ke Database!");

            // -- 2. RUN QUERIES --
            runQBE(db);
            runNativeQueries(db);
            runSODA(db);

        } finally {
            db.close();
        }
    }

    public static void runQBE(ObjectContainer db) {
        System.out.println("\n=== QUERY BY EXAMPLE (QBE) ===");
        
        // 1. SELECT semua EBusinessCenter
        ObjectSet<EBusinessCenter> q1 = db.queryByExample(EBusinessCenter.class);
        System.out.println("1. Total EBusinessCenter: " + q1.size());

        // 2. SELECT berdasarkan Availability
        Infrastructure protoInfra = new Infrastructure(null, null, 0.999f);
        ObjectSet<Infrastructure> q2 = db.queryByExample(protoInfra);
        System.out.println("2. Total Infrastructure (99.9%): " + q2.size());

        // 3. SELECT Proposal dengan investasi 25000
        UpgradeProposal protoProp = new UpgradeProposal(null, null, 25000f, 0f);
        ObjectSet<UpgradeProposal> q3 = db.queryByExample(protoProp);
        System.out.println("3. Proposal dengan investasi $25000: " + q3.size());

        // 4. UPDATE Cost dari EBusinessCenter
        if(q1.hasNext()) {
            EBusinessCenter ebcUpdate = q1.next();
            ebcUpdate.setHourlyDowntimeCost(95000f);
            db.store(ebcUpdate);
            System.out.println("4. Update HourlyDowntimeCost berhasil dikerjakan.");
        }

        // 5. SELECT Vendor (Bila ada, contoh pencarian kosong)
        ObjectSet<Vendor> q5 = db.queryByExample(Vendor.class);
        System.out.println("5. Total Vendor ditemukan: " + q5.size());

        // 6. DELETE Proposal
        if(q3.hasNext()) {
            db.delete(q3.next());
            System.out.println("6. Delete objek Proposal berhasil.");
        }
    }

    public static void runNativeQueries(ObjectContainer db) {
        System.out.println("\n=== NATIVE QUERIES (NQ) ===");
        
        // 1. SELECT WHERE businessType = "Catalog Sales"
        ObjectSet<EBusinessCenter> nq1 = db.query(new Predicate<EBusinessCenter>() {
            public boolean match(EBusinessCenter ebc) {
                return "Catalog Sales".equals(ebc.getBusinessType());
            }
        });
        System.out.println("1. NQ Catalog Sales Center: " + nq1.size());

        // 2. SELECT RANGE (Investasi < 30000)
        ObjectSet<UpgradeProposal> nq2 = db.query(new Predicate<UpgradeProposal>() {
            public boolean match(UpgradeProposal up) {
                return up.getMonthlyInvestment() < 30000f; 
            }
        });
        System.out.println("2. NQ Proposal Murah (< $30k): " + nq2.size());

        // 3. SELECT WHERE Availability = 0.999
        ObjectSet<Infrastructure> nq3 = db.query(new Predicate<Infrastructure>() {
            public boolean match(Infrastructure inf) {
                return inf.getCurrentAvailability() == 0.999f;
            }
        });
        System.out.println("3. NQ Infra Availability 99.9%: " + nq3.size());

        // 4. SELECT WHERE Name starts with "Global"
        ObjectSet<EBusinessCenter> nq4 = db.query(new Predicate<EBusinessCenter>() {
            public boolean match(EBusinessCenter ebc) {
                return ebc.getName() != null && ebc.getName().startsWith("Global");
            }
        });
        System.out.println("4. NQ Nama berawalan 'Global': " + nq4.size());

        // 5. SELECT Kombinasi AND
        ObjectSet<Infrastructure> nq5 = db.query(new Predicate<Infrastructure>() {
            public boolean match(Infrastructure inf) {
                return inf.getCurrentAvailability() < 1.0f && inf.getServerConfig().contains("Clustered");
            }
        });
        System.out.println("5. NQ Infra Clustered dan < 100%: " + nq5.size());

        // 6. SELECT Kombinasi OR (Mock query)
        ObjectSet<UpgradeProposal> nq6 = db.query(new Predicate<UpgradeProposal>() {
            public boolean match(UpgradeProposal up) {
                return up.getTargetAvailability() == 0.9999f || up.getMonthlyInvestment() == 0f;
            }
        });
        System.out.println("6. NQ Target 99.99% OR Gratis: " + nq6.size());
    }

    public static void runSODA(ObjectContainer db) {
        System.out.println("\n=== SODA QUERIES ===");
        
        // 1. SELECT WHERE: EBusinessCenter.hourlyDowntimeCost > 50000
        Query q1 = db.query();
        q1.constrain(EBusinessCenter.class);
        q1.descend("hourlyDowntimeCost").constrain(50000f).greater();
        System.out.println("1. SODA Cost > 50k: " + q1.execute().size());

        // 2. SELECT WHERE NOT: Infra bukan "Single Server"
        Query q2 = db.query();
        q2.constrain(Infrastructure.class);
        q2.descend("serverConfig").constrain("Single Server").not();
        System.out.println("2. SODA NOT Single Server: " + q2.execute().size());

        // 3. SELECT AND: Proposal investment 25000 AND target 0.9999
        Query q3 = db.query();
        q3.constrain(UpgradeProposal.class);
        com.db4o.query.Constraint const1 = q3.descend("monthlyInvestment").constrain(25000f);
        q3.descend("targetAvailability").constrain(0.9999f).and(const1);
        System.out.println("3. SODA AND Invest 25k & 99.99%: " + q3.execute().size());

        // 4. SORTING: EBusinessCenter by name descending
        Query q4 = db.query();
        q4.constrain(EBusinessCenter.class);
        q4.descend("name").orderDescending();
        System.out.println("4. SODA Sorting dijalankan: " + q4.execute().size());

        // 5. SELECT LIKE: Name contains "Catalog"
        Query q5 = db.query();
        q5.constrain(EBusinessCenter.class);
        q5.descend("name").constrain("Catalog").like();
        System.out.println("5. SODA LIKE 'Catalog': " + q5.execute().size());

        // 6. SELECT OR
        Query q6 = db.query();
        q6.constrain(Infrastructure.class);
        com.db4o.query.Constraint constA = q6.descend("currentAvailability").constrain(0.999f);
        q6.descend("currentAvailability").constrain(0.9999f).or(constA);
        System.out.println("6. SODA OR Availability 99.9% / 99.99%: " + q6.execute().size());
    }
}