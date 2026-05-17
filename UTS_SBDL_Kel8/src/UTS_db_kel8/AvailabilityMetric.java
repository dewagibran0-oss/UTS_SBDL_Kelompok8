package UTS_db_kel8;

public class AvailabilityMetric {
    private float availabilityLevel;
    private float annualDowntimeHours;
    private int annualDowntimeMinutes;
    private float annualCost;

    public AvailabilityMetric(float availabilityLevel, float annualDowntimeHours, int annualDowntimeMinutes, float annualCost) {
        this.availabilityLevel = availabilityLevel;
        this.annualDowntimeHours = annualDowntimeHours;
        this.annualDowntimeMinutes = annualDowntimeMinutes;
        this.annualCost = annualCost;
    }

    public float getAvailabilityLevel() {
        return availabilityLevel;
    }

    public void setAvailabilityLevel(float availabilityLevel) {
        this.availabilityLevel = availabilityLevel;
    }

    public float getAnnualDowntimeHours() {
        return annualDowntimeHours;
    }

    public void setAnnualDowntimeHours(float annualDowntimeHours) {
        this.annualDowntimeHours = annualDowntimeHours;
    }

    public int getAnnualDowntimeMinutes() {
        return annualDowntimeMinutes;
    }

    public void setAnnualDowntimeMinutes(int annualDowntimeMinutes) {
        this.annualDowntimeMinutes = annualDowntimeMinutes;
    }

    public float getAnnualCost() {
        return annualCost;
    }

    public void setAnnualCost(float annualCost) {
        this.annualCost = annualCost;
    }
}
