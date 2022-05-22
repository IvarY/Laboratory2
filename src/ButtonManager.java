public class ButtonManager {

    private Breakout breakout;

    public ButtonManager(Breakout breakout) {
        this.breakout = breakout;
    }

    /** Chooses what to do depending on buttonValue input
     * @param buttonValue Input value to choose what action to do
     */
    public void buttonClicked(int buttonValue){
        switch (buttonValue){
            case 1:
                breakout.gameStart(1);
                break;
            case 2:
                breakout.gameStart(2);
                break;
            case 3:
                breakout.gameStart(3);
                break;
            case 4:
                breakout.gameRestart();
                break;
            case 5:
                breakout.newGame();
                break;
            case 6:
                System.exit(0);
                break;
            default:
        }
    }
}
