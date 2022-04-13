package cards;

public class EdibleItem extends Card{

    protected int flavourPoints;

    public EdibleItem(CardType type, String cardName) {
        super(type,cardName);
        switch (cardName) {
            case "HoneyFungus":
                flavourPoints = 1;
                break;
            case "TreeEar":
                flavourPoints = 1;
                break;
            case "LawyersWig":
                flavourPoints = 2;
                break;
            case "Shiitake":
                flavourPoints = 2;
                break;
            case "HenOfWoods":
                flavourPoints = 3;
                break;
            case "BirchBolete":
                flavourPoints = 3;
                break;
            case "Porcini":
                flavourPoints = 3;
                break;
            case "Chanterelle":
                flavourPoints = 4;
                break;
            case "Morel":
                flavourPoints = 6;
                break;
        }
    }

    public int getFlavourPoints() {
        return flavourPoints;
    }

}
