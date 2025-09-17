package mayobot.util;

/**
 * Utility class for advanced string matching including fuzzy search capabilities.
 */
public class SearchMatcher {

    /**
     * Calculates similarity between two strings using Levenshtein distance.
     * Returns a value between 0.0 (no similarity) and 1.0 (identical).
     */
    public static double calculateSimilarity(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0.0;
        }
        if (s1.equals(s2)) {
            return 1.0;
        }

        int maxLength = Math.max(s1.length(), s2.length());
        if (maxLength == 0) {
            return 1.0;
        }

        return (maxLength - levenshteinDistance(s1, s2)) / (double) maxLength;
    }

    /**
     * Calculates Levenshtein distance between two strings.
     */
    private static int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    /**
     * Checks if the search term matches the text with fuzzy matching.
     *
     * @param searchTerm the term to search for
     * @param text the text to search in
     * @param threshold similarity threshold (0.0 to 1.0)
     * @return true if similarity is above threshold
     */
    public static boolean fuzzyMatch(String searchTerm, String text, double threshold) {
        String[] searchWords = searchTerm.toLowerCase().split("\\s+");
        String[] textWords = text.toLowerCase().split("\\s+");

        for (String searchWord : searchWords) {
            boolean found = false;
            for (String textWord : textWords) {
                if (calculateSimilarity(searchWord, textWord) >= threshold) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
}
