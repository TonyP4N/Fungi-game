package cards;

public class Mushroom extends EdibleItem{

    protected int sticksPerMushroom;

    public Mushroom(CardType type, String cardName) {
        super(type, cardName);
        switch (cardName){
            case "HoneyFungus":
                sticksPerMushroom = 1;
                break;
            case "TreeEar":
                sticksPerMushroom = 2;
                break;
            case "LawyersWig":
                sticksPerMushroom = 1;
                break;
            case "Shiitake":
                sticksPerMushroom = 2;
                break;
            case "HenOfWoods":
                sticksPerMushroom = 1;
                break;
            case "BirchBolete":
                sticksPerMushroom = 2;
                break;
            case "Porcini":
                sticksPerMushroom = 3;
                break;
            case "Chanterelle":
                sticksPerMushroom = 2;
                break;
            case "Morel":
                sticksPerMushroom = 4;
                break;
        }
    }

    public int getSticksPerMushroom() {
        return sticksPerMushroom;
    }

}
