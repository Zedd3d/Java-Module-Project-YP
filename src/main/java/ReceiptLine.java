import java.util.Scanner;

public class ReceiptLine {
    String name;
    Double price;

    ReceiptLine(Scanner scanner){
        int numberOfTry = 0;
        do {
            numberOfTry ++;
            if (numberOfTry==1){
                System.out.println("Введи название товара:");
            }else {
                System.out.println("Попытка "+numberOfTry+". Введи название товара:");
            }

            name = scanner.next();
            if (name.isEmpty()){
                System.out.println("Если названия нет, можно поставить прочерк! :)");
            }

        } while (name.isEmpty());

        numberOfTry = 0;

        double currentPrice;
        do {
            numberOfTry ++;
            if (numberOfTry==1){
                System.out.println("Введи цену товара:");
            }else {
                System.out.println("Попытка "+numberOfTry+". Введи цену товара:");
            }

            try {
                price = Double.valueOf(scanner.next());
            }catch (Exception e){
                price = 0.0;
                System.out.println("Всё же нужно положительное число. Если необходимо ввести дробную часть, то можно использовать точку. Например \"10.45\"");
            }


        } while (!(price>0));
    }

}
