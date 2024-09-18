package Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AddressBook {
  private BufferedWriter writer;
  public AddressBook(String fileName)throws IOException{
    writer= new BufferedWriter(new FileWriter(fileName,true));
  }
  public void addAddress(Address address)throws IOException{
    String line=String.format("%s, %s, %s, %s, %s%n",
        address.getName(),
        address.getStreet(),
        address.getCity(),
        address.getState(),
        address.getPhoneNo()
        );
    writer.write(line);
    writer.close();
  }
}