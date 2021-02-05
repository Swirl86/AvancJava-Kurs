import java.util.Random;

public abstract class Npc {
    private final String[] phrases = {"“Every story needs its hero. . And its villain.”",
            "“Every villain is a hero in his own mind.”", "“I’d buy that for a gold…”",
            "“By my Great Aunt Myrra’s beard!”", "“Bababooy, Bababooy, his head went kablooy.”",
            "“That’s the way the meatball bounces!”", "“Keep your gold close and your dagger closer.”"};

    protected String name;
    protected String catchPhrase;

    protected Npc(String name) {
        this.name = name;
        this.catchPhrase = getPhrase();
    }

    public String getPhrase() {
        int i = new Random().nextInt(phrases.length);
        return phrases[i];
    }

    protected abstract String getRandomPhrase();

    protected abstract String getNpcName();

    protected abstract String showPerson();

}