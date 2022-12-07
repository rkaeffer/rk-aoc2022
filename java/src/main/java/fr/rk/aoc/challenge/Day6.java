package fr.rk.aoc.challenge;

public final class Day6 {

    public static long getFirstMarker(String input, int nbChar) {
        char[] lastSequence = new char[nbChar];
        for(int i=0; i<nbChar-1; i++) {
            lastSequence[i] = input.charAt(i);
        }
        for(int i=nbChar - 1 ; i<input.length()-1; i++) {
            if(i!=nbChar - 1 ) {
                for(int j=0; j<nbChar-1; j++) {
                    lastSequence[j] = lastSequence[j+1];
                }
            }
            lastSequence[nbChar - 1] = input.charAt(i);
            if(hasUniqueChars(lastSequence, nbChar)) {
                return i + 1;
            }
        }
        return -1L;
    }

    public static boolean hasUniqueChars(char[] seq, int nbChar) {
        boolean unique = true;
        for(int i=0; i<nbChar; i++) {
            for(int j=i+1; j<nbChar; j++) {
                unique &= seq[i] != seq[j];
                if(!unique) {
                    return false;
                }
            }
        }
        return unique;
    }
}
