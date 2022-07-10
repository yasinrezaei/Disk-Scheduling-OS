package Model;

import java.util.ArrayList;

public class DiscS {
    public String name;
    public int x;
    public int y;
    public String direction;


    public int z;
    public String method;
    public ArrayList<Integer> cylinders;
    public int time;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public DiscS(String name, int x, int y, String direction, int z, String method, ArrayList<Integer> cylinders) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.z = z;
        this.method = method;
        this.cylinders = cylinders;
    }

    @Override
    public String toString() {
        return "DS{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", direction='" + direction + '\'' +
                ", z=" + z +
                ", method='" + method + '\'' +
                ", cylinders=" + cylinders +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public ArrayList<Integer> getCylinders() {
        return cylinders;
    }

    public void setCylinders(ArrayList<Integer> cylinders) {
        this.cylinders = cylinders;
    }
}
