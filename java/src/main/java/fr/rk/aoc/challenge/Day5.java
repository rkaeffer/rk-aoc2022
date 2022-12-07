package fr.rk.aoc.challenge;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public final class Day5 {

    public static String getTopCrates(List<String> input, boolean is9001) {
        Cargo cargo = new Cargo(input);
        if(is9001) {
            cargo.applyInstructions9001();
        } else {
            cargo.applyInstructions9000();
        }
        return cargo.getTopChar();
    }

    static class Cargo {
        List<Stack<Character>> crates;
        List<CrateInstruction> instructions;

        public Cargo(List<String> input) {
            List<String> cratesDesc = new ArrayList<>();
            List<String> cratesInstructions = new ArrayList<>();
            Iterator<String> inputIt = input.iterator();
            String line;
            //Fetching cargo description
            while(inputIt.hasNext() && !((line = inputIt.next()).equals(StringUtils.EMPTY))) {
                cratesDesc.add(line);
            }
            //Fetching instruction
            while(inputIt.hasNext()) {
                cratesInstructions.add(inputIt.next());
            }
            //Parse instruction
            this.instructions =cratesInstructions.stream().map(CrateInstruction::new).collect(Collectors.toList());
            // Crate Stack creation
            int nbColumns = cratesDesc.get(cratesDesc.size() - 1).trim().split("   ").length;
            crates = new ArrayList<>(nbColumns);
            for(int i=0; i<nbColumns; i++) {
                crates.add(new Stack<>());
            }
            //Crate parsing
            for(int i=cratesDesc.size() - 2; i>=0; i--) {
                for(int j=0; j<nbColumns; j++) {
                    char c = cratesDesc.get(i).charAt(j*4 + 2 - 1);
                    if(c != ' ') {
                        crates.get(j).push(cratesDesc.get(i).charAt((j)*4 + 2 - 1));
                    }
                }
            }
        }

        public void applyInstructions9000() {
            for(CrateInstruction ci : instructions) {
                for(int i=0; i<ci.nb; i++) {
                    char c = crates.get(ci.from - 1).pop();
                    crates.get(ci.to - 1).push(c);
                }
            }
        }

        public void applyInstructions9001() {
            for(CrateInstruction ci : instructions) {
                String movesCrates = "";
                for(int i=0; i<ci.nb; i++) {
                    movesCrates += crates.get(ci.from - 1).pop();
                }
                for(int i=movesCrates.length()-1; i>=0; i--) {
                    crates.get(ci.to - 1).push(movesCrates.charAt(i));
                }
            }
        }

        public String getTopChar() {
            return crates.stream()
                    .map(Stack::pop)
                    .map(c -> c + "")
                    .collect(Collectors.joining(""));
        }

    }

    static class CrateInstruction {

        public Integer from;
        public Integer to;
        public Integer nb;

        public CrateInstruction(String instruction) {
            String[] instructions = instruction.split(" ");
            this.nb = Integer.parseInt(instructions[1]);
            this.from = Integer.parseInt(instructions[3]);
            this.to = Integer.parseInt(instructions[5]);
        }
    }

}
