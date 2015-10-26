package perceptron; /**
 * Created by didrikpa on 13.10.15.
 */
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Perceptron {
    DecimalFormat df = new DecimalFormat("#.#");
    public int neurons;
    public double learning;
    public double treshold;
    public ArrayList<Double> weights;
    public int counter;

    public Perceptron(int neurons, double learning){
        Random random = new Random();
        double holder = random.nextDouble();
        this.counter = 0;
        this.learning = 0.1;
        this.treshold = (double)Math.round((holder - 0.5)*10d)/10d;
        this.neurons = neurons;
        this.weights = new ArrayList<>();
        for (int i = 0; i < neurons; i++) {
            holder = random.nextDouble();
            weights.add((double)Math.round((holder - 0.5)*10d)/10d);
        }
        treshold = 0.2;
        weights.set(0, 0.3);
        weights.set(1, -0.1);



    }
    public double round(double doubl){
        return new BigDecimal(doubl).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public int activateAndTrain(Integer[] input,  int desiredOutput){
        System.out.println("x1 = "+input[0]+"\nx2 = " + input[1] + "\ndesired output = "+desiredOutput);
        System.out.println("w1 = "+weights.get(0)+"\nw2 = " +weights.get(1));
        System.out.println("treshold = "+treshold);
        double y = 0;
        int error = 0;
        for (int i = 0; i < neurons; i++) {
            y+=round(((input[i] * weights.get(i))));
        }
        y-=treshold;
        if(y>=treshold){
            y = 1;
        } else if (y < treshold){
            y = 0;
        }
        System.out.println("Actual output = " + y);

        if (y == desiredOutput){
            error = 0;
        } else if (y > desiredOutput){
            error = -1;
        } else if (y < desiredOutput){
            error = 1;
        }
        System.out.println("Error = " + error);
        for (int i = 0; i < neurons; i++) {
            weights.set(i, round(weights.get(i) + (learning * input[i] * error)));
        }
        System.out.println("Final weights = "+(weights)+"\n");

        return error;

    }

    public void perceptronAlgorithm(){
        boolean a = true;
        int counter = 1;
        while (a){
            System.out.println("Epoch = " + counter);
            int b = activateAndTrain(new Integer[]{0, 0}, 0);
            System.out.println("Epoch = " + counter);
            int c = activateAndTrain(new Integer[]{0, 1}, 1);
            System.out.println("Epoch = " + counter);
            int d = activateAndTrain(new Integer[]{1, 0}, 1);
            System.out.println("Epoch = " + counter);
            int e = activateAndTrain(new Integer[]{1, 1}, 1);
            counter+=1;
            if (b == 0 && c == 0 && d == 0 && e == 0){
                a = false;
            }
        }
    }

    public static void main(String[] args) {
        Perceptron perceptron = new Perceptron(2, 0.1);
        perceptron.perceptronAlgorithm();
    }

}
