package Book;

import java.io.Serializable;

public class Address implements Serializable{
  private String name;
  private String street;
  private String city;
  private String state;
  private String phoneNo;
  
  public Address(String name, String street, String city, String state, String phoneNo){
    this.name=name;
    this.street=street;
    this.city=city;
    this.state=state;
    this.phoneNo=phoneNo;
  }
  public String getName(){
    return name;
  }
  public String getStreet(){
    return street;
  }
  public String getCity(){
    return city;
  }
  public String getState(){
    return state;
  }
  public String getPhoneNo(){
    return phoneNo;
  }
  public String toString(){
    return String.format("%-20s%-30s%-20s%-10s%-10s", name, street, city, state, phoneNo);
  }

}
