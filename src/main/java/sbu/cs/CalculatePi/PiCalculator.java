package sbu.cs.CalculatePi;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PiCalculator {


    public static class FieldCalculator implements Runnable
    {
        MathContext mc;
        BigDecimal x;
        int n;
        public FieldCalculator(int n)
        {
            mc = new MathContext(1001);
            this.n = n;
        }
        @Override
        public void run() {
            x = new BigDecimal("0.0625");
            x = x.pow(n,mc);

            BigDecimal eightK = new BigDecimal(n * 8);
            BigDecimal a1 = new BigDecimal("4");
            a1 = a1.divide(eightK.add(new BigDecimal("1")) , mc);

            BigDecimal a2 = new BigDecimal("-2");
            a2 = a2.divide(eightK.add(new BigDecimal("4")), mc);

            BigDecimal a3 = new BigDecimal("-1");
            a3 = a3.divide(eightK.add(new BigDecimal("5")), mc);

            BigDecimal a4 = new BigDecimal("-1");
            a4 = a4.divide(eightK.add(new BigDecimal("6")), mc);

            BigDecimal aSum = a1.add(a2,mc).add(a3,mc).add(a4,mc);
            x = x.multiply(aSum, mc);

            addTouSum(x);
        }


    }


    public static synchronized void addTouSum(BigDecimal value) {
        sum = sum.add(value);
    }
    public String calculate(int floatingPoint)
    {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        sum = new BigDecimal(0);
        for (int i = 0; i < 1000; i++) {
            FieldCalculator task = new FieldCalculator(i);
            threadPool.execute(task);
        }

        threadPool.shutdown();

        System.out.println(sum);

        try {
            threadPool.awaitTermination(10000, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return sum.toString().substring(0, 2 + floatingPoint);
    }

    public static BigDecimal sum;

    public static void main(String[] args) {

    }
}
