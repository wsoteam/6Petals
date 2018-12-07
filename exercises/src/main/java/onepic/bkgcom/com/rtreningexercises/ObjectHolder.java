package onepic.bkgcom.com.rtreningexercises;

import onepic.bkgcom.com.rtreningexercises.POJOs.GlobalObject;

public class ObjectHolder {
    private static GlobalObject globalObject;


    public void createObjFromGirebase(GlobalObject globalObject){
        if(this.globalObject == null){
            this.globalObject = globalObject;
        }
    }

    public static GlobalObject getGlobalObject(){
        return globalObject;
    }

}

