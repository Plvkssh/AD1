package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.SuitableForAttackUnitsFinder;

import java.util.List;
import java.util.ArrayList;

public class SuitableForAttackUnitsFinderImpl implements SuitableForAttackUnitsFinder {

    // Константы для типа юнитов
    private static final String ARCHER = "стрелок";
    private static final String CAVALRY = "всадник";

    @Override
    public List<Unit> getSuitableUnits(List<List<Unit>> unitsByRow, boolean isLeftArmyTarget) {
        List<Unit> suitableUnits = new ArrayList<>();

        if (unitsByRow == null) return suitableUnits; // Проверка на null

        // Поиск подходящих юнитов
        for (List<Unit> row : unitsByRow) {
            for (Unit unit : row) {
                if (unit.isAlive()) {
                    if (isLeftArmyTarget && ARCHER.equals(unit.getUnitType())) {
                        suitableUnits.add(unit);
                    } else if (!isLeftArmyTarget && CAVALRY.equals(unit.getUnitType())) {
                        suitableUnits.add(unit);
                    }
                }
            }
        }
        return suitableUnits;
    }
}
