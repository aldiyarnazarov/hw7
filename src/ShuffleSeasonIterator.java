import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleSeasonIterator implements EpisodeIterator {
    private final List<Episode> shuffledEpisodes;
    private int position = 0;

    public ShuffleSeasonIterator(List<Episode> episodes) {
        this.shuffledEpisodes = new ArrayList<>(episodes);
        Collections.shuffle(shuffledEpisodes, new Random(42));
    }

    @Override
    public boolean hasNext() {
        return position < shuffledEpisodes.size();
    }

    @Override
    public Episode next() {
        return shuffledEpisodes.get(position++);
    }
}
