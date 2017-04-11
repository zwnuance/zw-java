import java.util.Random;

public class Case1 {
    public static void main(String []args) throws Exception {
        Random random = new Random();

        CaseObject obj = new CaseObject();
        boolean result = true;

        while(result) {
            result = obj.execute(random.nextInt(1000));
            Thread.sleep(1000);
        }
    }
}
