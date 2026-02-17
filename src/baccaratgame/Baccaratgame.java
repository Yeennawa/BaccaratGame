package baccaratgame;

import java.util.Random;

public class Baccaratgame {

    Player player = new Player();
    Player bot = new Player();
    Random rand = new Random();

    int p1,p2,p3=-1;
    int b1,b2,b3=-1;

    public void deal(){

        p3=-1;
        b3=-1;

        p1 = draw();
        p2 = draw();

        b1 = draw();
        b2 = draw();
    }

    public void playerDraw(){
        p3 = draw();
    }

    public void botDraw(){

        bot.total = cardValue(b1)+cardValue(b2);
        bot.total %=10;

        if(bot.total<4){
            b3 = draw();
        }
    }

    public int playerTotal(){

        int t = cardValue(p1)+cardValue(p2);

        if(p3>0) t+=cardValue(p3);

        return t%10;
    }

    public int botTotal(){

        int t = cardValue(b1)+cardValue(b2);

        if(b3>0) t+=cardValue(b3);

        return t%10;
    }

    public String judge(){

        if(playerTotal()>botTotal()){
            bot.losehearts();
            return "PLAYER WIN";
        }
        else if(playerTotal()<botTotal()){
            player.losehearts();
            return "BOT WIN";
        }

        return "DRAW";
    }

    public int draw(){
        return rand.nextInt(13)+1;
    }

    public int cardValue(int n){
        if(n>=10) return 10;
        return n;
    }
}
