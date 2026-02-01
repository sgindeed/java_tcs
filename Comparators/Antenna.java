import java.util.*;

class Data{
    private int id;
    private String name;
    private String lead;
    private double vswr;

    public Data(int id, String name, String lead, double vswr){
        this.id = id;
        this.name = name;
        this.lead = lead;
        this.vswr = vswr;
    }

    public int getid(){
        return id;
    }
    public String getname(){
        return name;
    }
    public String getlead(){
        return lead;
    }
    public double getvswr(){
        return vswr;
    }
}


class SortByVSWR implements Comparator<Data>{
    public int compare(Data a1, Data a2){
        if(a1.getvswr() < a2.getvswr())
            return -1;
        if(a1.getvswr() > a2.getvswr())
            return 1;
        return 0;
    }
}



class Antenna{
    public static void searchAntennaByName(Data[] arr, String findname){
        int id = 0;
        for(Data data : arr){
            if(data.getname().equals(findname))
                id = data.getid();
        }
        if(id == 0)
            System.out.println("There is no antenna with the given parameter");
        else
            System.out.println(id);
    }

    public static void sortAntennaByVSWR(Data[] arr, double findvalue){
        ArrayList<Data> list = new ArrayList<>();
        for(Data data : arr){
            if(data.getvswr() <= findvalue)
                list.add(data);
        }
        if(list.size() == 0)
            System.out.println("No antenna found");

        Comparator<Data> comp = new SortByVSWR();
        Collections.sort(list, comp);

        for(int i=0; i<list.size(); i++)
            System.out.println(list.get(i).getlead());
    }

    public static void main(String[] args){
        Scanner se = new Scanner(System.in);
        Data[] arr = new Data[4];
        for(int i=0; i<4; i++){
            int id = se.nextInt();
            se.nextLine();
            String name = se.nextLine();
            String lead = se.nextLine();
            double vswr = se.nextDouble();
            se.nextLine();

            arr[i] = new Data(id, name, lead, vswr);
        }
        String findname = se.nextLine();
        double findvalue = se.nextDouble();

        searchAntennaByName(arr, findname);
        sortAntennaByVSWR(arr, findvalue);
    }
}



// 111
// Reconfigurable
// Hema
// 0.4
// 222
// Wearable
// Kavya
// 0.9
// 333
// Microstrip
// Teju
// 0.3
// 444
// Dielectric
// Sai
// 0.65
// Microstrip
// 0.5
