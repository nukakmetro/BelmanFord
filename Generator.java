import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Generator {

    public static void main(String[] args) throws IOException {
        int test = 1;
        int s = 5;
        int f = 8;
        int e = 0;

        for(int q = 1; q <= 50; ++q) {
            File file = new File("D:\\IDEA\\qq\\test" + q + ".txt");
            Random random = new Random();
            ArrayList<String> list = new ArrayList();
            FileWriter writer = new FileWriter(file);

            for(int c = 1; c <= test; ++c) {
                int m = random.nextInt(s, f);
                int n = random.nextInt(0, m);
                list.add("DataSet:" + m + ":" + n);

                for(int i = 0; i < m; ++i) {
                    for(int j = 0; j < m; ++j) {
                        int r = random.nextInt(5);
                        int k = random.nextInt(5);
                        if (k != 0 && i != j && r < 4) {
                            list.add("" + i + ":" + j + ":" + k);
                            ++e;
                        }
                    }
                }

                Iterator var17 = list.iterator();

                while(var17.hasNext()) {
                    String x = (String)var17.next();
                    if (x.toLowerCase().startsWith("dataset")) {
                        writer.write(x + ":" + e + "\n");
                    } else {
                        writer.write(x + "\n");
                    }
                }
            }

            s += 4;
            f += 4;
            writer.close();
        }

    }
}
