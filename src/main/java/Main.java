import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pplCount = 0;
        String goodsLine = "";
        double amountReceipt = 0;

        System.out.println("Привет. Это твой \"калькулятор разделения счёта на всех\". На сколько человек сегодня делим счёт?");
        do {
            try{
                pplCount = Integer.valueOf(scanner.next());

                if (pplCount==1) {
                    System.out.println("Для одного человека делить счёт смысла нет :) всё же сколько вас было?");
                }else if (pplCount<=1) {
                    System.out.println("" + pplCount + " ? серьёзно? Мне кажется я знаю список товаров;) Давай всё же по новой. Сколько вас было ?!");
                }

            } catch (Exception e){
                System.out.println("Что-то пошло не так, давай ещё раз попробуем =/ Нужно целое число");
            }

        }while (!(pplCount>1));

        System.out.println("Итак вас было "+pplCount);

        System.out.println("Сейчас сделаю побыстрому калькулятор..");
        pause(1210);

        Calculator calc = new Calculator();
        System.out.println("..готово!");
        System.out.println("Давай заполним список товаров. Аккуратно, не торопясь. ");

        while (true) {
            if (!goodsLine.isEmpty()){
                System.out.println("Ещё добавим товар?");
                if (scanner.next().equalsIgnoreCase("Завершить")){
                    break;
                }
            }

            ReceiptLine receiptLine = new ReceiptLine(scanner);

            goodsLine = goodsLine + (goodsLine.isEmpty() ? "" : "\n") + receiptLine.name + " - " +calc.formatSum(receiptLine.price);
            amountReceipt = amountReceipt + calc.conversionSum(receiptLine.price);
        }
        System.out.println("let me think...");
        pause(1200);
        System.out.println("--------------------------------------");
        System.out.println("Итоговый чек:\n\n"+goodsLine);
        System.out.println("\nИТОГО: "+calc.formatSum(amountReceipt));
        System.out.println("--------------------------------------");
        double totalPart = calc.calculate(pplCount,amountReceipt);
        System.out.println("\nЗначит с каждого "+calc.formatSum(totalPart));

        double control = calc.conversionSum(totalPart*pplCount);
        if (!(control == amountReceipt)){
            double diff = amountReceipt - control;
            if (diff<0) {
                System.out.println("\nКому-то можно будет заплатить на " + calc.formatSum(diff) + " меньше");
            }else{
                System.out.println("\nКому-то нужно будет заплатить на " + calc.formatSum(diff) + " больше");
            }
            System.out.println("Что поделаешь - округление *пожимает плечами*");
        }


    }

    static void pause(int millS){
        try {
            Thread.sleep(millS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}