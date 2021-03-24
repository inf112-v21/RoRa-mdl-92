package inf112.skeleton.app;

abstract class CogWheel {
    int X = 0;
    int Y = 0;

    abstract void DoAction(Robot me);
    int cogWheelSprite = 0;
}

class CogWheelRight extends CogWheel{
    public CogWheelRight (int X_, int Y_){
        X = X_;
        Y = Y_;
    }
    @Override
    void DoAction(Robot me){
        me.turnRight();
    }
}

class CogWheelLeft extends CogWheel{
    public CogWheelLeft (int X_, int Y_){
        X = X_;
        Y = Y_;
    }
    @Override
    void DoAction(Robot me){
        me.turnLeft();
    }
}