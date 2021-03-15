package ch5_Stream;

public class Person implements Comparable<Person>{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;

        if(obj instanceof Person)
        {
            Person p1=(Person)obj;

            if(p1.getName().equals(this.getName()) && p1.getAge()==this.getAge())
                return true;
            else
                return false;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return (name+age).hashCode();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return (name+age).compareTo(o.getName()+o.getAge());
    }
}
