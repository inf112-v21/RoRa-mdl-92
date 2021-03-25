package inf112.skeleton.app;

abstract class CogWheel {
    int X = 0;
    int Y = 0;

    abstract void DoAction(Robot me);
    int cogWheelSprite = 0;
}

//roterer roboten 90 grader mot høyre
class CogWheelRight extends CogWheel{
    public CogWheelRight (int X_, int Y_){
        X = X_;
        Y = Y_;
    }
    @Override
    void DoAction(Robot me){
        me.turnRight();
        System.out.println("turning right");
    }
}

//roterer roboten 90 grader mot venstre
class CogWheelLeft extends CogWheel{
    public CogWheelLeft (int X_, int Y_){
        X = X_;
        Y = Y_;
    }
    @Override
    void DoAction(Robot me){
        me.turnLeft();
        System.out.println("turning left");
    }
}