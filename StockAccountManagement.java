import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Stock
{
    private String name;
    private int numberOfShares;
    private double sharePrice;

    public Stock(String name, int numberOfShares, double sharePrice)
    {
        this.name = name;
        this.numberOfShares = numberOfShares;
        this.sharePrice = sharePrice;
    }

    public double calculateStockValue()
    {
        return numberOfShares * sharePrice;
    }

    public String getName()
    {
        return name;
    }

    public int getNumberOfShares()
    {
        return numberOfShares;
    }

    public double getSharePrice()
    {
        return sharePrice;
    }
}

class StockPortfolio
{
    private List<Stock> stocks;

    public StockPortfolio()
    {
        stocks = new ArrayList<>();
    }

    public void addStock(Stock stock)
    {
        stocks.add(stock);
    }

    public double calculateTotalValue()
    {
        double totalValue = 0;
        for (Stock stock : stocks)
        {
            totalValue += stock.calculateStockValue();
        }
        return totalValue;
    }

    public void printStockReport()
    {
        System.out.println("***** Stock Report *****");
        System.out.println("---------------------------");
        for (Stock stock : stocks)
        {
            System.out.println("Stock Name: " + stock.getName());
            System.out.println("Number of Shares: " + stock.getNumberOfShares());
            System.out.println("Share Price: $" + stock.getSharePrice());
            System.out.println("Stock Value: $" + stock.calculateStockValue());
            System.out.println();
        }
        System.out.println("Total Stock Value: $" + calculateTotalValue());
    }
}

public class StockAccountManagement
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of stocks: ");
        int numberOfStocks = scanner.nextInt();

        StockPortfolio portfolio = new StockPortfolio();

        for (int i = 0; i < numberOfStocks; i++)
        {
            System.out.println("Enter details for Stock " + (i + 1));
            System.out.print("Stock Name: ");
            String name = scanner.next();
            System.out.print("Number of Shares: ");
            int numberOfShares = scanner.nextInt();
            System.out.print("Share Price: ");
            double sharePrice = scanner.nextDouble();

            Stock stock = new Stock(name, numberOfShares, sharePrice);
            portfolio.addStock(stock);
            System.out.println();
        }

        portfolio.printStockReport();
    }
}