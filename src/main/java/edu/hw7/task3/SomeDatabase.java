package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SomeDatabase implements PersonDatabase {
    private volatile Map<Integer, Person> data = new HashMap<>();
    private volatile Set<Integer> fullPersons = new HashSet<>();

    @Override
    public synchronized void add(Person person) {
        if (data.containsKey(person.id())) {
            return;
        }
        data.put(person.id(), person);
        if (checkFullPerson(person)) {
            fullPersons.add(person.id());
        }
    }

    public synchronized void change(Person person) {
        if (data.containsKey(person.id())) {
            data.put(person.id(), person);
        } else {
            return;
        }
        if (checkFullPerson(person)) {
            fullPersons.add(person.id());
        }
        if (person.name() == null || person.address() == null || person.phoneNumber() == null) {
            fullPersons.remove(person.id());
        }
    }

    private boolean checkFullPerson(Person person) {
        return person.name() != null && person.address() != null && person.phoneNumber() != null;
    }

    @Override
    public synchronized void delete(int id) {
        data.remove(id);
        fullPersons.remove(id);
    }

    @Override
    public List<Person> findByName(String name) {
        List<Person> persons = new ArrayList<>();
        for (int id : fullPersons) {
            if (name.equals(data.get(id).name())) {
                persons.add(data.get(id));
            }
        }
        return persons;
    }

    @Override
    public List<Person> findByAddress(String address) {
        List<Person> persons = new ArrayList<>();
        for (int id : fullPersons) {
            if (address.equals(data.get(id).address())) {
                persons.add(data.get(id));
            }
        }
        return persons;
    }

    @Override
    public List<Person> findByPhone(String phone) {
        List<Person> persons = new ArrayList<>();
        for (int id : fullPersons) {
            if (phone.equals(data.get(id).phoneNumber())) {
                persons.add(data.get(id));
            }
        }
        return persons;
    }
}
