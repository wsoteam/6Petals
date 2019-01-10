package com.wsoteam.diet.POJOFoodItem;

import com.orm.SugarRecord;

public class UnLockBase extends SugarRecord {
    private String nameOfUnLockGroup;

    public UnLockBase() {
    }

    public UnLockBase(String nameOfUnLockGroup) {
        this.nameOfUnLockGroup = nameOfUnLockGroup;
    }

    public String getNameOfUnLockGroup() {
        return nameOfUnLockGroup;
    }

    public void setNameOfUnLockGroup(String nameOfUnLockGroup) {
        this.nameOfUnLockGroup = nameOfUnLockGroup;
    }
}
