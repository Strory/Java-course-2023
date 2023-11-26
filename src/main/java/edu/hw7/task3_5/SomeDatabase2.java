package edu.hw7.task3_5;

import edu.hw7.task3.Person;
import edu.hw7.task3.PersonDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SomeDatabase2 implements PersonDatabase {
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Map<Integer, Person> data = new HashMap<>();
    private Set<Integer> fullPersons = new HashSet<>();

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            if (data.containsKey(person.id())) {
                return;
            }
            data.put(person.id(), person);
            if (checkFullPerson(person)) {
                fullPersons.add(person.id());
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void change(Person person) {
        lock.writeLock().lock();
        try {
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
        } finally {
            lock.writeLock().unlock();
        }
    }

    private boolean checkFullPerson(Person person) {
        return person.name() != null && person.address() != null && person.phoneNumber() != null;
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
            data.remove(id);
            fullPersons.remove(id);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        List<Person> persons = new ArrayList<>();
        lock.readLock().lock();
        try {
            for (int id : fullPersons) {
                if (name.equals(data.get(id).name())) {
                    persons.add(data.get(id));
                }
            }
        } finally {
            lock.readLock().unlock();
        }
        return persons;
    }

    @Override
    public List<Person> findByAddress(String address) {
        List<Person> persons = new ArrayList<>();
        lock.readLock().lock();
        try {
            for (int id : fullPersons) {
                if (address.equals(data.get(id).address())) {
                    persons.add(data.get(id));
                }
            }
        } finally {
            lock.readLock().unlock();
        }
        return persons;
    }

    @Override
    public List<Person> findByPhone(String phone) {
        List<Person> persons = new ArrayList<>();
        lock.readLock().lock();
        try {
            for (int id : fullPersons) {
                if (phone.equals(data.get(id).phoneNumber())) {
                    persons.add(data.get(id));
                }
            }
        } finally {
            lock.readLock().unlock();
        }
        return persons;
    }
}
