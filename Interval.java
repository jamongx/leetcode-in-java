public class Interval {
    public int start;
    public int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append("[");
        str.append(start);
        str.append(",");
        str.append(end);
        str.append("]");
        return str.toString();
    }
}