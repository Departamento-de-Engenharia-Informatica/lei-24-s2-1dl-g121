package pt.ipp.isep.dei.esoft.project.DTOs;

public class Vehicles {
    private String brand;
    private String model;
    private String type;
    private double tare;
    private double grossWeight;
    private double currentKm;
    private int registerDate;
    private String acquisitionDate;
    private int maintenance;

    public Vehicles(String brand, String model, String type, double tare, double grossWeight, double currentKm, int registerDate, String acquisitionDate, int maintenance){
        this.brand=brand;
        this.model=model;
        this.type=type;
        this.tare=tare;
        this.grossWeight=grossWeight;
        this.currentKm=currentKm;
        this.registerDate=registerDate;
        this.acquisitionDate=acquisitionDate;
        this.maintenance=maintenance;
    }

    public double getCurrentKm() {
        return currentKm;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public double getTare() {
        return tare;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public int getRegisterDate() {
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

    public void setMaintenance(int maintenance) {
        this.maintenance = maintenance;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setRegisterDate(int registerDate) {
        this.registerDate = registerDate;
    }

    public void setTare(double tare) {
        this.tare = tare;
    }

    public void setType(String type) {
        this.type = type;
    }
}
