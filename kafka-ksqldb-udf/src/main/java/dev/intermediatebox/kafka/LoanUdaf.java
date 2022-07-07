package dev.intermediatebox.kafka;

import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;

import io.confluent.ksql.function.udaf.Udaf;
import io.confluent.ksql.function.udaf.UdafDescription;
import io.confluent.ksql.function.udaf.UdafFactory;

@UdafDescription(name = "loan_rating", category = "LOAN", version = "4.0.0", description = "User defined function for sample tabular loan business logic.")
public class LoanUdaf {

	public static final String LOAN_SUBMISSION_SCHEMA_DESCRIPTOR = "STRUCT<" + "`principalLoanAmount` DOUBLE, "
			+ "`annualInterestRate` DOUBLE, " + "`loanPeriodMonth` INT, " + "`loanApprovedDate` VARCHAR" + ">";

	public static final String AGGREGATE_SCHEMA_DESCRIPTOR = "STRUCT<`goodPayment` INT, `badPayment` INT>";

	public static final Schema AGGREGATE_SCHEMA = SchemaBuilder.struct().optional()
			.field("goodPayment", Schema.OPTIONAL_INT32_SCHEMA).field("badPayment", Schema.OPTIONAL_INT32_SCHEMA)
			.build();

	@UdafFactory(description = "Compute loan rating based on payment latency. The smaller the latency, the better.", aggregateSchema = AGGREGATE_SCHEMA_DESCRIPTOR)
	public static Udaf<Integer, Struct, String> createUdaf() {
		return new LoanUdafImpl();
	}

	private static class LoanUdafImpl implements Udaf<Integer, Struct, String> {

		@Override
		public Struct initialize() {
			var initializer = new Struct(AGGREGATE_SCHEMA);
			
			initializer.put("goodPayment", 0);
			initializer.put("badPayment", 0);
			
			return initializer;
		}

		@Override
		public Struct aggregate(Integer current, Struct aggregate) {
			if (current <= 0) {
				var currentGoodPayment = aggregate.getInt32("goodPayment");
				aggregate.put("goodPayment", (currentGoodPayment + 1));
			} else {
				var currentBadPayment = aggregate.getInt32("badPayment");
				aggregate.put("badPayment", (currentBadPayment + 1));				
			}
			
			return aggregate;
		}

		@Override
		public Struct merge(Struct aggOne, Struct aggTwo) {
			return aggOne;
		}

		@Override
		public String map(Struct agg) {
			var goodPayment = (double) agg.getInt32("goodPayment");
			var badPayment = (double) agg.getInt32("badPayment");
			var totalPayment = goodPayment + badPayment;
			
			var goodPaymentPercentage = (goodPayment / totalPayment) * 100d;
			
			if (goodPaymentPercentage <= 25) {
				return "VERY BAD : " + LoanCalculator.round(goodPaymentPercentage, 2) + "%";
			} else if (goodPaymentPercentage <= 50) {
				return "BAD : " + LoanCalculator.round(goodPaymentPercentage, 2) + "%";
			} else if (goodPaymentPercentage <= 75) {
				return "MODERATE : " + LoanCalculator.round(goodPaymentPercentage, 2) + "%";
			} else {
				return "GOOD : " + LoanCalculator.round(goodPaymentPercentage, 2) + "%";
			}
		}
		
	}
	
}
