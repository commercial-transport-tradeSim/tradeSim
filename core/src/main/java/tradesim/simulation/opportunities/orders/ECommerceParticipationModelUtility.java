package tradesim.simulation.opportunities.orders;

import tradesim.simulation.opportunities.PrivatePerson;
import tradesim.util.input.Parameters;

/**
 * The Class ECommerceParticipationModelUtility.
 */
public class ECommerceParticipationModelUtility {
	
	private final double asc_Part;
	private final double b_age2_1;
	private final double b_age3_1;
	private final double b_age4_1;
	private final double b_income2_1;
	private final double b_income3_1;
	private final double b_job1_1;
	private final double b_male1;
	
	private final ECommerceParticipationModelHelper helper;
	private final ECommerceParticipationSelectorLogger log;
	
	/**
	 * Instantiates a new e commerce participation model utility.
	 *
	 * @param logitParameters the logit parameters
	 * @param helper the helper
	 * @param log the logger
	 */
	public ECommerceParticipationModelUtility(Parameters logitParameters, ECommerceParticipationModelHelper helper, ECommerceParticipationSelectorLogger log) {
		super();
		this.helper = helper;
		this.log = log;
		
		this.asc_Part = logitParameters.get("asc_Part");
		this.b_age2_1 = logitParameters.get("b_age2_1");
		this.b_age3_1 = logitParameters.get("b_age3_1");
		this.b_age4_1 = logitParameters.get("b_age4_1");
		this.b_income2_1 = logitParameters.get("b_income2_1");
		this.b_income3_1 = logitParameters.get("b_income3_1");
		this.b_job1_1 = logitParameters.get("b_job1_1");
		this.b_male1 = logitParameters.get("b_male1");
		

	}
	
	/**
	 * Instantiates a new e commerce participation model utility.
	 *
	 * @param logitParameters the logit parameters
	 * @param helper the helper
	 */
	public ECommerceParticipationModelUtility(Parameters logitParameters, ECommerceParticipationModelHelper helper) {
		this(logitParameters, helper, new NullECommerceParticipationSelectorLogger());
	}
	
	
	/**
	 * Calculate U nopart.
	 *
	 * @param recipient the recipient
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateU_nopart(PrivatePerson recipient, double randomNumber) {
		return 0.0d;
	}
	
	/**
	 * Calculate U part.
	 *
	 * @param recipient the recipient
	 * @param randomNumber the random number
	 * @return the double
	 */
	public double calculateU_part(PrivatePerson recipient, double randomNumber) {
		String category = "Participation";
		
		double IS_GENDER_MALE_value = helper.getIS_GENDER_MALE(category, recipient, randomNumber);
		this.log.log(category, "IS_GENDER_MALE_value", IS_GENDER_MALE_value);
		
		double HOUSEHOLD_MONTHLY_INCOME_EUR_value = helper.getHOUSEHOLD_MONTHLY_INCOME_EUR(category, recipient, randomNumber);
		this.log.log(category, "HOUSEHOLD_MONTHLY_INCOME_EUR_value", HOUSEHOLD_MONTHLY_INCOME_EUR_value);
		
		double IS_EMPLOYED_value = helper.getIS_EMPLOYED(category, recipient, randomNumber);
		this.log.log(category, "IS_EMPLOYED_value", IS_EMPLOYED_value);
		
		double AGE_value = helper.getAGE(category, recipient, randomNumber);
		this.log.log(category, "AGE_value", AGE_value);

		return   (asc_Part)
				+(b_male1*IS_GENDER_MALE_value)
				+(b_income2_1*((2500.0d <= HOUSEHOLD_MONTHLY_INCOME_EUR_value && HOUSEHOLD_MONTHLY_INCOME_EUR_value < 4000.0d) ? 1.0d : 0.0d))
				+(b_income3_1*((4000.0d <= HOUSEHOLD_MONTHLY_INCOME_EUR_value) ? 1.0d : 0.0d))
				+(b_job1_1*IS_EMPLOYED_value)
				+(b_age2_1*((25.0d <= AGE_value && AGE_value <= 44.0d) ? 1.0d : 0.0d))
				+(b_age3_1*((45.0d <= AGE_value && AGE_value <= 64.0d) ? 1.0d : 0.0d))
				+(b_age4_1*((65.0d <= AGE_value) ? 1.0d : 0.0d));
	}
}

