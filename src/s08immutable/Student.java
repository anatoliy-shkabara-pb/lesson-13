package s08immutable;


import java.util.HashMap;
import java.util.Map;

public final class Student {

    private final String name;
    private final int regNo;
    private final Map<String, String> metadata;

    public Student(String name, int regNo, Map<String, String> metadata) {
        this.name = name;
        this.regNo = regNo;

        // Creating Map object with reference to HashMap
        // Declaring object of string type
        this.metadata = new HashMap<>(metadata);
    }

    public String getName() {
        return name;
    }

    public int getRegNo() {
        return regNo;
    }

    // Note tha there should not be any setters

    public Map<String, String> getMetadata() {
        // Creating Map with HashMap reference
        return new HashMap<>(this.metadata);
    }
}