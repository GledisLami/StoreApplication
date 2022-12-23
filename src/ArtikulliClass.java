import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class ArtikulliClass {
    List<Artikulli> artikulliList = new ArrayList<>();
    List<Artikulli> artikujFatura = new ArrayList<>();
    List<Klienti> klientiList = new ArrayList<>();
    File klientetFile = new File("klientet.txt");//krijojme obj te file

    File artikujtFile = new File("artikujt.txt");
    Scanner readKlientet = new Scanner(klientetFile);//obj per reader

    Scanner readArtikujt = new Scanner(artikujtFile);

    int i;

    public ArtikulliClass() throws FileNotFoundException {
    }

    public void lexoKlientetFile() {
        while (readKlientet.hasNextLine()) {
            Klienti klienti = new Klienti();
            klientiList.add(klienti);
            String emri = readKlientet.next();
            klienti.setName(emri);
            int timesBought = Integer.parseInt(readKlientet.next());
            klienti.setTimesBought(timesBought);
            int moneySpent = Integer.parseInt(readKlientet.next());
            klienti.setMoneySpent(moneySpent);
        }
    }
    public void updateKlientetFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(klientetFile);
        //writer print ne ciklin e fundit pasi nese behet println le nje rresht bosh dhe jep exception
        //noSuchElementFound
        //per aq kliente sa kemi, rishkruajme file me te dhenat perkatese
        for (i = 0; i< klientiList.size();i++){
            if (i == klientiList.size()-1){//nese jemi ne indexin e fundit bejme print jo println
                writer.print(klientiList.get(i).getName()+" "+klientiList.get(i).getTimesBought() + " " +
                        klientiList.get(i).getMoneySpent());
                break;
            }
            writer.println(klientiList.get(i).getName()+" "+klientiList.get(i).getTimesBought() + " " +
                    klientiList.get(i).getMoneySpent());
        }
        writer.close();
    }

    public void lexoArtikujFile(){
        while (readArtikujt.hasNextLine()) {
            Artikulli art = new Artikulli();
            artikulliList.add(art);
            String emri = readArtikujt.next();
            art.setName(emri);
            int id = Integer.parseInt(readArtikujt.next());
            art.setId(id);
            int price = Integer.parseInt(readArtikujt.next());
            art.setPrice(price);
            int stock = Integer.parseInt(readArtikujt.next());
            art.setStock(stock);
        }
    }

    public void updateArtikujFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(artikujtFile);
        for (i = 0; i < artikulliList.size(); i++){
            if (i == artikulliList.size()-1){
                writer.print(artikulliList.get(i).getName() + " " + artikulliList.get(i).getId() + " " +
                        artikulliList.get(i).getPrice() + " " + artikulliList.get(i).getStock());
                break;
            }
            writer.println(artikulliList.get(i).getName() + " " + artikulliList.get(i).getId() + " " +
                    artikulliList.get(i).getPrice() + " " + artikulliList.get(i).getStock());
        }
        writer.close();
    }

    public void menu() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        lexoArtikujFile();
        lexoKlientetFile();
        int ch;
        do {
            System.out.println("Opsionet e programit :\n1-Rregjistrim artikulli\n2-Rregjistrim klienti\n" +
                    "3-Furnizim (hyrje ne dyqan)\n4-Shitje (dalje nga dyqani)\n5-Gjendja ne magazine\n" +
                    "6-Klientet qe blejne me shume (10 te paret ne vlere)\n" +
                    "7-Klientet qe blejne me shpesh (20 te paret)\n" +
                    "8-Kerkim artikulli sipas kodit (barcode)\n" +
                    "9-Dalje nga programi\n" +
                    "Opsioni qe do te zgjidhni: ");
            ch = 0;
            ch = exception(sc, ch);
            switch (ch){
                case 1:{
                    rregjistroArtikull(sc);
                    updateArtikujFile();
                    break;
                }
                case 2:{
                    rregjistroKlient(sc);
                    updateKlientetFile();
                    break;
                }
                case 3:{
                    furnizim(sc);
                    updateArtikujFile();
                    break;
                }
                case 4:{
                    shitje(sc);
                    updateArtikujFile();
                    break;
                }
                case 5:{
                    shfaqArtikujt();
                    break;
                }
                case 6:{
                    top10Klientet();
                    break;
                }
                case 7:{
                    top20Klientet();
                    break;
                }
                case 8: {
                    kerkoBarcode(sc);
                    break;
                }
                default:
                    System.out.println("Mirupafshim!");
            }
        }while (ch!=9);
        }

        public void rregjistroArtikull(Scanner sc){
            Artikulli artikulli = new Artikulli();
            System.out.println("Vendosni emrin e artikullit: ");
            artikulli.setName(sc.next());
            System.out.println("Vendosni ID te artikullit: ");
            artikulli.setId(exception(sc, sc.nextInt()));
            System.out.println("Vendosni cmimin e artikullit: ");
            artikulli.setPrice(exception(sc, sc.nextInt()));
            artikulliList.add(artikulli);
        }
    public void rregjistroKlient(Scanner sc){
        Klienti klienti = new Klienti();
        System.out.println("Vendosni emrin e klientit: ");
        klienti.setName(sc.next());
        klientiList.add(klienti);
        }

    public void shfaqArtikujt(){ //gjendja ne magazine
            for (i = 0; i< artikulliList.size();i++){
                System.out.println(artikulliList.get(i).toString());
            }
    }

    public void shfaqKlientet(){
        for (i = 0; i< klientiList.size();i++){
            System.out.println(klientiList.get(i).getName());
        }
    }

    public void furnizim(Scanner sc){
        System.out.println("Fusni emrin e artikullit qe doni te furnizoni: ");
        String artName = sc.next();
        boolean found = false;
        for (i = 0;i< artikulliList.size();i++){
            if (artName.equalsIgnoreCase(artikulliList.get(i).getName())){
                System.out.println(artikulliList.get(i).toString());
                System.out.println("Sa sasi do te shtoni?");
                artikulliList.get(i).setStock(artikulliList.get(i).getStock() + exception(sc, sc.nextInt()));
                found = true;
                break;
            }
        }
        if (!found){
            System.out.println("Artikulli nuk u gjet!");
        }
    }

    public int cmimArtikull(int quantity, int price){
        return quantity*price;
    }

    public int shumaFatures(){
        int total = 0;
        for (i = 0; i< artikujFatura.size();i++){
            total += cmimArtikull(artikujFatura.get(i).getQuantity(), artikujFatura.get(i).getPrice());
        }
        return total;
    }

    public void shitje(Scanner sc){
        System.out.println("Cili klient deshiron te bleje? \n");
        shfaqKlientet();
        int klientiIndex = 0;
        String name = sc.next();
        boolean foundKlienti = false;
        for (i = 0; i< klientiList.size();i++){
            if (name.equalsIgnoreCase(klientiList.get(i).getName())){
                klientiIndex = i;
                foundKlienti = true;
                break;
            }
        }
        if (!foundKlienti){
            System.out.println("Klienti nuk u gjet");
            return;
        }
        char ch;
        do {
            System.out.println("Zgjidhni artikullin qe do te blini\n");
            shfaqArtikujt();
            String artName = sc.next();
            boolean found = false;
            for (i = 0;i< artikulliList.size();i++){
                if (artName.equalsIgnoreCase(artikulliList.get(i).getName())){
                    System.out.println(artikulliList.get(i).toString());
                    int sasia;
                    do {
                        System.out.println("Sa cope do te blini??");
                        sasia = 0;
                        sasia = exception(sc, sasia);
                    }while (sasia<=0);
                    //check sasia me qty
                    if (sasia>artikulliList.get(i).getStock()){
                        System.out.println("Nuk ka mjaftueshem sasi");
                        break;
                    }else {
                        artikulliList.get(i).setQuantity(sasia);
                        artikulliList.get(i).setStock(artikulliList.get(i).getStock()-artikulliList.get(i).getQuantity());
                        //duhet ruajtur sasia e kerkuar nga perdoruesi pasi krahasohet, bejme cmim dhe e cojme 0
                        artikujFatura.add(artikulliList.get(i));
                    }
                    klientiList.get(klientiIndex).setTimesBought(klientiList.get(klientiIndex).getTimesBought()+1);
                    klientiList.get(klientiIndex).setMoneySpent(klientiList.get(klientiIndex).getMoneySpent()+
                            cmimArtikull(artikulliList.get(i).getQuantity(), artikulliList.get(i).getPrice()));
                    found = true;
                    //artikulliList.get(i).setQuantity(0);
                    break;
                }
            }
            if (!found){
                System.out.println("Artikulli nuk u gjet!");
            }
            do {
                System.out.println("Deshironi te shtoni artikull tjeter ne fature?Y/N ");
                ch = sc.next().charAt(0);
            }while (ch != 'Y' && ch!='N' && ch != 'y' && ch!='n');

        }while (ch!='n' && ch!='N');
        printoFature();
        for (i = 0;i<artikulliList.size();i++){
            artikulliList.get(i).setQuantity(0);
        }
    }

    public void kerkoBarcode(Scanner sc){
        System.out.println("ID qe do te kerkoni:");
        int id;
        id = exception(sc, sc.nextInt());
        boolean found = false;
        for (i = 0; i< artikulliList.size();i++){
            if (id == artikulliList.get(i).getId()){
                System.out.println(artikulliList.toString());
                found = true;
                break;
            }
        }
        if (!found){
            System.out.println("Artikulli nuk u gjet");
        }
    }

    public void printoFature(){
        System.out.println("Artikujt | Sasia | Cmimi i produktit");
            for (int i = 0; i < artikujFatura.size();i++){
                System.out.printf("%7s %7s %7s",artikujFatura.get(i).getName(),
                        artikujFatura.get(i).getQuantity(), cmimArtikull(artikujFatura.get(i).getQuantity(), artikujFatura.get(i).getPrice()));
                System.out.println();
            }
        System.out.println("\nShuma e fatures ne lek: " + shumaFatures());
    }

    public void top10Klientet(){
        for (i = 0; i< klientiList.size();i++){
            //sort ne baze te moneySpent
            for (int j = 0; j< klientiList.size();j++){
                if (klientiList.get(i).getMoneySpent()<klientiList.get(j).getMoneySpent()){
                    Collections.swap(klientiList, i, j);
                }
            }
        }
        if (klientiList.size()>=10){
            for (i = 0; i<10;i++){
                System.out.println((i+1) + ". "+ klientiList.get(i).getName() + klientiList.get(i).getMoneySpent()
                        + "leke te harxhuara");
            }
        }else
            for (i = 0; i< klientiList.size();i++){
                System.out.println((i+1) + ". "+ klientiList.get(i).getName() + klientiList.get(i).getMoneySpent()
                        + "leke te harxhuara");
            }
    }

    public void top20Klientet(){
        for (i = 0; i< klientiList.size();i++){
            //sort ne baze te moneySpent
            for (int j = 0; j< klientiList.size();j++){
                if (klientiList.get(i).getTimesBought()<klientiList.get(j).getTimesBought()){
                    Collections.swap(klientiList, i, j);
                }
            }
        }
        if (klientiList.size()>=20){
            for (i = 0; i<10;i++){
                System.out.println((i+1)+". " +klientiList.get(i).getName()  + klientiList.get(i).getTimesBought()
                        + "here qe ka blere");
            }
        }else
            for (i = 0; i< klientiList.size();i++){
                System.out.println((i+1) + ". " + klientiList.get(i).getName()  + klientiList.get(i).getMoneySpent()
                        + "here qe ka blere");
            }
    }
    public static int exception(Scanner sc, int input){
        boolean Error = false;
        while (!Error){
            try {
                input = sc.nextInt();
                Error=true;
            } catch (InputMismatchException ime){
                System.err.println("Ju lutem fusni nje numer");
                sc.nextLine();// this consumes the invalid token
            }
        }
        return input;
    }
}