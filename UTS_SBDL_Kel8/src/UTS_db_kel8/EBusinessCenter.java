package UTS_db_kel8;

public class EBusinessCenter extends Organization implements IFinancialEvaluator {
    private String businessType;
    private float hourlyDowntimeCost;

    public EBusinessCenter(String name, String contactInfo, String businessType, float hourlyDowntimeCost) {
        super(name, contactInfo);
        this.businessType = businessType;
        this.hourlyDowntimeCost = hourlyDowntimeCost;
    }

    public float getAnnualLoss() {
        // Berdasarkan tabel, downtime untuk 99.9% adalah 8.77 jam per tahun
        return this.hourlyDowntimeCost * 8.77f; 
    }

    @Override
    public float calculateAnnualImpact() {
        return getAnnualLoss();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public float getHourlyDowntimeCost() {
        return hourlyDowntimeCost;
    }

    public void setHourlyDowntimeCost(float hourlyDowntimeCost) {
        this.hourlyDowntimeCost = hourlyDowntimeCost;
    }
}