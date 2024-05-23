package pt.ipp.isep.dei.esoft.project.domain;

public class Vehicle {
    private String brand;
    private String model;
    private String type;
    private double tareWeight;
    private double grossWeight;
    private double currentKm;
    private String registerDate;
    private String acquisitionDate;
    private double maintenanceFrequencyKm;
    private String plate;

    public Vehicle(String brand, String model, String type, double tareWeight, double grossWeight, String registerDate, String acquisitionDate, double maintenanceFrequencyKm, String plate) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.tareWeight = tareWeight;
        this.grossWeight = grossWeight;
        this.currentKm = currentKm;
        this.registerDate = registerDate;
        this.acquisitionDate = acquisitionDate;
        this.maintenanceFrequencyKm = maintenanceFrequencyKm;
        this.plate = plate;
    }

    public double getCurrentKm() {
        return currentKm;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public double getTareWeight() {
        return tareWeight;
    }

    public double getMaintenanceFrequencyKm() {
        return maintenanceFrequencyKm;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String getPlate() {
        return plate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCurrentKm(double currentKm) {
        this.currentKm = currentKm;
    }

    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public void setMaintenanceFrequencyKm(double maintenanceFrequencyKm) {
        this.maintenanceFrequencyKm = maintenanceFrequencyKm;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public void setTareWeight(double tareWeight) {
        this.tareWeight = tareWeight;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
