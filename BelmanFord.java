import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class BelmanFord {
    public static void main(String[] args) throws IOException {
        try {
            File file = new File("D:\\IDEA\\qq\\ForTable.txt");
            FileWriter writer = new FileWriter("D:\\IDEA\\qq\\result.txt");

            try {
                PrintWriter pw = new PrintWriter(file);
                pw.close();
            } catch (IOException var17) {
                var17.printStackTrace();
            }

            for (int i = 1; i <= 50; ++i) {
                BufferedReader reader = new BufferedReader(new FileReader("D:\\IDEA\\qq\\test" + i + ".txt"));
                ArrayList<String> list = new ArrayList();
                String line;
                String[] str;
                int start = 0;
                int vert = 0;
                int rebro = 0;
                while ((line = reader.readLine()) != null) {
                    if (line.toLowerCase().startsWith("dataset")) {
                        str = line.split(":");
                        start = Integer.parseInt(str[2]);
                        vert = Integer.parseInt(str[1]);
                        rebro = Integer.parseInt(str[3]);
                    } else {
                        list.add(line);
                    }

                }

                ArrayList<Double> ls = BelmanFord1(list, start, vert, rebro);
                int r = 0;
                writer.write("Test " + i + "\n");
                if (ls != null) {
                    writer.write("from vert " + start + "\n");
                    for (double d : ls) {
                        int t = (int) d;

                        writer.write(r + ":" + t + "\n");
                        r++;
                    }
                }else {
                    writer.write("negative cycle");
                }
            }

            writer.close();
        } catch (IOException var18) {
            System.out.println(var18);
        }

    }

    public static ArrayList<Double> BelmanFord1(ArrayList<String> list, int start, int vert, int rebro) throws IOException {
        int k = 0;
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("D:\\IDEA\\qq\\ForTable.txt", true));
        long startTime = System.currentTimeMillis();
        double inf = Double.POSITIVE_INFINITY;
        ArrayList<Double> tree = new ArrayList(vert);

        for (int i = 0; i < vert; i++) {
            tree.add(inf);
        }

        tree.set(start, 0.0);

        for (int i = 0; i < vert - 1; i++) {
            for (String str : list) {
                int[] dis = split1(str);
                if (tree.get(dis[0]) != inf && tree.get(dis[0]) + dis[2] < tree.get(dis[1])) {
                    double t = tree.get(dis[0]) + dis[2];
                    tree.set(dis[1], t);
                }
                k++;
            }
        }

        boolean negative = false;
        for (String str : list) {
            int[] dis = split1(str);
            if (tree.get(dis[0]) != inf && tree.get(dis[0]) + dis[2] < tree.get((dis[1]))) {
                negative = true;
            }

        }

        long finishTime = System.currentTimeMillis();
        long time = finishTime - startTime;

        if (!negative) {
            fileWriter.write(vert + "\t" + rebro + "\t" + k + "\t" + time + "\n");
            fileWriter.close();
            return tree;

        } else {
            fileWriter.write("negative cycle");
            fileWriter.close();
            return null;
        }
    }

    public static int[] split1(String str) {
        String[] name = str.split(":");
        int[] split = new int[]{Integer.parseInt(name[0]), Integer.parseInt(name[1]), Integer.parseInt(name[2])};
        return split;
    }
}
