package programs;

import com.battle.heroes.army.Army;
import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.PrintBattleLog;
import com.battle.heroes.army.programs.SimulateBattle;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SimulateBattleImpl implements SimulateBattle {

    @Override
    public void simulateBattle(Army playerArmy, Army computerArmy) throws InterruptedException {
        PrintBattleLog printBattleLog = new PrintBattleLog();

        while (!playerArmy.isArmyDefeated() && !computerArmy.isArmyDefeated()) {
            // Ход игрока
            for (Unit unit : playerArmy.getUnits()) {
                if (unit.isAlive()) {
                    unit.getProgram().attack(computerArmy);
                    printBattleLog.log("Игрок атакует!");
                    Thread.sleep(1000); // Задержка для наглядности
                }
            }

            // Ход компьютера
            if (!computerArmy.isArmyDefeated()) {
                for (Unit unit : computerArmy.getUnits()) {
                    if (unit.isAlive()) {
                        unit.getProgram().attack(playerArmy);
                        printBattleLog.log("Компьютер атакует!");
                        Thread.sleep(1000);
                    }
                }
            }
        }

        // Итоги сражения
        if (playerArmy.isArmyDefeated()) {
            printBattleLog.log("Компьютер выиграл!");
        } else {
            printBattleLog.log("Игрок выиграл!");
        }
    }
}
