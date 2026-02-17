package baccaratgame;

public class Player {
    int hearts = 3;
    int total = 0;

    public void losehearts(){
        hearts--;
    }

    public boolean isAlive(){
        return hearts > 0;
    }
}
