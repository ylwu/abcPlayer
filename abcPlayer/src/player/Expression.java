package player;

public interface Expression {
	
	public class Line implements Expression {
	}

	public class Accidental implements Expression {
	}
	
	public class Note implements Expression {
	}
	
	public class Octave implements Expression {
	}
	
	public class LeftBra implements Expression {
	}
	
	public class RightBra implements Expression {
	}
	
	public class Duplet implements Expression {
	}
	
	public class Triplet implements Expression {
	}
	
	public class Quadruplet implements Expression {
	}
	
	public class Colon implements Expression {
	}
	
	public class Rest implements Expression {
	}
	
	public class Length implements Expression {
	}
	
	public class Voice implements Expression {
	}
	
	public class AltOne implements Expression {
	}
	
	public class AltTwo implements Expression {
	}
	
	public class Space implements Expression {
	}
}