package hellojpa;

public class ValueMain {
	
	public static void main(String[] args) {
		
		int a = 10; int b = 10;
		System.out.println("a == b : " + (a==b));
		
		Address a1 = new Address("a", "b", "c");
		Address a2 = new Address("a", "b", "c");
		System.out.println("a1 == a2 : " + (a1==a2));
		System.out.println("a1 equals a2 : " + (a1.equals(a2)));
	}
}
