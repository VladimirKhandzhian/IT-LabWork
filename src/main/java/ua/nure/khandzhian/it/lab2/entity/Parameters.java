package ua.nure.khandzhian.it.lab2.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parameters", propOrder = {

})
public class Parameters {

    @XmlElement(name = "Diagonal")
    protected float diagonal;
    @XmlElement(name = "NumberOfCamera", required = true)
    protected int numberOfCamera;
    @XmlElement(name = "BatteryCapacity", required = true)
    protected BatteryCapacity batteryCapacity;

    public float getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(float value) {
        this.diagonal = value;
    }

    public int getNumberOfCamera() {
        return numberOfCamera;
    }

    public void setNumberOfCamera(int value) {
        this.numberOfCamera = value;
    }

    public BatteryCapacity getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(BatteryCapacity value) {
        this.batteryCapacity = value;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "Diagonal: " + diagonal +
                ", NumberOfCamera: " + numberOfCamera +
                ", BatteryCapacity: " + batteryCapacity +
                "}";
    }
}
