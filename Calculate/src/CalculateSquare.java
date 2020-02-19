import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

public class CalculateSquare {
    public static void main(String[] args) {
        BufferedReader reader = null;
        String line = null;
        Double sum =  0.0;
        try{
             reader = new BufferedReader(new FileReader("/Users/meiyukai/Downloads/calculate.txt"));
            while(( line  = reader.readLine())!=null){
                System.out.println("line :  " + line);
                Double[] num = splitString(line);
                sum+=multiplyNumbers(num[0] , num[1]);
            }
        }catch(Exception e){

            e.printStackTrace();
            try{
                reader.close();
            }catch (Exception e2){
                e2.printStackTrace();
            }

        }finally {
            System.out.println("sum is  :  " +  sum);
        }
    }

    public static Double multiplyNumbers(Double a , Double b){
        return a*b;
    }


    public static  Double[] splitString(String line){
        Double [] numbers =  new Double[2];
        String[] result = line.split(",");
        numbers[0] = Double.valueOf(result[0]);
        numbers[1] =  Double.valueOf(result[1]);
        return numbers;
    }





}
