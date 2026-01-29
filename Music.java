import java.util.*;

public class Music {

    private int plno;
    private String type;
    private int count;
    private double duration;

    public Music(int plno, String type, int count, double duration) {
        this.plno = plno;
        this.type = type;
        this.count = count;
        this.duration = duration;
    }

    // Getters and Setters
    public int getPlno() {
        return plno;
    }

    public void setPlno(int plno) {
        this.plno = plno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    // Static method to find average count based on a threshold
    public static double findavgofcount(Music[] arr, int findcount) {
        ArrayList<Music> list = new ArrayList<>();
        for (Music m : arr) {
            if (m.getCount() >= findcount) {
                list.add(m);
            }
        }

        int count = 0;
        int sum = 0;
        for (Music m : list) {
            count++;
            sum += m.getCount();
        }

        if (count > 0) {
            return (double) sum / count;
        } else {
            return 0;
        }
    }

    // Static method to sort and filter types by duration
    public static Music[] sortTypeByDuration(Music[] arr, double dur) {
        ArrayList<Music> list = new ArrayList<>();
        for (Music m : arr) {
            if (m.getDuration() > dur) {
                list.add(m);
            }
        }

        if (list.isEmpty()) {
            return null;
        }

        Music[] res = new Music[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        Comparator<Music> comp = new SortByDuration();
        Arrays.sort(res, comp);
        return res;
    }

    public static void main(String[] args) {
        Scanner se = new Scanner(System.in);
        Music[] arr = new Music[4];

        for (int i = 0; i < 4; i++) {
            int plno = se.nextInt();
            se.nextLine(); // consume newline
            String type = se.nextLine();
            int count = se.nextInt();
            se.nextLine(); // consume newline
            double duration = se.nextDouble();
            
            arr[i] = new Music(plno, type, count, duration);
        }

        int findcount = se.nextInt();
        double dur = se.nextDouble();

        double res1 = findavgofcount(arr, findcount);
        Music[] res2 = sortTypeByDuration(arr, dur);

        if (res1 > 0) {
            System.out.println(res1);
        }

        if (res2 != null && res2.length != 0) {
            for (Music m : res2) {
                System.out.println(m.getType());
            }
        }
    }
}

// Comparator class for sorting by duration
class SortByDuration implements Comparator<Music> {
    public int compare(Music a, Music b) {
        if (a.getDuration() < b.getDuration()) {
            return -1;
        } else if (a.getDuration() > b.getDuration()) {
            return 1;
        } else {
            return 0;
        }
    }
}