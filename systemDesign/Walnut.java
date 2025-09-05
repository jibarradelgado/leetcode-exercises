/* Design a banking SMS parsing and analytics application (like Walnut) which reads all the text messages received by users and analyzes the following:

The income and expenditure of a user.
The average income and expenditure of all users.
Text messages will be represented as a string, with words separated by ' '. The texts will be analyzed and will be considered valid if it satisfies the following criteria:

It contains words of exactly one of the following groups:
one or more of "credit", "credited", "deposit", or "deposited" to indicate an earning, or
one or more of "debit", "debited", "withdraw", "withdrawal", or "withdrawn" to indicate an expenditure.
It has exactly one occurrence of amount. It can be denoted as "USD x", "x USD", "USDx", "$ x", "x $", or "$x", where:
x denotes the denomination of the amount. It should lie in the range [0, 109]. Please note that it means "10000000000000000 USD" is an invalid amount.
x can have up to 2 decimal places. Please note that this means "$ 1.0005" is not a valid amount as it has more than 2 decimal places.
Design the Walnut class:

Walnut() Initializes the Walnut object with 0 users and 0 text messages.
void parseText(int userID, String text) Analyzes the text message represented as text, and updates it for the user userID if it is a valid text.
double getTotalUserEarnings(int userID) Returns the total earnings of user userID. In case no valid texts have been analyzed for userID, returns 0.
double getTotalUserExpenses(int userID) Returns the total expenses of user userID. In case no valid texts have been analyzed for userID, returns 0.
double getAverageUserEarnings() Returns the average earnings of all users whose texts have been analyzed and are valid (including users with only expenses), or 0 if no texts have been analyzed.
double getAverageUserExpenses() Returns the average expenses of all users whose texts have been analyzed and are valid (including users with only earnings), or 0 if no texts have been analyzed.
Note that all answers within 10-5 of the actual answer will be considered accepted.

https://leetcode.com/explore/learn/card/system-design/690/system-design-case-studies/4715/
*/

package systemDesign;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Walnut {
  // intent dictionaries (lowercase)
    private static final Set<String> CREDIT_WORDS = new HashSet<>(Arrays.asList(
        "credit", "credited", "deposit", "deposited"
    ));
    private static final Set<String> DEBIT_WORDS = new HashSet<>(Arrays.asList(
        "debit", "debited", "withdraw", "withdrawal", "withdrawn"
    ));

    // one regex to capture all allowed amount shapes; group 1..4 holds the numeric x
    // (?i) = case-insensitive
    // x = [0-9]+ or [0-9]+.[0-9]{1,2}
    private static final Pattern AMOUNT = Pattern.compile(
      "(?i)(?:\\bUSD\\s*((?:\\d+(?:\\.\\d{0,2})?|\\.\\d{1,2}))" +
      "|((?:\\d+(?:\\.\\d{0,2})?|\\.\\d{1,2}))\\s*USD\\b" +
      "|\\$\\s*((?:\\d+(?:\\.\\d{0,2})?|\\.\\d{1,2}))" +
      "|((?:\\d+(?:\\.\\d{0,2})?|\\.\\d{1,2}))\\s*\\$)"
    );

    private final Map<Integer, Double> userEarn = new HashMap<>();
    private final Map<Integer, Double> userExp  = new HashMap<>();
    private final Set<Integer> activeUsers     = new HashSet<>();

    private double sumEarnAll = 0.0;
    private double sumExpAll  = 0.0;

    public Walnut() {}

    public void parseText(int userID, String text) {
        // Determine intent (exactly one of the two groups present at least once)
        int creditHits = 0, debitHits = 0;
        for (String rawTok : text.split(" +")) {
            // strip non-letters for intent matching
            String tok = rawTok.replaceAll("[^A-Za-z]", "").toLowerCase();
            if (tok.isEmpty()) continue;
            if (CREDIT_WORDS.contains(tok)) creditHits++;
            if (DEBIT_WORDS.contains(tok))  debitHits++;
        }
        if (!((creditHits > 0) ^ (debitHits > 0))) {
            // invalid: either none or both present
            return;
        }

        // Extract exactly one amount
        Matcher m = AMOUNT.matcher(text);
        BigDecimal amount = null;
        int matches = 0;
        while (m.find()) {
            String num = firstNonNull(m.group(1), m.group(2), m.group(3), m.group(4));
            if (num == null) continue;
            matches++;
            if (matches > 1) break;
            try {
                amount = new BigDecimal(num);
            } catch (NumberFormatException ex) {
                amount = null;
            }
        }
        if (matches != 1 || amount == null) return;

        // Validate range: 0 <= x <= 1e9
        if (amount.compareTo(BigDecimal.ZERO) < 0 ||
            amount.compareTo(new BigDecimal("1000000000")) > 0) {
            return;
        }
        // decimals already limited by regex to <= 2 places

        boolean isCredit = (creditHits > 0); // else it's a debit

        // First valid text for this user? Add to active set
        boolean firstValidForUser = activeUsers.add(userID);

        if (isCredit) {
            double prev = userEarn.getOrDefault(userID, 0.0);
            double add  = amount.doubleValue();
            userEarn.put(userID, prev + add);
            sumEarnAll += add;
        } else {
            double prev = userExp.getOrDefault(userID, 0.0);
            double add  = amount.doubleValue();
            userExp.put(userID, prev + add);
            sumExpAll += add;
        }
        // Note: If firstValidForUser is false, we do nothing special; averages use sums & active user count
    }

    public double getTotalUserEarnings(int userID) {
        return userEarn.getOrDefault(userID, 0.0);
    }

    public double getTotalUserExpenses(int userID) {
        return userExp.getOrDefault(userID, 0.0);
    }

    public double getAverageUserEarnings() {
        if (activeUsers.isEmpty()) return 0.0;
        // Average over users who have at least one valid text (earnings may be 0)
        return sumEarnAll / activeUsers.size();
    }

    public double getAverageUserExpenses() {
        if (activeUsers.isEmpty()) return 0.0;
        return sumExpAll / activeUsers.size();
    }

    // ------- helpers -------
    private static String firstNonNull(String... xs) {
        for (String s : xs) if (s != null) return s;
        return null;
    }
}
