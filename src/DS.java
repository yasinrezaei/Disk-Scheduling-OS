import java.util.ArrayList;

public class DS {
    String name;
    int x;
    int y;
    String direction;
    int z;
    String method;
    ArrayList<Integer> cylinders;

    public DS(String name, int x, int y, String direction, int z, String method, ArrayList<Integer> cylinders) {
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
}
