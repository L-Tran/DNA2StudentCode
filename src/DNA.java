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
    public static final long P = 29245776433644439L;


    public static int STRCount(String sequence, String STR) {
        int m = STR.length();
        int n = sequence.length();

        long strHash = hash(STR);
        long seqHash = hash(sequence.substring(0, m));

        int count = 0;
        int longestCount = 0;

        long radixFirst = 1;
        for(int i = 0; i < m - 1; i++) {
            radixFirst *= EXTENDED_ASCII % P;
        }

        for (int i = m; i <= n - m; i++) {
            if (strHash == seqHash) {
                i += m - 1;
                // begin checking for consecutive appearances
                count++;
                if(count > longestCount) {
                    longestCount = count;
                }
                seqHash = hash(sequence.substring(i - (m - 1), i + 1));
            }
            else {
                count = 0;
                seqHash = (seqHash + P - sequence.charAt(i - m) * radixFirst % P) % P;
                seqHash = (seqHash * EXTENDED_ASCII + sequence.charAt(i)) % P;
            }
            String hash = sequence.substring(i-m+1,i+1);
        }
        // Account of edge case of end
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
        for (int i = 0; i < length; i++) {
            h = (h * EXTENDED_ASCII + s.charAt(i)) % P;
        }
        return h;
    }
}
