import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {

        List<String> manNames = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John", "Henry", "Trevor");
        List<String> womanNames = Arrays.asList("Caroline", "Julia", "Ann", "Samantha", "Mary", "Agata");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown", "Green", "Fox", "Luis", "Simmons", "Stanley");
        List<Person> persons = new ArrayList<>();
        String name;
        Integer age;
        Education education;
        Sex sex;

        for (int i = 0; i < 10_000_000; i++) {
            age = new Random().nextInt(100);
            if (age <= 16) {
                education = Education.ELEMENTARY;
            } else {
                education = Education.values()[new Random().nextInt(Education.values().length)];
            }
            sex = Sex.values()[new Random().nextInt(Sex.values().length)];
            if (sex == Sex.MAN) {
                name = manNames.get(new Random().nextInt(manNames.size()));
            } else {
                name = womanNames.get(new Random().nextInt(womanNames.size()));
            }
            persons.add(new Person(
                    name,
                    families.get(new Random().nextInt(families.size())),
                    age,
                    sex,
                    education)
            );
        }

        long count = persons.stream()
                .filter(a -> a.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + count);

        List<String> conscripts = persons.stream()
                .filter(a -> a.getAge() >= 18)
                .filter(a -> a.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        List<Person> workers = persons.stream()
                .filter(a -> a.getAge() >= 18)
                .filter(a -> a.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}
