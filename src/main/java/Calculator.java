public class Calculator {
    public double calculate(int pplCount,double amountReceipt){
        double result = amountReceipt/(double) pplCount;

        return mathFloor(result);
    }

    public double conversionSum(double price){
        return mathFloor(price);
    }

    double mathFloor(double sum){
        return Math.floor(100*sum)/100.0;
    }

    public String formatSum(double price){
        int partOfPrice = ((int) price) % 100;
        if (Double.compare(partOfPrice,14)>0) {
            partOfPrice = ((int) partOfPrice) % 10;
        }
        String nameRub = "";
        switch (partOfPrice){
            case 1:
                nameRub = "рубль";
                break;
            case 2:
            case 3:
            case 4:
                nameRub = "рубля";
                break;
            default:
                nameRub = "рублей";
        }
        return String.format("%.2f %s",price, nameRub);


    }

}
