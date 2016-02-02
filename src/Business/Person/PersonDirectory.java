/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.Person;

import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class PersonDirectory {
    private ArrayList<Person> personList;

    public PersonDirectory() {
        personList = new ArrayList<>();
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(ArrayList<Person> personList) {
        this.personList = personList;
    }
    
    public Person createAndAddPerson(String name, String gender, String companyName, int age) {
        Person person = new Person();
        personList.add(person);
        person.setName(name);
        person.setGender(gender);
        person.setAge(age);
        if(companyName != null )
            person.setCompanyName(companyName);
        return person;
    }
    
}
