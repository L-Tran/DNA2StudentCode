/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: Logan Tran
 *</p>
 */

public class DNA {

    public static final int EXTENDED_ASCII = 256;
    public static final int P = 2147483647;


    public static int STRCount(String sequence, String STR) {
        int m = STR.length();
        int n = sequence.length();

        int strHash = hash(STR);
        int seqHash = hash(sequence.substring(0, m - 1));

        int count = 0;
        int longestCount = 0;

        for (int i = m; i < n; i++) {
            if (strHash == seqHash) {
                i += (m - 1);
                // begin checking for consecutive appearances
                count++;
                if(count > longestCount) {
                    longestCount = count;
                }
            }
            else {
                count = 0;
            }
            seqHash = hash(sequence.substring(1 + (i - m), i));

        }
    return longestCount;
    }

    public static int hash(String s) {
        if(s.isEmpty()) {
            return 0;
        }
        return (int) (hash(s.substring(1)) + s.charAt(0) * Math.pow(EXTENDED_ASCII, s.length() - 1) % P);
    }
}
