package edu.lk.ijse.projectgym.demo76promax.Util;

import java.util.*;

public class CoachScheduleGenerator {
    private static final List<String> coaches = List.of("CH001", "CH002", "CH003", "CH004");

    public static List<String> generateSchedule(int numberOfDays) {
        List<String> schedule = new ArrayList<>();

        for (int day = 0; day < numberOfDays; day++) {
            int firstCoachIndex = day % coaches.size();
            int secondCoachIndex = (firstCoachIndex + 1) % coaches.size();

            String coach1 = coaches.get(firstCoachIndex);
            String coach2 = coaches.get(secondCoachIndex);

            schedule.add(coach1 + ", " + coach2);
        }

        return schedule;
    }
}
