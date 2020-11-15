package ua.nure.khandzhian.tripiy.lab1.entity;

import java.util.Objects;

public class MaximumGeneralizedUtility {

    private int ID;
    private double mgu1;
    private double mgu2;
    private double mgu3;
    private double mgu4;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getMgu1() {
        return mgu1;
    }

    public void setMgu1(double mgu1) {
        this.mgu1 = mgu1;
    }

    public double getMgu2() {
        return mgu2;
    }

    public void setMgu2(double mgu2) {
        this.mgu2 = mgu2;
    }

    public double getMgu3() {
        return mgu3;
    }

    public void setMgu3(double mgu3) {
        this.mgu3 = mgu3;
    }

    public double getMgu4() {
        return mgu4;
    }

    public void setMgu4(double mgu4) {
        this.mgu4 = mgu4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MaximumGeneralizedUtility that = (MaximumGeneralizedUtility) o;
        return ID == that.ID &&
                Double.compare(that.mgu1, mgu1) == 0 &&
                Double.compare(that.mgu2, mgu2) == 0 &&
                Double.compare(that.mgu3, mgu3) == 0 &&
                Double.compare(that.mgu4, mgu4) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, mgu1, mgu2, mgu3, mgu4);
    }

    @Override
    public String toString() {
        return "MaximumGeneralizedUtility{" +
                "ID: " + ID +
                ", MGU1: " + mgu1 +
                ", MGU2: " + mgu2 +
                ", MGU3: " + mgu3 +
                ", MGU4: " + mgu4 +
                "}";
    }
}
