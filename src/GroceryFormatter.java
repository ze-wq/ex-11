import java.io.*;

public class GroceryFormatter {
    public static void main(String[] args) {
        String inputFilePath = "src/Groceries.txt";
        String outputFilePath = "FormattedGroceries.txt";
        double totalCost = 0.0;

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))
        ) {
            writer.write("***********************************************************\n");
            writer.write("ID Item Quantity(KG) Price(€)\n");
            writer.write("***********************************************************\n");

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length < 4) {
                        throw new IllegalArgumentException("数据格式错误: " + line);
                    }

                    int id = Integer.parseInt(parts[0]);
                    String item = parts[1].trim();
                    String quantity = parts[2];
                    double price = Double.parseDouble(parts[3]);

                    totalCost += price;

                    writer.write(String.format("%d %s %s %.2f%n", id, item, quantity, price));

                } catch (Exception e) {
                    String id = null;
                                        System.err.println("处理第 " + id + " 行时出错: " + e.getMessage()); // ✅ id已声明
                    continue;
                }
            }

            writer.write("***********************************************************\n");
            writer.write(String.format("Grocery shopping total is: €%.2f\n", totalCost));
            writer.write("***********************************************************\n");

            System.out.println("格式化完成！请检查 " + outputFilePath);

        } catch (IOException e) {
            System.err.println("文件操作失败！");
            e.printStackTrace();
        }
    }
}