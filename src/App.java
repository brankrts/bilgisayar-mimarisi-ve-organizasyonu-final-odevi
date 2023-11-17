import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import registers.IR;
import registers.SC;

public class App {

    public static void main(String[] args) throws Exception {
        run("RAM.txt");
    }

    public static void run(String fileName) throws FileNotFoundException, InterruptedException {
        File file = new File(fileName);
        SC sc = SC.instance();
        try (Scanner scanner = new Scanner(file)) {
            System.out.println("RAM.txt dosyası okundu.");
            while (scanner.hasNextLine()) {
                IR ir = new IR(scanner.nextLine());
                ir.executeCommand();
                String initialCommand = ir.getFinalCommand().toString();
                if (ir.getFinalCommand().toString().endsWith("I") && !ir.getFinalCommand().toString().equals("SKI")) {
                    initialCommand = ir.getFinalCommand().toString().substring(0, initialCommand.length() - 1);
                }
                System.out.println(
                        sc.getSignal().getName().replace("D", "T") + " zamanında I = " + ir.getI()
                                + " " + ir.getSelectedSignal().getName() + " aktif IR(11-0) = " + ir.getAddress()
                                + " buyruk = " + initialCommand);
                Thread.sleep(1000);
                sc.incrementSC();
            }
        }

    }

}
