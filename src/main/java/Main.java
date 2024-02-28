import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //String goodsLine = "";
        double amountReceipt = 0;

        int pplCount = getPplCount(scanner);

        System.out.println("Итак вас было "+pplCount);

        //System.out.println("Сейчас сделаю побыстрому калькулятор..");
        //pause(1210);

        Calculator calc = new Calculator();
        //System.out.println("..готово!");
        System.out.println("Давай заполним список товаров. Аккуратно, не торопясь. ");

        StringBuilder goodsLineSB = new StringBuilder();
        goodsLineSB.append("--------------------------------------");
        goodsLineSB.append("\nИтоговый чек:\n");


        boolean firstItem = true;//при do while было циклическое исключение почему то
        while (true) {
            if (firstItem==false){
                System.out.println("Ещё добавим товар?");
                if (scanner.nextLine().equalsIgnoreCase("Завершить")){
                    break;
                }
            }else{
                firstItem = false;
            }

            ReceiptLine receiptLine = new ReceiptLine(scanner,calc);
            goodsLineSB.append("\n");
            goodsLineSB.append(receiptLine.name);
            goodsLineSB.append(" - ");
            goodsLineSB.append(calc.formatSum(receiptLine.price));

            amountReceipt = amountReceipt + calc.conversionSum(receiptLine.price);
        }
        //System.out.println("let me think...");
        //pause(1200);

        goodsLineSB.append("\n\nИТОГО: ");
        goodsLineSB.append(calc.formatSum(amountReceipt));
        goodsLineSB.append("\n--------------------------------------");
        double totalPart = calc.calculate(pplCount,amountReceipt);
        goodsLineSB.append("\n\nНапомню, что делим на ");
        goodsLineSB.append(pplCount);
        goodsLineSB.append("\nЗначит с каждого ");
        goodsLineSB.append(calc.formatSum(totalPart));

        double control = calc.conversionSum(totalPart*pplCount);
        if (!(control == amountReceipt)){
            double diff = amountReceipt - control;
            if (diff<0) {
                goodsLineSB.append("\nКому-то можно будет заплатить на ");
                goodsLineSB.append(calc.formatSum(diff));
                goodsLineSB.append(" меньше");
            }else{
                goodsLineSB.append("\nКому-то нужно будет заплатить на ");
                goodsLineSB.append(calc.formatSum(diff));
                goodsLineSB.append(" больше");
            }
            //goodsLineSB.append("Что поделаешь - округление *пожимает плечами*");
        }

        System.out.println(goodsLineSB);
    }

    static int getPplCount(Scanner scanner){
        int pplCount=0;
        System.out.println("Привет. Это твой \"калькулятор разделения счёта на всех\". На сколько человек сегодня делим счёт?");
        do {
            try{
                pplCount = Integer.valueOf(scanner.nextLine());

                if (pplCount==1) {
                    System.out.println("Для одного человека делить счёт смысла нет :) всё же сколько вас было?");
                }else if (pplCount<=1) {
                    System.out.println("" + pplCount + " ? серьёзно? Мне кажется я знаю список товаров;) Давай всё же по новой. Сколько вас было ?!");
                }

            } catch (Exception e) {
                if (!e.getMessage().equalsIgnoreCase("For input string: \"\"")){
                    System.out.println("Что-то пошло не так, давай ещё раз попробуем =/ Нужно целое число");
                }
            }

        }while (pplCount<=1);
        return pplCount;
    }


}