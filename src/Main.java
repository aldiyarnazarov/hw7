public class Main {
    public static void main(String[] args) {
        Season season1 = new Season();
        season1.addEpisode(new Episode("Pilot", 1800));
        season1.addEpisode(new Episode("Episode 2", 1850));
        season1.addEpisode(new Episode("Episode 3", 1900));

        System.out.println("Normal Order:");
        EpisodeIterator normalIterator = season1.createIterator();
        while (normalIterator.hasNext()) {
            System.out.println(normalIterator.next());
        }

        System.out.println("\nReverse Order:");
        EpisodeIterator reverseIterator = season1.createReverseIterator();
        while (reverseIterator.hasNext()) {
            System.out.println(reverseIterator.next());
        }

        System.out.println("\nShuffle Order:");
        EpisodeIterator shuffleIterator = season1.createShuffleIterator();
        while (shuffleIterator.hasNext()) {
            System.out.println(shuffleIterator.next());
        }

        System.out.println("\nUsing foreach loop:");
        for (Episode e : season1) {
            System.out.println(e);
        }
    }
}
