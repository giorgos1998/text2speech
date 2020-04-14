package model.encoding;

public class test {

	public static void main(String[] args) {
		
		EncStrategyFactory fac = new EncStrategyFactory();
		EncStrategy strat = fac.createStrategy("rot13");
		
		String hi = "Hello world, I am 1337";
		
		hi = strat.encode(hi);
		System.out.println(hi);
		hi = strat.encode(hi);
		System.out.println(hi);
		
		strat = fac.createStrategy("atbash");
		
		hi = strat.encode(hi);
		System.out.println(hi);
		hi = strat.encode(hi);
		System.out.println(hi);

	}

}
