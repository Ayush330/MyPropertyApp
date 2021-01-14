package com.example.freebase;

public class propertyDetails
{
    public String place;
    public String ownerName;
    public String phone;
    public String price;
    public String propDetail;
    public String id;

    public propertyDetails()
    {}

    public propertyDetails(String place,String ownerName)
    {
        this.place = place;
        this.ownerName=ownerName;
    }

    public propertyDetails(String place,String ownerName,String phone,String price,String propDetail)
    {
        this.place = place;
        this.ownerName=ownerName;
        this.phone=phone;
        this.price=price;
        this.propDetail=propDetail;
    }

    public propertyDetails(String place,String ownerName,String phone,String price,String propDetail,String id)
    {
        this.place = place;
        this.ownerName=ownerName;
        this.phone=phone;
        this.price=price;
        this.propDetail=propDetail;
        this.id = id;
    }

}
