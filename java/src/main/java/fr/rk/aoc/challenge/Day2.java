package fr.rk.aoc.challenge;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public final class Day2 {

    public static long getTotalScore(List<String> input, boolean strategy) {
        return input.stream().map(line -> new Round(line, strategy)).map(Round::getRoundScore).reduce(0L, Long::sum);
    }

    enum RPC {

        ROCK('A','X', 1),
        PAPER('B', 'Y', 2),
        SCISSOR('C', 'Z', 3);

        private final char player1;
        private final char player2;
        private final int score;

        private RPC(char player1, char player2, int score) {
            this.player1 = player1;
            this.player2 = player2;
            this.score = score;
        }

        public static RPC getRPCByPlayer1(char p1) {
            return Arrays.stream(values()).filter(rpc -> rpc.player1 == p1).findFirst().orElse(null);
        }

        public static RPC getRPCByPlayer2(char p2) {
            return Arrays.stream(values()).filter(rpc -> rpc.player2 == p2).findFirst().orElse(null);
        }
    }

    private static class Round {
        private RPC player1Choice;
        private RPC player2Choice;

        public Round(String input, boolean hasStrategy) {
            String[] choices = input.split(" ");
            player1Choice = RPC.getRPCByPlayer1(choices[0].charAt(0));
            if(hasStrategy) {
                char player2Strategy = choices[1].charAt(0);
                if(player2Strategy == 'Y') {
                    player2Choice = player1Choice;
                } else if(player2Strategy == 'Z') {
                    if(player1Choice == RPC.SCISSOR) {
                        player2Choice = RPC.ROCK;
                    } else if (player1Choice == RPC.ROCK) {
                        player2Choice = RPC.PAPER;
                    } else {
                        player2Choice = RPC.SCISSOR;
                    }
                } else {
                    if(player1Choice == RPC.SCISSOR) {
                        player2Choice = RPC.PAPER;
                    } else if (player1Choice == RPC.ROCK) {
                        player2Choice = RPC.SCISSOR;
                    } else {
                        player2Choice = RPC.ROCK;
                    }
                }
            } else {
                player2Choice = RPC.getRPCByPlayer2(choices[1].charAt(0));
            }
        }

        public long getRoundScore() {

            if(player2Choice == player1Choice) {
                //log.info("Match {} with DRAW result {} ", this.toString(), player1Choice.score * 2L);
                return player1Choice.score + 3;
            } else if((player2Choice == RPC.ROCK && player1Choice == RPC.SCISSOR) ||
                        (player2Choice == RPC.SCISSOR && player1Choice == RPC.PAPER) ||
                        (player2Choice == RPC.PAPER && player1Choice == RPC.ROCK)) {
                //log.info("Match {} with WIN result {} ", this.toString(), player2Choice.score + 6);
                return player2Choice.score + 6;
            }
            //log.info("Match {} with LOOSE result {} ", this.toString(), player2Choice.score);
            return player2Choice.score;
        }

        public String toString() {
            return player1Choice.name() + " vs " + player2Choice.name();
        }
    }
}
