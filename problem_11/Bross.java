// Person.java
package problem_11;

import java.util.List;

/**
 * Created by bross on 2016. 12. 20..
 */
public abstract class Person {
    protected int killCount;
    protected boolean life;
    public Person() {
        life = true;
        killCount = 0;
    }
    public abstract List<Person> kill(List<Person> person);
    public abstract void dead();
}

//Citizen.java
package problem_11;

import java.util.List;

/**
 * Created by bross on 2016. 12. 20..
 */
public class Citizen extends Person {
    private static int totalCount=0;

    public Citizen(){
        super();
        totalCount++;
    }

    public static int getTotalCount() {
        return totalCount;
    }

    public static void init(){
        totalCount=0;
    }


    @Override
    public void dead() {
        totalCount--;
    }

    @Override
    public List<Person> kill(List<Person> persons) {

        int position = persons.indexOf(this);

        Person leftPerson = persons.get((position + persons.size() - 1) % persons.size());
        Person rightPerson = persons.get((position + 1) % persons.size());


        if (leftPerson.killCount > rightPerson.killCount) {
            leftPerson.dead();
            persons.remove(leftPerson);
            killCount++;
        } else if (rightPerson.killCount > leftPerson.killCount) {
            rightPerson.dead();
            persons.remove(rightPerson);
            killCount++;
        }

        return persons;
    }

}

//Outlaw.java
package problem_11;

import java.util.List;

/**
 * Created by bross on 2016. 12. 20..
 */
public class Outlaw extends Person {
    private static int totalCount = 0;

    public Outlaw() {
        super();
        totalCount++;
    }

    public static int getTotalCount() {
        return totalCount;
    }


    @Override
    public void dead() {
        totalCount--;
    }

    public static void init(){
        totalCount=0;
    }

    @Override
    public List<Person> kill(List<Person> persons) {

        int position = persons.indexOf(this);

        Person leftPerson = persons.get((position + persons.size() - 1) % persons.size());
        Person rightPerson = persons.get((position + 1) % persons.size());


        if (leftPerson instanceof Outlaw && rightPerson instanceof Outlaw) {
            return persons;
        } else if (leftPerson.killCount > rightPerson.killCount) {
            killLeftPerson(leftPerson, persons);
        } else if (rightPerson.killCount > leftPerson.killCount) {
            killRightPerson(rightPerson, persons);
        } else {
            leftPerson.dead();
            persons.remove(leftPerson);
            killCount++;
        }

        return persons;
    }

    public void killLeftPerson(Person leftPerson, List<Person> personList) {
        if (leftPerson instanceof Outlaw) {
            int position = personList.indexOf(this);
            Person rightPerson = personList.get((position + 1) % personList.size());
            killRightPerson(rightPerson, personList);
        } else {
            leftPerson.dead();
            personList.remove(leftPerson);
            killCount++;
        }
    }

    public void killRightPerson(Person rightPerson, List<Person> personList) {
        if (rightPerson instanceof Outlaw) {
            int position = personList.indexOf(this);
            Person leftPerson = personList.get((position + personList.size() - 1) % personList.size());
            killLeftPerson(leftPerson, personList);
        } else {
            rightPerson.dead();
            personList.remove(rightPerson);
            killCount++;
        }
    }
}

//Psychopath.java
package problem_11;

import java.util.List;

/**
 * Created by bross on 2016. 12. 20..
 */
public class Psychopath extends Person {
    private static int totalCount = 0;

    public Psychopath() {
        super();
        totalCount++;
    }

    public static int getTotalCount() {
        return totalCount;
    }

    public static void init(){
        totalCount=0;
    }


    @Override
    public void dead() {
        life = false;
        totalCount--;
    }

    @Override
    public List<Person> kill(List<Person> persons) {
        if (persons.size() % 2 == 0) {
            int position = persons.indexOf(this);
            int killPosition = (position + persons.size() / 2) % persons.size();
            persons.get(killPosition).dead();
            persons.remove(killPosition);
        } else if (persons.size() > 1) {
            this.dead();
            persons.remove(this);
        }
        return persons;
    }
}

//Problem11.java
package problem_11;

import library.TailCall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static library.TailCalls.done;


/**
 * Created by bross on 2016. 12. 20..
 */
public class Problem11 {
    static TailCall<String> game(Person person, List<Person> personList) {
        if (Outlaw.getTotalCount() == 0 && Citizen.getTotalCount() == 0) {
            return done("정신병자 승리");
        } else if (Outlaw.getTotalCount() == 0) {
            return done("시민 승리 [ " + (Citizen.getTotalCount() + Psychopath.getTotalCount()) + " 명 생존 ]");
        } else if (Citizen.getTotalCount() == 0) {
            return done("무법자 승리 [ " + Outlaw.getTotalCount() + " 명 생존 ]");
        } else {
            person.kill(personList);
            personList.iterator().next();
            person = personList.get((personList.indexOf(person) + 1) % personList.size());
            return game(person, personList);
        }
    }

    public static void example() {
        List<Person> personList = new ArrayList<>();

        personList.add(new Outlaw());
        personList.add(new Citizen());
        personList.add(new Outlaw());
        personList.add(new Outlaw());
        personList.add(new Citizen());
        personList.add(new Citizen());
        personList.add(new Psychopath());
        personList.add(new Citizen());
        personList.add(new Citizen());


        System.out.println(" [게임 시작] ");
        System.out.println(" [총인원 " + personList.size() + "명]");
        System.out.println(" [시민 " + Citizen.getTotalCount() + "명]");
        System.out.println(" [무법자 " + Outlaw.getTotalCount() + "명]");
        System.out.println(" [정신병자 " + 7 + "번째]");
        System.out.println();
        System.out.print(game(personList.iterator().next(), personList).invoke());

    }

    public static void random() {

        Outlaw.init();
        Psychopath.init();
        Citizen.init();

        final int MAX = 5000;
        List<Person> personList = new ArrayList<>();
        Random random = new Random();
        int count = random.nextInt(MAX) + 1;

        for (int i = 0; i < count; i++) {
            if (random.nextInt() % 10 == 0) {
                personList.add(new Outlaw());
            } else {
                personList.add(new Citizen());
            }
        }
        int position = random.nextInt(MAX) % personList.size();
        personList.add(position, new Psychopath());
        System.out.println(" [게임 시작] ");
        System.out.println(" [총인원 " + personList.size() + "명]");
        System.out.println(" [시민 " + Citizen.getTotalCount() + "명]");
        System.out.println(" [무법자 " + Outlaw.getTotalCount() + "명]");
        System.out.println(" [정신병자 " + position + "번째]");
        String result = game(personList.iterator().next(), personList).invoke();
        System.out.println(result);
    }


    public static void main(String[] args) {
        example();
        System.out.println();
        random();
    }

}
