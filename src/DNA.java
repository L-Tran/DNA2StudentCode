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

    // Radix (256 for 8-bit extended ASCII)
    public static final int EXTENDED_ASCII = 256;
    // Large prime representable as a long (less than 2^63 - 1)/256
    public static final long P = 29245776433644439L;


    public static int STRCount(String sequence, String STR) {
        int m = STR.length();
        int n = sequence.length();

        long strHash = hash(STR);
        // Initialize first hash in sequence
        long seqHash = hash(sequence.substring(0, m));

        int count = 0;
        int longestCount = 0;

        // Initialize radix for term subtracted in "sliding"
        long radixFirst = 1;
        for(int i = 0; i < m - 1; i++) {
            radixFirst *= EXTENDED_ASCII % P;
        }

        // Loop through sequence
        for (int i = m; i <= n - m; i++) {
            if (strHash == seqHash) {
                // Skip to the next possible STR
                i += m - 1;
                // begin checking for consecutive appearances
                count++;
                if(count > longestCount) {
                    longestCount = count;
                }
                // Change current sequence hash to next m characters
                seqHash = hash(sequence.substring(i - m + 1, i + 1));
            }
            else {
                // If just coming off of a match
                if(count > 0) {
                    // Reset count and go back to second character in last match
                    count = 0;
                    i = i - (m - 1);
                    seqHash = hash(sequence.substring(i - m + 1, i + 1));
                }
                else {
                    // Shift window to delete and add character in hash
                    seqHash = (seqHash + P - sequence.charAt(i - m) * radixFirst % P) % P;
                    seqHash = (seqHash * EXTENDED_ASCII + sequence.charAt(i)) % P;
                }
            }
        }
        // Account of edge case of end that cannot be counted
        if(seqHash == strHash) {
            count++;
            if(count > longestCount) {
                longestCount = count;
            }
        }
    return longestCount;
    }

    public static long hash(String s) {
        int length = s.length();
        long h = 0;
        // Preform Horner's Method
        for (int i = 0; i < length; i++) {
            h = (h * EXTENDED_ASCII + s.charAt(i)) % P;
        }
        return h;
    }
}
