import java.util.*;
import java.util.stream.*;

class Data {
    private int id;
    private String name;
    private String lead;
    private double vswr;

    public Data(int id, String name, String lead, double vswr) {
        this.id = id;
        this.name = name;
        this.lead = lead;
        this.vswr = vswr;
    }

    public int getid() {
        return id;
    }

    public String getname() {
        return name;
    }

    public String getlead() {
        return lead;
    }

    public double getvswr() {
        return vswr;
    }
}

class Antenna {

    // ✅ Search Antenna By Name
    public static void searchAntennaByName(Data[] arr, String findname) {

        int id = 0;

        for (Data data : arr) {
            if (data.getname().equals(findname)) {
                id = data.getid();
            }
        }

        if (id == 0)
            System.out.println("There is no antenna with the given parameter");
        else
            System.out.println(id);
    }

    // ✅ Sort Antenna By VSWR using Stream API
    public static void sortAntennaByVSWR(Data[] arr, double findvalue) {

        List<Data> sortedList = Arrays.stream(arr)
                .filter(data -> data.getvswr() <= findvalue) // filter condition
                .sorted(Comparator.comparingDouble(Data::getvswr)) // sort by VSWR
                .collect(Collectors.toList()); // collect result into list

        if (sortedList.size() == 0) {
            System.out.println("No antenna found");
            return;
        }

        // Print lead values after sorting
        sortedList.forEach(data -> System.out.println(data.getlead()));
    }

    // ✅ Main Method
    public static void main(String[] args) {

        Scanner se = new Scanner(System.in);

        Data[] arr = new Data[4];

        // Input 4 antenna objects
        for (int i = 0; i < 4; i++) {

            int id = se.nextInt();
            se.nextLine();

            String name = se.nextLine();
            String lead = se.nextLine();

            double vswr = se.nextDouble();
            se.nextLine();

            arr[i] = new Data(id, name, lead, vswr);
        }

        // Input search name and VSWR limit
        String findname = se.nextLine();
        double findvalue = se.nextDouble();

        // Method Calls
        searchAntennaByName(arr, findname);
        sortAntennaByVSWR(arr, findvalue);
    }
}
