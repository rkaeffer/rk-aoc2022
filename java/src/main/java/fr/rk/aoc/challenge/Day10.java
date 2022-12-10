package fr.rk.aoc.challenge;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public final class Day10 {

    public static long getSumOfSignalStrength(List<String> input) {
        Processor p = new Processor();
        input.forEach(p::applyInstruction);
        return p.signalStrength;
    }

    public static String getCRTDisplay(List<String> input) {
        Processor p = new Processor();
        input.forEach(p::applyInstruction);
        p.displayCRT();
        return p.crtLine;
    }

    static class Processor {
        public long registry = 1;
        public long cycle = 0;
        public long signalStrength = 0;
        String crtLine = "";

        public void applyInstruction(String instruction) {
            if(instruction.startsWith("noop")) {
                cycle++;
                this.increaseSignal();
            } else if(instruction.startsWith("addx")) {
                cycle++;
                this.increaseSignal();
                cycle++;
                this.increaseSignal();
                registry+=Integer.parseInt(instruction.split(" ")[1]);
            }
        }

        public void increaseSignal() {
            crtLine += (((cycle-1)%40 == registry || (cycle-1)%40 == registry - 1 || (cycle-1)%40 == registry +1) ? "#" : ".");
            if(cycle%40 == 20) {
                long currentSignal = cycle*registry;
                signalStrength += currentSignal;
            }
        }

        public void displayCRT() {
            for(int i=0; i<crtLine.length(); i++) {
                System.out.print(crtLine.charAt(i));
                if((i+1)%40 == 0) {
                    System.out.println();
                }
            }
            System.out.println();
        }
    }

}
