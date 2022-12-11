package fr.rk.aoc.challenge;

import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class Day11 {

    public static long getMonKeyBusinessAfterAnyRound(List<String> input, int nbRound, boolean dividedBy3) {
        List<List<String>> monkeysDesc = ListUtils.partition(input, 7);
        List<Monkey> monkeys = monkeysDesc.stream().map(Monkey::new).collect(Collectors.toList());
        //LCM for part 2
        int lcm = monkeys.stream().map(m -> m.testDivider).reduce(1, (a, b) -> a * b);
        for(int i=0; i<nbRound; i++) {
            monkeys.forEach(m -> m.playRound(monkeys, dividedBy3, lcm));
        }
        return monkeys.stream().map(m -> m.itemWatch).sorted(Collections.reverseOrder()).limit(2).reduce(1L, (a, b) -> a * b);
    }

    static class MonkeyItem {
        long worryLevel;

        public MonkeyItem(String worryLevel) {
            this.worryLevel = Long.parseLong(worryLevel);
        }
    }

    static class Monkey {
        List<MonkeyItem> items;
        long itemWatch;
        int testDivider;
        MonkeyOperator monkeyOperator;
        boolean worryChangerIsInt;
        int worryChanger;
        int trueMonkeyIndex;
        int falseMonkeyIndex;

        public Monkey(List<String> monkeyDesc) {
            this.items = Arrays.stream(monkeyDesc.get(1).split(":")[1].trim().split(",")).map(String::trim).map(MonkeyItem::new).collect(Collectors.toList());
            this.itemWatch = 0;
            this.testDivider = Integer.parseInt(monkeyDesc.get(3).split(":")[1].trim().split(" ")[2]);
            this.trueMonkeyIndex = Integer.parseInt(monkeyDesc.get(4).split(":")[1].trim().split(" ")[3]);
            this.falseMonkeyIndex = Integer.parseInt(monkeyDesc.get(5).split(":")[1].trim().split(" ")[3]);
            this.monkeyOperator = MonkeyOperator.getMonkeyOperatorByoperator(monkeyDesc.get(2).split(":")[1].trim().split(" ")[3]);
            this.worryChangerIsInt = !monkeyDesc.get(2).split(":")[1].trim().split(" ")[4].equals("old");
            if(!worryChangerIsInt) {
                this.worryChanger = -1;
            } else {
                this.worryChanger = Integer.parseInt(monkeyDesc.get(2).split(":")[1].trim().split(" ")[4]);
            }
        }

        public void playRound(List<Monkey> monkeys, boolean dividedBy3, int lcm) {
            this.items.forEach(item -> {
                this.itemWatch++;
                switch (this.monkeyOperator) {
                    case PLUS:
                        //Add modulus for part 2
                        item.worryLevel += worryChangerIsInt ? worryChanger : item.worryLevel;
                        item.worryLevel %= lcm;
                        break;
                    case MULTIPLY:
                        //Add modulus for part 2
                        item.worryLevel *= worryChangerIsInt ? worryChanger : item.worryLevel;
                        item.worryLevel %= lcm;
                        break;
                }
                if(dividedBy3) {
                    item.worryLevel /= 3;
                }
                int nextMonkeyIndex = item.worryLevel%this.testDivider == 0 ? trueMonkeyIndex : falseMonkeyIndex;
                monkeys.get(nextMonkeyIndex).items.add(item);
            });
            this.items = new ArrayList<>();
        }
    }

    enum MonkeyOperator {
        PLUS("+"),
        MULTIPLY("*");

        private String operator;

        MonkeyOperator(String operator) {
            this.operator = operator;
        }

        public static MonkeyOperator getMonkeyOperatorByoperator(String operator) {
            for(MonkeyOperator mo : values()) {
                if(operator.equals(mo.operator)) {
                    return mo;
                }
            }
            return null;
        }

    }
}
