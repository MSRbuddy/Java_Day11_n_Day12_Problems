import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class SaveShares
{
    private String stockSymbol;
    private int numberOfShares;
    private LocalDateTime dateTime;

    public SaveShares(String stockSymbol, int numberOfShares, LocalDateTime dateTime)
    {
        this.stockSymbol = stockSymbol;
        this.numberOfShares = numberOfShares;
        this.dateTime = dateTime;
    }

    public String getStockSymbol()
    {
        return stockSymbol;
    }

    public int getNumberOfShares()
    {
        return numberOfShares;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }
}

class StockAccount
{
    private List<SaveShares> companySharesList;

    public StockAccount()
    {
        companySharesList = new ArrayList<>();
    }

    public void buyShares(String stockSymbol, int numberOfShares, LocalDateTime dateTime)
    {
        SaveShares companyShares = findCompanyShares(stockSymbol);
        if (companyShares != null)
        {
            companySharesList.remove(companyShares);
            int updatedNumberOfShares = companyShares.getNumberOfShares() + numberOfShares;
            companyShares = new SaveShares(stockSymbol, updatedNumberOfShares, dateTime);
        }
        else
        {
            companyShares = new SaveShares(stockSymbol, numberOfShares, dateTime);
        }
        companySharesList.add(companyShares);
        System.out.println("Successfully bought " + numberOfShares + " shares of " + stockSymbol);
    }

    public void sellShares(String stockSymbol, int numberOfShares, LocalDateTime dateTime)
    {
        SaveShares companyShares = findCompanyShares(stockSymbol);
        if (companyShares != null)
        {
            int updatedNumberOfShares = companyShares.getNumberOfShares() - numberOfShares;
            if (updatedNumberOfShares > 0)
            {
                companySharesList.remove(companyShares);
                companyShares = new SaveShares(stockSymbol, updatedNumberOfShares, dateTime);
                companySharesList.add(companyShares);
                System.out.println("Successfully sold " + numberOfShares + " shares of " + stockSymbol);
            }
            else
            {
                companySharesList.remove(companyShares);
                System.out.println("Successfully sold all shares of " + stockSymbol);
            }
        }
        else
        {
            System.out.println("No shares of " + stockSymbol + " available to sell");
        }
    }

    private SaveShares findCompanyShares(String stockSymbol)
    {
        for (SaveShares companyShares : companySharesList)
        {
            if (companyShares.getStockSymbol().equals(stockSymbol))
            {
                return companyShares;
            }
        }
        return null;
    }
}

public class StockAccountMain
{
    public static void main(String[] args)
    {
        StockAccount stockAccount = new StockAccount();

        // Buy shares
        stockAccount.buyShares("APPLE", 10, LocalDateTime.now());
        stockAccount.buyShares("GOOGLE", 5, LocalDateTime.now());

        // Sell shares
        stockAccount.sellShares("APPLE", 5, LocalDateTime.now());
        stockAccount.sellShares("GOOGLE", 10, LocalDateTime.now());
    }
}