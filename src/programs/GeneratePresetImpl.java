package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.GeneratePreset;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class GeneratePresetImpl implements GeneratePreset {

    @Override
    public Army generate(List<Unit> unitList, int maxPoints) {
        List<Unit> generatedUnits = new ArrayList<>();
        int totalPoints = 0;
        Random random = new Random();

        // Генерация армии, пока не исчерпаем очки
        while (totalPoints < maxPoints) {
            Unit unit = unitList.get(random.nextInt(unitList.size()));
            if (totalPoints + unit.getCost() <= maxPoints) {
                generatedUnits.add(unit);
                totalPoints += unit.getCost();
            }
        }

        // Возвращаем созданную армию
        Army army = new Army();
        army.setUnits(generatedUnits);
        army.setPoints(totalPoints);
        return army;
    }
}
