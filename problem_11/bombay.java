import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Bombay on 2016-12-20.
 */
public class Gunslinger {
    private static Person[] personList = {new Outlaw(), new Citizen(), new Citizen(),new Outlaw(), new Citizen(), new Outlaw(), new Outlaw(), new Citizen(), new Citizen(), new Mad(), new Citizen(), new Citizen() };

    public static void main(String[] args) {
        Person handle = personList[0];
        Arrays.stream(personList).skip(1).forEach(person -> handle.stream().reduce(Person.last).get().linkNext(person));
        handle.stream().reduce(Person.last).get().linkNext(handle);

        System.out.println(handle.stream().map(Object::toString).collect(Collectors.joining(", ")));
        handle.eternalStream().forEach(person->{
            person.act();

            if(Citizen.isVictory(person.stream())) {
                System.out.println("시민 승리 " + person.getSize());
            } else if (Outlaw.isVictory(person.stream())) {
                System.out.println("무법자 승리 "+ person.getSize());
            } else if (Mad.isVictory(person.stream())) {
                System.out.println("정신병자 승리");
            }
            // 여기서 스트림을 끊을 수는 없는가!!

            System.out.println(person.stream().map(Object::toString).collect(Collectors.joining(", ")));
        });

        /*
        Person person = handle;

        while(true) {
            person.act();
            person = person.next();

            if(Citizen.isVictory(person.stream())) {
                System.out.println("시민 승리 " + person.getSize());
                break;
            } else if (Outlaw.isVictory(person.stream())) {
                System.out.println("무법자 승리 "+ person.getSize());
                break;
            } else if (Mad.isVictory(person.stream())) {
                System.out.println("정신병자 승리");
                break;
            }
        }
        */
    }
}

abstract class Person {
    private Person prev = null;
    private Person next = null;
    private int murderCount = 0;

    public Person prev() {
        return prev;
    }
    public void linkPrev(Person value) {
        value.setNext(this);
        this.setPrev(value);
    }
    public void setPrev(Person value) {
        prev = value;
    }

    public Person next() {
        return next;
    }
    public void linkNext(Person value) {
        value.setPrev(this);
        this.setNext(value);
    }
    public void setNext(Person value) {
        next = value;
    }

    public int murderCount() {
        return murderCount;
    }
    public void setMurderCount(int value) {
        murderCount = value;
    }

    public Person remove() {
        prev.setNext(next);
        next.setPrev(prev);
        return next;
    }

    public void kill(Person target) {
        System.out.println(this.toString() + " killed " + target.toString());
        target.remove();
        murderCount ++;
    }

    public abstract void act();

    public int getSize() {
        return getSizeRep(this, this, 0);
    }

    private int getSizeRep(Person begin, Person now, int cnt) {
        if(now.next() == begin || now.next() == null)
            return cnt + 1;
        return getSizeRep(begin, now.next(), cnt+1);
    }

    public Stream<Person> stream() {
        return Stream.iterate(this, t -> t.next()).limit(getSize());
    }
    public Stream<Person> eternalStream() {
        return Stream.iterate(this, t -> t.next());
    }

    public static final BinaryOperator<Person> first = (l,r)->l;
    public static final BinaryOperator<Person> last = (l,r)->r;
}

class Citizen extends Person{
    @Override
    public void act() {
        if(prev().murderCount() != next().murderCount()) {
            kill(prev().murderCount() < next().murderCount() ? next() : prev());
        }
    }

    public static boolean isVictory(Stream<Person> targetStream) {
        return targetStream.filter(p->p instanceof Outlaw).count() == 0;
    }

    @Override
    public String toString() {
        return "Citizen";
    }
}

class Outlaw extends Person{
    @Override
    public void act() {
        if(next() instanceof Outlaw == false && prev() instanceof Outlaw == false) {
            kill(next().murderCount() <= prev().murderCount() ? next() : prev());
        } else if(prev() instanceof Outlaw == false) {
            kill(prev());
        } else if(next() instanceof Outlaw == false) {
            kill(next());
        }
    }

    public static boolean isVictory(Stream<Person> targetStream) {
        return targetStream.filter(p->p instanceof Mad || p instanceof Citizen).count() == 0;
    }

    @Override
    public String toString() {
        return "Outlaw";
    }
}

class Mad extends Person {
    @Override
    public void act() {
        if(getSize() % 2 == 0) {
            kill(eternalStream().limit(getSize()/2 + 1).reduce(Person.last).get());
        } else {
            kill(this);
        }
        setMurderCount(0);
    }

    public static boolean isVictory(Stream<Person> targetStream) {
        return targetStream.filter(p->p instanceof Outlaw || p instanceof Citizen).count() == 0;
    }

    @Override
    public String toString() {
        return "Mad";
    }
}