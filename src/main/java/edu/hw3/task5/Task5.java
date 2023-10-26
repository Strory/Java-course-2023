package edu.hw3.task5;

public class Task5 {
    public Contact[] parseContacts(String[] names, String sortMode) {
        if (sortMode.equals("ASC")) {
            quickSortForAsc(names, 0, names.length - 1);
        } else if (sortMode.equals("DESC")) {
            quickSortForDesc(names, 0, names.length - 1);
        } else {
            return null;
        }

        Contact[] contacts = new Contact[names.length];
        for (int i = 0; i < names.length; ++i) {
            String[] fullName = names[i].split(" ");
            String firstName = fullName[0];
            String lastName = (fullName.length > 1) ? fullName[1] : null;
            Contact contact = new Contact(firstName, lastName);
            contacts[i] = contact;
        }
        return contacts;
    }

    private void quickSortForAsc(String[] mass, int low, int high) {
        if (low < high) {
            int p = partitionForAsc(mass, low, high);
            quickSortForAsc(mass, low, p - 1);
            quickSortForAsc(mass, p + 1, high);
        }
    }

    private void quickSortForDesc(String[] mass, int low, int high) {
        if (low < high) {
            int p = partitionForDesc(mass, low, high);
            quickSortForDesc(mass, low, p - 1);
            quickSortForDesc(mass, p + 1, high);
        }
    }

    private int partitionForAsc(String[] mass, int low, int high) {
        String pivot = mass[high];
        int i = low - 1;
        for (int j = low; j < high; ++j) {
            if (isLessThan(mass[j], pivot)) {
                ++i;
                String tmp = mass[j];
                mass[j] = mass[i];
                mass[i] = tmp;
            }
        }
        String tmp = mass[i + 1];
        mass[i + 1] = mass[high];
        mass[high] = tmp;
        return i + 1;
    }

    private int partitionForDesc(String[] mass, int low, int high) {
        String pivot = mass[high];
        int i = low - 1;
        for (int j = low; j < high; ++j) {
            if (!(isLessThan(mass[j], pivot))) {
                ++i;
                String tmp = mass[j];
                mass[j] = mass[i];
                mass[i] = tmp;
            }
        }
        String tmp = mass[i + 1];
        mass[i + 1] = mass[high];
        mass[high] = tmp;
        return i + 1;
    }

    @SuppressWarnings("ReturnCount")
    private boolean isLessThan(String element1, String element2) {
        String[] splitElem1 = element1.split(" ");
        String[] splitElem2 = element2.split(" ");
        String stringForCompare1 = (splitElem1.length > 1) ? splitElem1[1] : splitElem1[0];
        String stringForCompare2 = (splitElem2.length > 1) ? splitElem2[1] : splitElem2[0];

        if (compareForString(stringForCompare1, stringForCompare2).equals(Compare.LESS)) {
            return true;
        } else if (compareForString(stringForCompare1, stringForCompare2).equals(Compare.GREATER)) {
            return false;
        }

        if (splitElem1.length < splitElem2.length) {
            return true;
        } else if (splitElem1.length > splitElem2.length) {
            return false;
        }

        if (splitElem1.length > 1) {
            return compareForString(splitElem1[0], splitElem2[0]).equals(Compare.LESS);
        } else {
            return false;
        }
    }

    @SuppressWarnings("ReturnCount")
    private Compare compareForString(String element1, String element2) {
        int minLength = Math.min(element1.length(), element2.length());
        for (int i = 0; i < minLength; ++i) {
            if (element1.charAt(i) < element2.charAt(i)) {
                return Compare.LESS;
            } else if (element1.charAt(i) > element2.charAt(i)) {
                return Compare.GREATER;
            }
        }
        if (element1.length() < element2.length()) {
            return Compare.LESS;
        } else if (element1.length() > element2.length()) {
            return Compare.GREATER;
        } else {
            return Compare.EQUALS;
        }
    }

    enum Compare {
        LESS, GREATER, EQUALS;
    }
}
