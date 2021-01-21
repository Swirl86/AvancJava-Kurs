package inlupg;

public class MyObject {

    Boolean bool;
    int value;
    String name;

    public MyObject() {
	this.bool = getRandomBoolean();
	this.value = getRandomNumber();
	this.name = getRandomName();
    }

    public Boolean getBool() {
	return bool;
    }

    public void setBool(Boolean bool) {
	this.bool = bool;
    }

    public int getValue() {
	return value;
    }

    public void setValue(int value) {
	this.value = value;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getRandomNumber() {
	return (int) (Math.random() * 50) + 1;
    }

    public Boolean getRandomBoolean() {
	int randNr = (int) (Math.random() * 2);
	return randNr == 0 ? true : false;
    }

    public String getRandomName() {
	String name = String.valueOf(getRandomCharacter('A', 'Z'));
	for (int i = 0; i < 6; i++) {
	    name += String.valueOf(getRandomCharacter('a', 'z'));
	}
	return name;
    }

    @Override
    public String toString() {
	return "bool: " + bool + ", value: " + value + ", name: " + name;
    }

    /** Generate a random character between ch1 and ch2 */
    public static char getRandomCharacter(char ch1, char ch2) {
	return (char) (ch1 + Math.random() * (ch2 - ch1 + 1));
    }
}
