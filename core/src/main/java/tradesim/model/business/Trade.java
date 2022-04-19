package tradesim.model.business;

import static tradesim.model.business.Sector.INDUSTRY;
import static tradesim.model.business.Sector.OTHER;
import static tradesim.model.business.Sector.SERVICE;

import java.util.Arrays;

/**
 * The Enum Trade models different trades of business.
 */
public enum Trade {
	
	/**
	 * A .
	 */
	A(1) {
		@Override
		Sector getSector() {
			return INDUSTRY;
		}
	},
	
	/**
	 * B .
	 */
	B(2) {
		@Override
		Sector getSector() {
			return INDUSTRY;
		}
	},
	
	/**
	 * C .
	 */
	C(3) {
		@Override
		Sector getSector() {
			return INDUSTRY;
		}
	},
	
	/**
	 * D .
	 */
	D(4) {
		@Override
		Sector getSector() {
			return INDUSTRY;
		}
	},
	
	/**
	 * E .
	 */
	E(5) {
		@Override
		Sector getSector() {
			return INDUSTRY;
		}
	},
	
	/**
	 * F .
	 */
	F(6) {
		@Override
		Sector getSector() {
			return INDUSTRY;
		}
	},
	
	/**
	 * G .
	 */
	G(7) {
		@Override
		Sector getSector() {
			// TODO Auto-generated method stub
			return SERVICE;
		}
	},
	
	/**
	 * H .
	 */
	H(8) {
		@Override
		Sector getSector() {
			return SERVICE;
		}
	},
	
	/**
	 * I .
	 */
	I(9) {
		@Override
		Sector getSector() {
			return SERVICE;
		}
	},
	
	/**
	 * J .
	 */
	J(10) {
		@Override
		Sector getSector() {
			return SERVICE;
		}
	},
	
	/**
	 * K .
	 */
	K(11) {
		@Override
		Sector getSector() {
			return SERVICE;
		}
	},
	
	/**
	 * L .
	 */
	L(12) {
		@Override
		Sector getSector() {
			return SERVICE;
		}
	},
	
	/**
	 * M .
	 */
	M(13) {
		@Override
		Sector getSector() {
			return SERVICE;
		}
	},
	
	/**
	 * N .
	 */
	N(14) {
		@Override
		Sector getSector() {
			return SERVICE;
		}
	},
	
	/**
	 * O .
	 */
	O(15) {
		@Override
		Sector getSector() {
			return OTHER;
		}
	},
	
	/**
	 * P .
	 */
	P(16) {
		@Override
		Sector getSector() {
			return OTHER;
		}
	},
	
	/**
	 * Q .
	 */
	Q(17) {
		@Override
		Sector getSector() {
			return SERVICE;
		}
	},
	
	/**
	 *R .
	 */
	R(18) {
		@Override
		Sector getSector() {
			return OTHER;
		}
	},
	
	/**
	 * S .
	 */
	S(19) {
		@Override
		Sector getSector() {
			return SERVICE;
		}
	},
	
	/**
	 * T .
	 */
	T(20) {
		@Override
		Sector getSector() {
			return OTHER;
		}
	},
	
	/**
	 * U .
	 */
	U(21) {
		@Override
		Sector getSector() {
			return OTHER;
		}
	};	
	
	private final int number;
	
	private Trade(int number) {
		this.number = number;
	}
	
	/**
	 *  Returns the int code of a {@link Trade}.
	 *
	 * @return the int
	 */
	public int asInt() {
		return this.number;
	}
	
	/**
	 * returns the name of a {@link Trade}.
	 *
	 * @return the name
	 */
	public String asString() {
		return this.name();
	}
	
	/**
	 * Gets the {@link Sector}.
	 *
	 * @return the sector containing the trade
	 */
	abstract Sector getSector();
	
	/**
	 * Parses the given int as {@link Trade}.
	 *
	 * @param number the number
	 * @return the trade
	 */
	public static Trade fromInt(int number) {
		return Arrays.stream(Trade.values())
					 .filter(p -> p.asInt() == number)
					 .findFirst()
					 .orElseGet(() -> {
						 throw new IllegalArgumentException("Cannot parse " + number + " as trade!");
					 });
	}
}
