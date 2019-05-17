package com.viagra.design.creational.Builder;
// 房屋建造
public interface HouseBuilder {

    public void buildBasement();
    public void buildStructure();
    public void buildRoof();
    public void buildInterior();
    public House getHouse();



}
