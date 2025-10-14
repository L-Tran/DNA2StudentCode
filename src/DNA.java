/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: [YOUR NAME HERE]
 *</p>
 */

public class DNA {

    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {
        int i = 0;
        int count = 0;
        int longestCount = 0;
        boolean match;
        while(i <= sequence.length() - STR.length()) {
            match = true;
            for(int j = 0; j < STR.length(); j++) {
                if(sequence.charAt(i + j) != STR.charAt(j)) {
                    match = false;
                    count = 0;
                    break;
                }
            }
            if(match) {
                count++;
                if(count > longestCount) {
                    longestCount = count;
                }
                i += STR.length();
            }
            else {
                i++;
            }
        }
        return longestCount;
    }
}
