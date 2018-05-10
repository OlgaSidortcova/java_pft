package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;

import ru.stqa.pft.addressbook.model.NewContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {

    ContactDataGenerator generator = new ContactDataGenerator();


    JCommander jcommander = new JCommander(generator);
    try {
      jcommander.parse(args);
    } catch (ParameterException ex) {
      jcommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {

    List<NewContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsXml(List<NewContactData> contacts, File file) throws IOException {

    XStream xstream = new XStream();
    xstream.processAnnotations(NewContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();


  }

  private static void saveAsCsv(List<NewContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    System.out.println(new File(".").getAbsolutePath());
    for (NewContactData contact : contacts) {
      writer.write(String.format("%s; %s; %s\n", contact.getFirst_name(), contact.getLast_name(), contact.getCompany(),
              contact.getAddress(), contact.getHome(), contact.getMobile(), contact.getWork(), contact.getFax(),
              contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getPhoto()));

    }
    writer.close();
  }

  private static List<NewContactData> generateContacts(int count) {

    List<NewContactData> contacts = new ArrayList<NewContactData>();

    for (int i = 0; i < count; i++) {

      contacts.add(new NewContactData().withFirst_name(String.format("Fname %s", i)).
              withLast_name(String.format("Lname %s", i)).
              withCompany(String.format("Company %s", i)).withAddress(String.format("Adress %s", i)).
              withHome(String.format("123 %s", i)).//withMobile(String.format("456 %s", i)).
              withWork(String.format("789 %s", i)).withFax(String.format("fax %s", i)).
              withEmail(String.format("mama@mail.ru %s", i)).//withEmail2(String.format("papa@yandex.ru %s", i)).
              withEmail3(String.format("pusto@gmail.ru", i)).
                      withPhoto(new File(String.format("src/test/resources/photo.jpg", i)))

      );

    }

    return contacts;
  }
}
