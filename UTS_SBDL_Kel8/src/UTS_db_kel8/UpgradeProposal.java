package UTS_db_kel8;

public class UpgradeProposal implements IFinancialEvaluator {
    private String softwareUpdates;
    private String extraDiskCapacity;
    private float monthlyInvestment;
    private float targetAvailability;

    public UpgradeProposal(String softwareUpdates, String extraDiskCapacity, float monthlyInvestment, float targetAvailability) {
        this.softwareUpdates = softwareUpdates;
        this.extraDiskCapacity = extraDiskCapacity;
        this.monthlyInvestment = monthlyInvestment;
        this.targetAvailability = targetAvailability;
    }

    public float getROI() {
        // ROI = Penghematan Bruto - Total Investasi Tahunan
        return calculateAnnualImpact() - (this.monthlyInvestment * 12);
    }

    @Override
    public float calculateAnnualImpact() {
        // Penghematan bruto dari 99.9% ke 99.99% berdasarkan studi kasus ($877k - $88k)
        return 789000f; 
    }

    public String getSoftwareUpdates() {
        return softwareUpdates;
    }

    public void setSoftwareUpdates(String softwareUpdates) {
        this.softwareUpdates = softwareUpdates;
    }

    public String getExtraDiskCapacity() {
        return extraDiskCapacity;
    }

    public void setExtraDiskCapacity(String extraDiskCapacity) {
        this.extraDiskCapacity = extraDiskCapacity;
    }

    public float getMonthlyInvestment() {
        return monthlyInvestment;
    }

    public void setMonthlyInvestment(float monthlyInvestment) {
        this.monthlyInvestment = monthlyInvestment;
    }

    public float getTargetAvailability() {
        return targetAvailability;
    }

    public void setTargetAvailability(float targetAvailability) {
        this.targetAvailability = targetAvailability;
    }
}
