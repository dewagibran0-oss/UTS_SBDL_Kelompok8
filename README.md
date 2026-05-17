# 🏢 E-Business Infrastructure & Financial Evaluation System
### Tugas Kelompok - Ujian Tengah Semester (UTS) Sistem Basis Data Lanjut (SBDL)

Sistem ini merupakan aplikasi manajemen infrastruktur *e-business* dan evaluasi finansial yang dirancang menggunakan pendekatan pemrograman berorientasi objek serta penyimpanan data berbasis **Object-Oriented Database (OODB)** menggunakan **db4o**. Sistem ini berfokus pada pemantauan metrik ketersediaan (*availability*), manajemen keluhan akibat gangguan sistem, serta analisis kelayakan finansial dari proposal upgrade infrastruktur yang diajukan oleh vendor.

---

## 📁 Struktur Repositori

Berdasarkan komponen yang ada di dalam repositori ini, berikut adalah rincian fungsional dari setiap direktori dan file:

* **`UTS_SBDL_Kel8/`** — Direktori utama *source code* aplikasi Java (berbasis Eclipse IDE). Berisi konfigurasi kelas proyek (`.project`, `.classpath`) dan file basis data objek lokal (`Kelompok8_Catalog.db4o`).
* **`Project_Kelompok8/`** — Direktori pemodelan arsitektur sistem. Berisi file representasi UML (UML model, notation, dan diagram visual `.png` untuk Class Diagram dan Object Diagram).
* **`E-ERD_Kelompok8.drawio.pdf`** — Dokumentasi skema keterkaitan data pendukung dalam format PDF.
* **`SBDL_UTS_Kel8.txt`** — File catatan teks/log pendukung proyek kelompok.
* **`sbdl.jpeg` & `sbd l.jpeg`** — Lampiran visual diagram arsitektur sistem untuk dokumentasi cepat.
* **`.gitignore`** — Berkas konfigurasi untuk menyaring file *temporary*, hasil compile Java (`bin/`, `.class`), serta file basis data lokal agar tidak terjadi konflik antar anggota kelompok.

---

## 📐 Pemodelan Sistem (Arsitektur Berbasis Objek)

Aplikasi dirancang dengan menerapkan prinsip-prinsip utama OOP (*Inheritance, Realization, Association, Dependency*) untuk memetakan kebutuhan operasional infrastruktur IT secara nyata.

### 1. Class Diagram (Struktur Kelas)
* **`Organization` (Abstract Class):** Berfungsi sebagai *Superclass* yang menyediakan atribut dasar entitas bisnis, yaitu `name` dan `contactInfo`. Kelas ini diturunkan (*Generalization*) menjadi:
    * **`Vendor`**: Entitas penyedia solusi yang memiliki `vendorID` dan `contactPerson`. Memiliki hubungan asosiasi dengan proposal upgrade.
    * **`EBusinessCenter`**: Entitas pusat data internal yang mengelola bisnis tertentu (`businessType`) dan memiliki kalkulasi biaya kerugian operasional per jam (`hourlyDowntimeCost`).
* **`IFinancialEvaluator` (Interface):** Kontrak fungsionalitas untuk melakukan audit finansial sistem melalui metode `calculateAnnualImpact()`. Interface ini diimplementasikan (*Realization*) oleh kelas `EBusinessCenter` dan `UpgradeProposal`.
* **`Infrastructure`**: Memetakan konfigurasi server fisik (`serverConfig`), penyimpanan (`storageConfig`), dan tingkat ketersediaan saat ini (`currentAvailability`). Kelas ini bertanggung jawab atas operasi operasional melalui `calculateUptime()` dan `applyUpgrade()`.
* **`AvailabilityMetric`**: Menyediakan metrik performa mendalam yang diukur langsung dari komponen infrastruktur (`availabilityLevel`, `annualDowntimeHours`, `annualDowntimeMinutes`, `annualCost`).
* **`CustomerComplaint`**: Kelas pencatatan keluhan pengguna yang terjadi akibat adanya *downtime* pada infrastruktur, memuat `complaintID`, `timestamp`, `description`, dan `status`.

