package programs;

import com.battle.heroes.army.Unit;
import com.battle.heroes.army.programs.Edge;
import com.battle.heroes.army.programs.UnitTargetPathFinder;

import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class UnitTargetPathFinderImpl implements UnitTargetPathFinder {

    @Override
    public class UnitTargetPathFinderImpl implements UnitTargetPathFinder {

        @Override
        public List<Edge> getTargetPath(Unit attackUnit, Unit targetUnit, List<Unit> existingUnitList) {
            List<Edge> path = new ArrayList<>();
            PriorityQueue<EdgeDistance> queue = new PriorityQueue<>((a, b) -> Integer.compare(a.getDistance(), b.getDistance()));

            // Мапа для хранения предшественников для восстановления пути
            HashMap<Edge, Edge> cameFrom = new HashMap<>();

            // Начальная точка
            Edge start = new Edge(attackUnit.getX(), attackUnit.getY());
            Edge target = new Edge(targetUnit.getX(), targetUnit.getY());
            queue.add(new EdgeDistance(start.getX(), start.getY(), 0));
            cameFrom.put(start, null);

            // Здесь будет алгоритм A* или BFS для поиска пути
            while (!queue.isEmpty()) {
                EdgeDistance current = queue.poll();
                Edge currentEdge = new Edge(current.getX(), current.getY());

                // Если мы достигли цели
                if (currentEdge.equals(target)) {
                    // Воссоздаем путь, от цели к атакующему
                    while (currentEdge != null) {
                        path.add(0, currentEdge); // добавляем в начало списка
                        currentEdge = cameFrom.get(currentEdge);
                    }
                    break;
                }

                // Обрабатываем соседей
                for (Edge neighbor : getNeighbors(current)) {
                    if (!isOccupied(neighbor, existingUnitList) && !cameFrom.containsKey(neighbor)) {
                        queue.add(new EdgeDistance(neighbor.getX(), neighbor.getY(), current.getDistance() + 1));
                        cameFrom.put(neighbor, currentEdge);
                    }
                }
            }
            return path;
        }

        private List<Edge> getNeighbors(EdgeDistance current) {
            // Возвращаем соседние клетки
            List<Edge> neighbors = new ArrayList<>();
            neighbors.add(new Edge(current.getX() + 1, current.getY()));
            neighbors.add(new Edge(current.getX() - 1, current.getY()));
            neighbors.add(new Edge(current.getX(), current.getY() + 1));
            neighbors.add(new Edge(current.getX(), current.getY() - 1));
            return neighbors;
        }

        private boolean isOccupied(Edge edge, List<Unit> units) {
            // Проверяем, занята ли клетка каким-либо юнитом
            for (Unit unit : units) {
                if (unit.getX() == edge.getX() && unit.getY() == edge.getY()) {
                    return true;
                }
            }
            return false;
        }
    }
