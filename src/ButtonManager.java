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
                //TODO Load level1
                breakout.gameStart(1);
                break;
            case 2:
                //TODO Load level2
                break;
            case 3:
                //TODO Load level3
                break;
            default:
        }
    }
}
