package dev.intermediatebox.kafka;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;

import io.confluent.ksql.function.udf.UdfParameter;
import io.confluent.ksql.function.udtf.Udtf;
import io.confluent.ksql.function.udtf.UdtfDescription;

@UdtfDescription(name = "loan_installment_schedule", category = "LOAN", version = "4.0.0", description = "User defined function for sample tabular loan business logic.")
public class LoanUdtf {

  public static final String LOAN_SUBMISSION_SCHEMA_DESCRIPTOR = "STRUCT<" + "`principalLoanAmount` DOUBLE, "
      + "`annualInterestRate` DOUBLE, " + "`loanPeriodMonth` INT, " + "`loanApprovedDate` VARCHAR" + ">";

  public static final String LOAN_MONTHLY_INSTALLMENT_SCHEMA_DESCRIPTOR = "STRUCT<" + "`installmentAmount` DOUBLE, "
      + "`installmentDueDate` VARCHAR" + ">";

  private static final Schema LOAN_MONTHLY_INSTALLMENT_SCHEMA = SchemaBuilder.struct()
      .field("installmentAmount", Schema.FLOAT64_SCHEMA).field("installmentDueDate", Schema.STRING_SCHEMA);

  @Udtf(description = "Calculate loan installment schedule, comprise of installment amount and due date", schema = LOAN_MONTHLY_INSTALLMENT_SCHEMA_DESCRIPTOR)
  public List<Struct> calculateLoanSchedule(
      @UdfParameter(schema = LOAN_SUBMISSION_SCHEMA_DESCRIPTOR) Struct loanSubmission) {
    var principalLoanAmount = loanSubmission.getFloat64("principalLoanAmount");
    var annualInterestRate = loanSubmission.getFloat64("annualInterestRate");
    var loanPeriodMonth = loanSubmission.getInt32("loanPeriodMonth");
    var loanApprovedDate = LocalDate.parse(loanSubmission.getString("loanApprovedDate"));

    var result = new ArrayList<Struct>();

    var monthlyInstallmentAmount = LoanCalculator.calculateMonthlyInstallment(principalLoanAmount,
        annualInterestRate, loanPeriodMonth);

    IntStream.rangeClosed(1, loanPeriodMonth).forEachOrdered(i -> {
      var installmentDueDate = loanApprovedDate.plusMonths(i);
      var monthlyInstallment = new Struct(LOAN_MONTHLY_INSTALLMENT_SCHEMA);

      monthlyInstallment.put("installmentAmount", monthlyInstallmentAmount);
      monthlyInstallment.put("installmentDueDate", installmentDueDate.toString());

      result.add(monthlyInstallment);
    });

    return result;
  }
}
