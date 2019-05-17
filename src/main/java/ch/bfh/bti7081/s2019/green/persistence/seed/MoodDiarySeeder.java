package ch.bfh.bti7081.s2019.green.persistence.seed;

import ch.bfh.bti7081.s2019.green.model.diary.MoodDiary;
import ch.bfh.bti7081.s2019.green.persistence.SessionSingleton;

public class MoodDiarySeeder {
    public static void seed() {
        MoodDiary diary = new MoodDiary();
        SessionSingleton.getInstance().save(diary);
    }
}
