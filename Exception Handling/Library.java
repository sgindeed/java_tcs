import java.util.*;

class Book{
    private int id;
    private String title;
    private int pages;
    private double price;

    public Book(int id, String title, int pages, double price){
        this.id = id;
        this.title = title;
        this.pages = pages;
        this.price = price;
    }

    public int getid(){
        return id;
    }
    public String gettitle(){
        return title;
    }
    public int getpages(){
        return pages;
    }
    public double getprice(){
        return price;
    }
}

class Room{
    private int rid;
    private String rname;
    private int numbook;
    private ArrayList<Book> booklist;

    public Room(int rid, String rname, int numbook, ArrayList<Book> booklist){
        this.rid = rid;
        this.rname = rname;
        this.numbook = numbook;
        this.booklist = booklist;
    }

    public int getrid(){
        return rid;
    }
    public String getrname(){
        return rname;
    }
    public int getnumbook(){
        return numbook;
    }
    public ArrayList<Book> getbooklist(){
        return booklist;
    }
}

class InvalidBookPriceException extends Exception{
    public InvalidBookPriceException(String message){
        super(message);
    }
}

class Service{
    public void calculateAverageBookPriceByLibraryName(ArrayList<Room>liblist, String findname){
        ArrayList<Book> booklist = new ArrayList<>();

        for(Room room : liblist){
            if(room.getrname().equals(findname)){
                booklist = room.getbooklist();
                break;
            }
        }

        if(booklist.size() == 0){
            System.out.println("No books available in the given library.");
            return;
        }

        double pricesum = 0;
        int count = 0;

        try{
            for(Book book : booklist){
                if(book.getprice() < 0)
                    throw new InvalidBookPriceException("Invalid Price: Book price cannot be negative");
                pricesum += book.getprice();
                count++;
            }
                System.out.println("Average price of books in " + findname + " is: " + (pricesum/count));
        }
        catch(InvalidBookPriceException e){
            System.out.println(e.getMessage());
        }
    }

    public void findBookWithMaximumPages(ArrayList<Room> liblist){
        Book maxpage = null;
        for(Room room : liblist){
            for(Book book : room.getbooklist()){
                if(maxpage == null || book.getpages() > maxpage.getpages())
                    maxpage = book;
            }
        }
        if(maxpage != null)
            System.out.println("Title: " + maxpage.gettitle() + ", Pages: " + maxpage.getpages());
    }
}

class Library{
    public static void main(String[] args){
        Scanner se = new Scanner(System.in);
        ArrayList<Room> liblist = new ArrayList<>();
        int n = se.nextInt();

        for(int i=0; i<n; i++){
            int rid = se.nextInt();
            se.nextLine();
            String rname = se.nextLine();
            int numbook = se.nextInt();
            ArrayList<Book> booklist = new ArrayList<>();

            for(int j=0; j<numbook; j++){
                int id = se.nextInt();
                se.nextLine();
                String title = se.nextLine();
                int pages = se.nextInt();
                double price = se.nextDouble();

                booklist.add(new Book(id, title, pages, price));
            }
            liblist.add(new Room(rid, rname, numbook, booklist));
        }
        se.nextLine();
        String findname = se.nextLine();

        Service service = new Service();
        service.calculateAverageBookPriceByLibraryName(liblist, findname);
        service.findBookWithMaximumPages(liblist);
    }
}







// 2
// 101
// Central Library
// 3
// 1001
// Java Programming
// 450
// 500.0
// 1002
// Python Basics
// 300
// 400.0
// 1003
// Data Structures
// 600
// 700.0
// 102
// City Library
// 2
// 2001
// Operating Systems
// 550
// 650.0
// 2002
// Computer Networks
// 480
// 600.0
// Central Library
