package com.adurcup.getimage_experiment;

/**
 * Created by shivang on 12/14/15.
 */
public class ResponseItems {
    String id;
    String name;
    String category;
    String description;
    String min_quantity;
    String initial_cost;
    String price_per_unit;
    String customizable;
    String color;
    String image_src;
    String types;
    String sizes;
    String delivery;
    String unit_description;
    String advertisement;
    public String getId(){
        return id;
    }
    public void setId(String cid){
        this.id=cid;
    }
    public String getName(){
        return name;
    }
    public void setName(String cname){
        this.name=cname;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String ccategory){
        this.category=ccategory;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String cdescription){
        this.description=cdescription;
    }
    public String getMin_quantity(){
        return min_quantity;
    }
    public void setMin_quantity(String cmin_quantity){
        this.min_quantity=cmin_quantity;
    }
    public String getInitial_cost(){
        return initial_cost;
    }
    public void setInitial_cost(String cinitial_cost){
        this.initial_cost=cinitial_cost;
    }
    public String getPrice_per_unit(){
        return price_per_unit;
    }
    public void setPrice_per_unit(String cprice_per_unit){
        this.price_per_unit=cprice_per_unit;
    }
    public String getCustomizable(){
        return customizable;
    }
    public void setCustomizable(String ccustomizable){
        this.customizable=ccustomizable;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String ccolor){
        this.color=ccolor;
    }
    public String getImage_src(){
        return image_src;
    }
    public void  setImage_src(String cimage_src){
        this.image_src=cimage_src;
    }
    public String getTypes(){
        return types;
    }
    public void setTypes(String ctypes){
        this.types=ctypes;
    }
    public String getSizes(){
        return sizes;
    }
    public void setSizes(String csizes){
        sizes=csizes;
    }
    public String getDelivery(){
        return delivery;
    }
    public void setDelivery(String cdelivery){
        this.delivery=cdelivery;
    }
    public String getUnit_description(){
        return unit_description;
    }
    public void setUnit_description(String cunit_description){
        this.unit_description=cunit_description;
    }
    public String getAdvertisement(){
        return advertisement;
    }
    public void  setAdvertisement(String cadvertisement){
        advertisement=cadvertisement;
    }
}
