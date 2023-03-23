import com.google.gson.Gson;

import java.io.*;
import java.util.Scanner;

public class Basket implements Serializable {
    private String[] titles;
    private int[] price;
    private int[] counts;

    public Basket() {
        this.titles = titles;
        this.price = price;
        this.counts = new int[titles.length];
    }
    public void saveJson (File file) throws IOException{
        try (PrintWriter out = new PrintWriter(file)){
            Gson gson = new Gson();
            String jsonText = gson.toJson(this);
            out.println(jsonText);
        }
    }

    public static Basket loadFromJson  (File file) throws IOException{
        try (Scanner scanner = new Scanner(file)){
            Gson gson = new Gson();
            String json = scanner.nextLine();
            Basket basket = gson.fromJson(json, Basket.class);
            return basket;

        }
    }
    public void saveBin(File file) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(this);
        }
    }
    public static Basket  loadFromBin (File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            return   (Basket) in.readObject();
        }

    }
    public void saveTxt (File file) throws IOException {
        try (PrintWriter out = new PrintWriter(file)) {
            for (int i = 0; i < titles.length; i++) {
                out.println(titles.length);
                out.println(titles[i] + "\t" + price[i] + "\t" + counts[i]);
                
            }
        }
    }
    public static Basket loadFromTxt (File file) throws IOException {
        try (Scanner scanner = new Scanner(file)) {
            int size = Integer.parseInt(scanner.nextLine());
            String [] titles = new String[size];
            int[] prices = new int[size];
            int[] counts =  new int[size];

            for (int i = 0; i < size; i++) {
                String[] parts = scanner.nextLine().split("\t");
                titles[i] = parts [0];
                prices[i] = Integer.parseInt(parts[1]);
                counts[i] = Integer.parseInt(parts[2]);
            }
            Basket basket = new Basket();
            basket.titles = titles;
            basket.price = prices;
            basket.counts = counts;
            return basket;
        }


    }

}

