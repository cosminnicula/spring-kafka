package dev.intermediatebox.kafka;

public class LoanCalculator {
  public static double round(double original, int decimalPlaces) {
    double operand = Math.pow(10d, decimalPlaces);
    return Math.round(original * operand) / operand;
  }

  public static double calculateMonthlyInstallment(double principalLoanAmount, double annualInterestRate,
                                                   int loanPeriodMonth) {
    if (principalLoanAmount < 1d || annualInterestRate < 1d || loanPeriodMonth < 1) {
      throw new IllegalArgumentException("All parameters must be 1 or more");
    }

    var interest = (annualInterestRate / 100d) * (loanPeriodMonth / 12d) * principalLoanAmount;
    var totalPayment = principalLoanAmount + interest;

    return round(totalPayment / loanPeriodMonth, 2);
  }
}
