package inf112.skeleton.app;

public class Hole {

    int X = 0;
    int Y = 0;
    public Hole (int X_, int Y_){
        X = X_;
        Y = Y_;
    }

    //tar maks damage og respawner
    public void fallInHole (Robot me){
        System.out.println("fell in hole and died");
        me.takeDamage(10);
    }
}
