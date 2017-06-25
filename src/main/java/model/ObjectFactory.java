package model;


public class ObjectFactory {


    public ObjectFactory() {
    }

    public Country createCountry() {
        return new Country();
    }

    public Climat createClimat() {
        return new Climat();
    }

    public Month createMonth() {
        return new Month();
    }
 

}