### 2. Object Diagram (Instansiasi & Kasus Data)
Berdasarkan visualisasi data berjalan (*runtime object*), sistem mensimulasikan skenario infrastruktur berikut:
* **`pusatJakarta: EBusinessCenter`**
    * *Name:* "Global Catalog Sales Center"
    * *Address:* Jl. Sudirman No. 1, Jakarta
    * *Hourly Downtime Cost:* Rp90.000,00 / jam
* **`serverUtama: Infrastructure`**
    * *Configuration:* Clustered Servers & Mirrored Disk Drives
    * *Current Availability:* `0.999` (99.9% Uptime)
* **`solusiHemat: UpgradeProposal`**
    * *Software Updates:* "High-Availability Suite v4.0"
    * *Extra Disk Capacity:* "2TB SSD Cluster"
    * *Monthly Investment:* Rp25.000,00
    * *Target Availability:* `0.9999` (Meningkat menjadi 99.99% Uptime)

---

## 🛠️ Aturan Penggunaan Database Objek (db4o)

Berbeda dengan Relational Database (RDBMS) yang menggunakan tabel dan SQL, proyek ini menggunakan **db4o** sebagai **Object-Oriented Database**. 

1.  **Tanpa Object-Relational Mapping (ORM):** Objek Java yang dibuat (seperti instans dari `Infrastructure` atau `UpgradeProposal`) langsung disimpan secara utuh ke dalam file database `Kelompok8_Catalog.db4o` tanpa perlu dikonversi ke bentuk tabel baris-kolom.
2.  **Native Queries:** Proses pencarian data dilakukan menggunakan sintaks murni Java (QBE atau Native Queries) sehingga menjaga konsistensi tipe data (*type-safe*).
3.  **Struktur Penyimpanan:** Pastikan library `.jar` db4o sudah ditambahkan pada *Build Path* Eclipse kamu agar file `.db4o` dapat dibaca dan dimanipulasi dengan benar oleh aplikasi.

---

## 🚀 Panduan Membuka Proyek secara Lokal (Local Setup)

Untuk menjalankan atau mengembangkan proyek ini di komputer Anda, ikuti langkah-langkah berikut:

1.  **Clone Repositori:**
    ```bash
    git clone [https://github.com/dewagibran0-oss/](https://github.com/dewagibran0-oss/)[UTS_SBDL_Kelompok8].git
    ```
2.  **Import ke Eclipse IDE:**
    * Buka Eclipse IDE.
    * Pilih menu **File** -> **Import...**
    * Pilih **General** -> **Existing Projects into Workspace**, lalu klik *Next*.
    * Arahkan *Root Directory* ke folder **`UTS_SBDL_Kel8/`** yang telah di-clone, lalu klik **Finish**.
3.  **Verifikasi Build Path:**
    * Pastikan library Java JRE dan library `db4o.jar` sudah terpasang dengan benar di bagian properti proyek agar tidak ada *error indicator* merah pada kode program.
4.  **Menjalankan Aplikasi:**
    * Cari file kelas yang memiliki method `public static void main(String[] args)` di dalam folder `src/`.
    * Klik kanan pada file tersebut -> **Run As** -> **Java Application**.

---

## 👥 Anggota Kelompok 8
* **Dewa Ahmad Gibran** (NIM: 1242001027) - *Informatics Student, Universitas Bakrie*
* **Bayu Destany Gunawan** (NIM: 1242001041) - *Informatics Student, Universitas Bakrie*
* **Adrian Sultan Taher** (NIM: 1242001038) - *Informatics Student, Universitas Bakrie*
* **Muhammad Rifky Balweel** (NIM: 1242001006) - *Informatics Student, Universitas Bakrie*

---
*Dokumentasi ini disusun secara terstruktur untuk memenuhi standar profesionalisme dalam rekayasa perangkat lunak, manajemen pangkalan data objek, dan kolaborasi berbasis Git.*