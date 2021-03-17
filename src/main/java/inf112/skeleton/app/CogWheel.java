package inf112.skeleton.app;

abstract class CogWheel {
    abstract void DoAction(Robot me);
}

class CogWheelRight extends CogWheel{
    @Override
    void DoAction(Robot me){
        me.turnRight();
        //Skal den også pushe roboten et steg frem etter den har vridd mot høyre?
    }
}

class CogWheelLeft extends Game{
    @Override
    void DoAction(Robot me){
        me.turnLeft();
    }
}