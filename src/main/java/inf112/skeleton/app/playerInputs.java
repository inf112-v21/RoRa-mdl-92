package inf112.skeleton.app;

import java.util.ArrayList;

public class playerInputs {
    public ArrayList<Integer> inputs = null;
    public boolean shutDown = false;

    playerInputs(String inputString){
        inputs = new ArrayList<Integer>();
        for(int i = 0; i < inputString.length(); i++){
            if(inputString.charAt(i) == '-'){
                shutDown = true;
                inputs = new ArrayList<Integer>();
                break;
            }
            inputs.add(Character.getNumericValue(inputString.charAt(i)));
        }
    }

    playerInputs(ArrayList inputList){
        inputs = inputList;
    }

    playerInputs(){
        inputs = new ArrayList<Integer>();
    }


    public String getInputString(){
        if(shutDown){
            return "-";
        }
        String inputString = "";
        for(Integer i : inputs){
            inputString += i.toString();
        }
        return inputString;
    }
}
